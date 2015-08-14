package no.notate.simplespawn.Commands;

import no.notate.simplespawn.SimpleSpawn;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    public SimpleSpawn simpleSpawn;
    public String permission;
    public Spawn(SimpleSpawn simpleSpawn){
        this.simpleSpawn = simpleSpawn;

        this.permission = simpleSpawn.getConfig().getString("permissions.spawn") != null ? simpleSpawn.getConfig().getString("permissions.spawn") : "simplespawn.spawn";
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        // Commandsender is not a player
        if(commandSender instanceof ConsoleCommandSender){
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', simpleSpawn.getConfig().getString("messages.onlyplayers")));
            return true;
        }

        // Getting player
        Player p = (Player) commandSender;

        // Checking if every player can use command with out permission, if false = check permission
        if(simpleSpawn.allspawn == false){

            // Checking permission or OP
            if(p.hasPermission(this.permission) || p.isOp()){

                // Teleporting player to location
                p.teleport(simpleSpawn.location);

                // Sending message to player
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', simpleSpawn.getConfig().getString("messages.spawn")));
            } else {

                // Sending message to player that player don't have permission
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', simpleSpawn.getConfig().getString("messages.nopermission")));
            }
        } else {

            // Every player can use command without permission
            // Teleport player to location
            p.teleport(simpleSpawn.location);

            // Sending message to player
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', simpleSpawn.getConfig().getString("messages.spawn")));
        }

        return true;
    }
}
