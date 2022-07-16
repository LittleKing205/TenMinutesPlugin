package de.pascalschreiber.tenminutesplugin.listener;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import de.pascalschreiber.tenminutesplugin.util.Serialization;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    private TenMinutesPlugin plugin;

    public PlayerLeaveListener(TenMinutesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (event.getReason() == PlayerQuitEvent.QuitReason.KICKED) {
            try {
                plugin.playerSelector.nextPlayer();
            } catch (Exception e) {
                plugin.getLogger().warning("No registred Players in List!");
            }
        }

        String[] base64invs = Serialization.invToBase64(event.getPlayer().getInventory());
        plugin.lastPlayerConfig.getConfig().set("lastSessionTimer", plugin.timer.getSessionTimeMilis());
        plugin.lastPlayerConfig.getConfig().set("inventory", base64invs[0]);
        plugin.lastPlayerConfig.getConfig().set("armor", base64invs[1]);
        plugin.lastPlayerConfig.save();
        plugin.timersConfig.getConfig().set("elipsedTime", plugin.timer.getElapsedTimeMilis());
        plugin.timer.stop();
    }
}
