package com.github.shiro8613.litebanssrvsync;

import litebans.api.Database;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Litebans {
    private final JavaPlugin plugin;

    public Litebans(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isBanned(@NotNull UUID uuid) {
        AtomicReference<Boolean> bool = new AtomicReference<>(false);
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            bool.set(Database.get().isPlayerBanned(uuid, null));
        });

        return bool.get();
    }

    public void BanPlayer(UUID uuid, @Nullable String reason, @Nullable String time) {
        Server server = plugin.getServer();
        String player = Objects.requireNonNull(server.getPlayer(uuid)).getName();
        String cmd = createBanCommand(player, reason, time);
        server.dispatchCommand(server.getConsoleSender(), cmd);
    }

    private String createBanCommand(String name, @Nullable String reason, @Nullable String time) {
        List<String> list = new ArrayList<String>();
        list.add("ban");
        list.add(name);
        if(time != null) list.add(time);
        if(reason != null) list.add(reason);
        return String.join(" ", list);
    }

}
