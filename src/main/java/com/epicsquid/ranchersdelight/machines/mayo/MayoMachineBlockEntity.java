package com.epicsquid.ranchersdelight.machines.mayo;

import com.epicsquid.ranchersdelight.machines.base.TickableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MayoMachineBlockEntity extends BlockEntity implements MenuProvider, TickableEntity {

	private static final int MAX_PROGRESS = 480;
	public final ItemStackHandler inv = new ItemStackHandler(2);
	private final LazyOptional<IItemHandler> invOp = LazyOptional.of(() -> inv);
	private int progress = 0;


	public MayoMachineBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
		super(pType, pPos, pBlockState);
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return invOp.cast();
		}
		return super.getCapability(cap);
	}

	@Override
	protected void saveAdditional(@NotNull CompoundTag tag) {
		super.saveAdditional(tag);
		tag.put("inv", inv.serializeNBT());
		tag.putInt("progress", progress);
	}

	@Override
	public void load(@NotNull CompoundTag tag) {
		super.load(tag);
		inv.deserializeNBT(tag.getCompound("inv"));
		progress = tag.getInt("progress");
	}

	public void sendToMenu(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(getBlockPos());
		buffer.writeNbt(getUpdateTag());
	}

	@NotNull
	@Override
	public Component getDisplayName() {
		return new TextComponent("Mayonnaise Machine");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
		return MayoMachineMenu.create(pContainerId, pPlayerInventory, this);
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgressScaled() {
		return progress / 20;
	}

	@Override
	public void tick(Level level, BlockPos pos, BlockState state) {
		if (++progress >= MAX_PROGRESS) {
			progress = 0;
			setChanged();
		}
	}
}
