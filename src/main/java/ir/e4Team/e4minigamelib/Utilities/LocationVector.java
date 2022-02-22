package ir.e4Team.e4minigamelib.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationVector {

    public LocationVector(double x,double y,double z){
        this.x = x; this.y = y; this.z = z;
    }
    public LocationVector(World world,double x,double y,double z){
        this.x = x; this.y = y; this.z = z;this.world = world;
    }
    public LocationVector(double x,double y,double z,double yaw,double pitch){
        this.x = x; this.y = y; this.z = z; this.yaw = yaw; this.pitch = pitch;
    }
    public LocationVector(World world,double x,double y,double z,double yaw,double pitch){
        this.x = x; this.y = y; this.z = z;this.yaw = yaw; this.pitch = pitch; this.world = world;
    }
    public LocationVector(Location location){
        this.world = location.getWorld();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public LocationVector(String str){
        String[] args = str.split(" ");
        if (args.length == 3){
            try {
                float x = Float.parseFloat(args[0]);
                float y = Float.parseFloat(args[1]);
                float z = Float.parseFloat(args[2]);
                this.x = x;
                this.y = y;
                this.z = z;
            }catch (NumberFormatException error){
                error.printStackTrace();
            }
        }else if (args.length == 4){
            try {
                World world = Bukkit.getWorld(args[0]);
                float x = Float.parseFloat(args[1]);
                float y = Float.parseFloat(args[2]);
                float z = Float.parseFloat(args[3]);
                this.world = world;
                this.x = x;
                this.y = y;
                this.z = z;
            }catch (NumberFormatException error){
                error.printStackTrace();
            }
        }else if (args.length == 5){
            try {
                float x = Float.parseFloat(args[0]);
                float y = Float.parseFloat(args[1]);
                float z = Float.parseFloat(args[2]);
                float yaw = Float.parseFloat(args[3]);
                float pitch = Float.parseFloat(args[4]);
                this.x = x;
                this.y = y;
                this.z = z;
                this.yaw = yaw;
                this.pitch = pitch;
            }catch (NumberFormatException error){
                error.printStackTrace();
            }
        }else if (args.length == 6){
            try {
                World world = Bukkit.getWorld(args[0]);
                float x = Float.parseFloat(args[1]);
                float y = Float.parseFloat(args[2]);
                float z = Float.parseFloat(args[3]);
                float yaw = Float.parseFloat(args[4]);
                float pitch = Float.parseFloat(args[5]);
                this.world = world;
                this.x = x;
                this.y = y;
                this.z = z;
                this.yaw = yaw;
                this.pitch = pitch;
            }catch (NumberFormatException error){
                error.printStackTrace();
            }
        }
    }

    private World world;
    private double x = 0;
    private double y = 0;
    private double z = 0;
    private double yaw = 0;
    private double pitch = 0;

    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public void setZ(double z){this.z = z;}
    public void setYaw(double yaw){this.yaw = yaw;}
    public void setPitch(double pitch){this.pitch = pitch;}
    public void setWorld(World world){this.world = world;}

    public double getX(){return x;}
    public double getY(){return y;}
    public double getZ(){return z;}
    public double getYaw(){return yaw;}
    public double getPitch(){return pitch;}
    public World getWorld(){return world;}

    public int getIX(){return (int)x;}
    public int getIY(){return (int)y;}
    public int getIZ(){return (int)z;}
    public int getIYaw(){return (int)yaw;}
    public int getIPitch(){return (int)pitch;}

    public String toString(){
        if (world == null){
            if (yaw == 0 || pitch == 0){
                return x + " " + y +" "+ z ;
            }else {
                return x + " " + y +" "+ z + " "+ yaw + " "+ pitch;
            }
        }
        else {
            if (yaw == 0 || pitch == 0){
                return world.getName() + " " + x + " " + y +" "+ z ;
            }else {
                return world.getName() + " " + x + " " + y +" "+ z + " "+ yaw + " "+ pitch;
            }
        }
    }

    public boolean equal(LocationVector location){
        if (location.getWorld() == null){
            if (location.yaw == 0 || location.pitch == 0)
                return (location.getX() == x && location.getY() == y && location.getZ() == z);
            else
                return location.getX() == x && location.getY() == y && location.getZ() == z
                        && location.yaw == yaw && location.pitch == pitch;
        }else {
            if (location.yaw == 0 || location.pitch == 0)
                return (world.getName().equals(world.getName()) && location.getX() == x && location.getY() == y && location.getZ() == z);
            else
                return world.getName().equals(world.getName()) && location.getX() == x && location.getY() == y
                        && location.getZ() == z && location.yaw == yaw && location.pitch == pitch;
        }
    }

    public boolean equalIgnoreFloat(LocationVector location){
        if (location.getWorld() == null)
            return (location.getIX() == (int)x && location.getIY() == (int)y && location.getIZ() == (int)z);
        else
            return (world.getName().equals(world.getName()) && location.getIX() == (int)x && location.getIY() == (int)y && location.getIZ() == (int)z);

    }

    public Location getAsLocation(){
        if (world != null){
            if (yaw != 0 && pitch != 0){
                return new Location(world,x,y,z,(float)yaw,(float)pitch);
            }else return new Location(world,x,y,z);
        }else return new Location(null,x,y,z);
    }

    public LocationVector getAsRond(){
        return new LocationVector(world,getIX(),getIY(),getIZ());
    }

}
