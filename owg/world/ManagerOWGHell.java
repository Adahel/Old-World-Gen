package owg.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.biome.BiomeGenBase;

public class ManagerOWGHell extends ManagerOWG
{
    /** The biome generator object. */
    private BiomeGenBase biomeGenerator;
    /** The rainfall in the world */
    private float rainfall;

    public ManagerOWGHell(BiomeGenBase biomeIn, float rainfall)
    {
        this.biomeGenerator = biomeIn;
        this.rainfall = rainfall;
    }

    @Override
    public List<BiomeGenBase> getBiomesToSpawnIn()
    {
        return Arrays.asList(new BiomeGenBase[] { this.biomeGenerator });
    }

    @Override
    public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair chunkcoordintpair)
    {
        return this.biomeGenerator;
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int i, int j)
    {
        return this.biomeGenerator;
    }

    /**
     * Returns the biome generator
     */
    @Override
    public BiomeGenBase getBiomeGenerator(BlockPos pos)
    {
        return this.biomeGenerator;
    }

    /**
     * Returns an array of biomes for the location input.
     */
    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomes, int i, int j, int width, int height)
    {
        if (biomes == null || biomes.length < width * height)
        {
            biomes = new BiomeGenBase[width * height];
        }

        Arrays.fill(biomes, 0, width * height, this.biomeGenerator);
        return biomes;
    }

    /**
     * Returns a list of rainfall values for the specified blocks. Args: listToReuse, i, j, width, length.
     */
    @Override
    public float[] getRainfall(float[] listToReuse, int i, int j, int width, int length)
    {
        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new float[width * length];
        }

        Arrays.fill(listToReuse, 0, width * length, this.rainfall);
        return listToReuse;
    }

    /**
     * Returns biomes to use for the blocks and loads the other data like temperature and humidity onto the WorldChunkManager Args: oldBiomeList, i, j, width, depth
     */
    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] oldBiomeList, int i, int j, int width, int depth)
    {
        if (oldBiomeList == null || oldBiomeList.length < width * depth)
        {
            oldBiomeList = new BiomeGenBase[width * depth];
        }

        Arrays.fill(oldBiomeList, 0, width * depth, this.biomeGenerator);
        return oldBiomeList;
    }

    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse, i, y, width, length, cacheFlag (if false, don't check biomeCache to avoid infinite loop in
     * BiomeCacheBlock)
     */
    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int i, int j, int width, int length, boolean cacheFlag)
    {
        return this.loadBlockGeneratorData(listToReuse, i, j, width, length);
    }

    @Override
    public BlockPos findBiomePosition(int i, int j, int range, List<BiomeGenBase> biomes, Random random)
    {
        return biomes.contains(this.biomeGenerator) ? new BlockPos(i - range + random.nextInt(range * 2 + 1), 0, j - range + random.nextInt(range * 2 + 1))
                : null;
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    @Override
    public boolean areBiomesViable(int i, int j, int radius, List<BiomeGenBase> allowed)
    {
        return allowed.contains(this.biomeGenerator);
    }

    @Override
    public boolean areViableOceanMonumentBiomes(int i, int j, int radius, List<BiomeGenBase> allowed)
    {
        return allowed.contains(this.biomeGenerator);
    }
}