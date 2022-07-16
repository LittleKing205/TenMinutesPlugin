package de.pascalschreiber.tenminutesplugin.listener;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import de.pascalschreiber.tenminutesplugin.util.Serialization;
import de.pascalschreiber.tenminutesplugin.util.Timer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener  implements Listener {

    private TenMinutesPlugin plugin;

    public PlayerJoinListener(TenMinutesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.lastPlayerConfig.getConfig().isSet("inventory") && plugin.lastPlayerConfig.getConfig().isSet("armor")) {
            String inventory = plugin.lastPlayerConfig.getConfig().getString("inventory");
            String armor = plugin.lastPlayerConfig.getConfig().getString("armor");
            ItemStack[][] inventorys = Serialization.base64ToInv(new String[]{ inventory, armor });
            event.getPlayer().getInventory().setContents(inventorys[0]);
            event.getPlayer().getInventory().setArmorContents(inventorys[1]);
            event.getPlayer().setHealth(plugin.lastPlayerConfig.getConfig().getDouble("health"));
            event.getPlayer().setFoodLevel(plugin.lastPlayerConfig.getConfig().getInt("hunger"));
        }

        plugin.timer = new Timer(plugin, plugin.timersConfig.getConfig().getInt("elipsedTime", 0), 0);
        plugin.timer.start(event.getPlayer());
    }
}
