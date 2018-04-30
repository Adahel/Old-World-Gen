package owg.deco;

import java.util.Random;

import net.minecraft.world.World;

public abstract class WorldGenerator extends WorldGenTree
{
    public WorldGenerator()
    {
        super(false);
    }

    public WorldGenerator(boolean notify)
    {
        super(notify);
    }

    public abstract boolean generate(World worldIn, Random rand, int x, int y, int z);

    public void func_517_a(double x, double y, double z)
    {
    }
}
