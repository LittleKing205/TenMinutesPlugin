package de.pascalschreiber.tenminutesplugin.util;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import org.bukkit.entity.Player;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;

import java.util.*;

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

    public void generateNewPlayersList() throws Exception {
        List<String> players = new ArrayList();
        Set<String> keys = plugin.playersListConfig.getConfig().getConfigurationSection("players").getKeys(false);
        if (keys.size() == 0)
            throw new Exception("List is empty.");
        for (String uuid : keys) {
            players.add(uuid);
        }
        plugin.currendRoundConfig.getConfig().set("players", players);
        plugin.currendRoundConfig.save();
    }

    public void nextPlayer() throws Exception {
        Random random = new Random();
        List<String> currentRoundList = plugin.currendRoundConfig.getConfig().getStringList("players");
        if (currentRoundList.size() == 0)
            generateNewPlayersList();
        int randomIndex = random.nextInt(currentRoundList.size());
        String uuid = currentRoundList.get(randomIndex);
        currentRoundList.remove(randomIndex);
        plugin.currentPlayerId = uuid;
        plugin.currendRoundConfig.getConfig().set("players", currentRoundList);
    }

    public boolean isCurrentPlayer(Player player) {
        return (plugin.currentPlayerId.equals(player.getUniqueId().toString()));
    }
}
