package ir.e4Team.e4minigamelib.base.item;

import ir.e4Team.e4minigamelib.base.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {

    private ArrayList<String> lore;
    private String displayName;
    private XMaterial material;

    public ItemBuilder() {
    }

    public ItemBuilder setDisplayMame(String name) {
        displayName = ChatColor.translateByAmpersand(name);
        return this;
    }
    public ItemBuilder setType(XMaterial material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setLore(ArrayList<String> lore) {
        this.lore = ChatColor.translateListByAmpersand(lore);
        return this;
    }
    public ItemBuilder setLore(String... lore) {
        this.lore = ChatColor.translateListByAmpersand(lore);
        return this;
    }
    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(material.parseMaterial(), 1, material.getData());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        if(lore != null) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }


}

