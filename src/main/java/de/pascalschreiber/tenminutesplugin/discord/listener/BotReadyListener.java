package de.pascalschreiber.tenminutesplugin.discord.listener;

import de.pascalschreiber.tenminutesplugin.discord.BotMain;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BotReadyListener implements EventListener {

    private BotMain bot;

    public BotReadyListener(BotMain bot) {
        this.bot = bot;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (!(event instanceof ReadyEvent))
            return;
        bot.setServer( bot.getApi().getGuilds().stream().findFirst().get() );

        List<Role> registredRoles = bot.getServer().getRolesByName(bot.getPlugin().getConfig().getString("registredRole"), false);
        if (registredRoles.size() > 0) {
            bot.registredRole = registredRoles.stream().findFirst().get();
        } else {
            bot.registredRole = bot.getServer().createRole().setName(bot.getPlugin().getConfig().getString("registredRole")).complete();
        }

        List<Role> activeRoles = bot.getServer().getRolesByName(bot.getPlugin().getConfig().getString("activeRole"), false);
        if (activeRoles.size() > 0) {
            bot.activeRole = activeRoles.stream().findFirst().get();
        } else {
            bot.activeRole = bot.getServer().createRole().setName(bot.getPlugin().getConfig().getString("activeRole")).complete();
        }

        bot.getPlugin().getLogger().info("Discord Bot is now Online");
    }
}
