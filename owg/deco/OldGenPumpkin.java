package owg.deco;

import java.util.Random;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import owg.OWGGenHelper;

public class OldGenPumpkin extends WorldGenerator
{

    public OldGenPumpkin()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        for (int l = 0; l < 64; l++)
        {
            int i1 = (i + random.nextInt(8)) - random.nextInt(8);
            int j1 = (j + random.nextInt(4)) - random.nextInt(4);
            int k1 = (k + random.nextInt(8)) - random.nextInt(8);
            if (OWGGenHelper.isAirBlock(world, i1, j1, k1) && OWGGenHelper.getBlock(world, i1, j1 - 1, k1) == Blocks.grass
                    && Blocks.pumpkin.canPlaceBlockAt(world, new BlockPos(i1, j1, k1)))
            {
                OWGGenHelper.setBlockState(world, j, k, l,
                        Blocks.pumpkin.getDefaultState().withProperty(BlockPumpkin.FACING, EnumFacing.Plane.HORIZONTAL.random(random)));
            }
        }

        return true;
    }
}
