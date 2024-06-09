package club.flame.flameutils.handlers;

import club.flame.flameutils.FlameUtils;
import lombok.Getter;
import club.flame.flameutils.utils.CreatorYML;

import java.util.*;

@Getter
public class BungeeHandler {

    private final List<String> whitelists;
    private final CreatorYML config = FlameUtils.getInstance().getConfigYML();
    
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
