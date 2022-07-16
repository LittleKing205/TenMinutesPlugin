package de.pascalschreiber.tenminutesplugin.util;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer {

    private final TenMinutesPlugin plugin;
    private int elapsedTime;
    private int sessionTime;
    private boolean paused;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(0);

    public Timer(TenMinutesPlugin plugin, int elapsedTime, int sessionTime) {
        this.elapsedTime = elapsedTime;
        this.plugin = plugin;
        this.sessionTime = sessionTime;
    }

    public void start(Player player) {
        this.executorService.scheduleAtFixedRate(() -> {
            elapsedTime += 100;
            sessionTime += 100;

            int hours = (elapsedTime / 3600000);
            int minutes = (elapsedTime / 60000) % 60;
            int seconds = (elapsedTime / 1000) % 60;

            StringBuilder message = new StringBuilder(ChatColor.GOLD.toString());
            message.append(String.format("%02d", hours)).append(" : ");
            message.append(String.format("%02d", minutes)).append(" : ");
            message.append(String.format("%02d", seconds));

            player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message.toString()));
        }, 5000, 100, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        executorService.shutdownNow();
    }

    public int getElapsedTimeMilis() {
        return sessionTime;
    }

    public int getSessionTimeMilis() {
        return sessionTime;
    }
}
