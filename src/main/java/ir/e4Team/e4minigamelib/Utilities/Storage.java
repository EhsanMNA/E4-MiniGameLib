package ir.e4Team.e4minigamelib.Utilities;

import ir.e4Team.e4minigamelib.E4MiniGameLib;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public final class Storage {

    static File data = new File(E4MiniGameLib.getFilePath(),"Data.yml");
    static YamlConfiguration yamlConfiguration;

    public static void load(){
        yamlConfiguration = YamlConfiguration.loadConfiguration(data);
    }

    public static YamlConfiguration getData(){
        return yamlConfiguration;
    }

    public static void saveData(){

    }


}
