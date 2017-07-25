package com.theendlessweb.electromaster;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


/* 
 * Created by xxq and Electromaster on 7/25/17.
 */

public class Main extends JavaPlugin {
	
	 	private Connection connection;
	    private String host, database, username, password;
	    private int port;
	    Map<UUID, Long> users;

	@Override
	public void onEnable() {
		users = new HashMap<UUID, Long>();
		host = "localhost";
		port = 3306;
		database = "TestDatabase";
		username = "user";
		password = "pass";    
		try {     
			openConnection();
			Statement statement = connection.createStatement(); 
			getLogger().info("Plugin enabled.");  
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

		@Override
		public void onDisable() {
			getLogger().info("Plugin disabled.");
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
			else if(command.getName().equalsIgnoreCase("eat")){
				if(sender instanceof Player && args.length == 0){
					Player player = (Player) sender;
					player.setSaturation(20.0F);
					player.setFoodLevel(20);
					
				}else{
					sender.sendMessage("Invalid syntax for this command, use /eat.");
				}
			//next command here.
			}
			return false;
		}
	
	/* Events */
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		// Do something here later.
	}
	
	 public void openConnection() throws SQLException, ClassNotFoundException {
		    if (connection != null && !connection.isClosed()) {
		        return;
		    }
	 }	
}
