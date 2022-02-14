package ir.e4Team.e4minigamelib.Team;

import ir.e4Team.e4minigamelib.Utilities.Colors;
import ir.e4Team.e4minigamelib.Utilities.LocationVector;

import java.util.Set;

public class Team {

    public Team(Colors color,String name){
        this.color = color;
        this.name = name;
    }

    Colors color;
    String name;
    boolean eliminated = true;
    int maxPlayer = 1;
    Set<String> players;
    TeamStatus stat = TeamStatus.EMPTY;
    LocationVector spawnPoint;
    LocationVector firstSpawnPoint;

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public void setPlayers(Set<String> players) {
        this.players = players;
    }

    public TeamStatus getStat() {
        return stat;
    }

    public void setStat(TeamStatus stat) {
        this.stat = stat;
    }

    public LocationVector getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(LocationVector spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public LocationVector getFirstSpawnPoint() {
        return firstSpawnPoint;
    }

    public void setFirstSpawnPoint(LocationVector firstSpawnPoint) {
        this.firstSpawnPoint = firstSpawnPoint;
    }

    public void addPlayer(String player){
        players.add(player);
    }

    public void removePlayer(String player){players.remove(player);}

    public boolean equalsTo(Team team){
        return team.getName().equalsIgnoreCase(team.getName());
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }
}
