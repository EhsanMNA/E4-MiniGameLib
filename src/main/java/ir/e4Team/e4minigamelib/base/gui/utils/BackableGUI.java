package ir.e4Team.e4minigamelib.base.gui.utils;

import ir.e4Team.e4minigamelib.E4MiniGameLib;
import ir.e4Team.e4minigamelib.base.gui.Back;
import ir.e4Team.e4minigamelib.base.gui.Button;
import ir.e4Team.e4minigamelib.base.gui.GUI;
import ir.e4Team.e4minigamelib.base.gui.Model;
import ir.e4Team.e4minigamelib.base.item.ItemBuilder;
import ir.e4Team.e4minigamelib.base.item.XMaterial;
import org.bukkit.entity.Player;

public abstract class BackableGUI extends GUI {

    protected Back back;

    public BackableGUI(Player player, E4MiniGameLib plugin, Back back) {
        super(player, plugin);
        this.back = back;
    }

    @Override
    protected void construct(Model model) {
        model.setTitle(getTitle());
        model.setSlots(54);
        model.button(back(), 49);
    }

    public abstract String getTitle();

    public Button back() {
        Button button = new Button();
        button.setItem(new ItemBuilder().setType(XMaterial.BARRIER).setDisplayMame("&c&lBack").setLore("&7Click to back menu.").toItemStack());
        button.addAction(cLickType -> back.onBack());
        return button;
    }

}
