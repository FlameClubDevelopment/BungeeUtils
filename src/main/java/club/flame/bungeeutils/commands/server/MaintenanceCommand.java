package club.flame.bungeeutils.commands.server;

import club.flame.bungeeutils.BungeeUtils;
import club.flame.bungeeutils.utils.MojangUtils;
import club.flame.bungeeutils.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.Objects;
import java.util.UUID;

/**
 * Author: HCFAlerts
 * Project: BungeeUtils
 * SRC and Jar available at dsc.gg/liteclubdevelopment
 * or github.com/HCFAlerts --> github.com/liteclubdevelopment
 */

public class MaintenanceCommand extends Command {

    public MaintenanceCommand() {
        super("bungeewhitelist", "bungeecord.command.maintenance", "maintenance", "maintenancemode", "bwl");
    }
    
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("bungeecord.command.maintenance")) {
            sender.sendMessage("&cYou don't have permissions.");
            return;
        }

        if (args.length < 1) {
            sender.sendMessage(CC.translate("&cUsage: /maintenance <on|off|list|add|remove> <name>"));
            return;
        }

        if (args[0].equalsIgnoreCase("on")) {
            sender.sendMessage(CC.translate("&aThe server is now whitelisted."));
            BungeeUtils.getInstance().whitelist(true);
            return;
        }

        if (args[0].equalsIgnoreCase("off")) {
            sender.sendMessage(CC.translate("&cThe server is no longer whitelisted."));
            BungeeUtils.getInstance().whitelist(false);
            return;
        }

        if (args[0].equalsIgnoreCase("list")) {
            sender.sendMessage("");
            sender.sendMessage(CC.translate("&aPeople whitelisted:"));
            String error = null;
            for (String uuid : BungeeUtils.getInstance().getBungeeHandler().getWhitelists()) {
                try {
                    sender.sendMessage(CC.translate(" &a- &f" + MojangUtils.fetchName(UUID.fromString(uuid))));
                }
                catch (Exception e) {
                    error = CC.translate("&cSomething went wrong while loading the whitelist.");
                }
            }
            if (error != null) {
                sender.sendMessage("");
                sender.sendMessage(error);
            }
            sender.sendMessage("");
            return;
        }
        try {
            if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("add")) {
                    String uuid2 = args[1];
                    BungeeUtils.getInstance().getBungeeHandler().setWhitelisted(Objects.requireNonNull(MojangUtils.fetchUUID(uuid2)).toString(), true);
                    sender.sendMessage(CC.translate("&aYou successfully added " + uuid2 + " to the whitelist!"));
                    return;
                }
                if (args[0].equalsIgnoreCase("remove")) {
                    String uuid2 = args[1];
                    BungeeUtils.getInstance().getBungeeHandler().setWhitelisted(Objects.requireNonNull(MojangUtils.fetchUUID(uuid2)).toString(), false);
                    sender.sendMessage(CC.translate("&cYou successfully removed " + uuid2 + " from the whitelist!"));
                    return;
                }
            }
        }
        catch (Exception ignored) {}
        sender.sendMessage(CC.translate("&cUsage: /maintenance <on|off|list|add|remove> <name>"));
    }
}
