package owg.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import owg.OWGGenHelper;

public class DecoIndevHouse extends WorldGenerator
{
    private int typeID;

    public DecoIndevHouse(int type)
    {
        this.typeID = type;
    }

    @Override
    public boolean generate(World worldIn, Random rand, int x, int y, int z)
    {
        // STONE FLOOR
        for (int flx = -3 + x; flx < 4 + x; flx++)
        {
            for (int flz = -3 + z; flz < 4 + z; flz++)
            {
                OWGGenHelper.setBlockWithNotify(worldIn, flx, y - 1, flz, Blocks.cobblestone);
            }
        }
        for (int flx = -3 + x; flx < 4 + x; flx++)
        {
            for (int flz = -3 + z; flz < 4 + z; flz++)
            {
                OWGGenHelper.setBlockWithNotify(worldIn, flx, y - 2, flz, Blocks.cobblestone);
            }
        }
        for (int flx = -3 + x; flx < 4 + x; flx++)
        {
            for (int flz = -3 + z; flz < 4 + z; flz++)
            {
                OWGGenHelper.setBlockWithNotify(worldIn, flx, y - 3, flz, Blocks.cobblestone);
            }
        }

        // WOODEN FRAME
        if (this.typeID == 2)
        {
            for (int frx = -3 + x; frx < 4 + x; frx++)
            {
                for (int fry = 0 + y; fry < 4 + y; fry++)
                {
                    for (int frz = -3 + z; frz < 4 + z; frz++)
                    {
                        OWGGenHelper.setBlockWithNotify(worldIn, frx, fry, frz, Blocks.mossy_cobblestone);
                    }
                }
            }
        }
        else
        {
            for (int frx = -3 + x; frx < 4 + x; frx++)
            {
                for (int fry = 0 + y; fry < 4 + y; fry++)
                {
                    for (int frz = -3 + z; frz < 4 + z; frz++)
                    {
                        OWGGenHelper.setBlockWithNotify(worldIn, frx, fry, frz, Blocks.planks);
                    }
                }
            }
        }

        // GENERATE air
        for (int airx = -2 + x; airx < 3 + x; airx++)
        {
            for (int airy = 0 + y; airy < 3 + y; airy++)
            {
                for (int airz = -2 + z; airz < 3 + z; airz++)
                {
                    OWGGenHelper.setBlockWithNotify(worldIn, airx, airy, airz, Blocks.air);
                }
            }
        }

        // CREATE DOOR AND torchES
        if (OWGGenHelper.isAirBlock(worldIn, x - 4, y + 1, z) && !OWGGenHelper.isAirBlock(worldIn, x - 4, y - 1, z)
                || !OWGGenHelper.isAirBlock(worldIn, x - 4, y - 2, z))
        {
            OWGGenHelper.setBlockWithNotify(worldIn, x - 3, y, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x - 3, y + 1, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x - 4, y, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x - 4, y + 1, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z + 2, Blocks.torch);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z - 2, Blocks.torch);
        }
        else if (OWGGenHelper.isAirBlock(worldIn, x + 4, y + 1, z) && !OWGGenHelper.isAirBlock(worldIn, x + 4, y - 1, z)
                || !OWGGenHelper.isAirBlock(worldIn, x + 4, y - 2, z))
        {
            OWGGenHelper.setBlockWithNotify(worldIn, x + 3, y, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x + 3, y + 1, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x + 4, y, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x + 4, y + 1, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z + 2, Blocks.torch);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z - 2, Blocks.torch);
        }
        else if (OWGGenHelper.isAirBlock(worldIn, x, y + 1, z - 4) && !OWGGenHelper.isAirBlock(worldIn, x, y - 1, z - 4)
                || !OWGGenHelper.isAirBlock(worldIn, x, y - 2, z - 4))
        {
            OWGGenHelper.setBlockWithNotify(worldIn, x, y, z - 3, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z - 3, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y, z - 4, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z - 4, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x + 2, y + 1, z, Blocks.torch);
            OWGGenHelper.setBlockWithNotify(worldIn, x - 2, y + 1, z, Blocks.torch);
        }
        else if (OWGGenHelper.isAirBlock(worldIn, x, y + 1, z + 4) && !OWGGenHelper.isAirBlock(worldIn, x, y - 1, z + 4)
                || !OWGGenHelper.isAirBlock(worldIn, x, y - 2, z + 4))
        {
            OWGGenHelper.setBlockWithNotify(worldIn, x, y, z + 3, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z + 3, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y, z + 4, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z + 4, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x + 2, y + 1, z, Blocks.torch);
            OWGGenHelper.setBlockWithNotify(worldIn, x - 2, y + 1, z, Blocks.torch);
        }
        else
        {
            OWGGenHelper.setBlockWithNotify(worldIn, x - 3, y, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x - 3, y + 1, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x - 4, y, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x - 4, y + 1, z, Blocks.air);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z + 2, Blocks.torch);
            OWGGenHelper.setBlockWithNotify(worldIn, x, y + 1, z - 2, Blocks.torch);
        }
        return true;
    }
}
