package club.flame.flameutils.commands;

import club.flame.flameutils.FlameUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class BungeeCommand extends Command {

    public BungeeCommand() {
        super("test");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            FlameUtils.getInstance().getConfigYML().reload();
        }

    }
}
