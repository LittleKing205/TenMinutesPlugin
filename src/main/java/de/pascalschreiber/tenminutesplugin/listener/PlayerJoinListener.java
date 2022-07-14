package de.pascalschreiber.tenminutesplugin.listener;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import de.pascalschreiber.tenminutesplugin.util.Timer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener  implements Listener {

    private TenMinutesPlugin plugin;

    public PlayerJoinListener(TenMinutesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        plugin.timer = new Timer(plugin, plugin.timersConfig.getConfig().getInt("elipsedTime"), 0);
        plugin.timer.start(event.getPlayer());
    }
}
