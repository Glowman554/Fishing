package de.glowman554.fishing.drops;

import java.util.ArrayList;
import java.util.logging.Level;

import de.glowman554.fishing.FishingMain;

public class DropManager
{
	private ArrayList<AbstractDrop> drops = new ArrayList<AbstractDrop>();
	private double currentSlice = 0;

	public void addDrop(AbstractDrop drop)
	{
		drop.setDropSliceBegin(currentSlice);
		currentSlice += drop.getDropChance();
		drop.setDropSliceEnd(currentSlice);
		
		drops.add(drop);
		
		FishingMain.getInstance().getLogger().log(Level.INFO, String.format("New drop (slice %f - %f) with chance %f %s", drop.getDropSliceBegin(), drop.getDropSliceEnd(), drop.getDropChance(), drop));
	}
	
	public void finish()
	{
		if (currentSlice != 100)
		{
			throw new IllegalStateException("Final drop slice not at 100! Was " + currentSlice);
		}
	}
	
	public AbstractDrop getRandomDrop()
	{
		double dropSlice = Math.random() * 100;
		
		for (AbstractDrop drop : drops)
		{
			if (drop.getDropSliceBegin() < dropSlice && drop.getDropSliceEnd() > dropSlice)
			{
				return drop;
			}
		}
		
		throw new IllegalStateException("Should be unreachable!");
	}
}
