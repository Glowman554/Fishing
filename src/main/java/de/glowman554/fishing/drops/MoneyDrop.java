package de.glowman554.fishing.drops;

import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.glowman554.fishing.FishingMain;
import net.milkbowl.vault.economy.EconomyResponse;

public class MoneyDrop extends AbstractDrop
{
	private final double ammount;

	public MoneyDrop(double dropChance, double ammount)
	{
		super(dropChance);
		this.ammount = ammount;
	}

	@Override
	public ItemStack performAction(Player player)
	{
		ItemStack customItem = new ItemStack(Material.GOLD_INGOT);

		EconomyResponse res = FishingMain.getInstance().getEconomy().depositPlayer(player, ammount);
		if (res.type != EconomyResponse.ResponseType.SUCCESS)
		{
			FishingMain.getInstance().getLogger().log(Level.WARNING, "Failed to deposit " + ammount + " to player " + player.getName());
		}

		return customItem;
	}

}
