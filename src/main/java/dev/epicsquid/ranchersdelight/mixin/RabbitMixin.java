package dev.epicsquid.ranchersdelight.mixin;

import dev.epicsquid.ranchersdelight.config.Config;
import dev.epicsquid.ranchersdelight.register.ItemRegistry;
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

	private int getConfigRabbitWoolTime() {
		return Config.INSTANCE.getRANCHERS_DELIGHT_CONFIG().getRabbitWoolSpawnDelay().get();
	}

	private int getConfigRabbitFootTime() {
		return Config.INSTANCE.getRANCHERS_DELIGHT_CONFIG().getRabbitFootSpawnDelay().get();
	}

	private int rabbitWoolTime = this.random.nextInt(getConfigRabbitWoolTime()) + getConfigRabbitWoolTime();
	private int rabbitFootTime = this.random.nextInt(getConfigRabbitWoolTime()) + getConfigRabbitWoolTime();

	protected RabbitMixin(EntityType<? extends Animal> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@Inject(at = {@At("TAIL")}, method = "aiStep")
	public void aiStep(CallbackInfo ci) {
		if (!this.level.isClientSide && this.isAlive() && !this.isBaby()) {
			if (--this.rabbitWoolTime <= 0) {
				this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				this.spawnAtLocation(ItemRegistry.INSTANCE.getRabbitWool());
				this.rabbitWoolTime = this.random.nextInt(getConfigRabbitWoolTime()) + getConfigRabbitWoolTime();
			}
			if (--this.rabbitFootTime <= 0) {
				this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
				this.spawnAtLocation(Items.RABBIT_FOOT);
				this.rabbitWoolTime = this.random.nextInt(getConfigRabbitFootTime()) + getConfigRabbitFootTime();
			}
		}
	}
}
