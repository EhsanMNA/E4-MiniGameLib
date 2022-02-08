package me.ehsanmna.e4minigamelib.Utilities.NMS;

import org.bukkit.entity.Player;

public interface NmsUtils {


    public void sendTitle(Player player,String message,int time);

    public void sendActionBar(Player player,String message,int time);

    public void sendBossBar(Player player,String message,float health);


}
