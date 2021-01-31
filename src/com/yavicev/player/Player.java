package com.yavicev.player;

public interface Player {
    String getName();

    String getGroupName();

    boolean isStaff();

    int getPriority();

    String getGroupPrefix();

    boolean isStaffChatToggle();

    void setStaffChatToggle(boolean true);
}
