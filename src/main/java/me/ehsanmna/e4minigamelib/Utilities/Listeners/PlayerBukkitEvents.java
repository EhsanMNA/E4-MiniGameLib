package me.ehsanmna.e4minigamelib.Utilities.Listeners;

import me.ehsanmna.e4minigamelib.Arena.Arena;
import me.ehsanmna.e4minigamelib.Arena.ArenaManager;
import me.ehsanmna.e4minigamelib.Team.Team;
import me.ehsanmna.e4minigamelib.Utilities.LocationVector;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashSet;
import java.util.Set;

public class PlayerBukkitEvents implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            Player damager = (Player) e.getDamager();
            Player damaged = (Player) e.getEntity();
            if (ArenaManager.isPlaying(damager) && ArenaManager.isPlaying(damaged)){
                if (ArenaManager.getArenaOfPlayer(damager).getStats().isDisablePvp()) {e.setCancelled(true); return; }
                if (ArenaManager.getPlayingTeam(damager,ArenaManager.getPlayingArena(damager)).equals(
                    ArenaManager.getPlayingTeam(damaged,ArenaManager.getArenaOfPlayer(damaged)))){

                    if (ArenaManager.getArenaOfPlayer(damager).getStats().isDisableTeamShot()) e.setCancelled(true);

                }
            }
        }
    }

    @EventHandler
    public void onPlaceBlockEvent(BlockPlaceEvent e){
        Player player = e.getPlayer();
        if (ArenaManager.isPlaying(player)){
            Arena arena = ArenaManager.getArenaOfPlayer(player);
            if (!arena.getStats().canPlaceBlock()){
                e.setCancelled(true);
                return;
            }
            Team team = ArenaManager.getPlayingTeam(player,arena);
            for (Team tm : arena.getStats().getTeamBases().keySet()){
                if (tm == team) continue;
                if(tm.getSpawnPoint().equalIgnoreFloat(new LocationVector(e.getBlockPlaced().getLocation()))) e.setCancelled(true);
                LocationVector teamLoc = tm.getSpawnPoint();
                LocationVector blockLoc = new LocationVector(e.getBlockPlaced().getLocation()).getAsRond();
                int xM = Math.max(teamLoc.getIX(), blockLoc.getIX());
                int yM = Math.max(teamLoc.getIY(), blockLoc.getIY());
                int zM = Math.max(teamLoc.getIZ(), blockLoc.getIZ());
                int xL = Math.min(teamLoc.getIX(), blockLoc.getIX());
                int yL = Math.min(teamLoc.getIY(), blockLoc.getIY());
                int zL = Math.min(teamLoc.getIZ(), blockLoc.getIZ());
                if (yM - yL <= 3)
                    if (xM - xL <= 3)
                        if (zM - zL <= 3) e.setCancelled(true);
            }
            if(!e.isCancelled()) arena.getStats().getPlacedBlocks().add(new LocationVector(e.getBlockPlaced().getLocation()).getAsRond());
                /*
                if (arena.getStats().getPlacedBlocks().contains(player.getUniqueId())){
                    if (arena.getStats().getPlacedBlocks().get(player.getUniqueId()).isEmpty()){
                        Set<LocationVector> set = new HashSet<>();
                        set.add(new LocationVector(e.getBlockPlaced().getLocation()));
                        arena.getStats().getPlacedBlocks().put(player.getUniqueId(),set);
                    }else {
                        arena.getStats().getPlacedBlocks().get(player.getUniqueId()).add(new LocationVector(e.getBlockPlaced().getLocation()));
                    }
                }else {
                    Set<LocationVector> set = new HashSet<>();
                    set.add(new LocationVector(e.getBlockPlaced().getLocation()));
                    arena.getStats().getPlacedBlocks().put(player.getUniqueId(),set);
                }*/
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if (ArenaManager.isPlaying(player)){
            Arena arena = ArenaManager.getArenaOfPlayer(player);
            if (!arena.getStats().canBreakDefaultBlocks()){
                if (!arena.getStats().canBreakPlacedBlocks()) {e.setCancelled(true); return;}
                else {
                    LocationVector loc = new LocationVector(e.getBlock().getLocation()).getAsRond();
                    if (!arena.getStats().getPlacedBlocks().contains(loc)) {e.setCancelled(true); return;}
                }
            }
            arena.getStats().getPlacedBlocks().remove(new LocationVector(e.getBlock().getLocation()).getAsRond());
        }
    }

    @EventHandler
    public void onDeath(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();
            if (ArenaManager.isPlaying(player)){
                Arena arena = ArenaManager.getPlayingArena(player);
                if (e.getDamage() > player.getHealth()){
                    e.setCancelled(true);
                    player.teleport(ArenaManager.getPlayingTeam(player,arena).getSpawnPoint().getAsLocation());
                }
            }
        }
    }

}
