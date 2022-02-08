package ir.e4Team.e4minigamelib.GUI;

import ir.e4Team.e4minigamelib.GUI.Click.ClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface IGUI {

    int getSize();

    String getDisplayName();

    Inventory getInventory();

    GUIOwner getOwner();

    Map<Integer, ClickEvent> getClickEvents();

    void setItem(int slot, ItemStack item, ClickEvent event);

    void open();
}
