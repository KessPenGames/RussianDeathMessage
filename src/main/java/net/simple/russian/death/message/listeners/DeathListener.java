package net.simple.russian.death.message.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.simple.russian.death.message.config.MainConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    private final MainConfig config;

    public DeathListener(MainConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        TranslatableComponent component = (TranslatableComponent) event.deathMessage();
        if (component == null) return;
        TextComponent deathMessage = (TextComponent) MiniMessage.miniMessage().deserialize(config.getDeathMessageByKey(component.key().replace(".", "-")));
        for (int i = 0; component.args().toArray().length > i; i++) {
            int requiemI = i + 1;
            Component arg = component.args().get(i);
            if (arg instanceof TextComponent text) deathMessage = (TextComponent) deathMessage.replaceText(TextReplacementConfig.builder().replacement(text).match("%" + requiemI).build());
            else if (arg instanceof TranslatableComponent text) deathMessage = (TextComponent) deathMessage.replaceText(TextReplacementConfig.builder().replacement(text).match("%" + requiemI).build());
        }
        event.deathMessage(deathMessage);
    }
}
