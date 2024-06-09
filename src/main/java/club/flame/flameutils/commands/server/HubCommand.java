package club.flame.flameutils.commands.server;

import club.flame.flameutils.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Random;

public class HubCommand extends Command {

    public HubCommand() {
        super("hub");
    }
    
    public void execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer)commandSender;
        if (args.length == 0) {
            Random random = new Random();
            int attachment = random.nextInt(1) + 1;
            if (attachment < 2) {
                player.sendMessage(CC.translate("&4Sending you to the &fHub-01" + attachment + "&4..."));
                ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo("Hub-01" + attachment);
                player.connect(serverInfo);
                return;
            }
            player.sendMessage(CC.translate("&cWe don't have more hubs than 3, please try again."));
        }
        else {
            if (args.length != 1) {
                return;
            }
            try {
                Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e) {
                player.sendMessage(CC.translate("&cA hub with that name cannot be found."));
                return;
            }

            /*
            Hubcores:
            4 = 3
            3 = 2
            2 = 1
            */

            if (Integer.parseInt(args[0]) < 2) {
                player.sendMessage(CC.translate("&4Sending you to the &fHub-01" + args[0] + "&4..."));
                ServerInfo serverInfo2 = ProxyServer.getInstance().getServerInfo("Hub-01" + args[0]);
                player.connect(serverInfo2);
                return;
            }
            player.sendMessage(CC.translate("&cWe don't have more hubs than 1, please try again."));
        }
    }
}
