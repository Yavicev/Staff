package com.yavicev.player;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class PlayerUtil {
    private static final Map<String, Player> players = new HashMap();
    private static Connection CONNECTION;

    private PlayerUtil() {
    }

    public static Player getPlayer(String playerName) {
        return (Player)players.get(playerName);
    }

    public static void addPlayer(Player player) {
        players.put(player.getName(), player);
    }

    public static void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/data";

        try {
            CONNECTION = (Connection)DriverManager.getConnection(url, "root", "testyavicev");
            Statement stmt = (Statement)CONNECTION.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS grouped_players (nickname VARCHAR(16) NOT NULL PRIMARY KEY, playerGroup TEXT NOT NULL, playerPrefix TEXT NOT NULL, isStaff TEXT NOT NULL, intPriority INT(16) NOT NULL)");
            stmt.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static void loadOrCreate(String name) {
        try {
            PreparedStatement data = CONNECTION.prepareStatement("SELECT * FROM players WHERE nickname=?");
            data.setString(1, name);
            ResultSet dataSet = data.executeQuery();
            if (dataSet.next()) {
                addPlayer(new CustomPlayer(dataSet.getString(1), dataSet.getString(2), dataSet.getString(3), dataSet.getString(4).equals("true"), dataSet.getInt(5), true));
            } else {
                addPlayer(new CustomPlayer(name, "", "", false, 0, false));
            }
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }
}
