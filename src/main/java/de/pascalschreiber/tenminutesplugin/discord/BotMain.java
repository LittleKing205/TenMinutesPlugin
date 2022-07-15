package de.pascalschreiber.tenminutesplugin.discord;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import de.pascalschreiber.tenminutesplugin.discord.commands.RegisterCommand;
import de.pascalschreiber.tenminutesplugin.discord.listener.BotReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;


import javax.security.auth.login.LoginException;

public class BotMain {

    private TenMinutesPlugin plugin;
    private JDA jda;

    public BotMain(TenMinutesPlugin plugin) {
        this.plugin = plugin;
        try {
            jda = JDABuilder.createDefault(getPlugin().getConfig().getString("discordBotToken"))
                    .addEventListeners(new BotReadyListener(this))
                    .addEventListeners(new RegisterCommand(this))
                    .build();
        } catch(LoginException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        jda.shutdown();
    }

    public TenMinutesPlugin getPlugin() {
        return plugin;
    }

    public JDA getDicordBot() {
        return jda;
    }
}
