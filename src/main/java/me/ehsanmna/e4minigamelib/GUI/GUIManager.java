package me.ehsanmna.e4minigamelib.GUI;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GUIManager {

    protected static final Map<UUID, GUIOwner> playerOwners = new HashMap<>();
    public static final Map<UUID, GUI> playerGUIs = new HashMap<>();

    public static GUIOwner getGUIOwner(Player player) {
        return getGUIOwner(player.getUniqueId());
    }
    public static GUIOwner getGUIOwner(UUID uuid) {
        if (!playerOwners.containsKey(uuid)) {
            playerOwners.put(uuid, new GUIOwner(uuid));
        }
        return playerOwners.get(uuid);
    }
}
