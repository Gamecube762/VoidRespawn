package com.github.Gamecube762.VR;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.Gamecube762.VR.PlayerListener;

public class Main extends JavaPlugin{
	
	public FileConfiguration config;
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.config = this.getConfig();
        getServer().getPluginManager().registerEvents(new PlayerListener(this.config), this);
		getLogger().info("Made by Gamecube762");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Goodbye! D=");
	}
}
