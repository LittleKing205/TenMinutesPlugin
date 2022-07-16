package de.pascalschreiber.tenminutesplugin.discord;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import de.pascalschreiber.tenminutesplugin.discord.commands.RegisterCommand;
import de.pascalschreiber.tenminutesplugin.discord.listener.BotReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.GatewayIntent;


import javax.security.auth.login.LoginException;
import java.util.List;

public class BotMain {

    private TenMinutesPlugin plugin;
    private JDA jda;

    private Guild server;
    public Role registredRole;
    public Role activeRole;

    public BotMain(TenMinutesPlugin plugin) {
        this.plugin = plugin;
        try {
            jda = JDABuilder.createDefault(getPlugin().getConfig().getString("discordBotToken"),
                            GatewayIntent.DIRECT_MESSAGES,
                            GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.GUILD_MESSAGES)
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

    public JDA getApi() {
        return jda;
    }

    public Guild getServer() {
        return server;
    }

    public void setServer(Guild server) {
        this.server = server;
    }
}
