package com.yavicev.command;

import com.yavicev.player.Player;
import com.yavicev.player.PlayerUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatToggle extends Command {
    public StaffChatToggle() {
        super("sct");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        Player player = PlayerUtil.getPlayer(commandSender.getName());
        player.setStaffChatToggle(!player.isStaffChatToggle());
        PlayerUtil.addPlayer(player);
    }

}
