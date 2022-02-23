package ir.e4Team.e4minigamelib.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public enum ChatColor {
    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),
    MAGIC('k'),
    BOLD('l'),
    STRIKETHROUGH('m'),
    UNDERLINE('n'),
    ITALIC('o'),
    RESET('r');

    public final char icon;

    ChatColor(char icon) {
        this.icon = icon;
    }

    public static String translate(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; ++i) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = 167;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

    public static String translateByAmpersand(String textToTranslate) {
        return translate('&', textToTranslate);
    }

    public static ArrayList<String> translateListByAmpersand(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, translateByAmpersand(list.get(i)));
        }
        return list;
    }

    public static ArrayList<String> translateListByAmpersand(String... list) {
        ArrayList<String> newList = new ArrayList<>(Arrays.asList(list));
        return translateListByAmpersand(newList);
    }

    public static ChatColor random() {
        Random random = new Random();
        ChatColor color = null;
        color = values()[random.nextInt(values().length)];
        while (color == BLACK || color == RESET) {
            color = values()[random.nextInt(values().length)];
        }
        return color;
    }

    @Override
    public String toString() {
        return '§' + "" + icon;
    }

}
