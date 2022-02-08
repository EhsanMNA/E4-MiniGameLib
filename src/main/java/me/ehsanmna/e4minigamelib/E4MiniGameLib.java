package me.ehsanmna.e4minigamelib;

import me.ehsanmna.e4minigamelib.Utilities.Utils;
import me.ehsanmna.e4minigamelib.tasks.ToolsTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class E4MiniGameLib extends JavaPlugin {

    int errors = 0;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(Utils.color("&a==========================="));
        new ToolsTask(this).implementsApi();



        getServer().getConsoleSender().sendMessage(Utils.color("&a==========================="));
        getServer().getConsoleSender().sendMessage(Utils.color("&2E4 MiniGame library has been enabled."));
        if (errors != 0) getServer().getConsoleSender().sendMessage(Utils.color("&e[&6" + errors + "&e] &cErrors has detected!"));
        getServer().getConsoleSender().sendMessage(Utils.color("&2Created by EhsanMNA"));
        getServer().getConsoleSender().sendMessage(Utils.color("&a==========================="));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getFilePath(){
        return getFilePath();
    }
}
