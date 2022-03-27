package de.fynn.sco.permissionapi;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PermissionAPIPlugin extends JavaPlugin {

    private static PermissionAPIPlugin plugin;

    @Override
    public void onEnable(){
        instance = this;
    }

    @Override
    public void onDisable() {

    }

    public static PermissionAPIPlugin getPlugin() {
        return plugin;
    }

}
