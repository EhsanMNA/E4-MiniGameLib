package ir.e4Team.e4minigamelib.Arena;

import ir.e4Team.e4minigamelib.E4MiniGameLib;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ArenaStorage {

    File file;
    FileConfiguration yml;

    Arena arena;

    public ArenaStorage(Arena arena) {
        this.arena = arena;
        this.file = new File(E4MiniGameLib.getInstance().getDataFolder().getPath()
                + "\\PluginsData\\" + arena.getPlugin().getName() + "\\arenas\\" + arena.getName() + ".yml");
        load();
    }

    public void load() {
        yml = YamlConfiguration.loadConfiguration(file);
    }



}
