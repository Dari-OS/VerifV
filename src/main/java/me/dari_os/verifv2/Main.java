package me.dari_os.verifv2;

import me.dari_os.events.EventHandlerVerifV2;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.dari_os.commands.*;
import java.util.HashMap;
import java.util.UUID;

import static me.dari_os.util.Util.*;

public final class Main extends JavaPlugin {
private static Main instanceOFmain;
public static HashMap valid;
public static HashMap<UUID, String> verifCode = new HashMap<>();
    @Override
    public void onEnable() {
        instanceOFmain = this; // NEEDS TO BE FIRST
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        valid = verifiedToHashMap();
        onlinePlayerToValid(); //incase the server reloads while players are online
        Bukkit.getPluginManager().registerEvents(new EventHandlerVerifV2(), this);
        getCommand("verify").setExecutor(new VerifyCommand(this));

    }

    @Override
    public void onDisable() {
        updateVerified(valid);
        // Plugin shutdown logic
    }
 public static Main getInstanceOFmain() {
        return instanceOFmain;
 }
}
