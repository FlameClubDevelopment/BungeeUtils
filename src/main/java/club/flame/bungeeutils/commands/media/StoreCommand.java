package club.flame.bungeeutils.commands.media;

import club.flame.bungeeutils.BungeeUtils;
import club.flame.bungeeutils.utils.CC;
import club.flame.bungeeutils.utils.CreatorYML;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Author: HCFAlerts
 * Project: BungeeUtils
 * SRC and Jar available at dsc.gg/liteclubdevelopment
 * or github.com/HCFAlerts --> github.com/liteclubdevelopment
 */

public class StoreCommand extends Command {

    private final CreatorYML config = BungeeUtils.getInstance().getConfigYML();

    public StoreCommand() {
        super("store");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }
        config.getConfiguration().getStringList("STORE.MESSAGE").stream().map(CC::translate).forEach(sender::sendMessage);
    }
}
