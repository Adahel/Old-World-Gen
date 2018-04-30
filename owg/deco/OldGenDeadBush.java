package owg.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import owg.OWGGenHelper;

public class OldGenDeadBush extends WorldGenerator
{
    private BlockBush field_28058_a;

    public OldGenDeadBush(BlockBush i)
    {
        this.field_28058_a = i;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        Block b;
        for (; ((b = OWGGenHelper.getBlock(world, i, j, k)) == Blocks.air || b == Blocks.leaves) && j > 0; j--)
        {
        }
        for (int i1 = 0; i1 < 4; i1++)
        {
            int j1 = (i + random.nextInt(8)) - random.nextInt(8);
            int k1 = (j + random.nextInt(4)) - random.nextInt(4);
            int l1 = (k + random.nextInt(8)) - random.nextInt(8);
            if (OWGGenHelper.isAirBlock(world, j1, k1, l1) && OWGGenHelper.canBlockStay(world, j1, k1, l1, this.field_28058_a))
            {
                OWGGenHelper.setBlock(world, j1, k1, l1, this.field_28058_a);
            }
        }

        return true;
    }
}
