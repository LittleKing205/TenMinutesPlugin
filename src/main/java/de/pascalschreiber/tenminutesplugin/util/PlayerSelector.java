package de.pascalschreiber.tenminutesplugin.util;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerSelector {

    private TenMinutesPlugin plugin;

    public PlayerSelector(TenMinutesPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isPlayerRegistred(Player player) {
        return plugin.playersListConfig.getConfig().isSet("player." + player.getUniqueId().toString() + ".Name");
    }

    public boolean isPlayerActive(Player player) {
        String discordUserId = plugin.playersListConfig.getConfig().getString("player." + player.getUniqueId().toString() + ".DiscordId");
        Member discordUser = plugin.getDiscordBot().getServer().retrieveMemberById(discordUserId).complete();
        return discordUser.getRoles().contains(plugin.getDiscordBot().activeRole);
    }

    public List<String> generateNewPlayersList() throws Exception {
        List<String> players = new ArrayList();
        Set<String> keys = plugin.playersListConfig.getConfig().getConfigurationSection("player").getKeys(false);
        if (keys.size() == 0)
            throw new Exception("List is empty.");
        for (String uuid : keys) {
            players.add(uuid);
        }
        plugin.currendRoundConfig.getConfig().set("player", players);
        plugin.currendRoundConfig.save();
        return players;
    }

    public void nextPlayer() throws Exception {
        nextPlayer(false);
    }

    public void nextPlayer(boolean notify) throws Exception {
        Random random = new Random();
        List<String> currentRoundList = plugin.currendRoundConfig.getConfig().getStringList("player");
        if (currentRoundList.size() == 0)
            currentRoundList = generateNewPlayersList();
        int randomIndex = random.nextInt(currentRoundList.size());
        String uuid = currentRoundList.get(randomIndex);
        currentRoundList.remove(randomIndex);
        plugin.currentPlayerId = uuid;
        plugin.currendRoundConfig.getConfig().set("player", currentRoundList);
        plugin.currendRoundConfig.save();
        if (notify) {
            Member discordUser = plugin.getDiscordBot().getServer().getMemberById(plugin.playersListConfig.getConfig().getString("player." + uuid + ".DiscordId"));
            discordUser.getUser().openPrivateChannel().complete().sendMessage(
                    String.format("Hey %s du bist nun an der reihe mit deinen 10 Minuten. Mach schnell, die anderen sind schon ganz gespannt.",
                            plugin.playersListConfig.getConfig().getString("player." + uuid + ".Name"))
            ).complete();
        }
    }

    public boolean isCurrentPlayer(Player player) {
        if (plugin.currentPlayerId == null || plugin.currentPlayerId.isEmpty()) {
            try {
                nextPlayer();
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return (plugin.currentPlayerId.equals(player.getUniqueId().toString()));
    }
}
