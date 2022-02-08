package ir.e4Team.e4minigamelib.Utilities;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class Region {

    LocationVector firstPos;
    LocationVector secondPos;
    World world;

    public LocationVector getFirstPos() {
        return firstPos;
    }

    public void setFirstPos(LocationVector firstPos) {
        this.firstPos = firstPos;
    }

    public LocationVector getSecondPos() {
        return secondPos;
    }

    public void setSecondPos(LocationVector secondPos) {
        this.secondPos = secondPos;
    }

    public boolean isOnRegion(@NotNull Entity entity){
        if (entity.getLocation().getWorld().getName().equals(world.getName())){

            //checker x ha

            if (entity.getLocation().getBlockX() < firstPos.getIX()){
                if (entity.getLocation().getBlockX() < secondPos.getIX()) return false;
                // dar geyre in soorat dar vage x e location aval bozorg tare hast va x location dovom kochik tar yani x esh doroste !!!
                // in yani x toshe !!!!
            }
            if (entity.getLocation().getBlockX() > firstPos.getIX()){
                // dar in gesmat dar vage x e location aval kochik tar az location player hast va x location dovom age kochik tar bashe ke player nist
                if (entity.getLocation().getBlockX() > secondPos.getIX()) return false;
            }

            // checker z ha

            if (entity.getLocation().getBlockZ() < firstPos.getIZ()){
                if (entity.getLocation().getBlockZ() < secondPos.getIZ()) return false;
            }
            if (entity.getLocation().getBlockZ() > firstPos.getIZ()){
                if (entity.getLocation().getBlockZ() > secondPos.getIZ()) return false;
            }

            // checker y ha

            if (entity.getLocation().getBlockY() < firstPos.getIY()){
                if (entity.getLocation().getBlockZ() < secondPos.getIZ()) return false;
            }
            if (entity.getLocation().getBlockY() > firstPos.getIY()){
                if (entity.getLocation().getBlockY() > secondPos.getIY()) return false;
            }

            return true;

        }
        return false;
    }

    public boolean isOnRegion(@NotNull Block block){
        if (block.getLocation().getWorld().getName().equals(world.getName())){

            //checker x ha

            if (block.getLocation().getBlockX() < firstPos.getIX()){
                if (block.getLocation().getBlockX() < secondPos.getIX()) return false;
                // dar geyre in soorat dar vage x e location aval bozorg tare hast va x location dovom kochik tar yani x esh doroste !!!
                // in yani x toshe !!!!
            }
            if (block.getLocation().getBlockX() > firstPos.getIX()){
                // dar in gesmat dar vage x e location aval kochik tar az location player hast va x location dovom age kochik tar bashe ke player nist
                if (block.getLocation().getBlockX() > secondPos.getIX()) return false;
            }

            // checker z ha

            if (block.getLocation().getBlockZ() < firstPos.getIZ()){
                if (block.getLocation().getBlockZ() < secondPos.getIZ()) return false;
            }
            if (block.getLocation().getBlockZ() > firstPos.getIZ()){
                if (block.getLocation().getBlockZ() > secondPos.getIZ()) return false;
            }

            // checker y ha

            if (block.getLocation().getBlockY() < firstPos.getIY()){
                if (block.getLocation().getBlockZ() < secondPos.getIZ()) return false;
            }
            if (block.getLocation().getBlockY() > firstPos.getIY()){
                if (block.getLocation().getBlockY() > secondPos.getIY()) return false;
            }

            return true;

        }
        return false;
    }

    public String toString(){
        return "World => " + world.getName() + " First position => " + firstPos.toString() + " Second position => " + secondPos.toString();
    }


}
