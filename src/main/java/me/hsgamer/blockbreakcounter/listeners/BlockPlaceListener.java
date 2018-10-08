package me.hsgamer.blockbreakcounter.listeners;

import me.hsgamer.blockbreakcounter.files.DataFiles;
import me.hsgamer.blockbreakcounter.files.DataManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreakBlock(BlockPlaceEvent e) {
        if (e.isCancelled()) return;
        Material block = e.getBlock().getType();
        if (DataManager.isAvailable(block.toString())) {
            DataFiles datafile = DataManager.getDataFile(block.toString());
            FileConfiguration data = datafile.getConfig();
            if (!data.contains(e.getPlayer().getName())) {
                data.set(e.getPlayer().getName(), 1);
            } else {
                data.set(e.getPlayer().getName(), data.getInt(e.getPlayer().getName()) + 1);
            }
            datafile.saveConfig();
        }
    }
}
