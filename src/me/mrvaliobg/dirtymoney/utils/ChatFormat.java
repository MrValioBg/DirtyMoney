package me.mrvaliobg.dirtymoney.utils;

import org.bukkit.ChatColor;

public final class ChatFormat {

    private ChatFormat() {
        //na baba ti putkata
    }

    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
