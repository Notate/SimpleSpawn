package no.notate.simplespawn.Commands;

import no.notate.simplespawn.SimpleSpawn;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    public SimpleSpawn simpleSpawn;
    public String permission;
    public SetSpawn(SimpleSpawn simpleSpawn){
        this.simpleSpawn = simpleSpawn;

        this.permission = simpleSpawn.getConfig().getString("permissions.setspawn") != null ? simpleSpawn.getConfig().getString("permissions.setspawn") : "simplespawn.setspawn";
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

        // Checking permission or OP
        if(p.hasPermission(this.permission) || p.isOp()){

            // Getting players location
            Location loc = p.getLocation();

            // Saving to config
            simpleSpawn.getConfig().set("spawn.world", loc.getWorld().getName());
            simpleSpawn.getConfig().set("spawn.x", loc.getX());
            simpleSpawn.getConfig().set("spawn.y", loc.getY());
            simpleSpawn.getConfig().set("spawn.z", loc.getZ());
            simpleSpawn.getConfig().set("spawn.yaw", loc.getYaw());
            simpleSpawn.getConfig().set("spawn.pitch", loc.getPitch());
            simpleSpawn.saveConfig();

            // Setting location variable
            simpleSpawn.location = loc;

            // Just setting spawn location for the world, for safety reasons :P
            loc.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());

            // Sending message
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', simpleSpawn.getConfig().getString("messages.setspawn")));
        } else {

            // Sending no permission message
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', simpleSpawn.getConfig().getString("messages.nopermission")));
        }
        return true;
    }

}
