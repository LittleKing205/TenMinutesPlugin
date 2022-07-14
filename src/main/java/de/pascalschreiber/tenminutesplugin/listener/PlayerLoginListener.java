package de.pascalschreiber.tenminutesplugin.listener;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;;

public class PlayerLoginListener implements Listener {

    private final TenMinutesPlugin plugin;

    public PlayerLoginListener(TenMinutesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {

    }
}
