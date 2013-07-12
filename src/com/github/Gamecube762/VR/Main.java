package com.github.Gamecube762.VR;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.Gamecube762.VR.PlayerListener;

public class Main extends JavaPlugin implements Listener{
	
	public FileConfiguration config;
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.config = this.getConfig();
        getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String labal, String[] args) {
		Player p = (Player)sender;
		Location pLoc = p.getLocation();
		
		if(cmd.getName().equalsIgnoreCase("s")){
			if (!p.hasPermission("setspawn")){p.sendMessage("NoPermission!");return true;}
			
			Location newSpawn = pLoc;
			this.config.set("spawn.world", pLoc.getWorld());
			this.config.set("spawn.x", pLoc.getBlockX()+0.5D);
			this.config.set("spawn.y", pLoc.getBlockY()+0.5D);
			this.config.set("spawn.z", pLoc.getBlockZ()+0.5D);
			this.config.set("spawn.isSet", false);
			p.sendMessage("spawnpoint set");
			return true;
		}
	}
	
	@EventHandler
	public void PlayerRespawn(PlayerRespawnEvent e) {
		
		if (this.config.getBoolean("spawn.isSet")){
			
			double x = this.config.getDouble("spawn.x");
			double y = this.config.getDouble("spawn.y");
			double z = this.config.getDouble("spawn.z");
			World  w = Bukkit.getWorld(this.config.getString("spawn.world"));
			
			Location respawnLocation = new Location(w, x, y, z);
			e.setRespawnLocation(respawnLocation);
		}
	}
}
