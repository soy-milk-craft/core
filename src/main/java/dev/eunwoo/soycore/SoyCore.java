package dev.eunwoo.soycore;

import dev.eunwoo.soycore.listener.CreateUserData;
import dev.eunwoo.soycore.listener.ManageExp;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;

import java.io.File;

public final class SoyCore extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Soy Milk Craft Core loaded!");

        if (!getDataFolder().exists()) getDataFolder().mkdir();
        File userDataDir = new File(getDataFolder() + "/users");
        if (!userDataDir.exists()) userDataDir.mkdir();

        FileConfiguration config = this.getConfig();
        config.addDefault("expRate.hunting", 0);
        config.addDefault("expRate.harvesting", 0);
        config.addDefault("expRate.mining", 0);
        config.addDefault("defaultMoney", 0);
        config.options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new CreateUserData(this), this);
        getServer().getPluginManager().registerEvents(new ManageExp(this), this);

        if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
            Skript.registerAddon(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
