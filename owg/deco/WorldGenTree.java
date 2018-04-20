package owg.deco;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenTree extends WorldGenAbstractTree
{
    public WorldGenTree(boolean p_i45448_1_)
    {
        super(p_i45448_1_);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        return false;
    }
}
