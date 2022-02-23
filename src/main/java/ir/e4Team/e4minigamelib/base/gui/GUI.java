package ir.e4Team.e4minigamelib.base.gui;

import ir.e4Team.e4minigamelib.E4MiniGameLib;
import ir.e4Team.e4minigamelib.base.schelder.RecurringTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.List;

public abstract class GUI implements Listener {

    private final E4MiniGameLib plugin;
    protected Player player;
    private Inventory inventory;
    private final RecurringTask recurringTask;
    private HashMap<Integer, List<Action>> actions;

    public GUI(Player player, E4MiniGameLib plugin) {
        this.player = player;
        this.plugin = plugin;
        this.recurringTask = E4MiniGameLib.getScheduler().runTaskTimer(() -> {
            Model model = new Model();
            construct(model);
            if (inventory == null || !player.getOpenInventory().getTitle().equals(model.getTitle()) || inventory.getSize() != model.getSlots()) {
                inventory = Bukkit.createInventory(null, model.getSlots(), model.getTitle());
                player.openInventory(inventory);
            }
            HashMap<Integer, Button> buttons = model.getButtons();
            actions = new HashMap<>();
            for (int i : buttons.keySet()) {
                ItemStack item = buttons.get(i).getItem();
                if (inventory.getItem(i) == null) {
                    if (item != null) {
                        inventory.setItem(i, item);
                    }
                }
                if(item != null && !item.equals(inventory.getItem(i))) {
                    inventory.setItem(i, item);
                }
                actions.put(i, buttons.get(i).getActions());
            }
            player.updateInventory();
        }, 0, 1);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void reopen() {
        if (this.inventory != null) return;
        this.recurringTask.start();
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    private void close() {
        this.inventory = null;
        this.recurringTask.stop();
        HandlerList.unregisterAll(this);
        onClose();
    }

    @EventHandler
    public void click(InventoryClickEvent event) {
        if (!event.getWhoClicked().equals(this.player))
            return;
        if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            event.setCancelled(true);
            return;
        }
        event.setCancelled(true);
        if (this.actions == null)
            return;
        if (!(event.getClickedInventory() instanceof PlayerInventory) && this.actions.containsKey(event.getSlot())) {
            this.actions.get(event.getSlot()).forEach(action -> action.click(ClickType.convert(event.getClick())));
        }
    }

    @EventHandler
    public void drag(InventoryDragEvent event) {
        if (!event.getWhoClicked().equals(this.player)) return;
        if (event.getInventory() instanceof PlayerInventory) return;
        if (event.getInventorySlots().size() > 1)
            event.setCancelled(true);
    }

    @EventHandler
    public void leave(PlayerQuitEvent event) {
        if (event.getPlayer().equals(this.player)) close();
    }

    @EventHandler
    public void close(InventoryCloseEvent event) {
        if (this.inventory != null && this.inventory.equals(event.getInventory())) close();
    }

    public E4MiniGameLib getPlugin() {
        return plugin;
    }

    public Inventory getInventory() {
        return inventory;
    }

    protected abstract void construct(Model model);

    public void onClose() {
    }
}
