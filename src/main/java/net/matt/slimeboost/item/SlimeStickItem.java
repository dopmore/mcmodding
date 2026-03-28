package net.matt.slimeboost.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.matt.slimeboost.util.Scheduler;

public class SlimeStickItem extends Item {

  private static final int MAX_DURABILITY = 64;

  public SlimeStickItem(Settings settings) {
    super(settings.maxDamage(MAX_DURABILITY)); // make item damageable
  }

  @Override
  public boolean isItemBarVisible(ItemStack stack) {
    return stack.isDamaged();
  }

  @Override
  public int getItemBarStep(ItemStack stack) {
    return Math.round((1f - ((float) stack.getDamage() / stack.getMaxDamage())) * 13f);
  }

  @Override
  public int getItemBarColor(ItemStack stack) {
    return 0x00FF00;
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    return TypedActionResult.pass(user.getStackInHand(hand));
  }

  @Override
  public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
    if (!(entity instanceof SlimeEntity slime)) {
      return ActionResult.PASS;
    }

    World world = entity.getWorld();
    if (!world.isClient) {
      ItemStack handStack = user.getStackInHand(hand);

      if (handStack.getDamage() > 0) {
        handStack.setDamage(handStack.getDamage() - 5);
      }

      if (slime.getSize() > 1) {
        slime.setSize(slime.getSize() - 1, true);
      } else {
        slime.kill();
      }
    }

    return ActionResult.success(world.isClient);
  }

  @Override
  public net.minecraft.util.ActionResult useOnBlock(ItemUsageContext context) {
    World world = context.getWorld();
    ItemStack stack = context.getStack();
    BlockPos pos = context.getBlockPos();

    if (world.isClient)
      return net.minecraft.util.ActionResult.SUCCESS;
    if (stack.getDamage() >= stack.getMaxDamage())
      return net.minecraft.util.ActionResult.FAIL;

    BlockState originalState = world.getBlockState(pos);
    BlockPos restorePos = pos.toImmutable();
    world.setBlockState(pos, Blocks.SLIME_BLOCK.getDefaultState());

    Scheduler.schedule(20, () -> {
      if (world.getBlockState(restorePos).isOf(Blocks.SLIME_BLOCK)) {
        world.setBlockState(restorePos, originalState);
      }
    });

    if (context.getPlayer() != null) {
      stack.setDamage(stack.getDamage() + 1);

    }

    return net.minecraft.util.ActionResult.SUCCESS;
  }

}