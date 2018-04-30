package owg.map;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class MapGenOLD
{
    protected int range;
    protected Random rand;
    protected World worldObj;

    public MapGenOLD()
    {
        this.range = 8;
        this.rand = new Random();
    }

    public void generate(World world, int i, int j, ChunkPrimer chunk)
    {
        int k = this.range;
        this.worldObj = world;
        this.rand.setSeed(this.worldObj.getSeed());
        long l = (this.rand.nextLong() / 2L) * 2L + 1L;
        long l1 = (this.rand.nextLong() / 2L) * 2L + 1L;
        for (int i1 = i - k; i1 <= i + k; i1++)
        {
            for (int j1 = j - k; j1 <= j + k; j1++)
            {
                this.rand.setSeed((long) i1 * l + (long) j1 * l1 ^ this.worldObj.getSeed());
                this.recursiveGenerate(this.worldObj, i1, j1, i, j, chunk);
            }
        }
    }

    protected void recursiveGenerate(World world, int i, int j, int k, int l, ChunkPrimer chunk)
    {
    }
}
