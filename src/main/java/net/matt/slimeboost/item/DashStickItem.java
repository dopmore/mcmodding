package net.matt.slimeboost.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DashStickItem extends Item {

  public DashStickItem(Settings settings) {
    super(settings);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

    if (!world.isClient) {
      Vec3d look = player.getRotationVec(1.0F);

      double dashStrength = 2.5;

      player.addVelocity(
          look.x * dashStrength,
          look.y * dashStrength,
          look.z * dashStrength);

      player.velocityModified = true;

      // 1 second cooldown
      player.getItemCooldownManager().set(this, 100);
    }

    return TypedActionResult.success(player.getStackInHand(hand));
  }
}