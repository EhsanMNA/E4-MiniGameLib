package ir.e4Team.e4minigamelib.Arena;


import ir.e4Team.e4minigamelib.Arena.Events.*;
import ir.e4Team.e4minigamelib.E4API;
import ir.e4Team.e4minigamelib.Exceptions.ArenaSaveException;
import ir.e4Team.e4minigamelib.Utilities.Utils;
import ir.e4Team.e4minigamelib.Arena.ScoreBoard.ArenaBoard;
import ir.e4Team.e4minigamelib.Team.Team;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class Arena {

    public Arena(String nam,ArenaStats stats){
        this.name = nam;
        this.stats = stats;
    }

    JavaPlugin plugin;
    ArenaStorage storage;
    String name;
    String displayName;
    int maxPlayersPerTeam;
    int id;
    ArenaStatus stat = ArenaStatus.Disable;
    Set<String> players;
    List<Team> teams = new ArrayList<>();
    ArenaStats stats;
    ArenaBoard boards;
    GameMode waitingGameMode = GameMode.ADVENTURE;
    GameMode gameplayGameMode = GameMode.SURVIVAL;
    BukkitTask task;
    int waitingT = 30;

    public int getMaxPlayersPerTeam() {
        return maxPlayersPerTeam;
    }

    public void setMaxPlayersPerTeam(int maxPlayersPerTeam) {
        this.maxPlayersPerTeam = maxPlayersPerTeam;
    }

    public ArenaBoard getBoards() {
        return boards;
    }

    public void setBoards(ArenaBoard boards) {
        this.boards = boards;
    }

    public GameMode getWaitingGameMode() {
        return waitingGameMode;
    }

    public void setWaitingGameMode(GameMode waitingGameMode) {
        this.waitingGameMode = waitingGameMode;
    }

    public GameMode getGameplayGameMode() {
        return gameplayGameMode;
    }

    public void setGameplayGameMode(GameMode gameplayGameMode) {
        this.gameplayGameMode = gameplayGameMode;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public ArenaStorage getStorage() {
        return storage;
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArenaStatus getStat() {
        return stat;
    }

    public void setStat(ArenaStatus stat) {
        this.stat = stat;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public void setPlayers(Set<String> players) {
        this.players = players;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public ArenaStats getStats() {
        return stats;
    }

    public void setStats(ArenaStats stats) {
        this.stats = stats;
    }

    public void saveArena() throws ArenaSaveException {
        if (name == null || stats == null || id == 0 || teams == null) throw new ArenaSaveException("Could not save the arena name=" + name + " arenaStats=" + stats.toString());
        FileConfiguration yml = storage.yml;
        yml.set("name",name);
        yml.set("displayname",getDisplayName());
        yml.set("maxPlayerPerTeam",getMaxPlayersPerTeam());
        yml.set("waitingTime",waitingT);
        yml.set("GameModes.waiting",waitingGameMode);
        yml.set("GameModes.game",gameplayGameMode);
        stat = ArenaStatus.Enable;
    }

    public void leaveArena(Player player){
        PlayerLeaveArenaEvent event = new PlayerLeaveArenaEvent(player,this);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()){
            E4API api = new E4API(plugin);
            player.teleport(api.getLobbySpawnPoint().getAsLocation());
            players.remove(player.getName());
            ArenaManager.getPlayingTeam(player,this).removePlayer(player.getName());

        }
    }

    public void joinArena(Player player){
        PlayerJoinArenaEvent event = new PlayerJoinArenaEvent(player,this);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()){
            if (!(maxPlayersPerTeam * teams.size() == players.size())){
                players.add(player.getName());
                player.teleport(stats.getWaitingPoint().getAsLocation());
                player.setScoreboard(boards.getWaitingBoard());
                player.setGameMode(waitingGameMode);
            }
        }
    }

    public void startWaitingTimer(){

        task = new BukkitRunnable() {
            @Override
            public void run() {
                startMatch();
                ArenaTickEvent event = new ArenaTickEvent(waitingT,TickStats.Waiting,ArenaManager.getArenaByName(name,plugin));
                Bukkit.getPluginManager().callEvent(event);
                if (!event.isCancelled()) task.cancel();

                if (waitingT <= 5){
                    E4API api = new E4API(plugin);
                    for (String p : players){
                        Player player = Bukkit.getPlayer(p);
                        if (player == null) continue;
                        api.getNmsUtils().sendTitle(player, Utils.color("&d" + waitingT),20);
                    }
                }

                if (waitingT == 0) task.cancel();

                waitingT--;
            }
        }.runTaskLater(plugin, 20);
    }

    public void startMatch(){
        ArenaMatchStartEvent event = new ArenaMatchStartEvent(this);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()){
            Set<String> rp = players;
            Set<String> remaining = new HashSet<>();

            while (!rp.isEmpty()){
                Random r = new Random();
                for (String p : rp){
                    int x = r.nextInt(teams.size() - 1) + 1;
                    Team team = teams.get(x);
                    if (team == null) {
                        remaining.add(p);
                        continue;
                    }
                    if (team.getPlayers().size() == maxPlayersPerTeam) {
                        remaining.add(p);
                        continue;
                    }
                    TeamSelectEvent e = new TeamSelectEvent(null,team,Bukkit.getPlayer(p));
                    Bukkit.getPluginManager().callEvent(e);
                    if(e.isCancelled()){
                        remaining.add(p);
                        continue;
                    }
                    team.addPlayer(p);
                    rp.remove(p);
                }
            }

            while (!remaining.isEmpty()){
                for (Team team : teams){
                    for (String p : remaining){
                        if (team == null) continue;
                        if (team.getPlayers().size() == maxPlayersPerTeam) continue;
                        TeamSelectEvent e = new TeamSelectEvent(null,team,Bukkit.getPlayer(p));
                        Bukkit.getPluginManager().callEvent(e);
                        if(e.isCancelled()){
                            remaining.add(p);
                            continue;
                        }
                        team.addPlayer(p);
                        remaining.remove(p);
                    }
                }
            }

            for (Team team : teams){
                for (String p : team.getPlayers()){
                    Player player = Bukkit.getPlayer(p);
                    if (player == null) continue;
                    player.teleport(team.getFirstSpawnPoint().getAsLocation());
                    player.setScoreboard(boards.getTeamBoards().get(ArenaManager.getPlayingTeam(Bukkit.getPlayer(p),this)));
                }
            }
        }

    }

}
