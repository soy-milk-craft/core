package dev.eunwoo.soycore.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateUserData implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();

        // TODO: 데이터 폴더를 가져올 좀 더 좋은 방법 찾아보기
        File file = new File("plugins/soy-core/users/" + player.getUniqueId() + ".yml");
        if (!file.exists()) file.createNewFile();
        FileConfiguration userData = YamlConfiguration.loadConfiguration(file);

        // TODO: 좀 더 깔끔하게... (Map 등 사용?)
        if (!userData.contains("exp.hunting")) userData.set("exp.hunting", 0);
        if (!userData.contains("exp.harvesting")) userData.set("exp.harvesting", 0);
        if (!userData.contains("exp.mining")) userData.set("exp.mining", 0);

        userData.save(file);

    }
}
