package club.flame.flameutils.commands.server;

import club.flame.flameutils.FlameUtils;
import club.flame.flameutils.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ServerCommand extends Command {

    public ServerCommand() {
        super("server", "bungeecord.command.server", "connect");
    }
    
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)commandSender;
            if (player.hasPermission("bungeecord.command.server")) {
                if (args.length == 0) {
                    player.sendMessage(CC.translate("&4You are currently connected to &f" + player.getServer().getInfo().getName() + "&4."));
                    StringBuilder servers = new StringBuilder("&4Servers&7: &f");
                    boolean first = true;
                    for (String name : FlameUtils.getInstance().getProxy().getServers().keySet()) {
                        if (first) {
                            servers.append(name);
                        }
                        else {
                            servers.append("&7, &f").append(name);
                        }
                        first = false;
                    }
                    player.sendMessage(CC.translate(servers.toString()));
                    player.sendMessage(CC.translate("&4Connect to a server with &f/server <name>"));
                }
                else if (args.length == 1) {
                    ServerInfo si = FlameUtils.getInstance().getProxy().getServerInfo(args[0]);
                    if (si != null) {
                        player.sendMessage(CC.translate("&4Connecting you to &f" + si.getName() + "&4!"));
                        player.connect(si);
                    }
                    else {
                        player.sendMessage(CC.translate("&cNo Permission"));
                    }
                }
            }
            else {
                player.sendMessage(CC.translate("&cNo permission."));
            }
        }
    }
}
