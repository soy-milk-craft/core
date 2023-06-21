package dev.eunwoo.soycore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoyCore extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Soy Milk Craft Core loaded!");
        if (!getDataFolder().exists()) getDataFolder().mkdir();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
