package club.flame.bungeeutils.handlers;

import club.flame.bungeeutils.BungeeUtils;
import lombok.Getter;
import club.flame.bungeeutils.utils.CreatorYML;

import java.util.*;

/**
 * Author: HCFAlerts
 * Project: BungeeUtils
 * SRC and Jar available at dsc.gg/liteclubdevelopment
 * or github.com/HCFAlerts --> github.com/liteclubdevelopment
 */

@Getter
public class BungeeHandler {

    private final List<String> whitelists;
    private final CreatorYML config = BungeeUtils.getInstance().getConfigYML();
    
    public BungeeHandler() {
        this.whitelists = config.getConfiguration().getStringList("WHITELIST.PLAYERS");
    }
    
    public boolean isWhitelisted(String uuid) {
        return whitelists.contains(uuid);
    }
    
    public void setWhitelisted(String uuid, boolean whitelisted) {
        if (whitelisted) {
            if (!this.isWhitelisted(uuid)) {
                this.whitelists.add(uuid);
                config.getConfiguration().set("WHITELIST.PLAYERS", this.whitelists);
                config.save();
            }
        }
        else if (this.isWhitelisted(uuid)) {
            this.whitelists.remove(uuid);
            config.getConfiguration().set("WHITELIST.PLAYERS", this.whitelists);
            config.save();
        }
    }
}
