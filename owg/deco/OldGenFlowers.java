package owg.deco;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import owg.OWGGenHelper;

public class OldGenFlowers extends WorldGenerator
{
    private BlockBush plantBlockId;

    public OldGenFlowers(BlockBush b)
    {
        this.plantBlockId = b;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        for (int l = 0; l < 64; l++)
        {
            int i1 = (i + random.nextInt(8)) - random.nextInt(8);
            int j1 = (j + random.nextInt(4)) - random.nextInt(4);
            int k1 = (k + random.nextInt(8)) - random.nextInt(8);

            if (this.plantBlockId == Blocks.red_mushroom || this.plantBlockId == Blocks.brown_mushroom)
            {
                if (OWGGenHelper.isAirBlock(world, i1, j1, k1) && this.canMushroomStay(world, i1, j1, k1))
                {
                    OWGGenHelper.setBlock(world, i1, j1, k1, this.plantBlockId);
                }
            }
            else
            {
                if (OWGGenHelper.isAirBlock(world, i1, j1, k1) && OWGGenHelper.canBlockStay(world, i1, j1, k1, this.plantBlockId))
                {
                    OWGGenHelper.setBlock(world, i1, j1, k1, this.plantBlockId);
                }
            }
        }

        return true;
    }

    public boolean canMushroomStay(World world, int i, int j, int k)
    {
        if (j < 0 || j >= 128)
        {
            return false;
        }
        else
        {
            return world.getLight(new BlockPos(i, j, k)) < 13 && OWGGenHelper.getBlock(world, i, j - 1, k).isOpaqueCube();
        }
    }
}
