package me.dari_os.util;


import me.dari_os.verifv2.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import javax.annotation.Nonnull;
import java.util.*;

import static me.dari_os.verifv2.Main.*;

public class Util {
    private static Main main = getInstanceOFmain();
    private static final String chrSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_.+#!?*&1234567890";

    public static String randomString(int min, int max) {
        if(max<=min) {
            throw new IllegalArgumentException("RandomString: " + max + " can´t be less or equal than " + min);
        //If the code gets this exception, Idk know what I did wrong in my life.
        }
        String retString = "";
        Random rand = new Random(System.currentTimeMillis());
        int length = rand.nextInt( max+1-min)+min;
        for (int i = 0; i <= length; i++) {
            retString += chrSet.charAt(rand.nextInt(chrSet.length()));
        }
        return retString;
    }

    public static void addToVerified(@Nonnull Player... players) {
        for (Player player : players) {
            List<String> list = main.getConfig().getStringList("Verified-Players");
            list.add(player.getUniqueId().toString());
            main.getConfig().set("Verified-Players", list);
            main.saveConfig();
        }
    }

        public static void updateVerified(HashMap<UUID, Boolean> hashMap) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<UUID, Boolean> entry: hashMap.entrySet()) {
            if(entry.getValue()) {
                list.add(entry.getKey().toString());
            }
        }
        main.getConfig().set("Verified-Players", list);
        main.saveConfig();
}
        public static HashMap verifiedToHashMap() {
            List<String> list = main.getConfig().getStringList("Verified-Players");
            HashMap <UUID, Boolean> hashMap = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                hashMap.put(UUID.fromString(list.get(i)), true);
            }
            return hashMap;
        }



        public static String addMagicToString(String str) {
        Random rand = new Random(System.nanoTime());
        ArrayList<String> arrList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            arrList.add(str.charAt(i) + "");
        }
        int indexLength = rand.nextInt(5)+3;
        for (int i = 0; i < indexLength; i++) {
            arrList.add(rand.nextInt(arrList.size()), ChatColor.MAGIC + "" + chrSet.charAt(rand.nextInt(chrSet.length())) + ChatColor.RESET);
        }
        return String.join("", arrList);
        }

        public static void onlinePlayerToValid() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!valid.containsKey(player.getUniqueId())) {
                valid.put(player.getUniqueId(), false);
                getNewCode(player);
            }

        }
        }

        public static void getNewCode(Player player) {
            verifCode.put(player.getPlayer().getUniqueId(), Util.randomString(4, 10));
            player.getPlayer().sendTitle(ChatColor.ITALIC + addMagicToString(verifCode.get(player.getPlayer().getUniqueId())), ChatColor.GREEN + "" + ChatColor.UNDERLINE + "Do /verify [code from above]", 60, 6000, 60); //stays for 5 minutes
        }
}
