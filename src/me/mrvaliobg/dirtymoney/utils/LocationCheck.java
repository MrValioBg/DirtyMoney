package me.mrvaliobg.dirtymoney.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public final class LocationCheck {

    private static final Location constantLocation;

    static {
        constantLocation = new Location(Bukkit.getWorld("world"), 46, 80, -114);
    }

    private LocationCheck() {
        //Util
    }

    public static boolean isIn(Location loc) {
        return loc.distance(constantLocation) <= 5;
    }

    public static boolean isLaundry(Location loc) {
        return loc.equals(constantLocation);
    }


}
