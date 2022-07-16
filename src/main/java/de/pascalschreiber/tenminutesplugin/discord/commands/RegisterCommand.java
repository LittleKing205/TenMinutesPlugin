package de.pascalschreiber.tenminutesplugin.discord.commands;

import de.pascalschreiber.tenminutesplugin.discord.BotMain;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegisterCommand implements EventListener {

    private BotMain bot;

    public RegisterCommand(BotMain bot) {
        this.bot = bot;
    }

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (!(genericEvent instanceof MessageReceivedEvent))
            return;
        MessageReceivedEvent event = (MessageReceivedEvent) genericEvent;
        bot.getPlugin().getLogger().info("Message Received...");
        if (event.getMessage().getContentRaw().startsWith("!register ")) {
            bot.getPlugin().getLogger().info("... Message startet with register.");
            String code = event.getMessage().getContentRaw().split(" ")[1];
            if (bot.getPlugin().registerCodes.containsKey(code)) {
                bot.getPlugin().getLogger().info("Code exists");
                Player player = bot.getPlugin().registerCodes.get(code);
                bot.getPlugin().registerCodes.remove(code);
                bot.getPlugin().playersListConfig.getConfig().set("player." + player.getUniqueId().toString() + ".Name", player.getName());
                bot.getPlugin().playersListConfig.getConfig().set("player." + player.getUniqueId().toString() + ".DiscordId", event.getAuthor().getId());
                bot.getPlugin().playersListConfig.getConfig().set("player." + player.getUniqueId().toString() + ".UUID", player.getUniqueId().toString());
                bot.getPlugin().playersListConfig.save();
                bot.getServer().addRoleToMember(event.getAuthor(), bot.registredRole).complete();
                event.getMessage().getChannel().sendMessage(String.format("Moin %s... Du bist nun erfolgreich Registriert", player.getName())).complete();
                bot.getPlugin().getLogger().info(String.format("Moin %s... Du bist nun erfolgreich Registriert", player.getName()));
            } else {
                bot.getPlugin().getLogger().info(String.format("Code %s DONT exists", code));
                event.getMessage().reply("Dieser Code wurde in unserem System leider nicht gefunden.").complete();
            }
        }
    }
}
