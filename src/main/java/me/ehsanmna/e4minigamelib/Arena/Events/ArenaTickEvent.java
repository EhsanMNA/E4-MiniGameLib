package me.ehsanmna.e4minigamelib.Arena.Events;

import me.ehsanmna.e4minigamelib.Arena.Arena;
import me.ehsanmna.e4minigamelib.Arena.TickStats;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArenaTickEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    public ArenaTickEvent(int tickRemaining, TickStats stats, Arena arena){
        this.arena = arena;
        this.tickRemaining = tickRemaining;
        this.stats = stats;
    }

    Arena arena;
    int tickRemaining;
    TickStats stats;
    boolean cancel = false;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
