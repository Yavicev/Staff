package com.yavicev;

import net.md_5.bungee.api.ChatColor;

public enum StringConstant {
    STAFF_PREFIX(ChatColor.translateAlternateColorCodes('&', "&c&e&lSTAFF§f§l#  %s&f&l >>")),
    DONAT_PREFIX(ChatColor.translateAlternateColorCodes('&', "&c&e&lDONATE§f§l#  %s&f&l >>"));

    private String string;

    private StringConstant(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }
}
