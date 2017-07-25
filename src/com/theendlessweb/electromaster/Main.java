package com.theendlessweb.electromaster;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


public class Main extends JavaPlugin {
	
	 	private Connection connection;
	    private String host, database, username, password;
	    private int port;
}
	@Override
	public void onEnable() {
		host = "localhost";
		port = 3306;
		database = "TestDatabase";
		username = "user";
		password = "pass";    
		try {     
			openConnection();
			Statement statement = connection.createStatement(); 
			log.info(“Plugin enabled.”);   
		}

		@Override
		public void onDisable() {
			log.info(“Plugin disabled.”);
		}

		@Override
		public boolean onCommand(CommandSender sender,
				Command command,
				String label,
				String[] args) {
			if (command.getName().equalsIgnoreCase("worldnews")) {
				sender.sendMessage("You ran /mycommand!");
				return true;
			}
			return false;
		}

	}
