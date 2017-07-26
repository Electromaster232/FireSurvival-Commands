package com.theendlessweb.electromaster;

import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
	
		// some test stuff for /worldnews/
	 	private Connection connection;
	    private String host, database, username, password;
	    private int port;
	    private Map<UUID, Long> users;
	    public static final String PLUGIN_VERSION = "1.21";
	    List<String> supplierNames = Arrays.asList("Given by the gods.");

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
			getLogger().info("CustomCommands v" + PLUGIN_VERSION + " enabled.");  
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

		@Override
		public void onDisable() {
			getLogger().info("CustomCommands v" + PLUGIN_VERSION + " disabled.");
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
			else if(command.getName().equalsIgnoreCase("tools")){
				if(sender instanceof Player && args.length == 0){
					if((users.get(((Player) sender).getUniqueId()) == null)){
						ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
						ItemMeta swordMeta = sword.getItemMeta();
						swordMeta.setDisplayName("Divine Rapier");
						swordMeta.setUnbreakable(true);
						swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 32767, true); // Sharpness
						swordMeta.addEnchant(Enchantment.DURABILITY, 32767, true); // Unbreaking
						swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 32767, true); // Fire Aspect
						swordMeta.addEnchant(Enchantment.MENDING, 32767, true); // Mending
						swordMeta.addEnchant(Enchantment.SWEEPING_EDGE, 32767, true); // Cleaving affect
						ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE, 1);
						ItemMeta pickMeta = pick.getItemMeta();
						pickMeta.setDisplayName("Hoarfrost");
						pickMeta.setUnbreakable(true);
						pickMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
						pickMeta.addEnchant(Enchantment.DURABILITY, 3, true);
						pickMeta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
						pickMeta.addEnchant(Enchantment.MENDING, 1, true);
						sword.setItemMeta(swordMeta);
						pick.setItemMeta(pickMeta);
						((Player) sender).getInventory().addItem(sword);
						((Player) sender).getInventory().addItem(pick);
						sender.sendMessage(ccMsg("Added xxq's Abyssal Blade to your inventory."));
						
						
					}
				}else{
					sender.sendMessage(ccErrMsg("Invalid syntax!!!"));
					return false;
				}
			}
			else if(command.getName().equalsIgnoreCase("armor")){
				if(sender instanceof Player && args.length == 0){
					if((users.get(((Player) sender).getUniqueId()) == null)){
						ItemStack helm = new ItemStack(Material.DIAMOND_HELMET, 1);
						ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
						ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
						ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
						
						ItemMeta helmMeta = helm.getItemMeta();
						ItemMeta chestMeta = chest.getItemMeta();
						ItemMeta legsMeta = legs.getItemMeta();
						ItemMeta bootsMeta = boots.getItemMeta();
						
						/* Names */
						helmMeta.setDisplayName("Veil of Discord");
						chestMeta.setDisplayName("Assault Cuirass");
						legsMeta.setDisplayName("Shiva's Guard");
						bootsMeta.setDisplayName("Boots of Travel");
						
						/* Protection */
						helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
						chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
						legsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
						bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
						
						/* Unbreaking */
						helmMeta.addEnchant(Enchantment.DURABILITY, 3, true);
						chestMeta.addEnchant(Enchantment.DURABILITY, 3, true);
						legsMeta.addEnchant(Enchantment.DURABILITY, 3, true);
						bootsMeta.addEnchant(Enchantment.DURABILITY, 3, true);
						
						/* Mending */
						helmMeta.addEnchant(Enchantment.MENDING, 1, true);
						chestMeta.addEnchant(Enchantment.MENDING, 1, true);
						legsMeta.addEnchant(Enchantment.MENDING, 1, true);
						bootsMeta.addEnchant(Enchantment.MENDING, 1, true);
						
						/* Misc */
						helmMeta.addEnchant(Enchantment.OXYGEN, 3, true);
						helmMeta.addEnchant(Enchantment.WATER_WORKER, 1, true);
						bootsMeta.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);
						bootsMeta.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
						
						helm.setItemMeta(helmMeta);
						chest.setItemMeta(chestMeta);
						legs.setItemMeta(legsMeta);
						boots.setItemMeta(bootsMeta);
						
						((Player) sender).getInventory().addItem(helm);
						((Player) sender).getInventory().addItem(chest);
						((Player) sender).getInventory().addItem(legs);
						((Player) sender).getInventory().addItem(boots);
						sender.sendMessage(ccMsg("Added god armor to your inventory."));
					}
				}else{
					sender.sendMessage(ccErrMsg("Invalid syntax!"));
				}
			}
			else if(command.getName().equalsIgnoreCase("axe")){
				if(sender instanceof Player && args.length == 0){
					if((users.get(((Player) sender).getUniqueId()) == null)){
						ItemStack axe = new ItemStack(Material.DIAMOND_AXE, 1);
						ItemMeta axeMeta = axe.getItemMeta();
						axeMeta.setDisplayName(Color.red + "Electromaster's God Axe of Power");
						axeMeta.setLore(supplierNames);
						axeMeta.setUnbreakable(true);
						axeMeta.addEnchant(Enchantment.DAMAGE_ALL, 32767, true); // Sharpness
						axeMeta.addEnchant(Enchantment.DURABILITY, 32767, true); // Unbreaking
						axeMeta.addEnchant(Enchantment.FIRE_ASPECT, 32767, true); // Fire Aspect
						axeMeta.addEnchant(Enchantment.MENDING, 32767, true); // Mending
						axeMeta.addEnchant(Enchantment.SWEEPING_EDGE, 32767, true); // Cleaving affect
						axeMeta.addEnchant(Enchantment.DIG_SPEED, 32767, true);
						axe.setItemMeta(axeMeta);
						((Player) sender).getInventory().addItem(axe);
						sender.sendMessage(ccMsg("Added Electromaster's God Axe of Power to your inventory."));
					}
				}
			else if(command.getName().equalsIgnoreCase("moar")){
				Player p = (Player) sender;
				ItemStack item = p.getInventory().getItemInMainHand();
				if(sender instanceof Player && args.length == 0){
						if(p.getInventory().getItemInMainHand() != null){
							if(item.getAmount() < item.getMaxStackSize() && item.getAmount() != item.getMaxStackSize()){
								item.setAmount(item.getMaxStackSize());
							}		
							if(item.getAmount() == item.getMaxStackSize()){
								sender.sendMessage(ccErrMsg("The stack in your hand is already full."));
							}
						if(p.getInventory().getItemInMainHand() == null){
							sender.sendMessage(ccErrMsg("You need to have an item in your hand."));
						}
						
					}
				}else{
					sender.sendMessage(ccErrMsg("Invalid syntax!"));
				}
			}
			}
			return false;
		}
	
	/* Events */
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		event.getPlayer().sendMessage("[CustomCommands] This server is running CustomCommands V1.0. By: Electromaster and xxq.");
		event.getPlayer().sendMessage(ccMsg("This server is running CustomCommands v" + PLUGIN_VERSION + "."));
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
 
