package me.ehsanmna.e4minigamelib.GUI.Click;

import org.bukkit.entity.Player;

public interface ClickEvent {

    void run(ClickType clickType, Player player);
}
