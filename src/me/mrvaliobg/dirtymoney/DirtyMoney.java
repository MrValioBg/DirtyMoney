package me.mrvaliobg.dirtymoney;

import me.ablax.decode.AdvancedPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class DirtyMoney extends JavaPlugin {


    public static Plugin p;

    @Override
    public void onEnable() {
        p = this;
        final Plugin advancedPluginApi = Bukkit.getPluginManager().getPlugin("AdvancedPluginApi");
        if (advancedPluginApi != null) {
            final AdvancedPlugin pluginApi = (AdvancedPlugin) advancedPluginApi;
            pluginApi.registerPlugin(this);


        }
    }
}

