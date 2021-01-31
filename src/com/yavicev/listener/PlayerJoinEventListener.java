package com.yavicev.listener;

import com.yavicev.player.PlayerUtil;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerJoinEventListener implements Listener {
    @EventHandler
    public void onJoin(ServerConnectEvent event) {
        PlayerUtil.loadOrCreate(event.getPlayer().getName());
    }
}
