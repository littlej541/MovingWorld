package com.elytradev.movingworld.common.chunk;

import com.google.common.base.MoreObjects;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LocatedBlock {
    public static final LocatedBlock AIR = new LocatedBlock(Blocks.AIR.getDefaultState(), BlockPos.ORIGIN);

    public final IBlockState state;
    public final TileEntity tile;
    public final BlockPos pos;
    public final BlockPos posNoOffset;

    public LocatedBlock(IBlockState state, BlockPos pos) {
        this(state, null, pos);
    }

    public LocatedBlock(IBlockState state, TileEntity tileentity, BlockPos pos) {
        this(state, tileentity, pos, null);
    }

    public LocatedBlock(IBlockState state, TileEntity tile, BlockPos pos, BlockPos posNoOffset) {
        this.state = state;
        this.tile = tile;
        this.pos = pos;
        this.posNoOffset = posNoOffset;
    }

    public LocatedBlock(NBTTagCompound tag, World world) {
        this.state = Block.getStateById(tag.getInt("block"));
        this.pos = BlockPos.fromLong(tag.getLong("pos"));
        this.posNoOffset = null;
        this.tile = world == null ? null : world.getTileEntity(this.pos);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("state", this.state)
                .add("tile", this.tile)
                .add("pos", this.pos)
                .add("posNoOffset", this.posNoOffset)
                .toString();
    }

    @Override
    public LocatedBlock clone() {
        return new LocatedBlock(this.state, this.tile, this.pos, this.posNoOffset);
    }

    public Block getBlock() {
        return this.state.getBlock();
    }

    public void writeToNBT(NBTTagCompound tag) {
        tag.putInt("block", Block.getStateId(this.state));
        tag.putLong("pos", this.pos.toLong());
    }
}
