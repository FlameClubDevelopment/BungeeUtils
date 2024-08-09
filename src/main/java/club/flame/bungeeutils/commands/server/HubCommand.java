package club.flame.bungeeutils.commands.server;

import club.flame.bungeeutils.BungeeUtils;
import club.flame.bungeeutils.utils.CC;
import club.flame.bungeeutils.utils.CreatorYML;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Random;

/**
 * Author: HCFAlerts
 * Project: BungeeUtils
 * SRC and Jar available at dsc.gg/liteclubdevelopment
 * or github.com/HCFAlerts --> github.com/liteclubdevelopment
 */

public class HubCommand extends Command {

    private final CreatorYML config = BungeeUtils.getInstance().getConfigYML();

    public HubCommand() {
        super("hub", "", "lobby");
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
                player.sendMessage(CC.translate((config.getConfiguration().getString("HUB.MESSAGE") + " " + (config.getConfiguration().getString("HUB.HUB-NAME") + attachment + (config.getConfiguration().getString("HUB.FINAL"))))));
                ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo((config.getConfiguration().getString("HUB.HUB-NAME") + attachment));
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
                player.sendMessage(CC.translate((config.getConfiguration().getString("HUB.MESSAGE") + " " + (config.getConfiguration().getString("HUB.HUB-NAME") + args[0] + (config.getConfiguration().getString("HUB.FINAL"))))));
                ServerInfo serverInfo2 = ProxyServer.getInstance().getServerInfo((config.getConfiguration().getString("HUB.HUB-NAME") + args[0]));
                player.connect(serverInfo2);
                return;
            }
            player.sendMessage(CC.translate("&cWe don't have more hubs than 1, please try again."));
        }
    }
}
