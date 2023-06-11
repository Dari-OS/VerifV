package me.dari_os.commands;

import me.dari_os.verifv2.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static me.dari_os.verifv2.Main.*;
import static me.dari_os.util.Util.*;
public class VerifyCommand implements CommandExecutor {
    private Main main;

    public VerifyCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            if (strings.length == 1 && verifCode.containsKey(player.getUniqueId())) {
                if(strings[0].equals(verifCode.get(player.getUniqueId()))) {
                    valid.put(player.getUniqueId(), true);
                    player.sendMessage(ChatColor.GREEN + main.getConfig().getString("OnVerified-message"));
                    updateVerified(valid);
                    player.resetTitle();
                    verifCode.remove(player.getUniqueId());
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED + main.getConfig().getString("OnWrongVerifyCode"));
                }
            }
        }
        return false;
    }
}
