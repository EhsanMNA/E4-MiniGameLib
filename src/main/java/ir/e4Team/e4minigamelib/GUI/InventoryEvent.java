package ir.e4Team.e4minigamelib.GUI;

import ir.e4Team.e4minigamelib.GUI.Click.ClickEvent;
import ir.e4Team.e4minigamelib.GUI.Click.ClickType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryEvent implements Listener {

    JavaPlugin plugin;

    public InventoryEvent(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;
        for (GUI gui : GUIManager.playerGUIs.values()) {
            if (!e.getInventory().equals(gui.getInventory())) return;
            e.setCancelled(true);
            if (!(e.getRawSlot() <= e.getClickedInventory().getSize())) return;
            ClickEvent event = gui.getClickEvents().get(e.getSlot());
            if (event != null) {
                event.run(ClickType.parseClick(e.getClick()), (Player) e.getWhoClicked());
            }
        }
    }
}
