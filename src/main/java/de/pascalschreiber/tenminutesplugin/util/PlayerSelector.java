package de.pascalschreiber.tenminutesplugin.util;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;

public class PlayerSelector {

    private TenMinutesPlugin plugin;

    public PlayerSelector(TenMinutesPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isPlayerRegistred(Player player) {
        return plugin.playersListConfig.getConfig().isSet("players." + player.getUniqueId().toString() + ".Name");
    }

    public boolean isPlayerRegistred(User user) {
        for (String key : plugin.playersListConfig.getConfig().getConfigurationSection("players").getKeys(false)) {
            if (plugin.playersListConfig.getConfig().getLong("players." + key + ".DiscordId") == user.getId())
                return true;
        }
        return false;
    }

    public boolean isPlayerActive(Player player) {
        long discordUserId = plugin.playersListConfig.getConfig().getLong("players." + player.getUniqueId().toString() + ".DiscordId");
        try {
            User discordUser = plugin.getDiscordBot().getApi().getUserById(discordUserId).get();
            for (Role role : discordUser.getRoles(plugin.getDiscordBot().getServer())) {
                if (role.getId() == plugin.getDiscordBot().activeRole.getId())
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean isPlayerActive(User user) {
        for (Role role : user.getRoles(plugin.getDiscordBot().getServer())) {
            if (role.getId() == plugin.getDiscordBot().activeRole.getId())
                return true;
        }
        return false;
    }

    public void rollNewRound() {

    }
}
