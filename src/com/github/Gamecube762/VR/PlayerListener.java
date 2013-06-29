package com.github.Gamecube762.VR;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener{
	
	public FileConfiguration config;
	public boolean specificLoc;
	
	public PlayerListener(FileConfiguration con){
		this.config = con;
		this.specificLoc = this.config.getBoolean("Settings.TeleportSpecific.enabled");
		
	}
	
	@EventHandler
	public void PlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Location pLoc = p.getLocation();
		World world = pLoc.getWorld();
		Location wSpawn = world.getSpawnLocation();
		int FallLevel = this.config.getInt("Settings.TeleportLevel");
		
		if (this.specificLoc) {
			world = Bukkit.getWorld(this.config.getString("Settings.TeleportSpecific.world"));
			wSpawn = new Location(world, this.config.getInt("Settings.TeleportSpecific.X"), this.config.getInt("Settings.TeleportSpecific.Y"), this.config.getInt("Settings.TeleportSpecific.Z"));
		}
		
		if (pLoc.getBlockY() < FallLevel) {
			p.setFallDistance(0);
			p.teleport(wSpawn);
		}
		
	}
	
}
