package net.matt.slimeboost.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.matt.slimeboost.SlimeBoostMod;
import net.matt.slimeboost.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

  public static final Item SLIME_STICK = new SlimeStickItem(new Item.Settings());

  public static final Item DASH_STICK = new DashStickItem(new Item.Settings());

  public static final Item SPEED_BLOCK_ITEM = new BlockItem(
      ModBlocks.SPEED_BLOCK,
      new Item.Settings());

  public static void initialize() {

    Registry.register(
        Registries.ITEM,
        Identifier.of(SlimeBoostMod.MOD_ID, "slime_stick"),
        SLIME_STICK);

    Registry.register(
        Registries.ITEM,
        Identifier.of(SlimeBoostMod.MOD_ID, "speed_block"),
        SPEED_BLOCK_ITEM);

    Registry.register(
        Registries.ITEM,
        Identifier.of(SlimeBoostMod.MOD_ID, "dash_stick"),
        DASH_STICK);

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
        .register(entries -> {
          entries.add(SLIME_STICK);
          entries.add(DASH_STICK);
        });

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
        .register(entries -> entries.add(SPEED_BLOCK_ITEM));

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
        .register(entries -> entries.add(SLIME_STICK));
  }
}