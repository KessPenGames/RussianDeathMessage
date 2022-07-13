package net.simple.russian.death.message.config;

import org.bukkit.configuration.file.FileConfiguration;

public class MainConfig {
    private final FileConfiguration config;

    public MainConfig(FileConfiguration config) {
        this.config = config;
    }

    public String getDeathMessageByKey(String key) {
        return config.getString("death-message-color") + config.getString(key);
    }
}
