package de.pascalschreiber.tenminutesplugin.discord.listener;

import de.pascalschreiber.tenminutesplugin.discord.BotMain;
import de.pascalschreiber.tenminutesplugin.util.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.javacord.api.event.server.member.ServerMemberLeaveEvent;
import org.javacord.api.listener.server.member.ServerMemberLeaveListener;

public class MemberLeaveListener implements ServerMemberLeaveListener {

    private BotMain bot;

    private MemberLeaveListener(BotMain bot) {
        this.bot = bot;
    }

    @Override
    public void onServerMemberLeave(ServerMemberLeaveEvent event) {
        Config playerListConfig = bot.getPlugin().playersListConfig;
        ConfigurationSection playersSection = playerListConfig.getConfig().getConfigurationSection("players");
        for (String key : playersSection.getKeys(false)) {
            if (playerListConfig.getConfig().getLong("players." + key + ".DiscordId") == event.getUser().getId()) {
                playerListConfig.getConfig().set("players." + key, null);
                playerListConfig.save();
            }
        }
    }
}
