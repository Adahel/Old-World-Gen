// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package owg.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import owg.OWGGenHelper;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves,
//            BlockFlower

public class OldGenTallGrass extends WorldGenerator
{
    private IBlockState field_28060_a;

    public OldGenTallGrass(BlockTallGrass.EnumType type)
    {
        this.field_28060_a = Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, type);
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        Block b;
        for (; ((b = OWGGenHelper.getBlock(world, i, j, k)) == Blocks.air || b == Blocks.leaves) && j > 0; j--)
        {
        }
        for (int i1 = 0; i1 < 128; i1++)
        {
            int j1 = (i + random.nextInt(8)) - random.nextInt(8);
            int k1 = (j + random.nextInt(4)) - random.nextInt(4);
            int l1 = (k + random.nextInt(8)) - random.nextInt(8);
            if (OWGGenHelper.isAirBlock(world, j1, k1, l1) && OWGGenHelper.canBlockStay(world, j1, k1, l1, Blocks.tallgrass, this.field_28060_a))
            {
                OWGGenHelper.setBlockState(world, j1, k1, l1, this.field_28060_a);
            }
        }

        return true;
    }
}
