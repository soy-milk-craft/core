package dev.eunwoo.soycore;

import dev.eunwoo.soycore.listener.CreateUserData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SoyCore extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Soy Milk Craft Core loaded!");
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        File userDataDir = new File(getDataFolder() + "/users");
        if (!userDataDir.exists()) userDataDir.mkdir();
        getServer().getPluginManager().registerEvents(new CreateUserData(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
