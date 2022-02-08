package me.ehsanmna.e4minigamelib.Utilities.NMS.v1_17;

import me.ehsanmna.e4minigamelib.Utilities.NMS.NmsUtils;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NmsVersion_1_17 implements NmsUtils {
    @Override
    public void sendTitle(Player player, String message, int time) {
        CraftPlayer cp = (CraftPlayer) player;
        EntityPlayer p = cp.getHandle();
        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"),time,time,time);
        p.playerConnection.sendPacket(packet);
    }

    @Override
    public void sendActionBar(Player player, String message, int time) {

    }

    @Override
    public void sendBossBar(Player player, String message, float health) {

    }
}
