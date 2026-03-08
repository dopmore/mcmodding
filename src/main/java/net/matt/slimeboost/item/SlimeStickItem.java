package net.matt.slimeboost.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class SlimeStickItem extends Item {

  public SlimeStickItem(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {

    if (!(context.getWorld() instanceof ServerWorld world)) {
      return ActionResult.SUCCESS;
    }

    BlockPos pos = context.getBlockPos();

    // Sava org block
    BlockState originalState = world.getBlockState(pos);

    // Replace with slime block
    world.setBlockState(pos, Blocks.SLIME_BLOCK.getDefaultState());

    // Replace with org Block
    world.getServer().execute(() -> {
      world.getServer().submit(() -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

        world.setBlockState(pos, originalState);
      });
    });

    return ActionResult.SUCCESS;
  }
}