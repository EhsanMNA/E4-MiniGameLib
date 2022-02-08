package ir.e4Team.e4minigamelib.GUI.Click;

import org.bukkit.entity.Player;

public interface ClickEvent {

    void run(ClickType clickType, Player player);
}
