package owg.world;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import owg.biomes.BiomeGenBase;
import owg.noise.OldNoiseGeneratorOctaves2;
import owg.support.Support;
import owg.util.CellNoise;
import owg.util.PerlinNoise;

public class ManagerOWG
{
    private OldNoiseGeneratorOctaves2 field_4194_e;
    private OldNoiseGeneratorOctaves2 field_4193_f;
    private OldNoiseGeneratorOctaves2 field_4192_g;
    public double[] temperature;
    public double[] humidity;
    public double[] field_4196_c;
    public BiomeGenBase[] field_4195_d;

    public ArrayList<BiomeGenBase> biomeList_snow;
    public ArrayList<BiomeGenBase> biomeList_cold;
    public ArrayList<BiomeGenBase> biomeList_hot;
    public ArrayList<BiomeGenBase> biomeList_wet;
    public ArrayList<BiomeGenBase> biomeList_small;
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
    }

    public ManagerOWG(World world, boolean remote, int biomes)
    {
        long seed = world.getSeed();

        if (remote)
        {
            this.field_4194_e = new OldNoiseGeneratorOctaves2(new Random(world.getSeed() * 9871L), 4);
            this.field_4193_f = new OldNoiseGeneratorOctaves2(new Random(world.getSeed() * 39811L), 4);
            this.field_4192_g = new OldNoiseGeneratorOctaves2(new Random(world.getSeed() * 543321L), 2);

            this.biomeList_snow = new ArrayList<BiomeGenBase>();
            this.biomeList_cold = new ArrayList<BiomeGenBase>();
            this.biomeList_hot = new ArrayList<BiomeGenBase>();
            this.biomeList_wet = new ArrayList<BiomeGenBase>();
            this.biomeList_small = new ArrayList<BiomeGenBase>();
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
                this.biomeList_small.addAll(Support.biomes_small);
            }

            if (biomes > 0)
            {
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
        }
    }

    public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair par1ChunkCoordIntPair)
    {
        return this.getBiomeGenAt(par1ChunkCoordIntPair.chunkXPos << 4, par1ChunkCoordIntPair.chunkZPos << 4);
    }

    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        return this.func_4069_a(par1, par2, 1, 1)[0];
    }

    public double getTemperature(int par1, int par2)
    {
        this.temperature = this.field_4194_e.func_4112_a(this.temperature, (double) par1, (double) par2, 1, 1, 0.02500000037252903D, 0.02500000037252903D,
                0.5D);
        return this.temperature[0];
    }

    public BiomeGenBase[] func_4069_a(int par1, int par2, int par3, int par4)
    {
        this.field_4195_d = this.loadBlockGeneratorData(this.field_4195_d, par1, par2, par3, par4);
        return this.field_4195_d;
    }

    public double[] getColdTemperatures(double ad[], int i, int j, int k, int l)
    {
        if (ad == null || ad.length < k * l)
        {
            ad = new double[k * l];
        }
        ad = this.field_4194_e.func_4112_a(ad, i, j, k, l, 0.02500000037252903D, 0.02500000037252903D, 0.25D);
        this.field_4196_c = this.field_4192_g.func_4112_a(this.field_4196_c, i, j, k, l, 0.25D, 0.25D, 0.58823529411764708D);
        int i1 = 0;
        for (int j1 = 0; j1 < k; j1++)
        {
            for (int k1 = 0; k1 < l; k1++)
            {
                double d = this.field_4196_c[i1] * 1.1000000000000001D + 0.5D;
                double d1 = 0.01D;
                double d2 = 1.0D - d1;
                double d3 = (ad[i1] * 0.14999999999999999D + 0.69999999999999996D) * d2 + d * d1;
                d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
                if (d3 < 0.0D)
                {
                    d3 = 0.0D;
                }
                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }
                ad[i1] = d3;
                i1++;
            }

        }

        return ad;
    }

    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        this.temperature = this.field_4194_e.func_4112_a(this.temperature, (double) par2, (double) par3, par4, par4, 0.02500000037252903D, 0.02500000037252903D,
                0.25D);
        this.humidity = this.field_4193_f.func_4112_a(this.humidity, (double) par2, (double) par3, par4, par4, 0.05000000074505806D, 0.05000000074505806D,
                0.3333333333333333D);
        this.field_4196_c = this.field_4192_g.func_4112_a(this.field_4196_c, (double) par2, (double) par3, par4, par4, 0.25D, 0.25D, 0.5882352941176471D);
        int i = 0;

        for (int j = 0; j < par4; ++j)
        {
            for (int k = 0; k < par5; ++k)
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
                par1ArrayOfBiomeGenBase[i++] = BiomeGenBase.getBiomeFromLookup(d3, d4);
            }
        }

        if (this.biomeSetting > 0)
        {
            float h;
            float c;
            int k11;
            int i2 = 0;
            float offX, offY;
            for (int j11 = 0; j11 < par4; j11++)
            {
                for (k11 = 0; k11 < par5; k11++)
                {
                    offX = this.perlin.noise2((par2 + j11) / 30F, (par3 + k11) / 30F) * 80 + this.perlin.noise2((par2 + j11) / 7F, (par3 + k11) / 7F) * 20;
                    offY = this.perlin.noise2((par2 + j11 + 1000) / 30F, (par3 + k11) / 30F) * 80
                            + this.perlin.noise2((par2 + j11 - 1000) / 7F, (par3 + k11) / 7F) * 20;
                    c = (this.biomecell.noise((par2 + j11 + offX + 1000) / 1000D, (par3 + k11 - offY) / 1000D, 1D) * 0.5f) + 0.5f;

                    if (this.isSmallEnabled && (this.biomecell.noise(par2 / 140D, par3 / 140D, 1D) * 0.5f) + 0.5f > 0.95f)
                    {
                        h = (this.biomecell.noise((par2 + j11 + offX + 2000) / 180D, (par3 + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListSmallLength;
                        par1ArrayOfBiomeGenBase[i2++] = this.biomeList_small.get((int) (h));
                    }
                    else if (c < 0.25f)
                    {
                        h = (this.biomecell.noise((par2 + j11 + offX + 2000) / 180D, (par3 + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListSnowLength;
                        par1ArrayOfBiomeGenBase[i2++] = this.biomeList_snow.get((int) (h));
                    }
                    else if (c < 0.5f)
                    {
                        h = (this.biomecell.noise((par2 + j11 + offX + 3000) / 180D, (par3 + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListColdLength;
                        par1ArrayOfBiomeGenBase[i2++] = this.biomeList_cold.get((int) (h));
                    }
                    else if (c < 0.75f)
                    {
                        h = (this.biomecell.noise((par2 + j11 + offX + 4000) / 180D, (par3 + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListHotLength;
                        par1ArrayOfBiomeGenBase[i2++] = this.biomeList_hot.get((int) (h));
                    }
                    else
                    {
                        h = (this.biomecell.noise((par2 + j11 + offX + 5000) / 180D, (par3 + k11 - offY) / 180D, 1D) * 0.5f) + 0.5f;
                        h = h < 0f ? 0f : h >= 0.9999999f ? 0.9999999f : h;
                        h *= this.biomeListWetLength;
                        par1ArrayOfBiomeGenBase[i2++] = this.biomeList_wet.get((int) (h));
                    }
                }
            }
        }
        return par1ArrayOfBiomeGenBase;
    }
}
