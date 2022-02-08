package ir.e4Team.e4minigamelib.GUI;

import ir.e4Team.e4minigamelib.Utilities.Utils;
import ir.e4Team.e4minigamelib.GUI.Click.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class GUI implements IGUI {

    int size;
    String displayName;
    Inventory inventory;
    GUIOwner guiOwner;
    Map<Integer, ClickEvent> clickEvents;

    public GUI(int size, String displayName, GUIOwner guiOwner) {
        this.size = size;
        this.displayName = displayName;
        this.inventory = Bukkit.createInventory(null, size, Utils.color(displayName));
        this.guiOwner = guiOwner;
        this.clickEvents = new HashMap<>();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public GUIOwner getOwner() {
        return guiOwner;
    }

    @Override
    public Map<Integer, ClickEvent> getClickEvents() {
        return clickEvents;
    }

    @Override
    public void setItem(int slot, ItemStack item, ClickEvent event) {
        if(item == null) return;
        inventory.setItem(slot, item);
        if(event == null) return;
        clickEvents.put(slot, event);
    }

    @Override
    public void open() {
        Player player = guiOwner.toPlayer();
        player.openInventory(inventory);
        GUIManager.playerGUIs.put(guiOwner.getUuid(), this);
    }
}
