package net.simple.russian.death.message;

import net.simple.russian.death.message.config.MainConfig;
import net.simple.russian.death.message.listeners.DeathListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class RussianDeathMessageMain extends JavaPlugin {

    @Override
    public void onEnable() {
        File fileConfig = new File(getDataFolder() + File.separator + "config.yml");
        if(!fileConfig.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        MainConfig config = new MainConfig(getConfig());

        Bukkit.getPluginManager().registerEvents(new DeathListener(config), this);

        getLogger().info("Plugin started up!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled up!");
    }
}
