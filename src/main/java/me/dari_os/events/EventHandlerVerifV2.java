package me.dari_os.events;

import me.dari_os.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import static me.dari_os.util.Util.*;
import static me.dari_os.verifv2.Main.valid;
import static me.dari_os.verifv2.Main.verifCode;

public class EventHandlerVerifV2 implements Listener {
    @EventHandler
    public void onMovement(PlayerMoveEvent pme) {
            pme.setCancelled(!(Boolean) valid.get(pme.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onChat(PlayerChatEvent pce) {
        pce.setCancelled(!(Boolean) valid.get(pce.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent ple) {
        if (!valid.containsKey(ple.getPlayer().getUniqueId())) {
            valid.put(ple.getPlayer().getUniqueId(), false);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent pje) {
        if (!((Boolean) valid.get(pje.getPlayer().getUniqueId()))) {
            verifCode.put(pje.getPlayer().getUniqueId(), Util.randomString(4, 10));
            pje.getPlayer().sendTitle(ChatColor.ITALIC + addMagicToString(verifCode.get(pje.getPlayer().getUniqueId())), ChatColor.GREEN + "" + ChatColor.UNDERLINE + "Do /verify [code from above]", 60, 6000, 60); //stays for 5 minutes
        }
        }
}
