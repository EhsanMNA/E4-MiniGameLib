package me.ehsanmna.e4minigamelib.tasks;

import me.ehsanmna.e4minigamelib.E4API;
import me.ehsanmna.e4minigamelib.E4MiniGameLib;
import me.ehsanmna.e4minigamelib.Utilities.Utils;
import org.bukkit.scheduler.BukkitRunnable;

public class ToolsTask {

    E4MiniGameLib plugin;

    public ToolsTask(E4MiniGameLib plugin){
        this.plugin = plugin;
    }

    public void implementsApi(){
        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println(Utils.color("&e[&6" + E4API.tools.size() + "&e] &aAre using E4 mini game library !"));
            }
        }.runTaskLaterAsynchronously(plugin, 20* 60 * 5);
    }


}
