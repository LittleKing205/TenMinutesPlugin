package de.pascalschreiber.tenminutesplugin.discord.listener;

import de.pascalschreiber.tenminutesplugin.discord.BotMain;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class RegisterCommand implements MessageCreateListener {

    private BotMain bot;

    public RegisterCommand(BotMain bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessage().getContent().startsWith("/register ")) {
            String code = event.getMessage().getContent().split(" ")[1];
            if (bot.getPlugin().registerCodes.containsKey(code)) {
                Player player = bot.getPlugin().registerCodes.get(code);
                bot.getPlugin().playersListConfig.getConfig().set("player." + player.getUniqueId() + ".name", player.getName());
                bot.getPlugin().playersListConfig.getConfig().set("player." + player.getUniqueId() + ".DiscordId", event.getMessage().getUserAuthor().get().getId());
                bot.getPlugin().playersListConfig.getConfig().set("player." + player.getUniqueId() + ".uuid", player.getUniqueId());
                bot.getPlugin().playersListConfig.save();
                event.getMessage().getChannel().sendMessage(String.format("Moin %s... Du bist nun erfolgreich Registriert", player.getName()));
                event.deleteMessage();
            }
        }
    }
}
