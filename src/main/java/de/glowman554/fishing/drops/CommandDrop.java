package de.glowman554.fishing.drops;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandDrop extends AbstractDrop
{
	private final String command;

	public CommandDrop(double dropChance, String command)
	{
		super(dropChance);
		this.command = command;
	}

	@Override
	public ItemStack performAction(Player player)
	{
		ItemStack customItem = new ItemStack(Material.COMPASS);
		player.performCommand(command);
		
		return customItem;
	}
}
