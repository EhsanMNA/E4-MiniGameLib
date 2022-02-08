package me.ehsanmna.e4minigamelib.GUI.Click;

public enum ClickType {
    RIGHT_CLICK, LEFT_CLICK, SHIFT_CLICK;

    public static ClickType parseClick(org.bukkit.event.inventory.ClickType type) {
        if(type.isRightClick()) {
            return RIGHT_CLICK;
        }
        if(type.isLeftClick()) {
            return LEFT_CLICK;
        }
        if(type.isShiftClick()) {
            return SHIFT_CLICK;
        }
        return null;
    }
}
