package ir.e4Team.e4minigamelib.Arena;

import ir.e4Team.e4minigamelib.Team.Team;
import ir.e4Team.e4minigamelib.Utilities.LocationVector;
import ir.e4Team.e4minigamelib.Utilities.Region;

import java.util.HashMap;
import java.util.HashSet;

public class ArenaStats {


    HashSet<LocationVector> placedBlocks = new HashSet<>();
    LocationVector waitingPoint;
    LocationVector spectatorPoint;
    HashMap<Team,LocationVector> spawnPoints = new HashMap<>();
    HashMap<Team,LocationVector> firstSpawnPoints = new HashMap<>();
    HashMap<Team,Region> teamBases = new HashMap<>();
    Region region;


    boolean canPlaceBlock = true;
    boolean canBreakDefaultBlocks = false;
    boolean canBreakPlacedBlocks = true;
    boolean disableHunger = true;
    boolean disableDamage = false;
    boolean disablePvp = false;
    boolean disableTeamShot = true;

    public boolean isDisablePvp() {
        return disablePvp;
    }

    public void setDisablePvp(boolean disablePvp) {
        this.disablePvp = disablePvp;
    }

    public boolean isDisableTeamShot() {
        return disableTeamShot;
    }

    public void setDisableTeamShot(boolean disableTeamShot) {
        this.disableTeamShot = disableTeamShot;
    }

    public LocationVector getWaitingPoint() {
        return waitingPoint;
    }

    public void setWaitingPoint(LocationVector waitingPoint) {
        this.waitingPoint = waitingPoint;
    }

    public LocationVector getSpectatorPoint() {
        return spectatorPoint;
    }

    public void setSpectatorPoint(LocationVector spectatorPoint) {
        this.spectatorPoint = spectatorPoint;
    }

    public HashMap<Team, LocationVector> getSpawnPoints() {
        return spawnPoints;
    }

    public void setSpawnPoints(HashMap<Team, LocationVector> spawnPoints) {
        this.spawnPoints = spawnPoints;
    }

    public HashMap<Team, LocationVector> getFirstSpawnPoints() {
        return firstSpawnPoints;
    }

    public void setFirstSpawnPoints(HashMap<Team, LocationVector> firstSpawnPoints) {
        this.firstSpawnPoints = firstSpawnPoints;
    }

    public HashMap<Team, Region> getTeamBases() {
        return teamBases;
    }

    public void setTeamBases(HashMap<Team, Region> teamBases) {
        this.teamBases = teamBases;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public boolean canPlaceBlock() {
        return canPlaceBlock;
    }

    public void setCanPlaceBlock(boolean canPlaceBlock) {
        this.canPlaceBlock = canPlaceBlock;
    }

    public boolean canBreakDefaultBlocks() {
        return canBreakDefaultBlocks;
    }

    public void setCanBreakDefaultBlocks(boolean canBreakDefaultBlocks) {
        this.canBreakDefaultBlocks = canBreakDefaultBlocks;
    }

    public boolean canBreakPlacedBlocks() {
        return canBreakPlacedBlocks;
    }

    public void setCanBreakPlacedBlocks(boolean canBreakPlacedBlocks) {
        this.canBreakPlacedBlocks = canBreakPlacedBlocks;
    }

    public HashSet<LocationVector> getPlacedBlocks() {
        return placedBlocks;
    }

    public void setPlacedBlocks(HashSet<LocationVector> placedBlocks) {
        this.placedBlocks = placedBlocks;
    }

    public boolean isDisableHunger() {
        return disableHunger;
    }

    public void setDisableHunger(boolean disableHunger) {
        this.disableHunger = disableHunger;
    }

    public boolean isDisableDamage() {
        return disableDamage;
    }

    public void setDisableDamage(boolean disableDamage) {
        this.disableDamage = disableDamage;
    }

    public String toString(){
        return "waitingPoint=" + waitingPoint.toString() + " - spectatorPoint=" + spectatorPoint.toString()
                + " - spawnPoints=" + spawnPoints.toString() + " - firstSpawnPoints=" + firstSpawnPoints.toString()
                + " - teamBases=" + teamBases.toString() + " - region=" + region.toString()
                + " - disabled flags => " + "blockPlace=" + canPlaceBlock + " blockBreak=" + canBreakDefaultBlocks
                + " blockBreakPlayers=" + canBreakPlacedBlocks + " hunger=" + disableHunger
                + " damage=" + disableDamage + " pvp=" + disablePvp + " teamShot=" + disableTeamShot;
    }
}
