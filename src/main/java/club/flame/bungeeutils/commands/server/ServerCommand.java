package club.flame.bungeeutils.commands.server;

import club.flame.bungeeutils.BungeeUtils;
import club.flame.bungeeutils.utils.CC;
import club.flame.bungeeutils.utils.CreatorYML;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Author: HCFAlerts
 * Project: BungeeUtils
 * SRC and Jar available at dsc.gg/liteclubdevelopment
 * or github.com/HCFAlerts --> github.com/liteclubdevelopment
 */

public class ServerCommand extends Command {

    private final CreatorYML config = BungeeUtils.getInstance().getConfigYML();

    public ServerCommand() {
        super("server", "bungeecord.command.server", "connect", "play");
    }
    
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)commandSender;
            if (player.hasPermission("bungeecord.command.server")) {
                if (args.length == 0) {
                    player.sendMessage(CC.translate((config.getConfiguration().getString("SERVER.CONNECTED") + " " + player.getServer().getInfo().getName() + (config.getConfiguration().getString("SERVER.CONNECTED_FINAL")))));
                    StringBuilder servers = new StringBuilder("&4Servers&7: &f");
                    boolean first = true;
                    for (String name : BungeeUtils.getInstance().getProxy().getServers().keySet()) {
                        if (first) {
                            servers.append(name);
                        }
                        else {
                            servers.append("&7, &f").append(name);
                        }
                        first = false;
                    }
                    player.sendMessage(CC.translate(servers.toString()));
                    player.sendMessage(CC.translate((config.getConfiguration().getString("SERVER.USAGE"))));
                }
                else if (args.length == 1) {
                    ServerInfo si = BungeeUtils.getInstance().getProxy().getServerInfo(args[0]);
                    if (si != null) {
                        player.sendMessage(CC.translate((config.getConfiguration().getString("SERVER.MESSAGE") + " " + si.getName() + (config.getConfiguration().getString("SERVER.FINAL")))));
                        player.connect(si);
                    }
                    else {
                        player.sendMessage(CC.translate((config.getConfiguration().getString("SERVER.NO_PERMISSION"))));
                    }
                }
            }
            else {
                player.sendMessage(CC.translate((config.getConfiguration().getString("SERVER.NO_PERMISSION"))));
            }
        }
    }
}
