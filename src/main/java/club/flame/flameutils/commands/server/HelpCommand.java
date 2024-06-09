package club.flame.flameutils.commands.server;

import club.flame.flameutils.FlameUtils;
import club.flame.flameutils.utils.CC;
import club.flame.flameutils.utils.CreatorYML;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HelpCommand extends Command {

    private final CreatorYML config = FlameUtils.getInstance().getConfigYML();

    public HelpCommand() {
        super("help", "");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }
        CC.translate(config.getConfiguration().getStringList("HELP.TEXT"));
    }
}
