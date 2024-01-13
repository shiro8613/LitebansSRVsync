package com.github.shiro8613.litebanssrvsync;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Guild;
import github.scarsz.discordsrv.dependencies.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class DiscordEventListener {

    private JavaPlugin plugin;
    private Litebans litebans;

    public DiscordEventListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.litebans = new Litebans(plugin);
    }

    @Subscribe
    public void DiscordBAN(Guild.Ban event) {
        User user = event.getUser();
        UUID uuid = DiscordSRV.getPlugin().getAccountLinkManager().getUuid(user.getId());

        if (uuid == null) return;

        if (litebans.isBanned(uuid)) return;

        litebans.BanPlayer(uuid, "DiscordがBANされたためサーバーからBANされました。", null);

    }
}
