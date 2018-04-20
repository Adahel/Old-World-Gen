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

    public abstract boolean generate(World var1, Random var2, int var3, int var4, int var5);

    public void func_517_a(double x, double y, double z)
    {
    }
}
