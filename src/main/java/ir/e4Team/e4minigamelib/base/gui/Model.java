package ir.e4Team.e4minigamelib.base.gui;

import java.util.HashMap;

public class Model {
    private final HashMap<Integer, Button> buttons = new HashMap<>();
    private String title = "Unnamed Inventory";
    private int slots = 27;

    public void button(Button button, int slot) {
        this.buttons.put(slot, button);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String paramString) {
        this.title = paramString;
    }

    public int getSlots() {
        return this.slots;
    }

    public void setSlots(int paramInt) {
        this.slots = paramInt;
    }

    public HashMap<Integer, Button> getButtons() {
        return this.buttons;
    }
}

