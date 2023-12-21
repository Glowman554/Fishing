package de.glowman554.fishing;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import de.glowman554.fishing.drops.CommandDrop;
import de.glowman554.fishing.drops.DropManager;
import de.glowman554.fishing.drops.EffectDrop;
import de.glowman554.fishing.drops.ItemDrop;
import de.glowman554.fishing.drops.MoneyDrop;
import de.glowman554.fishing.listener.FishingListener;
import net.milkbowl.vault.economy.Economy;

public class FishingMain extends JavaPlugin
{
	private static FishingMain instance;
	private Economy economy;

	private DropManager dropManager = new DropManager();
	private FileConfiguration config = getConfig();

	private String worldName;

	public FishingMain()
	{
		instance = this;
	}

	private void loadItems()
	{
		List<String> itemString = (List<String>) config.getList("items");

		for (int i = 0; i < itemString.size(); i++)
		{
			String[] itemSplit = itemString.get(i).split("@");

			Material item = Material.getMaterial(itemSplit[0].toUpperCase());
			if (item == null)
			{
				throw new IllegalArgumentException("Item " + itemSplit[0] + " could not be found!");
			}

			dropManager.addDrop(new ItemDrop(Double.parseDouble(itemSplit[1]), item));
		}
	}

	private void loadCommands()
	{
		List<String> commandString = (List<String>) config.getList("commands");

		for (int i = 0; i < commandString.size(); i++)
		{
			String[] commandSplit = commandString.get(i).split("@");
			dropManager.addDrop(new CommandDrop(Double.parseDouble(commandSplit[1]), commandSplit[0]));
		}
	}

	private void loadMoney()
	{
		List<String> moneyString = (List<String>) config.getList("money");

		for (int i = 0; i < moneyString.size(); i++)
		{
			String[] moneySplit = moneyString.get(i).split("@");
			dropManager.addDrop(new MoneyDrop(Double.parseDouble(moneySplit[1]), Double.parseDouble(moneySplit[0])));
		}
	}

	private void loadEffects()
	{
		List<String> effectString = (List<String>) config.getList("effects");

		for (int i = 0; i < effectString.size(); i++)
		{
			String[] effectSplit = effectString.get(i).split("@");

			PotionEffectType type = PotionEffectType.getByKey(NamespacedKey.fromString(effectSplit[0]));
			if (type == null)
			{
				throw new IllegalArgumentException("Effect " + effectSplit[0] + " could not be found!");
			}

			dropManager.addDrop(new EffectDrop(Double.parseDouble(effectSplit[1]), type));
		}
	}

	@Override
	public void onLoad()
	{
		config.addDefault("items", new String[] {"apple@10", "carrot@10", "stone@10"});
		config.addDefault("commands", new String[] {"say 1@10", "say 2@10"});
		config.addDefault("money", new String[] {"100@10", "1000@10", "10000@10"});
		config.addDefault("effects", new String[] {"jump_boost@10", "night_vision@10"});

		config.addDefault("world", "world");

		config.options().copyDefaults(true);
		saveConfig();

		loadItems();
		loadCommands();
		loadMoney();
		loadEffects();

		worldName = config.getString("world");
		getLogger().log(Level.INFO, "Activated for world " + worldName);

		dropManager.finish();
	}

	@Override
	public void onEnable()
	{
		if (getServer().getPluginManager().getPlugin("Vault") == null)
		{
			throw new Error("Vault not found!");
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null)
		{
			throw new Error("Vault economy not found!");
		}
		economy = rsp.getProvider();

		getServer().getPluginManager().registerEvents(new FishingListener(), this);

	}

	@Override
	public void onDisable()
	{
		// TODO Auto-generated method stub
	}

	public Economy getEconomy()
	{
		return economy;
	}

	public DropManager getDropManager()
	{
		return dropManager;
	}

	public String getWorldName()
	{
		return worldName;
	}

	public static FishingMain getInstance()
	{
		return instance;
	}
}
