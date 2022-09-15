package com.epicsquid.ranchersdelight.mixin;

import com.epicsquid.ranchersdelight.config.RanchersDelightConfig;
import com.epicsquid.ranchersdelight.init.RanchersDelightItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Pig.class)
public abstract class PigMixin extends Animal {

	private int truffleTime = this.random.nextInt(RanchersDelightConfig.SERVER_CONFIG.TRUFFLE_SPAWN_DELAY.get()) + (RanchersDelightConfig.SERVER_CONFIG.TRUFFLE_SPAWN_DELAY.get() / 2);

	protected PigMixin(EntityType<? extends Animal> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (!this.level.isClientSide && this.isAlive() && !this.isBaby() && --this.truffleTime <= 0) {
			this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			this.spawnAtLocation(RanchersDelightItems.TRUFFLE.get());
			this.truffleTime = this.random.nextInt(RanchersDelightConfig.SERVER_CONFIG.TRUFFLE_SPAWN_DELAY.get()) + (RanchersDelightConfig.SERVER_CONFIG.TRUFFLE_SPAWN_DELAY.get() / 2);
		}
	}
}
