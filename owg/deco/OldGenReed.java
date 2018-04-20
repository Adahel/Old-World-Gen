package owg.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import owg.OWGGenHelper;

public class OldGenReed extends WorldGenerator
{

    public OldGenReed()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        for (int l = 0; l < 20; l++)
        {
            int i1 = (i + random.nextInt(4)) - random.nextInt(4);
            int j1 = j;
            int k1 = (k + random.nextInt(4)) - random.nextInt(4);
            if (!OWGGenHelper.isAirBlock(world, i1, j1, k1) || OWGGenHelper.getBlockMaterial(world, i1 - 1, j1 - 1, k1) != Material.water
                    && OWGGenHelper.getBlockMaterial(world, i1 + 1, j1 - 1, k1) != Material.water
                    && OWGGenHelper.getBlockMaterial(world, i1, j1 - 1, k1 - 1) != Material.water
                    && OWGGenHelper.getBlockMaterial(world, i1, j1 - 1, k1 + 1) != Material.water)
            {
                continue;
            }
            int l1 = 2 + random.nextInt(random.nextInt(3) + 1);
            for (int i2 = 0; i2 < l1; i2++)
            {
                if (this.canBlockStay(world, i1, j1 + i2, k1))
                {
                    OWGGenHelper.setBlock(world, i1, j1 + i2, k1, Blocks.reeds);
                }
            }

        }

        return true;
    }

    public boolean canBlockStay(World world, int i, int j, int k)
    {
        Block l = OWGGenHelper.getBlock(world, i, j - 1, k);
        if (l == Blocks.reeds)
        {
            return true;
        }
        if (l != Blocks.grass && l != Blocks.dirt)
        {
            return false;
        }
        if (OWGGenHelper.getBlockMaterial(world, i - 1, j - 1, k) == Material.water)
        {
            return true;
        }
        if (OWGGenHelper.getBlockMaterial(world, i + 1, j - 1, k) == Material.water)
        {
            return true;
        }
        if (OWGGenHelper.getBlockMaterial(world, i, j - 1, k - 1) == Material.water)
        {
            return true;
        }
        return OWGGenHelper.getBlockMaterial(world, i, j - 1, k + 1) == Material.water;
    }
}
