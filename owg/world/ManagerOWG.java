package owg.world;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.biomes.BiomeList;
import owg.noise.OldNoiseGeneratorOctaves2;
import owg.support.Support;
import owg.util.CellNoise;
import owg.util.PerlinNoise;

public class ManagerOWG extends WorldChunkManager implements WorldChunkManagerBase
{
    public static List<BiomeGenBase> allowedBiomes = Lists.newArrayList(BiomeGenBase.forest, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.taigaHills,
            BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills);
    public static List<BiomeGenBase> allowedBiomesBeta = Lists.newArrayList(BiomeList.OLDforest, BiomeList.OLDplains, BiomeList.OLDtaiga,
            BiomeList.OLDrainforest, BiomeList.OLDseasonalForest, BiomeList.OLDshrubland, BiomeList.OLDtundra);
    private ManagerOWG genBiomes;
    /** The biome list. */
    private BiomeCache biomeCache;
    private List<BiomeGenBase> biomesToSpawnIn;
    private OldNoiseGeneratorOctaves2 field_4194_e;
    private OldNoiseGeneratorOctaves2 field_4193_f;
    private OldNoiseGeneratorOctaves2 field_4192_g;
    public double[] temperature;
    public double[] humidity;
    public double[] field_4196_c;
    public BiomeGenBase[] field_4195_d;

    public List<BiomeGenBase> biomeList_snow;
    public List<BiomeGenBase> biomeList_cold;
    public List<BiomeGenBase> biomeList_hot;
    public List<BiomeGenBase> biomeList_wet;
    public List<BiomeGenBase> biomeList_small;
    public int biomeListSnowLength;
    public int biomeListColdLength;
    public int biomeListHotLength;
    public int biomeListWetLength;
    public int biomeListSmallLength;
    public int biomeSetting;
    public boolean isSmallEnabled;

    private PerlinNoise perlin;
    private CellNoise biomecell;

    protected ManagerOWG()
    {
        this.genBiomes = this;
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = Lists.<BiomeGenBase> newArrayList();
    }

    public ManagerOWG(long seed, WorldType p_i45744_3_, String p_i45744_4_)
    {
        this();
    }

    public ManagerOWG(World worldIn)
    {
        this(worldIn.getSeed(), worldIn.getWorldInfo().getTerrainType(), worldIn.getWorldInfo().getGeneratorOptions());
    }

    public ManagerOWG(World worldIn, boolean isRemote, int biomes)
    {
        this(worldIn);
        long seed = worldIn.getSeed();

        if (isRemote)
        {
            this.field_4194_e = new OldNoiseGeneratorOctaves2(new Random(seed * 9871L), 4);
            this.field_4193_f = new OldNoiseGeneratorOctaves2(new Random(seed * 39811L), 4);
            this.field_4192_g = new OldNoiseGeneratorOctaves2(new Random(seed * 0x84a59L), 2);

            this.biomeList_snow = Lists.<BiomeGenBase> newArrayList();
            this.biomeList_cold = Lists.<BiomeGenBase> newArrayList();
            this.biomeList_hot = Lists.<BiomeGenBase> newArrayList();
            this.biomeList_wet = Lists.<BiomeGenBase> newArrayList();
            this.biomeList_small = Lists.<BiomeGenBase> newArrayList();

            this.biomeSetting = biomes;
            this.perlin = new PerlinNoise(seed);
            this.biomecell = new CellNoise(seed, (short) 0);

            if (biomes == 1)
            {
                this.biomeList_snow.add(BiomeGenBase.coldTaiga);
                this.biomeList_snow.add(BiomeGenBase.coldTaigaHills);
                this.biomeList_snow.add(BiomeGenBase.icePlains);
                this.biomeList_snow.add(BiomeGenBase.iceMountains);
                this.biomeList_snow.add(BiomeGenBase.coldTaiga);
                this.biomeList_snow.add(BiomeGenBase.coldTaigaHills);

                this.biomeList_cold.add(BiomeGenBase.plains);
                this.biomeList_cold.add(BiomeGenBase.extremeHills);
                this.biomeList_cold.add(BiomeGenBase.forest);
                this.biomeList_cold.add(BiomeGenBase.taiga);
                this.biomeList_cold.add(BiomeGenBase.forestHills);
                this.biomeList_cold.add(BiomeGenBase.taigaHills);
                this.biomeList_cold.add(BiomeGenBase.extremeHillsEdge);
                this.biomeList_cold.add(BiomeGenBase.birchForest);
                this.biomeList_cold.add(BiomeGenBase.birchForestHills);
                this.biomeList_cold.add(BiomeGenBase.megaTaiga);
                this.biomeList_cold.add(BiomeGenBase.megaTaigaHills);
                this.biomeList_cold.add(BiomeGenBase.extremeHillsPlus);

                this.biomeList_hot.add(BiomeGenBase.desert);
                this.biomeList_hot.add(BiomeGenBase.desertHills);
                this.biomeList_hot.add(BiomeGenBase.savanna);
                this.biomeList_hot.add(BiomeGenBase.savannaPlateau);
                this.biomeList_hot.add(BiomeGenBase.mesa);
                this.biomeList_hot.add(BiomeGenBase.mesaPlateau_F);
                this.biomeList_hot.add(BiomeGenBase.mesaPlateau);
                this.biomeList_hot.add(BiomeGenBase.savanna);
                this.biomeList_hot.add(BiomeGenBase.savannaPlateau);

                this.biomeList_wet.add(BiomeGenBase.swampland);
                this.biomeList_wet.add(BiomeGenBase.jungle);
                this.biomeList_wet.add(BiomeGenBase.jungleHills);
                this.biomeList_wet.add(BiomeGenBase.jungleEdge);
                this.biomeList_wet.add(BiomeGenBase.roofedForest);
                this.biomeList_wet.add(BiomeGenBase.jungleEdge);
            }
            else if (biomes == 2)
            {
                this.biomeList_snow.addAll(Support.biomes_snow);
                this.biomeList_cold.addAll(Support.biomes_cold);
                this.biomeList_hot.addAll(Support.biomes_hot);
                this.biomeList_wet.addAll(Support.biomes_wet);
            }

            if (biomes > 0)
            {
                this.biomesToSpawnIn.addAll(allowedBiomes);

                this.biomeListSnowLength = this.biomeList_snow.size();
                this.biomeListColdLength = this.biomeList_cold.size();
                this.biomeListHotLength = this.biomeList_hot.size();
                this.biomeListWetLength = this.biomeList_wet.size();
                this.biomeListSmallLength = this.biomeList_small.size();

                if (this.biomeListSmallLength > 0)
                {
                    this.isSmallEnabled = true;
                }
            }
            else
            {
                this.biomesToSpawnIn.addAll(allowedBiomesBeta);
            }
        }
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = IntCache.getIntCache(areaWidth * areaHeight);

        this.temperature = this.field_4194_e.func_4112_a(this.temperature, (double) areaX, (double) areaY, areaWidth, areaWidth, 0.02500000037252903D,
                0.02500000037252903D,
                0.25D);
        this.humidity = this.field_4193_f.func_4112_a(this.humidity, (double) areaX, (double) areaY, areaWidth, areaWidth, 0.05000000074505806D,
                0.05000000074505806D,
                0.3333333333333333D);
        this.field_4196_c = this.field_4192_g.func_4112_a(this.field_4196_c, (double) areaX, (double) areaY, areaWidth, areaWidth, 0.25D, 0.25D,
                0.5882352941176471D);
        int i = 0;

        for (int j = 0; j < areaWidth; ++j)
        {
            for (int k = 0; k < areaHeight; ++k)
            {
                double d0 = this.field_4196_c[i] * 1.1D + 0.5D;
                double d1 = 0.01D;
                double d2 = 1.0D - d1;
                double d3 = (this.temperature[i] * 0.15D + 0.7D) * d2 + d0 * d1;
                d1 = 0.002D;
                d2 = 1.0D - d1;
                double d4 = (this.humidity[i] * 0.15D + 0.5D) * d2 + d0 * d1;
                d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
                if (d3 < 0.0D)
                {
                    d3 = 0.0D;
                }

                if (d4 < 0.0D)
                {
                    d4 = 0.0D;
                }

                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }

                if (d4 > 1.0D)
                {
                    d4 = 1.0D;
                }

                this.temperature[i] = d3;
                this.humidity[i] = d4;
                aint[i++] = BiomeList.getBiomeFromLookup(d3, d4);
            }
        }

        if (this.biomeSetting > 0)
        {
            float h;
            float c;
            int k11;
            int i2 = 0;
            float offX, offY;
            for (int j11 = 0; j11 < areaWidth; j11++)
            {
                for (k11 = 0; k11 < areaHeight; k11++)
                {
                    offX = this.perlin.noise2((areaX + j11) / 30F, (areaY + k11) / 30F) * 80 + this.perlin.noise2((areaX + j11) / 7F, (areaY + k11) / 7F) * 20;
                    offY = this.perlin.noise2((areaX + j11 + 1000) / 30F, (areaY + k11) / 30F) * 80
                            + this.perlin.noise2((areaX + j11 - 1000) / 7F, (areaY + k11) / 7F) * 20;
                    c = (this.biomecell.noise((areaX + j11 + offX + 1000) / 1000D, (areaY + k11 - offY) / 1000D, 1D) * 0.5f) + 0.5f;

                    if (this.isSmallEnabled && (this.biomecell.noise(areaX / 140D, areaY / 140D, 1D) * 0.5f) + 0.5f > 0.95f)
                    {
                        h = (this.biomecell.noise((areaX + j11 + offX + 2000) / 180D, (areaY + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListSmallLength;
                        aint[i2++] = this.biomeList_small.get((int) (h)).biomeID;
                    }
                    else if (c < 0.25f)
                    {
                        h = (this.biomecell.noise((areaX + j11 + offX + 2000) / 180D, (areaY + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListSnowLength;
                        aint[i2++] = this.biomeList_snow.get((int) (h)).biomeID;
                    }
                    else if (c < 0.5f)
                    {
                        h = (this.biomecell.noise((areaX + j11 + offX + 3000) / 180D, (areaY + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListColdLength;
                        aint[i2++] = this.biomeList_cold.get((int) (h)).biomeID;
                    }
                    else if (c < 0.75f)
                    {
                        h = (this.biomecell.noise((areaX + j11 + offX + 4000) / 180D, (areaY + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListHotLength;
                        aint[i2++] = this.biomeList_hot.get((int) (h)).biomeID;
                    }
                    else
                    {
                        h = (this.biomecell.noise((areaX + j11 + offX + 5000) / 180D, (areaY + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListWetLength;
                        aint[i2++] = this.biomeList_wet.get((int) (h)).biomeID;
                    }
                }
            }
        }
        return aint;
    }

    @Override
    public List<BiomeGenBase> getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    @Override
    public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair chunkcoordintpair)
    {
        return this.getBiomeGenAt(chunkcoordintpair.chunkXPos << 4, chunkcoordintpair.chunkZPos << 4);
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int x, int z)
    {
        return this.getBiomeGenerator(new BlockPos(x, 64, z));
    }

    /**
     * Returns the biome generator
     */
    @Override
    public BiomeGenBase getBiomeGenerator(BlockPos pos)
    {
        return this.getBiomeGenerator(pos, (BiomeGenBase) null);
    }

    @Override
    public BiomeGenBase getBiomeGenerator(BlockPos pos, BiomeGenBase biomeGenBaseIn)
    {
        return this.biomeCache.func_180284_a(pos.getX(), pos.getZ(), biomeGenBaseIn);
    }

    @Override
    public double[] getTemperatures(double listToReuse[], int x, int z, int width, int length)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new double[width * length];
        }

        listToReuse = this.field_4194_e.func_4112_a(listToReuse, x, z, width, length, 0.02500000037252903D, 0.02500000037252903D, 0.25D);
        this.field_4196_c = this.field_4192_g.func_4112_a(this.field_4196_c, x, z, width, length, 0.25D, 0.25D, 0.58823529411764708D);
        int i1 = 0;
        for (int j1 = 0; j1 < width; j1++)
        {
            for (int k1 = 0; k1 < length; k1++)
            {
                double d = this.field_4196_c[i1] * 1.1000000000000001D + 0.5D;
                double d1 = 0.01D;
                double d2 = 1.0D - d1;
                double d3 = (listToReuse[i1] * 0.14999999999999999D + 0.69999999999999996D) * d2 + d * d1;
                d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
                if (d3 < 0.0D)
                {
                    d3 = 0.0D;
                }
                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }
                listToReuse[i1] = d3;
                i1++;
            }
        }

        return listToReuse;
    }

    /**
     * Returns a list of rainfall values for the specified blocks. Args: listToReuse, x, z, width, length.
     */
    @Override
    public float[] getRainfall(float[] listToReuse, int x, int z, int width, int length)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new float[width * length];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, length);

        for (int i = 0; i < width * length; ++i)
        {
            try
            {
                float f = (float) BiomeGenBase.getBiomeFromBiomeList(aint[i], BiomeGenBase.plains).getIntRainfall() / 65536.0F;

                if (f > 1.0F)
                {
                    f = 1.0F;
                }

                listToReuse[i] = f;
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("DownfallBlock");
                crashreportcategory.addCrashSection("biome id", Integer.valueOf(i));
                crashreportcategory.addCrashSection("downfalls[] size", Integer.valueOf(listToReuse.length));
                crashreportcategory.addCrashSection("x", Integer.valueOf(x));
                crashreportcategory.addCrashSection("z", Integer.valueOf(z));
                crashreportcategory.addCrashSection("w", Integer.valueOf(width));
                crashreportcategory.addCrashSection("h", Integer.valueOf(length));
                throw new ReportedException(crashreport);
            }
        }

        return listToReuse;
    }

    /**
     * Return an adjusted version of a given temperature based on the y height
     */
    @Override
    @SideOnly(Side.CLIENT)
    public float getTemperatureAtHeight(float p_76939_1_, int p_76939_2_)
    {
        return p_76939_1_;
    }

    /**
     * Returns an array of biomes for the location input.
     */
    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height)
        {
            biomes = new BiomeGenBase[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        try
        {
            for (int i = 0; i < width * height; ++i)
            {
                biomes[i] = BiomeGenBase.getBiomeFromBiomeList(aint[i], BiomeGenBase.plains);
            }

            return biomes;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
            crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(biomes.length));
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(z));
            crashreportcategory.addCrashSection("w", Integer.valueOf(width));
            crashreportcategory.addCrashSection("h", Integer.valueOf(height));
            throw new ReportedException(crashreport);
        }
    }

    /**
     * Returns biomes to use for the blocks and loads the other data like temperature and humidity onto the WorldChunkManager Args: oldBiomeList, x, z, width, depth
     */
    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] oldBiomeList, int x, int z, int width, int depth)
    {
        return this.getBiomeGenAt(oldBiomeList, x, z, width, depth, true);
    }

    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false, don't check biomeCache to avoid infinite loop in
     * BiomeCacheBlock)
     */
    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new BiomeGenBase[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0)
        {
            BiomeGenBase[] abiomegenbase = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiomegenbase, 0, listToReuse, 0, width * length);
            return listToReuse;
        }
        else
        {
            int[] aint = this.genBiomes.getInts(x, z, width, length);

            for (int i = 0; i < width * length; ++i)
            {
                listToReuse[i] = BiomeGenBase.getBiomeFromBiomeList(aint[i], BiomeGenBase.plains);
            }

            return listToReuse;
        }
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    @Override
    public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List<BiomeGenBase> p_76940_4_)
    {
        IntCache.resetIntCache();
        int i = p_76940_1_ - p_76940_3_ >> 2;
        int j = p_76940_2_ - p_76940_3_ >> 2;
        int k = p_76940_1_ + p_76940_3_ >> 2;
        int l = p_76940_2_ + p_76940_3_ >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.genBiomes.getInts(i, j, i1, j1);

        try
        {
            for (int k1 = 0; k1 < i1 * j1; ++k1)
            {
                BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k1]);

                if (!p_76940_4_.contains(biomegenbase))
                {
                    return false;
                }
            }

            return true;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Manager");
            crashreportcategory.addCrashSection("Manager", this.genBiomes.toString());
            crashreportcategory.addCrashSection("x", Integer.valueOf(p_76940_1_));
            crashreportcategory.addCrashSection("z", Integer.valueOf(p_76940_2_));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(p_76940_3_));
            crashreportcategory.addCrashSection("allowed", p_76940_4_);
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public BlockPos findBiomePosition(int x, int z, int range, List<BiomeGenBase> biomes, Random random)
    {
        IntCache.resetIntCache();
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.genBiomes.getInts(i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for (int l1 = 0; l1 < i1 * j1; ++l1)
        {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[l1]);

            if (biomes.contains(biomegenbase) && (blockpos == null || random.nextInt(k1 + 1) == 0))
            {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }

        return blockpos;
    }

    /**
     * Calls the WorldChunkManager's biomeCache.cleanupCache()
     */
    @Override
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}