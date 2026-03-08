package net.matt.slimeboost.block;

import net.matt.slimeboost.SlimeBoostMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

  public static final Block SPEED_BLOCK = new SpeedBlock(
      AbstractBlock.Settings.create()
          .mapColor(MapColor.LIGHT_BLUE)
          .strength(1.5f));

  public static void initialize() {

    Registry.register(
        Registries.BLOCK,
        Identifier.of(SlimeBoostMod.MOD_ID, "speed_block"),
        SPEED_BLOCK);
  }
}