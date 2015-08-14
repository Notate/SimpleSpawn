package no.notate.simplespawn.Listener;

import no.notate.simplespawn.SimpleSpawn;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    public SimpleSpawn simpleSpawn;
    public PlayerJoin(SimpleSpawn simpleSpawn){
        this.simpleSpawn = simpleSpawn;
    }

    @EventHandler
    public void join(PlayerJoinEvent e){

        Player p = e.getPlayer();

        // Checking if player hasn't been online before
        if(p.hasPlayedBefore() == false){
            // Teleporting player to spawn location
            p.teleport(simpleSpawn.location);
        }

    }

}
