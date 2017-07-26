package com.theendlessweb.electromaster;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;


/* 
 * Created by xxq and Electromaster on 7/25/17.
 */

public class Main extends JavaPlugin {
	
	 	private Connection connection;
	    private String host, database, username, password;
	    private int port;
	    private Map<UUID, Long> users;
	    public static final String PLUGIN_VERSION = "1.2";

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
				sender.sendMessage("§eYou ran /mycommand!");
				return true;
			}
			else if(command.getName().equalsIgnoreCase("eat")){
				if(sender instanceof Player && args.length == 0){
					Player player = (Player) sender;
					player.setSaturation(40.0F);
					player.setFoodLevel(20);
					player.setHealth(20.0F);
					sender.sendMessage(ccMsg("Your player has been restored."));
					return true;
					
				}else{
					sender.sendMessage(ccErrMsg("Invalid syntax!"));
					return false;
				}
			}
			
			//next command here.
			else if(command.getName().equalsIgnoreCase("supertnt")){
				if(sender instanceof Player && args.length == 0){
					if ((users.get(((Player) sender).getUniqueId()) == null)) {
						ItemStack tnt = new ItemStack(Material.TNT, 64);
	                    ItemStack lighter = new ItemStack(Material.FLINT_AND_STEEL, 1);
	                    ItemMeta lightermeta = lighter.getItemMeta();
	                    ItemMeta TNTMeta = tnt.getItemMeta();
	                    lightermeta.setDisplayName("xxq's Flint/Steel"); 
	                    lightermeta.addEnchant(Enchantment.DURABILITY, 3, false);
	                    TNTMeta.setDisplayName("xxq's Dynamite");
	                    TNTMeta.addEnchant(Enchantment.MENDING, 1, false);
	                    TNTMeta.addEnchant(Enchantment.DURABILITY, 3, false);;
	                    tnt.setItemMeta(TNTMeta);
	                    lighter.setItemMeta(lightermeta);
	                    ((Player) sender).getInventory().addItem(tnt, lighter);
	                    sender.sendMessage(ccMsg("Enjoy your TNT!"));
	                    return true;
					}
				}else{
					sender.sendMessage(ccErrMsg("Invalid syntax!"));
					return false;
				}
			
			}
			else if(command.getName().equalsIgnoreCase("sword")){
				if(sender instanceof Player && args.length == 0){
					if((users.get(((Player) sender).getUniqueId()) == null)){
						ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
						ItemMeta swordMeta = sword.getItemMeta();
						swordMeta.setDisplayName("xxq's Abyssal Blade");
						swordMeta.setUnbreakable(true);
						swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 32767, true); // Sharpness
						swordMeta.addEnchant(Enchantment.DURABILITY, 32767, true); // Unbreaking
						swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 32767, true); // Fire Aspect
						swordMeta.addEnchant(Enchantment.MENDING, 32767, true); // Mending
						swordMeta.addEnchant(Enchantment.SWEEPING_EDGE, 32767, true); // Cleaving affect
						sword.setItemMeta(swordMeta);
						((Player) sender).getInventory().addItem(sword);
						sender.sendMessage(ccMsg("Added xxq's Abyssal Blade to your inventory."));
						
					}
				}else{
					sender.sendMessage(ccErrMsg("Invalid syntax!"));

				}
			}
			return false;
		}
	
	/* Events */
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
<<<<<<< HEAD
		player.sendMessage("[CustomCommands] This server is running CustomCommands V1.0. By: Electromaster and xxq.");
=======
		player.sendMessage(ccMsg("This server is running CustomCommands v" + PLUGIN_VERSION + "."));
>>>>>>> da0a69ce26982e5522ca57c9b5c01adffdeb7876
	}
	
	 public void openConnection() throws SQLException, ClassNotFoundException {
		    if (connection != null && !connection.isClosed()) {
		        return;
		    }

	 }	
	 
	 public String ccErrMsg(String message){
	 	return ChatColor.BLACK + "[" + ChatColor.AQUA + "CustomCommands" + ChatColor.BLACK + "]" + " " + ChatColor.RED + message;
	 }
	 
	 public String ccMsg(String message){
		 return ChatColor.BLACK + "[" + ChatColor.AQUA + "CustomCommands" + ChatColor.BLACK + "]" + " " + ChatColor.YELLOW + message;
	 }
	 

}
 