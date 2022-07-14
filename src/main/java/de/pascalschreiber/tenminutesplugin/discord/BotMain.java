package de.pascalschreiber.tenminutesplugin.discord;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandOption;

import java.util.Arrays;

public class BotMain {

    private TenMinutesPlugin plugin;
    private DiscordApi discordApi;
    private Server server;

    private long activeRoleId;
    public Role activeRole;

    private long registredRoleId;
    public Role registredRole;

    public BotMain(TenMinutesPlugin plugin) {
        this.plugin = plugin;
        discordApi = new DiscordApiBuilder()
                .setToken(plugin.getConfig().getString("discordBotToken"))
                .login().join();
        server = discordApi.getServers().stream().findFirst().get();
        startUp();
    }

    private void startUp() {
        for(Role role : server.getRoles()) {
            if (role.getName().equals(plugin.getConfig().getString("registredRole"))) {
                registredRoleId = role.getId();
                registredRole = role;
            }
            if (role.getName().equals(plugin.getConfig().getString("activeRole"))) {
                activeRoleId = role.getId();
                activeRole = role;
            }
        }

        if (registredRole == null) {
            registredRole = server.createRoleBuilder().setName(plugin.getConfig().getString("registredRole")).create().join();
            registredRoleId = registredRole.getId();
        }
        if (activeRole == null) {
            activeRole = server.createRoleBuilder().setName(plugin.getConfig().getString("activeRole")).create().join();
            activeRoleId = activeRole.getId();
        }

        SlashCommand command = SlashCommand.with("register", "Registriert einen Minecraft Account ins Spiel",
                        Arrays.asList(
                                SlashCommandOption.createStringOption("Code", "Der Registrierungscode, der beim Login angezeigt wird.", true)
                        ))
                .createForServer(server)
                .join();
    }

    public Server getServer() {
        return server;
    }

    public TenMinutesPlugin getPlugin() {
        return plugin;
    }

    public DiscordApi getApi() {
        return discordApi;
    }
}
