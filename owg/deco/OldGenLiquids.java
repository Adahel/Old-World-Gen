// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package owg.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import owg.OWGGenHelper;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block

public class OldGenLiquids extends WorldGenerator
{
    private final Block liquidBlockId;

    public OldGenLiquids(Block i)
    {
        this.liquidBlockId = i;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if (OWGGenHelper.getBlock(world, i, j + 1, k) != Blocks.stone)
        {
            return false;
        }
        if (OWGGenHelper.getBlock(world, i, j - 1, k) != Blocks.stone)
        {
            return false;
        }
        if (OWGGenHelper.getBlock(world, i, j, k) != Blocks.air && OWGGenHelper.getBlock(world, i, j, k) != Blocks.stone)
        {
            return false;
        }
        int l = 0;
        if (OWGGenHelper.getBlock(world, i - 1, j, k) == Blocks.stone)
        {
            l++;
        }
        if (OWGGenHelper.getBlock(world, i + 1, j, k) == Blocks.stone)
        {
            l++;
        }
        if (OWGGenHelper.getBlock(world, i, j, k - 1) == Blocks.stone)
        {
            l++;
        }
        if (OWGGenHelper.getBlock(world, i, j, k + 1) == Blocks.stone)
        {
            l++;
        }
        int i1 = 0;
        if (OWGGenHelper.isAirBlock(world, i - 1, j, k))
        {
            i1++;
        }
        if (OWGGenHelper.isAirBlock(world, i + 1, j, k))
        {
            i1++;
        }
        if (OWGGenHelper.isAirBlock(world, i, j, k - 1))
        {
            i1++;
        }
        if (OWGGenHelper.isAirBlock(world, i, j, k + 1))
        {
            i1++;
        }
        if (l == 3 && i1 == 1)
        {
            BlockPos blockpos = new BlockPos(i, j, k);
            OWGGenHelper.setBlockWithNotify(world, blockpos, this.liquidBlockId);
            world.forceBlockUpdateTick(this.liquidBlockId, blockpos, random);
        }
        return true;
    }
}
