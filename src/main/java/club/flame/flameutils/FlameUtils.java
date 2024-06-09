package club.flame.flameutils;

import club.flame.flameutils.utils.CC;
import lombok.Getter;
import lombok.Setter;
import club.flame.flameutils.commands.BungeeCommand;
import club.flame.flameutils.commands.media.DiscordCommand;
import club.flame.flameutils.commands.media.TeamSpeakCommand;
import club.flame.flameutils.commands.media.StoreCommand;
import club.flame.flameutils.commands.media.TwitterCommand;
import club.flame.flameutils.commands.server.*;
import club.flame.flameutils.handlers.BungeeHandler;
import club.flame.flameutils.listeners.BungeeListener;
import club.flame.flameutils.utils.CreatorYML;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

@Getter @Setter
public class FlameUtils extends Plugin {

    @Getter private static FlameUtils instance;
    private BungeeHandler bungeeHandler;
    private ScheduledTask scheduledTask;
    private CreatorYML configYML;

    public void onEnable() {
        FlameUtils.instance = this;
        this.files();
        this.handlers();
        this.listeners();
        this.commands();
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&7&m------------------------"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&cFlameUtils Bungee &8- &fv" + getDescription().getVersion()));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate(""));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&cLicense Info"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4Status&f: &aActivated"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4License&f: Open-Source"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate(""));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&cPlugin Info"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4Author&f: " + getDescription().getAuthor()));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4Discord&f: dsc.gg/flameclubdevelopment"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate(""));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&7&oThank you for using FlameUtils Bungee"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&7&m------------------------"));
    }

    public void onDisable() {
        configYML.getConfiguration().set("WHITELIST.PLAYERS", this.bungeeHandler.getWhitelists());
        getInstance().getScheduledTask().cancel();
    }

    private void files() {
        this.configYML = new CreatorYML(this, "config.yml");
    }

    private void handlers() {
        this.bungeeHandler = new BungeeHandler();
    }

    private void listeners() {
        this.getProxy().getPluginManager().registerListener(this, new BungeeListener());
    }

    private void commands() {
        this.getProxy().getPluginManager().registerCommand(this, new HubCommand());
        this.getProxy().getPluginManager().registerCommand(this, new MaintenanceCommand());
        this.getProxy().getPluginManager().registerCommand(this, new FindCommand());
        this.getProxy().getPluginManager().registerCommand(this, new ServerCommand());
        this.getProxy().getPluginManager().registerCommand(this, new TeamSpeakCommand());
        this.getProxy().getPluginManager().registerCommand(this, new DiscordCommand());
        this.getProxy().getPluginManager().registerCommand(this, new TwitterCommand());
        this.getProxy().getPluginManager().registerCommand(this, new StoreCommand());
        this.getProxy().getPluginManager().registerCommand(this, new BungeeCommand());
        this.getProxy().getPluginManager().registerCommand(this, new HelpCommand());
    }

    public void whitelist(boolean enabled) {
        configYML.getConfiguration().set("WHITELIST.ENABLED", enabled);
        configYML.save();
        configYML.reload();
    }
}