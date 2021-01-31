package com.yavicev.player;

import net.md_5.bungee.api.ChatColor;


public class CustomPlayer implements Player {
    private final String name;
    private final String groupName;
    private String groupPrefix;
    private final boolean staff;
    private final int priority;
    private boolean sct;

    public CustomPlayer(String name, String groupName, String groupPrefix, boolean staff, int priority, boolean sct) {
        this.name = name;
        this.groupName = groupName;
        this.groupPrefix = ChatColor.translateAlternateColorCodes('&', groupPrefix);
        this.staff = staff;
        this.priority = priority;
        this.sct = sct;
    }

    public String getName() {
        return this.name;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public boolean isStaff() {
        return this.staff;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getGroupPrefix() {
        return this.groupPrefix;
    }

    public boolean isStaffChatToggle() {
        return this.sct;
    }

    public void setStaffChatToggle(boolean b) {
        this.sct = b;
    }
}
