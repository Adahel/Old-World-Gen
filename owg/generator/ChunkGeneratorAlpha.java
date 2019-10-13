package owg.generator;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
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
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import owg.OWGGenHelper;
import owg.biomes.BiomeList;
import owg.data.DungeonLoot;
import owg.deco.OldGenBigTree;
import owg.deco.OldGenCactus;
import owg.deco.OldGenClay;
import owg.deco.OldGenDungeons;
import owg.deco.OldGenFlowers;
import owg.deco.OldGenLakes;
import owg.deco.OldGenLiquids;
import owg.deco.OldGenMinable;
import owg.deco.OldGenPumpkin;
import owg.deco.OldGenReed;
import owg.deco.OldGenTrees;
import owg.deco.WorldGenerator;
import owg.map.MapGenOLD;
import owg.map.MapGenOLDCaves;
import owg.noise.NoiseOctavesAlpha;
import owg.world.ManagerOWG;

public class ChunkGeneratorAlpha implements IChunkProvider
{
    private Random field_913_j;
    private Random rand2;
    private NoiseOctavesAlpha field_912_k;
    private NoiseOctavesAlpha field_911_l;
    private NoiseOctavesAlpha field_910_m;
    private NoiseOctavesAlpha field_909_n;
    private NoiseOctavesAlpha field_908_o;
    public NoiseOctavesAlpha field_922_a;
    public NoiseOctavesAlpha field_921_b;
    public NoiseOctavesAlpha field_920_c;

    private World worldObj_16;
    private final int strongholds;
    private final int mineshafts;
    private double field_4180_q[];
    private double field_905_r[];
    private double field_904_s[];
    private double field_903_t[];
    private MapGenOLD field_902_u;
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private BiomeGenBase field_4179_v[];
    double field_4185_d[];
    double field_4184_e[];
    double field_4183_f[];
    double field_4182_g[];
    double field_4181_h[];
    int field_914_i[][];
    private double field_4178_w[];
    private final ManagerOWG chunkManager;

    public ChunkGeneratorAlpha(World world, long l, int aStronghold, int aMineshaft)
    {
        this.strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(this.strongholdGenerator, InitMapGenEvent.EventType.STRONGHOLD);
        this.mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(this.mineshaftGenerator, InitMapGenEvent.EventType.MINESHAFT);
        this.field_905_r = new double[256];
        this.field_904_s = new double[256];
        this.field_903_t = new double[256];
        this.field_902_u = new MapGenOLDCaves();
        this.field_914_i = new int[32][32];
        this.worldObj_16 = world;
        this.strongholds = aStronghold;
        this.mineshafts = aMineshaft;
        this.field_913_j = new Random(l);
        this.rand2 = new Random(l);
        this.field_912_k = new NoiseOctavesAlpha(this.field_913_j, 16);
        this.field_911_l = new NoiseOctavesAlpha(this.field_913_j, 16);
        this.field_910_m = new NoiseOctavesAlpha(this.field_913_j, 8);
        this.field_909_n = new NoiseOctavesAlpha(this.field_913_j, 4);
        this.field_908_o = new NoiseOctavesAlpha(this.field_913_j, 4);
        this.field_922_a = new NoiseOctavesAlpha(this.field_913_j, 10);
        this.field_921_b = new NoiseOctavesAlpha(this.field_913_j, 16);
        this.field_920_c = new NoiseOctavesAlpha(this.field_913_j, 8);
        this.chunkManager = (ManagerOWG) this.worldObj_16.getWorldChunkManager();

        DungeonLoot.init(l);
    }

    public void generateTerrain(int i, int j, ChunkPrimer chunkprimer, double ad[])
    {
        byte byte0 = 4;
        byte byte1 = 64;
        int k = byte0 + 1;
        byte byte2 = 17;
        int l = byte0 + 1;
        this.field_4180_q = this.initializeNoiseField(this.field_4180_q, i * byte0, 0, j * byte0, k, byte2, l);
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
                                    if (d17 < 0.5D && k1 * 8 + l1 >= byte1 - 1)
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
                                    chunkprimer.setBlockState(j2, l2.getDefaultState());
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

    public void replaceBlocksForBiome(int i, int j, ChunkPrimer chunk, BiomeGenBase abiomegenbase[])
    {
        byte byte0 = 64;
        double d = 0.03125D;
        this.field_905_r = this.field_909_n.func_807_a(this.field_905_r, i * 16, j * 16, 0.0D, 16, 16, 1, d, d, 1.0D);
        this.field_904_s = this.field_909_n.func_807_a(this.field_904_s, j * 16, 109.0134D, i * 16, 16, 1, 16, d, 1.0D, d);
        this.field_903_t = this.field_908_o.func_807_a(this.field_903_t, i * 16, j * 16, 0.0D, 16, 16, 1, d * 2D, d * 2D, d * 2D);
        for (int k = 0; k < 16; k++)
        {
            for (int l = 0; l < 16; l++)
            {
                BiomeList biomegenbase = (BiomeList) abiomegenbase[k * 16 + l];
                boolean flag = this.field_905_r[k + l * 16] + this.field_913_j.nextDouble() * 0.20000000000000001D > 0.0D;
                boolean flag1 = this.field_904_s[k + l * 16] + this.field_913_j.nextDouble() * 0.20000000000000001D > 3D;
                int i1 = (int) (this.field_903_t[k + l * 16] / 3D + 3D + this.field_913_j.nextDouble() * 0.25D);
                int j1 = -1;
                Block byte1 = biomegenbase.topBlock;
                Block byte2 = biomegenbase.fillerBlock;
                for (int k1 = 127; k1 >= 0; k1--)
                {
                    int l1 = k << 12 | l << 8 | k1;
                    if (k1 <= 0 + this.field_913_j.nextInt(5))
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
                            byte1 = Blocks.flowing_water;
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
                    if (j1 > 0)
                    {
                        j1--;
                        chunk.setBlockState(l1, byte2.getDefaultState());
                    }
                }

            }

        }
    }

    @Override
    public Chunk provideChunk(BlockPos blockPosIn)
    {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }

    @Override
    public Chunk provideChunk(int i, int j)
    {
        this.field_913_j.setSeed((long) i * 341873128712L + (long) j * 132897987541L);
        this.field_4179_v = this.chunkManager.loadBlockGeneratorData(this.field_4179_v, i * 16, j * 16, 16, 16);
        double ad[] = this.chunkManager.temperature;
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.generateTerrain(i, j, chunkprimer, ad);
        this.replaceBlocksForBiome(i, j, chunkprimer, this.field_4179_v);

        this.field_902_u.generate(this.worldObj_16, i, j, chunkprimer);

        if (this.mineshafts == 0)
        {
            this.mineshaftGenerator.generate(this, this.worldObj_16, i, j, chunkprimer);
        }

        if (this.strongholds == 0)
        {
            this.strongholdGenerator.generate(this, this.worldObj_16, i, j, chunkprimer);
        }

        Chunk chunk = new Chunk(this.worldObj_16, chunkprimer, i, j);
        byte abyte1[] = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; k++)
        {
            abyte1[k] = (byte) this.field_4179_v[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] initializeNoiseField(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if (ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        double ad1[] = this.chunkManager.temperature;
        double ad2[] = this.chunkManager.humidity;
        this.field_4182_g = this.field_922_a.func_4109_a(this.field_4182_g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        this.field_4181_h = this.field_921_b.func_4109_a(this.field_4181_h, i, k, l, j1, 200D, 200D, 0.5D);
        this.field_4185_d = this.field_910_m.func_807_a(this.field_4185_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        this.field_4184_e = this.field_912_k.func_807_a(this.field_4184_e, i, j, k, l, i1, j1, d, d1, d);
        this.field_4183_f = this.field_911_l.func_807_a(this.field_4183_f, i, j, k, l, i1, j1, d, d1, d);
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
        BlockFalling.fallInstantly = true;
        int k = i * 16;
        int l = j * 16;
        BiomeGenBase biomegenbase = this.chunkManager.getBiomeGenAt(k + 16, l + 16);
        this.field_913_j.setSeed(this.worldObj_16.getSeed());
        long l1 = (this.field_913_j.nextLong() / 2L) * 2L + 1L;
        long l2 = (this.field_913_j.nextLong() / 2L) * 2L + 1L;
        this.field_913_j.setSeed((long) i * l1 + (long) j * l2 ^ this.worldObj_16.getSeed());
        this.rand2.setSeed((long) i * l1 + (long) j * l2 ^ this.worldObj_16.getSeed());
        double d = 0.25D;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);

        if (this.mineshafts == 0)
        {
            this.mineshaftGenerator.generateStructure(this.worldObj_16, this.field_913_j, chunkcoordintpair);
        }

        if (this.strongholds == 0)
        {
            this.strongholdGenerator.generateStructure(this.worldObj_16, this.field_913_j, chunkcoordintpair);
        }

        if (this.field_913_j.nextInt(4) == 0)
        {
            int i111 = k + this.field_913_j.nextInt(16) + 8;
            int l44 = this.field_913_j.nextInt(128);
            int i88 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenLakes(Blocks.water)).generate(this.worldObj_16, this.field_913_j, i111, l44, i88);
        }
        if (this.field_913_j.nextInt(8) == 0)
        {
            int j111 = k + this.field_913_j.nextInt(16) + 8;
            int i55 = this.field_913_j.nextInt(this.field_913_j.nextInt(120) + 8);
            int j88 = l + this.field_913_j.nextInt(16) + 8;
            if (i55 < 64 || this.field_913_j.nextInt(10) == 0)
            {
                (new OldGenLakes(Blocks.lava)).generate(this.worldObj_16, this.field_913_j, j111, i55, j88);
            }
        }

        for (int i1 = 0; i1 < 8; i1++)
        {
            int i4 = k + this.field_913_j.nextInt(16) + 8;
            int k6 = this.field_913_j.nextInt(128);
            int l8 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenDungeons()).generate(this.worldObj_16, this.field_913_j, i4, k6, l8);
        }

        for (int j1 = 0; j1 < 10; j1++)
        {
            int j4 = k + this.field_913_j.nextInt(16);
            int l6 = this.field_913_j.nextInt(128);
            int i9 = l + this.field_913_j.nextInt(16);
            (new OldGenClay(32, 1)).generate(this.worldObj_16, this.field_913_j, j4, l6, i9);
        }

        for (int k1 = 0; k1 < 20; k1++)
        {
            int k4 = k + this.field_913_j.nextInt(16);
            int i7 = this.field_913_j.nextInt(128);
            int j9 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.dirt, 32, 1)).generate(this.worldObj_16, this.field_913_j, k4, i7, j9);
        }

        for (int i2 = 0; i2 < 10; i2++)
        {
            int l4 = k + this.field_913_j.nextInt(16);
            int j7 = this.field_913_j.nextInt(128);
            int k9 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.gravel, 32, 1)).generate(this.worldObj_16, this.field_913_j, l4, j7, k9);
        }

        for (int j2 = 0; j2 < 20; j2++)
        {
            int i5 = k + this.field_913_j.nextInt(16);
            int k7 = this.field_913_j.nextInt(128);
            int l9 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.coal_ore, 16, 1)).generate(this.worldObj_16, this.field_913_j, i5, k7, l9);
        }

        for (int k2 = 0; k2 < 20; k2++)
        {
            int j5 = k + this.field_913_j.nextInt(16);
            int l7 = this.field_913_j.nextInt(64);
            int i10 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.iron_ore, 8, 1)).generate(this.worldObj_16, this.field_913_j, j5, l7, i10);
        }

        for (int i3 = 0; i3 < 2; i3++)
        {
            int k5 = k + this.field_913_j.nextInt(16);
            int i8 = this.field_913_j.nextInt(32);
            int j10 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.gold_ore, 8, 1)).generate(this.worldObj_16, this.field_913_j, k5, i8, j10);
        }

        for (int j3 = 0; j3 < 8; j3++)
        {
            int l5 = k + this.field_913_j.nextInt(16);
            int j8 = this.field_913_j.nextInt(16);
            int k10 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.redstone_ore, 7, 1)).generate(this.worldObj_16, this.field_913_j, l5, j8, k10);
        }

        for (int k3 = 0; k3 < 1; k3++)
        {
            int i6 = k + this.field_913_j.nextInt(16);
            int k8 = this.field_913_j.nextInt(16);
            int l10 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.diamond_ore, 7, 1)).generate(this.worldObj_16, this.field_913_j, i6, k8, l10);
        }

        d = 0.5D;
        int l3 = (int) ((this.field_920_c.func_806_a((double) k * d, (double) l * d) / 8D + this.field_913_j.nextDouble() * 4D + 4D) / 3D);
        int j6 = 0;
        if (this.field_913_j.nextInt(10) == 0)
        {
            j6++;
        }
        if (biomegenbase == BiomeList.OLDforest)
        {
            j6 += l3 + 5;
        }
        if (biomegenbase == BiomeList.OLDrainforest)
        {
            j6 += l3 + 5;
        }
        if (biomegenbase == BiomeList.OLDseasonalForest)
        {
            j6 += l3 + 2;
        }
        if (biomegenbase == BiomeList.OLDtaiga)
        {
            j6 += l3 + 5;
        }
        if (biomegenbase == BiomeList.OLDdesert)
        {
            j6 -= 20;
        }
        if (biomegenbase == BiomeList.OLDtundra)
        {
            j6 -= 20;
        }
        if (biomegenbase == BiomeList.OLDplains)
        {
            j6 -= 20;
        }
        Object obj = new OldGenTrees(1);
        if (this.field_913_j.nextInt(10) == 0)
        {
            obj = new OldGenBigTree(1);
        }
        if (biomegenbase == BiomeList.OLDrainforest && this.field_913_j.nextInt(3) == 0)
        {
            obj = new OldGenBigTree(1);
        }
        for (int i11 = 0; i11 < j6; i11++)
        {
            int i13 = k + this.field_913_j.nextInt(16) + 8;
            int l15 = l + this.field_913_j.nextInt(16) + 8;
            ((WorldGenerator) (obj)).func_517_a(1.0D, 1.0D, 1.0D);
            ((WorldGenerator) (obj)).generate(this.worldObj_16, this.field_913_j, i13, OWGGenHelper.getHeightValue(this.worldObj_16, i13, l15), l15);
        }

        for (int j11 = 0; j11 < 2; j11++)
        {
            int j13 = k + this.field_913_j.nextInt(16) + 8;
            int i16 = this.field_913_j.nextInt(128);
            int k18 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenFlowers(Blocks.yellow_flower)).generate(this.worldObj_16, this.field_913_j, j13, i16, k18);
        }

        if (this.field_913_j.nextInt(2) == 0)
        {
            int k11 = k + this.field_913_j.nextInt(16) + 8;
            int k13 = this.field_913_j.nextInt(128);
            int j16 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenFlowers(Blocks.red_flower)).generate(this.worldObj_16, this.field_913_j, k11, k13, j16);
        }
        if (this.field_913_j.nextInt(4) == 0)
        {
            int l11 = k + this.field_913_j.nextInt(16) + 8;
            int l13 = this.field_913_j.nextInt(128);
            int k16 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenFlowers(Blocks.brown_mushroom)).generate(this.worldObj_16, this.field_913_j, l11, l13, k16);
        }
        if (this.field_913_j.nextInt(8) == 0)
        {
            int i12 = k + this.field_913_j.nextInt(16) + 8;
            int i14 = this.field_913_j.nextInt(128);
            int l16 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenFlowers(Blocks.red_mushroom)).generate(this.worldObj_16, this.field_913_j, i12, i14, l16);
        }
        for (int j12 = 0; j12 < 10; j12++)
        {
            int j14 = k + this.field_913_j.nextInt(16) + 8;
            int i17 = this.field_913_j.nextInt(128);
            int l18 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenReed()).generate(this.worldObj_16, this.field_913_j, j14, i17, l18);
        }

        if (this.field_913_j.nextInt(32) == 0)
        {
            int k12 = k + this.field_913_j.nextInt(16) + 8;
            int k14 = this.field_913_j.nextInt(128);
            int j17 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenPumpkin()).generate(this.worldObj_16, this.field_913_j, k12, k14, j17);
        }
        int l12 = 0;
        if (biomegenbase == BiomeList.OLDdesert)
        {
            l12 += 10;
        }
        for (int l14 = 0; l14 < l12; l14++)
        {
            int k17 = k + this.field_913_j.nextInt(16) + 8;
            int i19 = this.field_913_j.nextInt(128);
            int i20 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenCactus()).generate(this.worldObj_16, this.field_913_j, k17, i19, i20);
        }

        for (int i15 = 0; i15 < 50; i15++)
        {
            int l17 = k + this.field_913_j.nextInt(16) + 8;
            int j19 = this.field_913_j.nextInt(this.field_913_j.nextInt(120) + 8);
            int j20 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenLiquids(Blocks.flowing_water)).generate(this.worldObj_16, this.field_913_j, l17, j19, j20);
        }

        for (int j15 = 0; j15 < 20; j15++)
        {
            int i18 = k + this.field_913_j.nextInt(16) + 8;
            int k19 = this.field_913_j.nextInt(this.field_913_j.nextInt(this.field_913_j.nextInt(112) + 8) + 8);
            int k20 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenLiquids(Blocks.flowing_lava)).generate(this.worldObj_16, this.field_913_j, i18, k19, k20);
        }

        SpawnerAnimals.performWorldGenSpawning(this.worldObj_16, this.chunkManager.getBiomeGenAtChunkCoord(chunkcoordintpair), k + 8, l + 8, 16, 16,
                this.rand2);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, this.worldObj_16, this.rand2, i, j, false));

        this.field_4178_w = this.chunkManager.getTemperatures(this.field_4178_w, k + 8, l + 8, 16, 16);
        for (int k15 = k + 8; k15 < k + 8 + 16; k15++)
        {
            for (int j18 = l + 8; j18 < l + 8 + 16; j18++)
            {
                int l19 = k15 - (k + 8);
                int l20 = j18 - (l + 8);
                int i21 = this.worldObj_16.getPrecipitationHeight(new BlockPos(k15, 0, j18)).getY();
                double d1 = this.field_4178_w[l19 * 16 + l20] - ((double) (i21 - 64) / 64D) * 0.29999999999999999D;
                if (d1 < 0.5D && i21 > 0 && i21 < 128 && OWGGenHelper.isAirBlock(this.worldObj_16, k15, i21, j18)
                        && OWGGenHelper.getBlockMaterial(this.worldObj_16, k15, i21 - 1, j18).isSolid()
                        && OWGGenHelper.getBlockMaterial(this.worldObj_16, k15, i21 - 1, j18) != Material.ice)
                {
                    OWGGenHelper.setBlockWithNotify(this.worldObj_16, k15, i21, j18, Blocks.snow_layer);
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, this.worldObj_16, this.rand2, i, j, false));

        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean func_177460_a(IChunkProvider ichunkprovider, Chunk chunkIn, int i, int j)
    {
        return false;
    }

    @Override
    public boolean saveChunks(boolean flag, IProgressUpdate progressCallback)
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
        return this.worldObj_16.getBiomeGenForCoords(pos).getSpawnableList(creatureType);
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
    public void recreateStructures(Chunk chunk, int i, int j)
    {
        if (this.mineshafts == 0)
        {
            this.mineshaftGenerator.generate(this, this.worldObj_16, i, j, (ChunkPrimer) null);
        }

        if (this.strongholds == 0)
        {
            this.strongholdGenerator.generate(this, this.worldObj_16, i, j, (ChunkPrimer) null);
        }
    }

    @Override
    public void saveExtraData()
    {
    }
}
