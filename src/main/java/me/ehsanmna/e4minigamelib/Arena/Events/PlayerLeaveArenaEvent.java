package me.ehsanmna.e4minigamelib.Arena.Events;

import me.ehsanmna.e4minigamelib.Arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLeaveArenaEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    boolean cancel = false;
    Player player;
    Arena arena;

    public PlayerLeaveArenaEvent(Player player,Arena arena){
        this.player = player;
        this.arena = arena;
    }

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

    public Player getPlayer(){
        return player;
    }
    public Arena getArena(){
        return arena;
    }

}
