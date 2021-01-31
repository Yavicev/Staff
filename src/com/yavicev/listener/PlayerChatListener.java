package com.yavicev.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yavicev.Main;
import com.yavicev.StringConstant;
import com.yavicev.player.Player;
import com.yavicev.player.PlayerUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class PlayerChatListener implements Listener {
    private final Plugin plugin;
    private final DateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public PlayerChatListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(ChatEvent event) {
        ProxiedPlayer proxiedPlayer;
        Player player;
        TextComponent message;
        if (event.getMessage().startsWith("~")) {
            proxiedPlayer = (ProxiedPlayer)event.getSender();
            player = PlayerUtil.getPlayer(proxiedPlayer.getName());
            if (player == null || !player.isStaff()) {
                return;
            }

            event.setCancelled(true);
            message = new TextComponent(String.format(StringConstant.STAFF_PREFIX.getString(), player.getGroupPrefix() + player.getName()) + " " + event.getMessage().replace("~", ""));
            TextComponent[] subInfo = new TextComponent[]{new TextComponent(ChatColor.DARK_AQUA + "Дополнительная информация: \n"), new TextComponent(ChatColor.AQUA + "Отправлено с сервера - " + proxiedPlayer.getServer().getInfo().getName() + "\n"), new TextComponent(ChatColor.AQUA + "UUID игрока - " + proxiedPlayer.getUniqueId() + "\n"), new TextComponent(ChatColor.AQUA + "Отправлено в - " + this.formater.format(new Date()))};
            message.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, subInfo));
            this.plugin.getProxy().getServers().forEach((s, serverInfo) -> {
                serverInfo.getPlayers().stream().filter((p) -> {
                    Player customPlayer = PlayerUtil.getPlayer(p.getName());
                    return customPlayer != null && customPlayer.isStaff() && customPlayer.isStaffChatToggle();
                }).forEach((p) -> {
                    p.sendMessage(message);
                });
            });
        } else if (event.getMessage().startsWith("%")) {
            proxiedPlayer = (ProxiedPlayer)event.getSender();
            player = PlayerUtil.getPlayer(proxiedPlayer.getName());
            if (player.getPriority() < 10) {
                return;
            }

            event.setCancelled(true);
            message = new TextComponent(String.format(StringConstant.DONAT_PREFIX.getString(), player.getGroupPrefix() + player.getName()) + " " + event.getMessage().replace("%", ""));
            this.plugin.getProxy().getServers().forEach((s, serverInfo) -> {
                serverInfo.getPlayers().stream().filter((p) -> {
                    Player customPlayer = PlayerUtil.getPlayer(p.getName());
                    return customPlayer != null && customPlayer.getPriority() >= 10;
                }).forEach((p) -> {
                    p.sendMessage(message);
                });
            });
        }

    }
}
