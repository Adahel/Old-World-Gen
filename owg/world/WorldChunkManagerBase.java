package owg.world;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.biome.BiomeGenBase;

public interface WorldChunkManagerBase
{
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight);

    public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair chunkcoordintpair);

    public BiomeGenBase getBiomeGenAt(int x, int z);

    public double[] getTemperatures(double listToReuse[], int x, int z, int width, int length);
}