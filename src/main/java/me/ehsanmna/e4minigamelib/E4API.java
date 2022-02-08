package me.ehsanmna.e4minigamelib;

import me.ehsanmna.e4minigamelib.Arena.ArenaManager;
import me.ehsanmna.e4minigamelib.Items.IVersion;
import me.ehsanmna.e4minigamelib.Team.TeamManager;
import me.ehsanmna.e4minigamelib.Utilities.LocationVector;
import me.ehsanmna.e4minigamelib.Utilities.NMS.NmsUtils;
import me.ehsanmna.e4minigamelib.Utilities.NMS.v1_8.NmsVersion_1_8;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class E4API {

    IVersion version;

    public static Set<JavaPlugin> tools = new HashSet<>();

    static HashMap<String,LocationVector> lobbySpawns = new HashMap<>();

    JavaPlugin plugin;

    public E4API(JavaPlugin plugin){
        String v = Bukkit.getBukkitVersion().split("-")[0];
        switch(v)
        {
            case "1.8":
                version = IVersion.v1_8;
                break;
            case "1.12":
                version = IVersion.v1_12;
                break;
            case "1.16":
                version = IVersion.v1_16;
                break;
            case "1.17":
            case "1.18":
                version = IVersion.v1_17;
                break;
        }
        tools.add(plugin);
        this.plugin = plugin;
    }

    public ArenaManager getArenaManager(){
        return new ArenaManager(plugin);
    }

    public TeamManager getTeamManager(){
        return new TeamManager();
    }

    public LocationVector getLobbySpawnPoint(){
        return lobbySpawns.get(plugin.getName());
    }

    public NmsUtils getNmsUtils(){
        if (version == IVersion.v1_8) return new NmsVersion_1_8();
        if (version == IVersion.v1_12) return new NmsVersion_1_8();
        if (version == IVersion.v1_16) return new NmsVersion_1_8();
        if (version == IVersion.v1_17) return new NmsVersion_1_8();
        else return null;
    }


}
