package de.glowman554.fishing.listener;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import de.glowman554.fishing.FishingMain;

public class FishingListener implements Listener
{
	@EventHandler
	public void onPlayerFish(PlayerFishEvent event)
	{
		if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH)
		{
			if (event.getCaught().getLocation().getWorld().getName().equals(FishingMain.getInstance().getWorldName()))
			{
				Item caughtItem = (Item) event.getCaught();

				Player player = event.getPlayer();
				ItemStack customItem = FishingMain.getInstance().getDropManager().getRandomDrop().performAction(player);

				caughtItem.setItemStack(customItem);
			}
		}
	}
}
