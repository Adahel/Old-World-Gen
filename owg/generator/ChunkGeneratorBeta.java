package owg.generator;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import owg.OWGGenHelper;
import owg.biomes.BiomeList;
import owg.data.DungeonLoot;
import owg.deco.OldGenCactus;
import owg.deco.OldGenClay;
import owg.deco.OldGenDeadBush;
import owg.deco.OldGenDungeons;
import owg.deco.OldGenFlowers;
import owg.deco.OldGenLakes;
import owg.deco.OldGenLiquids;
import owg.deco.OldGenMinable;
import owg.deco.OldGenPumpkin;
import owg.deco.OldGenReed;
import owg.deco.OldGenTallGrass;
import owg.deco.WorldGenerator;
import owg.map.MapGenOLD;
import owg.map.MapGenOLDCaves;
import owg.noise.NoiseOctavesBeta;
import owg.world.ManagerOWG;

public class ChunkGeneratorBeta implements IChunkProvider
{
    private Random rand;
    private Random rand2;

    private NoiseOctavesBeta field_912_k;
    private NoiseOctavesBeta field_911_l;
    private NoiseOctavesBeta field_910_m;
    private NoiseOctavesBeta field_909_n;
    private NoiseOctavesBeta field_908_o;
    public NoiseOctavesBeta field_922_a;
    public NoiseOctavesBeta field_921_b;
    public NoiseOctavesBeta mobSpawnerNoise;
    private NoiseGeneratorPerlin field_147430_m;

    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private double field_4180_q[];
    private double sandNoise[];
    private double gravelNoise[];
    private double stoneNoise[];
    private MapGenOLD field_902_u;
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenVillage villageGenerator = new MapGenVillage();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private BiomeGenBase biomesForGeneration[];
    private int biomeSettings;
    private int ravine;
    double field_4185_d[];
    double field_4184_e[];
    double field_4183_f[];
    double field_4182_g[];
    double field_4181_h[];
    int field_914_i[][];
    private double generatedTemperatures[];
    private final ManagerOWG chunkManager;

    public ChunkGeneratorBeta(World world, long l, boolean isEnabled, int bSettings, int bRavine)
    {
        this.sandNoise = new double[256];
        this.gravelNoise = new double[256];
        this.stoneNoise = new double[256];
        this.field_902_u = new MapGenOLDCaves();
        this.field_914_i = new int[32][32];
        this.worldObj = world;
        world.setSeaLevel(64);
        this.rand = new Random(l);
        this.rand2 = new Random(l);
        this.mapFeaturesEnabled = isEnabled;
        this.biomeSettings = bSettings;
        this.ravine = bRavine;
        this.field_912_k = new NoiseOctavesBeta(this.rand, 16);
        this.field_911_l = new NoiseOctavesBeta(this.rand, 16);
        this.field_910_m = new NoiseOctavesBeta(this.rand, 8);
        this.field_909_n = new NoiseOctavesBeta(this.rand, 4);
        this.field_908_o = new NoiseOctavesBeta(this.rand, 4);
        this.field_922_a = new NoiseOctavesBeta(this.rand, 10);
        this.field_921_b = new NoiseOctavesBeta(this.rand, 16);
        this.mobSpawnerNoise = new NoiseOctavesBeta(this.rand, 8);
        this.field_147430_m = new NoiseGeneratorPerlin(this.rand, 4);
        this.chunkManager = (ManagerOWG) this.worldObj.getWorldChunkManager();

        DungeonLoot.init(l);
    }

    public void generateTerrain(int i, int j, ChunkPrimerBeta chunk, double ad[])
    {
        byte byte0 = 4;
        byte byte1 = 64;
        int k = byte0 + 1;
        byte byte2 = 17;
        int l = byte0 + 1;
        this.field_4180_q = this.func_4061_a(this.field_4180_q, i * byte0, 0, j * byte0, k, byte2, l);
        for (int i1 = 0; i1 < byte0; i1++)
        {
            for (int j1 = 0; j1 < byte0; j1++)
            {
                for (int k1 = 0; k1 < 16; k1++)
                {
                    double d = 0.125D;
                    double d1 = this.field_4180_q[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d2 = this.field_4180_q[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d3 = this.field_4180_q[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d4 = this.field_4180_q[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d5 = (this.field_4180_q[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d1) * d;
                    double d6 = (this.field_4180_q[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d2) * d;
                    double d7 = (this.field_4180_q[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d3) * d;
                    double d8 = (this.field_4180_q[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d4) * d;
                    for (int l1 = 0; l1 < 8; l1++)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i2 = 0; i2 < 4; i2++)
                        {
                            int j2 = i2 + i1 * 4 << 12 | 0 + j1 * 4 << 8 | k1 * 8 + l1;
                            short short1 = 256;
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for (int k2 = 0; k2 < 4; k2++)
                            {
                                double d17 = ad[(i1 * 4 + i2) * 16 + (j1 * 4 + k2)];
                                Block l2 = null;
                                if (k1 * 8 + l1 < byte1)
                                {
                                    if (d17 < 0.5D && k1 * 8 + l1 >= byte1 - 1 && this.biomeSettings == 0)
                                    {
                                        l2 = Blocks.ice;
                                    }
                                    else
                                    {
                                        l2 = Blocks.water;
                                    }
                                }
                                if (d15 > 0.0D)
                                {
                                    l2 = Blocks.stone;
                                }
                                if (l2 != null)
                                {
                                    chunk.setBlockState(j2, l2.getDefaultState());
                                }
                                j2 += short1;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }

                }

            }

        }

    }

    public void replaceBlocksForBiome(int i, int j, ChunkPrimerBeta chunk, BiomeGenBase abiomegenbase[])
    {
        if (this.biomeSettings > 0)
        {
            // double d0 = 0.03125D;
            this.stoneNoise = this.field_147430_m.func_151599_a(this.stoneNoise, (double) (i * 16), (double) (j * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);
            // stoneNoise = field_908_o.generateNoiseOctaves(stoneNoise, i * 16, j * 16, 0.0D, 16, 16, 1, 0.03125D * 2D, 0.03125D * 2D, 0.03125D * 2D);
            int l;
            for (int k = 0; k < 16; k++)
            {
                for (l = 0; l < 16; l++)
                {
                    BiomeGenBase biomegenbase = abiomegenbase[k + l * 16];
                    biomegenbase.genTerrainBlocks(this.worldObj, this.rand, chunk, i * 16 + k, j * 16 + l, this.stoneNoise[l + k * 16]);
                }
            }
        }
        else
        {
            byte byte0 = 64;
            double d = 0.03125D;
            this.sandNoise = this.field_909_n.generateNoiseOctaves(this.sandNoise, i * 16, j * 16, 0.0D, 16, 16, 1, d, d, 1.0D);
            this.gravelNoise = this.field_909_n.generateNoiseOctaves(this.gravelNoise, i * 16, 109.0134D, j * 16, 16, 1, 16, d, 1.0D, d);
            this.stoneNoise = this.field_908_o.generateNoiseOctaves(this.stoneNoise, i * 16, j * 16, 0.0D, 16, 16, 1, d * 2D, d * 2D, d * 2D);
            for (int k = 0; k < 16; k++)
            {
                for (int l = 0; l < 16; l++)
                {
                    BiomeList biomegenbase = (BiomeList) abiomegenbase[k + l * 16];

                    boolean flag = this.sandNoise[k + l * 16] + this.rand.nextDouble() * 0.20000000000000001D > 0.0D;
                    boolean flag1 = this.gravelNoise[k + l * 16] + this.rand.nextDouble() * 0.20000000000000001D > 3D;
                    int i1 = (int) (this.stoneNoise[k + l * 16] / 3D + 3D + this.rand.nextDouble() * 0.25D);
                    int j1 = -1;
                    Block byte1 = biomegenbase.topBlock;
                    Block byte2 = biomegenbase.fillerBlock;
                    for (int k1 = 127; k1 >= 0; k1--)
                    {
                        int l1 = l << 12 | k << 8 | k1;
                        if (k1 <= 0 + this.rand.nextInt(5))
                        {
                            chunk.setBlockState(l1, Blocks.bedrock.getDefaultState());
                            continue;
                        }
                        Block byte3 = chunk.getBlockState(l1).getBlock();
                        if (byte3 == Blocks.air)
                        {
                            j1 = -1;
                            continue;
                        }
                        if (byte3 != Blocks.stone)
                        {
                            continue;
                        }
                        if (j1 == -1)
                        {
                            if (i1 <= 0)
                            {
                                byte1 = Blocks.air;
                                byte2 = Blocks.stone;
                            }
                            else if (k1 >= byte0 - 4 && k1 <= byte0 + 1)
                            {
                                byte1 = biomegenbase.topBlock;
                                byte2 = biomegenbase.fillerBlock;
                                if (flag1)
                                {
                                    byte1 = Blocks.air;
                                }
                                if (flag1)
                                {
                                    byte2 = Blocks.gravel;
                                }
                                if (flag)
                                {
                                    byte1 = Blocks.sand;
                                }
                                if (flag)
                                {
                                    byte2 = Blocks.sand;
                                }
                            }
                            if (k1 < byte0 && byte1 == Blocks.air)
                            {
                                byte1 = Blocks.water;
                            }
                            j1 = i1;
                            if (k1 >= byte0 - 1)
                            {
                                chunk.setBlockState(l1, byte1.getDefaultState());
                            }
                            else
                            {
                                chunk.setBlockState(l1, byte2.getDefaultState());
                            }
                            continue;
                        }
                        if (j1 <= 0)
                        {
                            continue;
                        }
                        j1--;
                        chunk.setBlockState(l1, byte2.getDefaultState());
                        if (j1 == 0 && byte2 == Blocks.sand)
                        {
                            j1 = this.rand.nextInt(4);
                            byte2 = Blocks.sandstone;
                        }
                    }
                }
            }
        }
    }

    public Chunk loadChunk(int i, int j)
    {
        return this.provideChunk(i, j);
    }

    @Override
    public Chunk provideChunk(int i, int j)
    {
        this.rand.setSeed((long) i * 341873128712L + (long) j * 132897987541L);
        this.biomesForGeneration = this.chunkManager.loadBlockGeneratorData(this.biomesForGeneration, i * 16, j * 16, 16, 16);
        double ad[] = this.chunkManager.temperature;
        ChunkPrimerBeta chunkprimer = new ChunkPrimerBeta();
        this.generateTerrain(i, j, chunkprimer, ad);
        this.replaceBlocksForBiome(i, j, chunkprimer, this.biomesForGeneration);

        this.field_902_u.generate(this.worldObj, i, j, chunkprimer);

        if (!(this.biomeSettings == 0) && this.ravine == 0)
        {
            this.ravineGenerator.generate(this, this.worldObj, i, j, chunkprimer);
        }

        if (this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generate(this, this.worldObj, i, j, chunkprimer);
            this.mineshaftGenerator.generate(this, this.worldObj, i, j, chunkprimer);
            if (this.biomeSettings > 0)
            {
                this.villageGenerator.generate(this, this.worldObj, i, j, chunkprimer);
                this.scatteredFeatureGenerator.generate(this, this.worldObj, i, j, chunkprimer);
            }
        }

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, i, j);
        byte abyte1[] = chunk.getBiomeArray();

        if (this.biomeSettings == 0)
        {
            for (int k = 0; k < abyte1.length; k++)
            {
                abyte1[k] = (byte) this.biomesForGeneration[k].biomeID;
            }
        }
        else
        {
            for (int k = 0; k < abyte1.length; k++)
            {
                abyte1[k] = (byte) this.biomesForGeneration[(int) (((k * 16) & 0xff) + (int) (k / 16))].biomeID;
            }
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] func_4061_a(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if (ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        double ad1[] = this.chunkManager.temperature;
        double ad2[] = this.chunkManager.humidity;
        this.field_4182_g = this.field_922_a.generateNoiseOctaves(this.field_4182_g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        this.field_4181_h = this.field_921_b.generateNoiseOctaves(this.field_4181_h, i, k, l, j1, 200D, 200D, 0.5D);
        this.field_4185_d = this.field_910_m.generateNoiseOctaves(this.field_4185_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        this.field_4184_e = this.field_912_k.generateNoiseOctaves(this.field_4184_e, i, j, k, l, i1, j1, d, d1, d);
        this.field_4183_f = this.field_911_l.generateNoiseOctaves(this.field_4183_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        int i2 = 16 / l;
        for (int j2 = 0; j2 < l; j2++)
        {
            int k2 = j2 * i2 + i2 / 2;
            for (int l2 = 0; l2 < j1; l2++)
            {
                int i3 = l2 * i2 + i2 / 2;
                double d2 = ad1[k2 * 16 + i3];
                double d3 = ad2[k2 * 16 + i3] * d2;
                double d4 = 1.0D - d3;
                d4 *= d4;
                d4 *= d4;
                d4 = 1.0D - d4;
                double d5 = (this.field_4182_g[l1] + 256D) / 512D;
                d5 *= d4;
                if (d5 > 1.0D)
                {
                    d5 = 1.0D;
                }
                double d6 = this.field_4181_h[l1] / 8000D;
                if (d6 < 0.0D)
                {
                    d6 = -d6 * 0.29999999999999999D;
                }
                d6 = d6 * 3D - 2D;
                if (d6 < 0.0D)
                {
                    d6 /= 2D;
                    if (d6 < -1D)
                    {
                        d6 = -1D;
                    }
                    d6 /= 1.3999999999999999D;
                    d6 /= 2D;
                    d5 = 0.0D;
                }
                else
                {
                    if (d6 > 1.0D)
                    {
                        d6 = 1.0D;
                    }
                    d6 /= 8D;
                }
                if (d5 < 0.0D)
                {
                    d5 = 0.0D;
                }
                d5 += 0.5D;
                d6 = (d6 * (double) i1) / 16D;
                double d7 = (double) i1 / 2D + d6 * 4D;
                l1++;
                for (int j3 = 0; j3 < i1; j3++)
                {
                    double d8 = 0.0D;
                    double d9 = (((double) j3 - d7) * 12D) / d5;
                    if (d9 < 0.0D)
                    {
                        d9 *= 4D;
                    }
                    double d10 = this.field_4184_e[k1] / 512D;
                    double d11 = this.field_4183_f[k1] / 512D;
                    double d12 = (this.field_4185_d[k1] / 10D + 1.0D) / 2D;
                    if (d12 < 0.0D)
                    {
                        d8 = d10;
                    }
                    else if (d12 > 1.0D)
                    {
                        d8 = d11;
                    }
                    else
                    {
                        d8 = d10 + (d11 - d10) * d12;
                    }
                    d8 -= d9;
                    if (j3 > i1 - 4)
                    {
                        double d13 = (float) (j3 - (i1 - 4)) / 3F;
                        d8 = d8 * (1.0D - d13) + -10D * d13;
                    }
                    ad[k1] = d8;
                    k1++;
                }

            }

        }

        return ad;
    }

    @Override
    public boolean chunkExists(int i, int j)
    {
        return true;
    }

    @Override
    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        if (this.biomeSettings == 0)
        {
            BlockFalling.fallInstantly = true;
            int k = i * 16;
            int l = j * 16;
            BiomeList biomegenbase = (BiomeList) this.chunkManager.getBiomeGenAt(k + 16, l + 16);
            this.rand.setSeed(this.worldObj.getSeed());
            long l1 = (this.rand.nextLong() / 2L) * 2L + 1L;
            long l2 = (this.rand.nextLong() / 2L) * 2L + 1L;
            this.rand.setSeed((long) i * l1 + (long) j * l2 ^ this.worldObj.getSeed());
            this.rand2.setSeed((long) i * l1 + (long) j * l2 ^ this.worldObj.getSeed());
            double d = 0.25D;
            ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);

            if (this.mapFeaturesEnabled)
            {
                this.strongholdGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
                this.mineshaftGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
            }

            if (this.rand.nextInt(4) == 0)
            {
                int i1 = k + this.rand.nextInt(16) + 8;
                int l4 = this.rand.nextInt(128);
                int i8 = l + this.rand.nextInt(16) + 8;
                (new OldGenLakes(Blocks.water)).generate(this.worldObj, this.rand, i1, l4, i8);
            }
            if (this.rand.nextInt(8) == 0)
            {
                int j1 = k + this.rand.nextInt(16) + 8;
                int i5 = this.rand.nextInt(this.rand.nextInt(120) + 8);
                int j8 = l + this.rand.nextInt(16) + 8;
                if (i5 < 64 || this.rand.nextInt(10) == 0)
                {
                    (new OldGenLakes(Blocks.lava)).generate(this.worldObj, this.rand, j1, i5, j8);
                }
            }
            for (int k1 = 0; k1 < 8; k1++) // GOOD
            {
                int j5 = k + this.rand.nextInt(16) + 8;
                int k8 = this.rand.nextInt(128);
                int j11 = l + this.rand.nextInt(16) + 8;
                (new OldGenDungeons()).generate(this.worldObj, this.rand, j5, k8, j11);
            }

            for (int i2 = 0; i2 < 10; i2++) // GOOD
            {
                int k5 = k + this.rand.nextInt(16);
                int l8 = this.rand.nextInt(128);
                int k11 = l + this.rand.nextInt(16);
                (new OldGenClay(32, 2)).generate(this.worldObj, this.rand, k5, l8, k11);
            }

            for (int j2 = 0; j2 < 20; j2++) // GOOD
            {
                int l5 = k + this.rand.nextInt(16);
                int i9 = this.rand.nextInt(128);
                int l11 = l + this.rand.nextInt(16);
                (new OldGenMinable(Blocks.dirt, 32, 2)).generate(this.worldObj, this.rand, l5, i9, l11);
            }

            for (int k2 = 0; k2 < 10; k2++) // GOOD
            {
                int i6 = k + this.rand.nextInt(16);
                int j9 = this.rand.nextInt(128);
                int i12 = l + this.rand.nextInt(16);
                (new OldGenMinable(Blocks.gravel, 32, 2)).generate(this.worldObj, this.rand, i6, j9, i12);
            }

            for (int i3 = 0; i3 < 20; i3++) // GOOD
            {
                int j6 = k + this.rand.nextInt(16);
                int k9 = this.rand.nextInt(128);
                int j12 = l + this.rand.nextInt(16);
                (new OldGenMinable(Blocks.coal_ore, 16, 2)).generate(this.worldObj, this.rand, j6, k9, j12);
            }

            for (int j3 = 0; j3 < 20; j3++) // GOOD
            {
                int k6 = k + this.rand.nextInt(16);
                int l9 = this.rand.nextInt(64);
                int k12 = l + this.rand.nextInt(16);
                (new OldGenMinable(Blocks.iron_ore, 8, 2)).generate(this.worldObj, this.rand, k6, l9, k12);
            }

            for (int k3 = 0; k3 < 2; k3++) // GOOD
            {
                int l6 = k + this.rand.nextInt(16);
                int i10 = this.rand.nextInt(32);
                int l12 = l + this.rand.nextInt(16);
                (new OldGenMinable(Blocks.gold_ore, 8, 2)).generate(this.worldObj, this.rand, l6, i10, l12);
            }

            for (int l3 = 0; l3 < 8; l3++) // GOOD
            {
                int i7 = k + this.rand.nextInt(16);
                int j10 = this.rand.nextInt(16);
                int i13 = l + this.rand.nextInt(16);
                (new OldGenMinable(Blocks.redstone_ore, 7, 2)).generate(this.worldObj, this.rand, i7, j10, i13);
            }

            for (int i4 = 0; i4 < 1; i4++) // GOOD
            {
                int j7 = k + this.rand.nextInt(16);
                int k10 = this.rand.nextInt(16);
                int j13 = l + this.rand.nextInt(16);
                (new OldGenMinable(Blocks.diamond_ore, 7, 2)).generate(this.worldObj, this.rand, j7, k10, j13);
            }

            for (int j4 = 0; j4 < 1; j4++) // GOOD
            {
                int k7 = k + this.rand.nextInt(16);
                int l10 = this.rand.nextInt(16) + this.rand.nextInt(16);
                int k13 = l + this.rand.nextInt(16);
                (new OldGenMinable(Blocks.lapis_ore, 6, 2)).generate(this.worldObj, this.rand, k7, l10, k13);
            }

            d = 0.5D;
            int k4 = (int) ((this.mobSpawnerNoise.func_806_a((double) k * d, (double) l * d) / 8D + this.rand.nextDouble() * 4D + 4D) / 3D);
            int l7 = 0;
            if (this.rand.nextInt(10) == 0)
            {
                l7++;
            }
            if (biomegenbase == BiomeList.OLDforest)
            {
                l7 += k4 + 5;
            }
            if (biomegenbase == BiomeList.OLDrainforest)
            {
                l7 += k4 + 5;
            }
            if (biomegenbase == BiomeList.OLDseasonalForest)
            {
                l7 += k4 + 2;
            }
            if (biomegenbase == BiomeList.OLDtaiga)
            {
                l7 += k4 + 5;
            }
            if (biomegenbase == BiomeList.OLDdesert)
            {
                l7 -= 20;
            }
            if (biomegenbase == BiomeList.OLDtundra)
            {
                l7 -= 20;
            }
            if (biomegenbase == BiomeList.OLDplains)
            {
                l7 -= 20;
            }
            for (int i11 = 0; i11 < l7; i11++)
            {
                int l13 = k + this.rand.nextInt(16) + 8;
                int j14 = l + this.rand.nextInt(16) + 8;
                WorldGenerator worldgenerator = biomegenbase.getRandomWorldGenForTrees(this.rand);
                worldgenerator.func_517_a(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(this.worldObj, this.rand, l13, OWGGenHelper.getHeightValue(this.worldObj, l13, j14), j14);
            }

            byte byte0 = 0;
            if (biomegenbase == BiomeList.OLDforest)
            {
                byte0 = 2;
            }
            if (biomegenbase == BiomeList.OLDseasonalForest)
            {
                byte0 = 4;
            }
            if (biomegenbase == BiomeList.OLDtaiga)
            {
                byte0 = 2;
            }
            if (biomegenbase == BiomeList.OLDplains)
            {
                byte0 = 3;
            }
            for (int i14 = 0; i14 < byte0; i14++) // GOOD
            {
                int k14 = k + this.rand.nextInt(16) + 8;
                int l16 = this.rand.nextInt(128);
                int k19 = l + this.rand.nextInt(16) + 8;
                (new OldGenFlowers(Blocks.yellow_flower)).generate(this.worldObj, this.rand, k14, l16, k19);
            }

            byte byte1 = 0;
            if (biomegenbase == BiomeList.OLDforest)
            {
                byte1 = 2;
            }
            if (biomegenbase == BiomeList.OLDrainforest)
            {
                byte1 = 10;
            }
            if (biomegenbase == BiomeList.OLDseasonalForest)
            {
                byte1 = 2;
            }
            if (biomegenbase == BiomeList.OLDtaiga)
            {
                byte1 = 1;
            }
            if (biomegenbase == BiomeList.OLDplains)
            {
                byte1 = 10;
            }
            for (int l14 = 0; l14 < byte1; l14++) // GOOD
            {
                BlockTallGrass.EnumType blocktallgrass$enumtype = BlockTallGrass.EnumType.GRASS;
                if (biomegenbase == BiomeList.OLDrainforest && this.rand.nextInt(3) != 0)
                {
                    blocktallgrass$enumtype = BlockTallGrass.EnumType.FERN;
                }
                int l19 = k + this.rand.nextInt(16) + 8;
                int k22 = this.rand.nextInt(128);
                int j24 = l + this.rand.nextInt(16) + 8;
                (new OldGenTallGrass(blocktallgrass$enumtype)).generate(this.worldObj, this.rand, l19, k22, j24);
            }

            byte1 = 0;
            if (biomegenbase == BiomeList.OLDdesert)
            {
                byte1 = 2;
            }
            for (int i15 = 0; i15 < byte1; i15++) // GOOD
            {
                int i17 = k + this.rand.nextInt(16) + 8;
                int i20 = this.rand.nextInt(128);
                int l22 = l + this.rand.nextInt(16) + 8;
                (new OldGenDeadBush(Blocks.deadbush)).generate(this.worldObj, this.rand, i17, i20, l22);
            }

            if (this.rand.nextInt(2) == 0) // GOOD
            {
                int j15 = k + this.rand.nextInt(16) + 8;
                int j17 = this.rand.nextInt(128);
                int j20 = l + this.rand.nextInt(16) + 8;
                (new OldGenFlowers(Blocks.red_flower)).generate(this.worldObj, this.rand, j15, j17, j20);
            }
            if (this.rand.nextInt(4) == 0) // GOOD
            {
                int k15 = k + this.rand.nextInt(16) + 8;
                int k17 = this.rand.nextInt(128);
                int k20 = l + this.rand.nextInt(16) + 8;
                (new OldGenFlowers(Blocks.brown_mushroom)).generate(this.worldObj, this.rand, k15, k17, k20);
            }
            if (this.rand.nextInt(8) == 0) // GOOD
            {
                int l15 = k + this.rand.nextInt(16) + 8;
                int l17 = this.rand.nextInt(128);
                int l20 = l + this.rand.nextInt(16) + 8;
                (new OldGenFlowers(Blocks.red_mushroom)).generate(this.worldObj, this.rand, l15, l17, l20);
            }
            for (int i16 = 0; i16 < 10; i16++)
            {
                int i18 = k + this.rand.nextInt(16) + 8;
                int i21 = this.rand.nextInt(128);
                int i23 = l + this.rand.nextInt(16) + 8;
                (new OldGenReed()).generate(this.worldObj, this.rand, i18, i21, i23);
            }

            if (this.rand.nextInt(32) == 0) // GOOD
            {
                int j16 = k + this.rand.nextInt(16) + 8;
                int j18 = this.rand.nextInt(128);
                int j21 = l + this.rand.nextInt(16) + 8;
                (new OldGenPumpkin()).generate(this.worldObj, this.rand, j16, j18, j21);
            }
            int k16 = 0;
            if (biomegenbase == BiomeList.OLDdesert)
            {
                k16 += 10;
            }
            for (int k18 = 0; k18 < k16; k18++) // GOOD
            {
                int k21 = k + this.rand.nextInt(16) + 8;
                int j23 = this.rand.nextInt(128);
                int k24 = l + this.rand.nextInt(16) + 8;
                (new OldGenCactus()).generate(this.worldObj, this.rand, k21, j23, k24);
            }

            for (int l18 = 0; l18 < 50; l18++) // GOOD
            {
                int l21 = k + this.rand.nextInt(16) + 8;
                int k23 = this.rand.nextInt(this.rand.nextInt(120) + 8);
                int l24 = l + this.rand.nextInt(16) + 8;
                (new OldGenLiquids(Blocks.flowing_water)).generate(this.worldObj, this.rand, l21, k23, l24);
            }

            for (int i19 = 0; i19 < 20; i19++) // GOOD
            {
                int i22 = k + this.rand.nextInt(16) + 8;
                int l23 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(112) + 8) + 8);
                int i25 = l + this.rand.nextInt(16) + 8;
                (new OldGenLiquids(Blocks.flowing_lava)).generate(this.worldObj, this.rand, i22, l23, i25);
            }

            SpawnerAnimals.performWorldGenSpawning(this.worldObj, this.chunkManager.getBiomeGenAtChunkCoord(chunkcoordintpair), k + 8, l + 8, 16, 16,
                    this.rand2);

            MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, this.worldObj, this.rand2, i, j, false));

            this.generatedTemperatures = this.chunkManager.getTemperatures(this.generatedTemperatures, k + 8, l + 8, 16, 16);
            for (int j19 = k + 8; j19 < k + 8 + 16; j19++)
            {
                for (int j22 = l + 8; j22 < l + 8 + 16; j22++)
                {
                    int i24 = j19 - (k + 8);
                    int j25 = j22 - (l + 8);
                    int k25 = this.worldObj.getPrecipitationHeight(new BlockPos(j19, 0, j22)).getY();
                    double d22 = this.generatedTemperatures[i24 * 16 + j25] - ((double) (k25 - 64) / 64D) * 0.29999999999999999D;
                    if (d22 < 0.5D && k25 > 0 && k25 < 128 && OWGGenHelper.isAirBlock(this.worldObj, j19, k25, j22)
                            && OWGGenHelper.getBlockMaterial(this.worldObj, j19, k25 - 1, j22).isSolid()
                            && OWGGenHelper.getBlockMaterial(this.worldObj, j19, k25 - 1, j22) != Material.ice)
                    {
                        OWGGenHelper.setBlockWithNotify(this.worldObj, j19, k25, j22, Blocks.snow_layer);
                    }
                }

            }

            MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, this.worldObj, this.rand2, i, j, false));

            BlockFalling.fallInstantly = false;
        }
        else
        {
            BlockFalling.fallInstantly = true;
            int k = i * 16;
            int l = j * 16;
            BiomeGenBase biomegenbase = this.chunkManager.getBiomeGenAt(k + 16, l + 16);
            this.rand.setSeed(this.worldObj.getSeed());
            long l1 = (this.rand.nextLong() / 2L) * 2L + 1L;
            long l2 = (this.rand.nextLong() / 2L) * 2L + 1L;
            this.rand.setSeed((long) i * l1 + (long) j * l2 ^ this.worldObj.getSeed());
            double d = 0.25D;
            boolean flag = false;
            ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);

            MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, this.worldObj, this.rand, i, j, false));

            if (this.mapFeaturesEnabled)
            {
                this.mineshaftGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
                this.strongholdGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
                this.mineshaftGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
                flag = this.villageGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
                this.strongholdGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
                this.scatteredFeatureGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
            }

            boolean gen = false;

            gen = TerrainGen.populate(this, this.worldObj, this.rand, i, j, flag, PopulateChunkEvent.Populate.EventType.LAKE);
            if (gen && this.rand.nextInt(4) == 0)
            {
                int i1 = k + this.rand.nextInt(16) + 8;
                int l4 = this.rand.nextInt(128);
                int i8 = l + this.rand.nextInt(16) + 8;
                (new OldGenLakes(Blocks.water)).generate(this.worldObj, this.rand, i1, l4, i8);
            }

            gen = TerrainGen.populate(this, this.worldObj, this.rand, i, j, flag, PopulateChunkEvent.Populate.EventType.LAVA);
            if (gen && this.rand.nextInt(8) == 0)
            {
                int j1 = k + this.rand.nextInt(16) + 8;
                int i5 = this.rand.nextInt(this.rand.nextInt(120) + 8);
                int j8 = l + this.rand.nextInt(16) + 8;
                if (i5 < 64 || this.rand.nextInt(10) == 0)
                {
                    (new OldGenLakes(Blocks.lava)).generate(this.worldObj, this.rand, j1, i5, j8);
                }
            }

            gen = TerrainGen.populate(this, this.worldObj, this.rand, i, j, flag, PopulateChunkEvent.Populate.EventType.DUNGEON);
            for (int k1 = 0; gen && k1 < 8; k1++)
            {
                int j5 = k + this.rand.nextInt(16) + 8;
                int k8 = this.rand.nextInt(128);
                int j11 = l + this.rand.nextInt(16) + 8;
                (new WorldGenDungeons()).generate(this.worldObj, this.rand, new BlockPos(j5, k8, j11));
            }

            for (int i2 = 0; i2 < 10; i2++)
            {
                int k5 = k + this.rand.nextInt(16);
                int l8 = this.rand.nextInt(128);
                int k11 = l + this.rand.nextInt(16);
                (new OldGenClay(32, 2)).generate(this.worldObj, this.rand, k5, l8, k11);
            }

            biomegenbase.decorate(this.worldObj, this.rand, new BlockPos(k, 0, l));

            if (TerrainGen.populate(this, this.worldObj, this.rand, i, j, flag, PopulateChunkEvent.Populate.EventType.ANIMALS))
            {
                SpawnerAnimals.performWorldGenSpawning(this.worldObj, this.chunkManager.getBiomeGenAtChunkCoord(chunkcoordintpair), k + 8, l + 8, 16, 16,
                        this.rand);
            }

            gen = TerrainGen.populate(this, this.worldObj, this.rand, i, j, flag, PopulateChunkEvent.Populate.EventType.ICE);

            k += 8;
            l += 8;
            for (int j19 = 0; gen && j19 < 16; j19++)
            {
                for (int j22 = 0; j22 < 16; j22++)
                {
                    BlockPos blockpos1 = this.worldObj.getPrecipitationHeight(new BlockPos(k + j19, 0, l + j22));
                    BlockPos blockpos2 = blockpos1.down();

                    if (this.worldObj.canBlockFreezeWater(blockpos2))
                    {
                        this.worldObj.setBlockState(blockpos2, Blocks.ice.getDefaultState(), 2);
                    }

                    if (this.worldObj.canSnowAt(blockpos1, true))
                    {
                        this.worldObj.setBlockState(blockpos1, Blocks.snow_layer.getDefaultState(), 2);
                    }
                }
            }

            MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, this.worldObj, this.rand, i, j, false));

            BlockFalling.fallInstantly = false;
        }
    }

    @Override
    public Chunk provideChunk(BlockPos blockPosIn)
    {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }

    @Override
    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_)
    {
        return false;
    }

    @Override
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate progressCallback)
    {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    @Override
    public boolean canSave()
    {
        return false;
    }

    @Override
    public String makeString()
    {
        return "RandomLevelSource";
    }

    @Override
    public List getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        if (this.biomeSettings == 0)
        {
            return this.worldObj.getBiomeGenForCoords(pos).getSpawnableList(creatureType);
        }
        else
        {
            BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);

            if (this.mapFeaturesEnabled)
            {
                if (creatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.func_175798_a(pos))
                {
                    return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
                }
            }

            return biomegenbase.getSpawnableList(creatureType);
        }
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position)
    {
        return "Stronghold".equals(structureName) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(worldIn, position)
                : null;
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }

    @Override
    public void saveExtraData()
    {
    }

    @Override
    public void recreateStructures(Chunk chunk, int i, int j)
    {
        if (this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generate(this, this.worldObj, i, j, (ChunkPrimerBeta) null);
            this.mineshaftGenerator.generate(this, this.worldObj, i, j, (ChunkPrimerBeta) null);
            if (this.biomeSettings > 0)
            {
                this.mineshaftGenerator.generate(this, this.worldObj, i, j, (ChunkPrimerBeta) null);
                this.villageGenerator.generate(this, this.worldObj, i, j, (ChunkPrimerBeta) null);
                this.strongholdGenerator.generate(this, this.worldObj, i, j, (ChunkPrimerBeta) null);
                this.scatteredFeatureGenerator.generate(this, this.worldObj, i, j, (ChunkPrimerBeta) null);

            }
        }
    }
}