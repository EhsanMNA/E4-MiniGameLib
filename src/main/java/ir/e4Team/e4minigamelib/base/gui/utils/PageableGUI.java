package ir.e4Team.e4minigamelib.base.gui.utils;

import ir.e4Team.e4minigamelib.E4MiniGameLib;
import ir.e4Team.e4minigamelib.base.gui.Back;
import ir.e4Team.e4minigamelib.base.gui.Button;
import ir.e4Team.e4minigamelib.base.gui.Model;
import ir.e4Team.e4minigamelib.base.item.ItemBuilder;
import ir.e4Team.e4minigamelib.base.item.XMaterial;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PageableGUI<T> extends BackableGUI {

    public int page = 0;

    public PageableGUI(Player player, E4MiniGameLib plugin, Back back) {
        super(player, plugin, back);
    }

    @Override
    protected void construct(Model model) {
        super.construct(model);
        List<T> list1 = new ArrayList<>(Arrays.asList(getObjects()));
        int i = Math.min(list1.size(), this.page * 36);
        int j = Math.min(list1.size(), i + 36);
        List<T> list2 = list1.subList(i, j);
        byte b = 0;
        for (T object : list2) {
            Button button = new Button();
            construct(button, object);
            model.button(button, b);
            b++;
        }
        int pages = (int) Math.round(Math.ceil(list1.size() / 36.0D));
        if(page != 0) {
            model.button(previous(), 45);
        }
        if(page + 1 < pages) {
            model.button(next(), 53);
        }
    }

    public Button previous() {
        Button button = new Button();
        button.setItem(new ItemBuilder().setType(XMaterial.ARROW).setDisplayMame("&a&lPrevious").toItemStack());
        button.addAction((cLickType) -> page--);
        return button;
    }

    public Button next() {
        Button button = new Button();
        button.setItem(new ItemBuilder().setType(XMaterial.ARROW).setDisplayMame("&a&lNext").toItemStack());
        button.addAction((cLickType) -> page++);
        return button;
    }


    public abstract String getTitle();

    public abstract T[] getObjects();

    public abstract void construct(Button button, T object);
}

