package ir.e4Team.e4minigamelib.Utilities.Listeners;

import ir.e4Team.e4minigamelib.Arena.Arena;
import ir.e4Team.e4minigamelib.Arena.ArenaManager;
import ir.e4Team.e4minigamelib.Arena.Events.PlayerReSpawnGameEvent;
import ir.e4Team.e4minigamelib.Team.Team;
import ir.e4Team.e4minigamelib.Utilities.LocationVector;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerBukkitEvents implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            Player damager = (Player) e.getDamager();
            Player damaged = (Player) e.getEntity();
            if (ArenaManager.isPlaying(damager) && ArenaManager.isPlaying(damaged)){
                Arena arena = ArenaManager.getArenaOfPlayer(damager);
                if (arena == null) return;
                if (arena.getStats().isDisablePvp()) {e.setCancelled(true); return; }
                Team team = ArenaManager.getPlayingTeam(damager,arena);
                if (ArenaManager.getPlayingTeam(damager,arena).equals(
                    ArenaManager.getPlayingTeam(damaged,ArenaManager.getArenaOfPlayer(damaged)))){

                    if (ArenaManager.getArenaOfPlayer(damager).getStats().isDisableTeamShot()) e.setCancelled(true);

                }
            }
        }
    }

    @EventHandler
    public void onPlaceBlockEvent(BlockPlaceEvent e){
        if(e.isCancelled()) return;
        Player player = e.getPlayer();
        if (ArenaManager.isPlaying(player)){
            Arena arena = ArenaManager.getArenaOfPlayer(player);
            if(arena == null) return;
            if (!arena.getStats().canPlaceBlock()){
                e.setCancelled(true);
                return;
            }
            Team team = ArenaManager.getPlayingTeam(player,arena);
            for (Team tm : arena.getTeams()){
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
                if (yM - yL <= 2 && xM - xL <= 2 && zM - zL <= 2) e.setCancelled(true);
            }
            if(!e.isCancelled()) arena.getStats().getPlacedBlocks().add(new LocationVector(e.getBlockPlaced().getLocation()).getAsRond());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if (ArenaManager.isPlaying(player)){
            Arena arena = ArenaManager.getArenaOfPlayer(player);
            if (arena == null) return;
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
                Team team = ArenaManager.getPlayingTeam(player,arena);
                if (arena == null || team == null) return;
                if (e.getDamage() > player.getHealth()){
                    e.setCancelled(true);
                    player.teleport(team.getSpawnPoint().getAsLocation());
                    PlayerReSpawnGameEvent event = new PlayerReSpawnGameEvent(arena,player);
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }
    }

}
