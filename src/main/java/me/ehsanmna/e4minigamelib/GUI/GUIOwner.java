package me.ehsanmna.e4minigamelib.GUI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GUIOwner {

    UUID uuid;

    public GUIOwner(UUID uuid) {
        this.uuid = uuid;
    }

    public Player toPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    @Override
    public String toString() {
        return "GUIOwner{" +
                "uuid=" + uuid +
                '}';
    }

    public UUID getUuid() {
        return uuid;
    }
}
