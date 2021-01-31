package com.yavicev;

import com.yavicev.command.StaffChatToggle;
import com.yavicev.listener.PlayerChatListener;
import com.yavicev.listener.PlayerJoinEventListener;
import com.yavicev.player.PlayerUtil;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
    public void onEnable() {
        this.getLogger().info("StaffChat enabling");
        PlayerUtil.init();
        this.getProxy().getPluginManager().registerCommand(this, new StaffChatToggle());
        this.getProxy().getPluginManager().registerListener(this, new PlayerChatListener(this));
        this.getProxy().getPluginManager().registerListener(this, new PlayerJoinEventListener());
    }

    public void onDisable(){
        this.getLogger().info("StaffChat disabled");
    }
}
