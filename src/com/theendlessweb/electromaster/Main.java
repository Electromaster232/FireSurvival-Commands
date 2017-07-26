package com.theendlessweb.electromaster;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


/* 
 * Created by xxq and Electromaster on 7/25/17.
 */

public class Main extends JavaPlugin {
	
	 	private Connection connection;
	    private String host, database, username, password;
	    private int port;
	    private Map<UUID, Long> users;

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
					player.setSaturation(20.0F);
					player.setFoodLevel(20);
					return true;
					
				}else{
					sender.sendMessage(ChatColor.RED + "Invalid syntax for this command, use /eat.");
					return false;
				}
			}
			
			//next command here.
			else if(command.getName().equalsIgnoreCase("jihad")){
				if(sender instanceof Player && args.length == 0){
					if ((users.get(((Player) sender).getUniqueId()) == null)) {
						ItemStack tnt = new ItemStack(Material.TNT, 64);
	                    ItemStack lighter = new ItemStack(Material.FLINT_AND_STEEL, 1);
	                    ItemMeta lightermeta = lighter.getItemMeta();
	                    ItemMeta TNTMeta = tnt.getItemMeta();
	                    lightermeta.setDisplayName("xxq's Flint/Steel"); 
	                    lightermeta.addEnchant(Enchantment.DURABILITY, 32767, false);
	                    TNTMeta.setDisplayName("xxq's Dynamite");
	                    TNTMeta.addEnchant(Enchantment.MENDING, 5, false);
	                    TNTMeta.addEnchant(Enchantment.DURABILITY, 32767, false);;
	                    tnt.setItemMeta(TNTMeta);
	                    lighter.setItemMeta(lightermeta);
	                    ((Player) sender).getInventory().addItem(tnt, lighter);
	                    return true;
					}
				}else{
					sender.sendMessage(ChatColor.RED + "Invalid syntax for this command. Use /jihad.");
					return false;
				}
			
			}
			else if(command.getName().equalsIgnoreCase("sword")){
				if(sender instanceof Player && args.length == 0){
					if((users.get(((Player) sender).getUniqueId()) == null)){
						ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 64);
						ItemMeta swordMeta = sword.getItemMeta();
						swordMeta.setDisplayName("xxq's Abyssal Blade");
						swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 32767, false); // Sharpness
						swordMeta.addEnchant(Enchantment.DURABILITY, 32767, false); // Unbreaking
						swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 32767, false); // Fire Aspect
						swordMeta.addEnchant(Enchantment.MENDING, 32767, false); // Mending
						swordMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10, false); // Looting for blocks
						swordMeta.addEnchant(Enchantment.SWEEPING_EDGE, 32767, false); 
						swordMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, false); // Vanishing curse
						sword.setItemMeta(swordMeta);
						((Player) sender).getInventory().addItem(sword);
						
					}
				}else{
					sender.sendMessage(ChatColor.RED + "Invalid syntax for this command. Use /goditems.");
				}
			}
			return false;
		}
	
	/* Events */
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.YELLOW + "write /help.");
	}
	
	 public void openConnection() throws SQLException, ClassNotFoundException {
		    if (connection != null && !connection.isClosed()) {
		        return;
		    }
	 }
}