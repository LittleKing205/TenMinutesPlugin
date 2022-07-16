package de.pascalschreiber.tenminutesplugin.listener;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
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
    }
}
