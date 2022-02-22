package ir.e4Team.e4minigamelib.Utilities.NMS.v1_17;

import ir.e4Team.e4minigamelib.Utilities.NMS.NmsUtils;
import ir.e4Team.e4minigamelib.Utilities.Utils;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NmsVersion_1_17 implements NmsUtils {
    @Override
    public void sendTitle(Player player, String message, int time) {
        CraftPlayer cp = (CraftPlayer) player;
        EntityPlayer p = cp.getHandle();
        ClientboundSetTitleTextPacket packet = new ClientboundSetTitleTextPacket(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"));
        p.b.sendPacket(packet);
    }

    @Override
    public void sendActionBar(Player player, String message, int time) {
        CraftPlayer cp = (CraftPlayer) player;
        EntityPlayer p = cp.getHandle();
        ClientboundSetActionBarTextPacket packet = new ClientboundSetActionBarTextPacket(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"));
        p.b.sendPacket(packet);
    }

    @Override
    public void sendBossBar(Player player, String message, float health) {
        BossBar bar = Bukkit.createBossBar(Utils.color(message), BarColor.GREEN, BarStyle.SOLID);
        bar.addPlayer(player);
    }
}
