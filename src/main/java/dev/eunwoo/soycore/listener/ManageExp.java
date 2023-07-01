package dev.eunwoo.soycore.listener;

import dev.eunwoo.soycore.SoyCore;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ManageExp implements Listener {
    SoyCore plugin;

    public ManageExp(SoyCore plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) throws IOException {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        File file = new File(this.plugin.getDataFolder(), "users/" + player.getUniqueId() + ".yml");
        FileConfiguration userData = YamlConfiguration.loadConfiguration(file);
        FileConfiguration config = this.plugin.getConfig();

        Material[] harvestTypes = {
                Material.WHEAT,
                Material.BEETROOTS,
                Material.CARROTS
        };

        List<Material> harvestList = Arrays.asList(harvestTypes);

        if (block.getType().toString().contains("ORE")) {
            userData.set("exp.mining", userData.getInt("exp.mining") + config.getInt("expRate.mining"));
        }

        if (harvestList.contains(block.getType())) {
            userData.set("exp.harvesting", userData.getInt("exp.harvesting") + config.getInt("expRate.harvesting"));
        }

        userData.save(file);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) throws IOException {
        LivingEntity entity = event.getEntity();
        Player killer = entity.getKiller();
        if (killer != null) {
            File file = new File(this.plugin.getDataFolder(), "users/" + killer.getUniqueId() + ".yml");
            FileConfiguration userData = YamlConfiguration.loadConfiguration(file);
            FileConfiguration config = this.plugin.getConfig();

            userData.set("exp.hunting", userData.getInt("exp.hunting") + config.getInt("expRate.hunting"));

            userData.save(file);
        }
    }
}
