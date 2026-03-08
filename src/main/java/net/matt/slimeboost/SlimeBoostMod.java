package net.matt.slimeboost;

import net.fabricmc.api.ModInitializer;
import net.matt.slimeboost.block.ModBlocks;
import net.matt.slimeboost.item.ModItems;

public class SlimeBoostMod implements ModInitializer {

	public static final String MOD_ID = "slimeboost";

	@Override
	public void onInitialize() {

		ModBlocks.initialize();
		ModItems.initialize();

	}
}