package ir.e4Team.e4minigamelib.Arena.Events;

import ir.e4Team.e4minigamelib.Arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerReSpawnGameEvent extends Event {


    Arena arena;
    Player player;

    public PlayerReSpawnGameEvent(Arena arena,Player player){
        this.arena = arena;
        this.player = player;
    }


    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
