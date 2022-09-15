package com.epicsquid.ranchersdelight.mixin;

import com.epicsquid.ranchersdelight.config.RanchersDelightConfig;
import com.epicsquid.ranchersdelight.init.RanchersDelightItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Rabbit.class)
public abstract class RabbitMixin extends Animal {

	private int rabbitWoolTime = this.random.nextInt(RanchersDelightConfig.SERVER_CONFIG.RABBIT_WOOL_SPAWN_DELAY.get()) + RanchersDelightConfig.SERVER_CONFIG.RABBIT_WOOL_SPAWN_DELAY.get();
	private int rabbitFootTime = this.random.nextInt(RanchersDelightConfig.SERVER_CONFIG.RABBIT_FOOT_SPAWN_DELAY.get()) + RanchersDelightConfig.SERVER_CONFIG.RABBIT_FOOT_SPAWN_DELAY.get();

	protected RabbitMixin(EntityType<? extends Animal> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Inject(at = {@At("TAIL")}, method = "aiStep")
	public void aiStep(CallbackInfo ci) {
		if (!this.level.isClientSide && this.isAlive() && !this.isBaby()) {
			if (--this.rabbitWoolTime <= 0) {
				this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				this.spawnAtLocation(RanchersDelightItems.RABBIT_WOOL.get());
				this.rabbitWoolTime = this.random.nextInt(RanchersDelightConfig.SERVER_CONFIG.RABBIT_WOOL_SPAWN_DELAY.get()) + RanchersDelightConfig.SERVER_CONFIG.RABBIT_WOOL_SPAWN_DELAY.get();
			}
			if (--this.rabbitFootTime <= 0) {
				this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				this.spawnAtLocation(Items.RABBIT_FOOT);
				this.rabbitWoolTime = this.random.nextInt(RanchersDelightConfig.SERVER_CONFIG.RABBIT_FOOT_SPAWN_DELAY.get()) + RanchersDelightConfig.SERVER_CONFIG.RABBIT_FOOT_SPAWN_DELAY.get();
			}
		}
	}
}
