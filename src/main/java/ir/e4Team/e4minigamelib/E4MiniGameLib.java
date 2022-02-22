package ir.e4Team.e4minigamelib;

import ir.e4Team.e4minigamelib.Arena.Arena;
import ir.e4Team.e4minigamelib.Arena.ArenaManager;
import ir.e4Team.e4minigamelib.Arena.ArenaStats;
import ir.e4Team.e4minigamelib.GUI.InventoryEvent;
import ir.e4Team.e4minigamelib.Team.Team;
import ir.e4Team.e4minigamelib.Utilities.LocationVector;
import ir.e4Team.e4minigamelib.Utilities.Region;
import ir.e4Team.e4minigamelib.Utilities.Utils;
import ir.e4Team.e4minigamelib.tasks.ToolsTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class E4MiniGameLib extends JavaPlugin {

    static E4MiniGameLib main;

    int errors = 0;


    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(Utils.color("&a==========================="));
        main = this;
        new ToolsTask(this).implementsApi();
        new InventoryEvent(this).register();
        loadArenas();


        getServer().getConsoleSender().sendMessage(Utils.color("&a==========================="));
        getServer().getConsoleSender().sendMessage(Utils.color("&2E4 MiniGame library has been enabled."));
        if (errors != 0) getServer().getConsoleSender().sendMessage(Utils.color("&e[&6" + errors + "&e] &cErrors has detected!"));
        getServer().getConsoleSender().sendMessage(Utils.color("&2Created by E4-Team"));
        getServer().getConsoleSender().sendMessage(Utils.color("&a==========================="));

    }

    @Override
    public void onDisable() {
        main = null;
    }

    public static E4MiniGameLib getInstance(){
        return main;
    }

    public void loadArenas(){
        try{
            File directory = new File(getDataFolder().getPath() + "\\PluginsData");
            if (directory.listFiles() == null) return;
            for (File pluginFolder : directory.listFiles()){
                File arenas = new File(getDataFolder().getPath() + "\\PluginsData\\" + pluginFolder.getName() + "\\arenas");
                for (File data : arenas.listFiles()){
                    try {
                        YamlConfiguration yml = YamlConfiguration.loadConfiguration(data);
                        Arena arena = new Arena(yml.getString("name"),null);
                        arena.setDisplayName(yml.getString("displayname"));
                        arena.setMaxPlayersPerTeam(yml.getInt("maxPlayerPerTeam"));
                        arena.setWaitingT(yml.getInt("waitingTime"));
                        arena.setWaitingGameMode(GameMode.valueOf(yml.getString("GameModes.waiting")));
                        arena.setGameplayGameMode(GameMode.valueOf(yml.getString("GameModes.game")));
                        ArenaStats stats = new ArenaStats();
                        if (yml.get("stats.waiting") instanceof LocationVector) stats.setWaitingPoint((LocationVector) yml.get("stats.waiting"));
                        if (yml.get("stats.spectating") instanceof LocationVector) stats.setSpectatorPoint((LocationVector) yml.get("stats.spectating"));
                        if (yml.get("stats.spectating") instanceof LocationVector) stats.setSpectatorPoint((LocationVector) yml.get("stats.spectating"));
                        if (yml.get("stats.teamBases") instanceof HashMap) stats.setTeamBases((HashMap<Team, Region>) yml.get("stats.teamBases"));
                        if (yml.get("stats.firstSpawnPoints") instanceof HashMap) stats.setFirstSpawnPoints((HashMap<Team, LocationVector>) yml.get("stats.firstSpawnPoints"));
                        if (yml.get("stats.region") instanceof Region) stats.setRegion((Region) yml.get("stats.region"));
                        stats.setDisableDamage(yml.getBoolean("stats.disableDamage"));
                        stats.setDisablePvp(yml.getBoolean("stats.pvp"));
                        stats.setCanBreakDefaultBlocks(yml.getBoolean("stats.block.breakDefault"));
                        stats.setCanBreakPlacedBlocks(yml.getBoolean("stats.block.breakPlayers"));
                        stats.setCanBreakPlacedBlocks(yml.getBoolean("stats.block.place"));
                        stats.setDisableHunger(yml.getBoolean("stats.disableHunger"));
                        stats.setDisableTeamShot(yml.getBoolean("stats.disableFriendFire"));
                        arena.setStats(stats);
                        ArenaManager.addArena((JavaPlugin) Bukkit.getPluginManager().getPlugin(pluginFolder.getName()),arena);
                        System.out.println("Loaded " + arena.getName() + ".");
                    }catch (Exception error){
                        error.printStackTrace();
                        errors++;
                        System.out.println("Could not load the " + pluginFolder.getName() + " " + data.getName() + ".");
                    }

                }
            }
        }catch (Exception error){
            error.printStackTrace();
            System.out.println("Could not load arenas.");
            errors++;
        }

    }
}
