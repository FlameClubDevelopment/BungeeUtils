package club.flame.bungeeutils;

import club.flame.bungeeutils.commands.media.*;
import club.flame.bungeeutils.utils.CC;
import lombok.Getter;
import lombok.Setter;
import club.flame.bungeeutils.commands.server.*;
import club.flame.bungeeutils.handlers.BungeeHandler;
import club.flame.bungeeutils.listeners.BungeeListener;
import club.flame.bungeeutils.utils.CreatorYML;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

/**
 * @author HCFAlerts
 * @BungeeUtils project
 * SRC and Jar available at dsc.gg/liteclubdevelopment
 * or github.com/HCFAlerts --> github.com/liteclubdevelopment
 */

@Getter @Setter
public class BungeeUtils extends Plugin {

    @Getter private static BungeeUtils instance;
    private BungeeHandler bungeeHandler;
    private ScheduledTask scheduledTask;
    private CreatorYML configYML;

    public void onEnable() {
        BungeeUtils.instance = this;
        this.files();
        this.handlers();
        this.listeners();
        this.commands();
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4&m=============================="));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&cBungeeUtils &8- &fv" + getDescription().getVersion()));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate(""));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4Author&f: " + getDescription().getAuthor()));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4State&f: &aEnabled"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate(""));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&7&oThank you for using BungeeUtils"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&7&oJoin our Discord dsc.gg/flameclubdevelopment"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4&m=============================="));
    }

    public void onDisable() {
        configYML.getConfiguration().set("WHITELIST.PLAYERS", this.bungeeHandler.getWhitelists());
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4&m=============================="));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&cBungeeUtils &8- &fv" + getDescription().getVersion()));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate(""));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4Author&f: " + getDescription().getAuthor()));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4State&f: &cDisabled"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate(""));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&7&oThank you for using BungeeUtils"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&7&oJoin our Discord dsc.gg/flameclubdevelopment"));
        BungeeCord.getInstance().getConsole().sendMessage(CC.translate("&4&m=============================="));
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
        this.getProxy().getPluginManager().registerCommand(this, new WebsiteCommand());
        this.getProxy().getPluginManager().registerCommand(this, new StoreCommand());
        this.getProxy().getPluginManager().registerCommand(this, new HelpCommand());
    }

    public void whitelist(boolean enabled) {
        configYML.getConfiguration().set("MAINTENANCE.ENABLED", enabled);
        configYML.save();
        configYML.reload();
    }
}