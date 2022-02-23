package ir.e4Team.e4minigamelib.base.gui;

public enum ClickType {
    RIGHT_CLICK, LEFT_CLICK, ONKDOWN;
    public static ClickType convert(org.bukkit.event.inventory.ClickType clickType) {
        if(clickType.isLeftClick()) return LEFT_CLICK;
        if(clickType.isRightClick()) return RIGHT_CLICK;
        return ONKDOWN;
    }

}
