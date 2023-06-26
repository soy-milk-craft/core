package dev.eunwoo.soycore.listener;

import dev.eunwoo.soycore.SoyCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class CreateUserData implements Listener {
    SoyCore plugin;
    public CreateUserData(SoyCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();

        File file = new File(this.plugin.getDataFolder(), "users/" + player.getUniqueId() + ".yml");
        if (!file.exists()) file.createNewFile();
        FileConfiguration userData = YamlConfiguration.loadConfiguration(file);

        FileConfiguration config = this.plugin.getConfig();

        // TODO: 좀 더 깔끔하게... (Map 등 사용?)
        if (!userData.contains("exp.hunting")) userData.set("exp.hunting", 0);
        if (!userData.contains("exp.harvesting")) userData.set("exp.harvesting", 0);
        if (!userData.contains("exp.mining")) userData.set("exp.mining", 0);
        if (!userData.contains("money")) userData.set("money", config.getInt("defaultMoney"));

        userData.save(file);

    }
}
