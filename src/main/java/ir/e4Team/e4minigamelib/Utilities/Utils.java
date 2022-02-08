package ir.e4Team.e4minigamelib.Utilities;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }

    public static List<String> colorList(List<String> messages){
        List<String> arrayList = new ArrayList<String>();
        for (String str: messages){
            arrayList.add(ChatColor.translateAlternateColorCodes('&',str));
        }
        return arrayList;
    }

    public static Set<String> colorList(Set<String> messages){
        Set<String> setList = new HashSet<>();
        for (String str: messages){
            setList.add(ChatColor.translateAlternateColorCodes('&',str));
        }
        return setList;
    }



}
