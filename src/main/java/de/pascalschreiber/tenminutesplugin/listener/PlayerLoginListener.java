package de.pascalschreiber.tenminutesplugin.listener;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import de.pascalschreiber.tenminutesplugin.util.RandomString;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
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

        // nicht verifiziert
        if (!plugin.playerSelector.isPlayerRegistred(event.getPlayer())) {
            String code = RandomString.get(7);
            plugin.registerCodes.put(code, event.getPlayer());
            plugin.getLogger().info("Ein neuer Spieler wurde entdeckt... Code: "+code);
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Component.text(code));
            return;
        }

        // Spieler abweisen
        if (!plugin.playerSelector.isPlayerActive(event.getPlayer())) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Component.text("Du bist kein Teilnehmer."));
            return;
        }

        //nicht dran
        if (!plugin.playerSelector.isCurrentPlayer(event.getPlayer())) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Component.text("Du bist nicht dran."));
            return;
        }

        event.allow();
    }
}
