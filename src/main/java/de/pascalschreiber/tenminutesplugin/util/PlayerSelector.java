package de.pascalschreiber.tenminutesplugin.util;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import org.bukkit.entity.Player;
import org.javacord.api.entity.user.User;

public class PlayerSelector {

    private TenMinutesPlugin plugin;

    public PlayerSelector(TenMinutesPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isPlayerRegistred(Player player) {
        return false;
    }

    public boolean isPlayerRegistred(User user) {
        return false;
    }

    public boolean isPlayerActive(Player player) {
        return false;
    }

    public boolean isPlayerActive(User user) {
        return false;
    }

    public void rollNewRound() {

    }
}
