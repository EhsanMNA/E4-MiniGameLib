package me.ehsanmna.e4minigamelib.Arena;

import me.ehsanmna.e4minigamelib.Exceptions.ArenaNotFoundException;
import me.ehsanmna.e4minigamelib.Team.Team;
import me.ehsanmna.e4minigamelib.Utilities.Colors;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArenaManager {

    static Map<String, ArrayList<Arena>> arenas = new HashMap<>();

    static Map<String,Arena> playingInArena = new HashMap<>();

    static Map<String,Arena> spectatingInArena = new HashMap<>();

    JavaPlugin plugin;


    public ArenaManager(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public ArrayList<Arena> getArenas(){
        return arenas.get(plugin.getName());
    }

    public Team createTeam(Arena arena, String name, Colors color){
        Team team = new Team(color,name);
        arena.getTeams().add(team);
        return team;
    }

    public Arena createArena(String name,ArenaStats stats){
        Arena arena = new Arena(name,stats);
        if (arenas.containsKey(plugin.getName())){
            arena.setId(arenas.size());
            arenas.get(plugin.getName()).add(arena);
        }else {
            ArrayList<Arena> list = new ArrayList<>();
            arena.setId(0);
            list.add(arena);
            arenas.put(plugin.getName(),list);
        }
        return arena;
    }

    public void removeArena(String name) throws ArenaNotFoundException {
        arenas.get(plugin.getName()).forEach(arena -> {
            if (arena.getName().equalsIgnoreCase(name)){
                arenas.get(plugin.getName()).remove(arena);
                return;
            }
        });
        throw new ArenaNotFoundException("Could not find the " + name);
    }

    public Arena getArena(String name)throws ArenaNotFoundException{
        for (Arena arena : arenas.get(plugin.getName())){
            if (arena.getName().equalsIgnoreCase(name)){
                return arena;
            }
        }
        throw new ArenaNotFoundException("Could not find the " + name);
    }

    public static ArrayList<Arena> getArenas(JavaPlugin plugin){
        return arenas.get(plugin.getName());
    }

    public static Arena getPlayingArena(Player player){
        return playingInArena.getOrDefault(player.getName(), null);
    }

    public static Arena getSpectatingArena(Player player){
        return spectatingInArena.getOrDefault(player.getName(),null);
    }

    public static boolean isSpectating(Player player){
        return spectatingInArena.containsKey(player.getName());
    }

    public static boolean isPlaying(Player player){
        return playingInArena.containsKey(player.getName());
    }

    public static Arena getArenaByName(String name,JavaPlugin plugin){
        for (Arena arena : arenas.get(plugin.getName())){
            if (arena.getName().equalsIgnoreCase(name)) return arena;
        }
        return null;
    }

    public static Arena getArenaOfPlayer(Player player){
        if (isPlaying(player)) return getPlayingArena(player);
        else if (isSpectating(player)) return getSpectatingArena(player);
        else return null;
    }

    public static Team getPlayingTeam(Player player,Arena arena){
        if (isPlaying(player)){
            for (Team team : arena.getTeams()){
                if (team.getPlayers().contains(player.getName())) return team;
            }
        }
        return null;
    }



}
