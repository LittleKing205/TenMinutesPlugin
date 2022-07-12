package de.pascalschreiber.tenminutesplugin.util;

import de.pascalschreiber.tenminutesplugin.TenMinutesPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private final TenMinutesPlugin plugin;
    private final String fileName;
    private final File folder;
    private FileConfiguration cfg;
    private File cfgFile;

    public Config(TenMinutesPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.folder = plugin.getDataFolder();
        cfg = null;
        cfgFile = null;
        reload();
    }

    public void reload() {
        if (!folder.exists())
            folder.mkdir();

        cfgFile = new File(folder, fileName);

        if(!cfgFile.exists()) {
            try {
                cfgFile.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        cfg = YamlConfiguration.loadConfiguration(cfgFile);
    }

    public FileConfiguration getConfig() {
        if (cfg == null)
            reload();
        return cfg;
    }

    public void save() {
        if(cfg == null || cfgFile == null)
            return;

        try {
            getConfig().save(cfgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
