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
    private Role activeRole;

    private long registredRoleId;
    private Role registredRole;

    public BotMain(TenMinutesPlugin plugin) {
        this.plugin = plugin;
        discordApi = new DiscordApiBuilder()
                .setToken(plugin.getConfig().getString("discordBotToken"))
                .login().join();
        server = discordApi.getServerById(plugin.getConfig().getInt("discordServerId")).get();

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

        if (activeRole == null || registredRole == null) {
            plugin.getLogger().warning("Can't find Server Roles. Shutting down Plugin");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }

        SlashCommand command = SlashCommand.with("register", "Registriert einen Minecraft Account ins Spiel",
                        Arrays.asList(
                                SlashCommandOption.createStringOption("Code", "Der Registrierungscode, der beim Login angezeigt wird.", true)
                        ))
                .createForServer(server)
                .join();

    }
}
