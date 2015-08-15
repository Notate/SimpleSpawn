package no.notate.simplespawn;

import no.notate.simplespawn.Commands.SetSpawn;
import no.notate.simplespawn.Commands.Spawn;
import no.notate.simplespawn.Listener.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SimpleSpawn extends JavaPlugin implements Listener {

    public Logger logger;
    public Location location;
    public boolean allspawn = true;


    public void onEnable(){
        this.logger = this.getServer().getLogger();
        logger.info("SimpleSpawn is enabled");

        // Getting default config
        this.saveDefaultConfig();

        // Checking if you need permission to type "/spawn"
        if(getConfig().getString("config.permtospawn") != null){
            try {
                allspawn = getConfig().getBoolean("config.permtospawn");
            } catch(Exception e){
                allspawn = true;
            }
        }

        // Getting spawn
        // Just a safe check if world is null, don't use config then
        if(getConfig().getString("spawn.world") == null){
            location = getServer().getWorlds().get(0).getSpawnLocation();
        } else {

            // World wasn't null, setting spawn location as it says in config.

            location = new Location(
                    Bukkit.getWorld(getConfig().getString("spawn.world")),
                    getConfig().getDouble("spawn.x"),
                    getConfig().getDouble("spawn.y"),
                    getConfig().getDouble("spawn.z"),
                    Float.parseFloat(getConfig().getString("spawn.yaw")),
                    Float.parseFloat(getConfig().getString("spawn.pitch"))
            );
        }

        // Register Listener
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);

        // Register Command
        getCommand("spawn").setExecutor(new Spawn(this));
        getCommand("setspawn").setExecutor(new SetSpawn(this));
    }




}
