package dev.epicsquid.ranchersdelight.mixin;

import dev.epicsquid.ranchersdelight.config.Config;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public abstract class ChickenMixin extends Animal {

	@Unique
	private int ranchersDelight$getConfigFeatherTime() {
		return Config.INSTANCE.getRANCHERS_DELIGHT_CONFIG().getFeatherSpawnDelay().get();
	}

	@Unique
	private int ranchersDelight$featherTime = this.random.nextInt(ranchersDelight$getConfigFeatherTime()) + (ranchersDelight$getConfigFeatherTime() / 4);

	protected ChickenMixin(EntityType<? extends Animal> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Inject(at = {@At("TAIL")}, method = "aiStep")
	public void aiStep(CallbackInfo ci) {
		if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && --this.ranchersDelight$featherTime <= 0) {
			this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.spawnAtLocation(Items.FEATHER);
			this.ranchersDelight$featherTime = this.random.nextInt(ranchersDelight$getConfigFeatherTime()) + (ranchersDelight$getConfigFeatherTime() / 4);
		}
	}
}
