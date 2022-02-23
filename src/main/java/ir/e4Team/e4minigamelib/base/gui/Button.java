package ir.e4Team.e4minigamelib.base.gui;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Button {
    private final List<Action> actions = new ArrayList<>();
    private ItemStack item;

    public void addAction(Action action) {
        actions.add(action);
    }
    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public List<Action> getActions() {
        return actions;
    }
}
