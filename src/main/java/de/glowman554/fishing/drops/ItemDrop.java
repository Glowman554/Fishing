package de.glowman554.fishing.drops;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemDrop extends AbstractDrop
{
	private final Material material;

	public ItemDrop(double dropChance, Material material)
	{
		super(dropChance);
		this.material = material;
	}

	@Override
	public ItemStack performAction(Player player)
	{
		ItemStack customItem = new ItemStack(material);
		return customItem;
	}

}
