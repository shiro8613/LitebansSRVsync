package com.github.shiro8613.litebanssrvsync;

import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.plugin.java.JavaPlugin;

public final class Litebanssrvsync extends JavaPlugin {

    private final DiscordEventListener discordEventListener = new DiscordEventListener(this);

    @Override
    public void onEnable() {
        DiscordSRV.api.subscribe(discordEventListener);
    }

    @Override
    public void onDisable() {
        DiscordSRV.api.unsubscribe(discordEventListener);
    }
}
