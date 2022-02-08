package ir.e4Team.e4minigamelib.Arena.Events;

import ir.e4Team.e4minigamelib.Team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TeamSelectEvent extends Event implements Cancellable {

    public TeamSelectEvent(Team lastTeam,Team newTeam,Player player){
        this.team = newTeam;
        this.lastTeam = lastTeam;
        this.player = player;
    }

    boolean cancel = false;
    Team team;
    Player player;
    Team lastTeam;

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getLastTeam() {
        return lastTeam;
    }

    public void setLastTeam(Team lastTeam) {
        this.lastTeam = lastTeam;
    }


}
