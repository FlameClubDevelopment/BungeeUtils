package club.flame.bungeeutils.listeners;

import club.flame.bungeeutils.BungeeUtils;
import club.flame.bungeeutils.utils.CC;
import club.flame.bungeeutils.utils.CreatorYML;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Author: HCFAlerts
 * Project: BungeeUtils
 * SRC and Jar available at dsc.gg/liteclubdevelopment
 * or github.com/HCFAlerts --> github.com/liteclubdevelopment
 */

public class BungeeListener implements Listener {

    private final CreatorYML config = BungeeUtils.getInstance().getConfigYML();

    @EventHandler(priority = 64)
    public void onPing(ProxyPingEvent event) {
        if (config.getConfiguration().getBoolean("MAINTENANCE.ENABLED")) {
            event.getResponse().setVersion(new ServerPing.Protocol(CC.translate(BungeeUtils.getInstance().getConfigYML().getConfiguration().getString("MAINTENANCE.PING-TEXT")), 9999));
            event.setResponse(event.getResponse());
        }
    }

    @EventHandler
    public void onKick(ServerKickEvent event) {
        ProxiedPlayer p = event.getPlayer();
        Random random = new Random();
        int rand = random.nextInt(1) + 1;
        ServerInfo connect = BungeeCord.getInstance().getServerInfo(config.getConfiguration().getString("MAINTENANCE.HUB-NAME") + rand);
        if (event.getKickedFrom() == connect) {
            p.disconnect(CC.translate(config.getConfiguration().getStringList("KICK.MESSAGE") + event.getKickedFrom().getName() + "&c: &f" + event.getKickReason()));
        }
        else {
            event.setCancelServer(connect);
            event.setCancelled(true);
            BungeeCord.getInstance().getScheduler().schedule(BungeeUtils.getInstance(), () -> p.sendMessage(CC.translate(config.getConfiguration().getStringList("KICK.MESSAGE") + event.getKickedFrom().getName() + "&c: &f" + event.getKickReason())), 2L, TimeUnit.SECONDS);
        }
    }

    @EventHandler
    public void onJoin(LoginEvent event) {
        CreatorYML config = BungeeUtils.getInstance().getConfigYML();
        if (config.getConfiguration().getBoolean("MAINTENANCE.ENABLED") && !BungeeUtils.getInstance().getBungeeHandler().isWhitelisted(event.getConnection().getUniqueId().toString())) {
            event.setCancelled(true);
            event.setCancelReason(config.getConfiguration().getStringList("MAINTENANCE.KICK-MESSAGE")
                    .stream()
                    .map(CC::translate)
                    .collect(Collectors.joining("\n")));
        }
    }
}