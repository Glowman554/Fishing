package de.glowman554.fishing.drops;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractDrop
{
	private final double dropChance;

	private double dropSliceBegin;
	private double dropSliceEnd;

	public AbstractDrop(double dropChance)
	{
		this.dropChance = dropChance;
	}

	public abstract ItemStack performAction(Player player);

	public double getDropChance()
	{
		return dropChance;
	}

	public double getDropSliceBegin()
	{
		return dropSliceBegin;
	}

	public double getDropSliceEnd()
	{
		return dropSliceEnd;
	}

	public void setDropSliceBegin(double dropSliceBegin)
	{
		this.dropSliceBegin = dropSliceBegin;
	}

	public void setDropSliceEnd(double dropSliceEnd)
	{
		this.dropSliceEnd = dropSliceEnd;
	}
}
