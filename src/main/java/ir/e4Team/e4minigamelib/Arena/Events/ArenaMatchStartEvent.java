package ir.e4Team.e4minigamelib.Arena.Events;

import ir.e4Team.e4minigamelib.Arena.Arena;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArenaMatchStartEvent extends Event implements Cancellable {

    boolean cancel = false;
    Arena arena;
    private static final HandlerList HANDLERS = new HandlerList();

    public ArenaMatchStartEvent(Arena arena){
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

    public Arena getArena(){
        return arena;
    }
}
