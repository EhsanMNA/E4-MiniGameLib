package me.ehsanmna.e4minigamelib.Utilities.NMS.v1_16;

import me.ehsanmna.e4minigamelib.Utilities.NMS.NmsUtils;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.craftbukkit.v1_16_R3.boss.CraftBossBar;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NmsVersion_1_16 implements NmsUtils {
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
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"), ChatMessageType.GAME_INFO,player.getUniqueId());
        p.playerConnection.sendPacket(packet);
    }

    @Override
    public void sendBossBar(Player player, String message, float health) {
        CraftBossBar bossBar = new CraftBossBar(message, BarColor.GREEN, BarStyle.SOLID, BarFlag.PLAY_BOSS_MUSIC);
        bossBar.addPlayer(player);
    }
}
