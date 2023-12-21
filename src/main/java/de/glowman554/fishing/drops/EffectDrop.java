package de.glowman554.fishing.drops;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectDrop extends AbstractDrop
{
	private final PotionEffectType effectType;

	public EffectDrop(double dropChance, PotionEffectType effectType)
	{
		super(dropChance);
		this.effectType = effectType;
	}

	@Override
	public ItemStack performAction(Player player)
	{
		ItemStack customItem = new ItemStack(Material.GLASS_BOTTLE);

		PotionEffect effect = new PotionEffect(effectType, 60 * 20, 0);
		player.addPotionEffect(effect);
		
		return customItem;
	}

}
