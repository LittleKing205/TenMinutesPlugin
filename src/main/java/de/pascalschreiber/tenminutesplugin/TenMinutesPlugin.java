package de.pascalschreiber.tenminutesplugin;

import de.pascalschreiber.tenminutesplugin.discord.BotMain;
import de.pascalschreiber.tenminutesplugin.listener.PlayerJoinListener;
import de.pascalschreiber.tenminutesplugin.listener.PlayerLeaveListener;
import de.pascalschreiber.tenminutesplugin.listener.PlayerLoginListener;
import de.pascalschreiber.tenminutesplugin.util.Config;
import de.pascalschreiber.tenminutesplugin.util.PlayerSelector;
import de.pascalschreiber.tenminutesplugin.util.Timer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class TenMinutesPlugin extends JavaPlugin {

    private BotMain discordBot;

    public Config playersListConfig;
    public Config lastPlayerConfig;
    public Config timersConfig;
    public Config currendRoundConfig;

    public Timer timer;
    public PlayerSelector playerSelector;
    public String currentPlayerId;
    public HashMap<String, Player> registerCodes;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if (getConfig().getString("discordBotToken").equalsIgnoreCase("PUT YOUR TOKEN HERE")) {
            getLogger().warning("There is no DiscordBot Token set. Shutting down Plugin.");
            getPluginLoader().disablePlugin(this);
        }

        registerCodes = new HashMap<String, Player>();
        playerSelector = new PlayerSelector(this);
        playersListConfig = new Config(this, "playerslist");
        lastPlayerConfig = new Config(this, "lastplayerdata");
        timersConfig = new Config(this, "timers");
        currendRoundConfig = new Config(this, "currentround");

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this), this);

        discordBot = new BotMain(this);
    }

    @Override
    public void onDisable() {

    }

    public BotMain getDiscordBot() {
        return discordBot;
    }
}
