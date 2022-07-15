package de.pascalschreiber.tenminutesplugin.discord.listener;

import de.pascalschreiber.tenminutesplugin.discord.BotMain;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class BotReadyListener implements EventListener {

    private BotMain bot;

    public BotReadyListener(BotMain bot) {
        this.bot = bot;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (!(event instanceof ReadyEvent))
            return;



        bot.getPlugin().getLogger().info("Discord Bot is now Online");
    }
}
