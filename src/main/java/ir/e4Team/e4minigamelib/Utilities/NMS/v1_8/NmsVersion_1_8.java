package ir.e4Team.e4minigamelib.Utilities.NMS.v1_8;

import ir.e4Team.e4minigamelib.Utilities.NMS.NmsUtils;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NmsVersion_1_8 implements NmsUtils {
    @Override
    public void sendTitle(Player player, String message, int time) {
        CraftPlayer cp = (CraftPlayer) player;
        EntityPlayer p = cp.getHandle();
        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"),time,time,time);
        p.playerConnection.sendPacket(packet);
    }

    @Override
    public void sendActionBar(Player player, String message, int time) {
        CraftPlayer cp = (CraftPlayer) player;
        EntityPlayer p = cp.getHandle();
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"),(byte) 2);
        p.playerConnection.sendPacket(packet);
    }

    @Override
    public void sendBossBar(Player player, String message, float health) {
        BarV1_8.setBar(player,message,health);
    }
}
