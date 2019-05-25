package com.tridevmc.movingworld.api.rotation;

import net.minecraft.block.state.IBlockState;
import net.minecraft.state.IProperty;

/**
 * If a block is for whatever reason not using one of the pre-made rotation systems Minecraft
 * provides they can make their custom property extend this so MovingWorld will know how to rotate
 * it.
 */
public interface IRotationProperty {

    /**
     * Rotates the specified block state without effecting the world.
     *
     * @param blockState the blockstate to rotate.
     * @param ccw        counterClockwise?
     * @return The blockstate post rotation.
     */
    IBlockState rotate(IProperty property, IBlockState blockState, boolean ccw);

}
