package dev.eunwoo.soycore.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class CreateUserData implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // TODO: 데이터 폴더를 가져올 좀 더 좋은 방법 찾아보기
        File file = new File("plugins/soy-core/users");
    }
}
