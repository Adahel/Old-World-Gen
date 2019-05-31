package owg.generator;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
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
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import owg.OWGGenHelper;
import owg.data.DungeonLoot;
import owg.deco.OldGenBigTree;
import owg.deco.OldGenCactus;
import owg.deco.OldGenClay;
import owg.deco.OldGenDungeons;
import owg.deco.OldGenFlowers;
import owg.deco.OldGenLiquids;
import owg.deco.OldGenMinable;
import owg.deco.OldGenReed;
import owg.deco.OldGenTrees;
import owg.deco.WorldGenerator;
import owg.map.MapGenOLD;
import owg.map.MapGenOLDCaves;
import owg.noise.NoiseOctavesInfdev;
import owg.world.ManagerOWGHell;

public class ChunkGeneratorInfdev implements IChunkProvider
{
    private Random field_913_j;
    private NoiseOctavesInfdev field_912_k;
    private NoiseOctavesInfdev field_911_l;
    private NoiseOctavesInfdev field_910_m;
    private NoiseOctavesInfdev field_909_n;
    private NoiseOctavesInfdev field_908_o;
    public NoiseOctavesInfdev field_922_a;
    public NoiseOctavesInfdev field_921_b;
    public NoiseOctavesInfdev field_920_c;

    private World field_907_p;
    public final int strongholds;
    public final int mineshafts;
    private double field_906_q[];
    private double field_905_r[];
    private double field_904_s[];
    private double field_903_t[];
    private MapGenOLD field_902_u;
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private BiomeGenBase[] biomesForGeneration;
    private final ManagerOWGHell chunkManager;

    double field_919_d[];
    double field_918_e[];
    double field_917_f[];
    double field_916_g[];
    double field_915_h[];
    int field_914_i[][];

    private final boolean alpha;

    public ChunkGeneratorInfdev(World world, long l, int infStrongholds, int infMineshafts, boolean a)
    {
        this.field_905_r = new double[256];
        this.field_904_s = new double[256];
        this.field_903_t = new double[256];
        this.field_902_u = new MapGenOLDCaves();
        this.field_914_i = new int[32][32];
        this.field_907_p = world;
        this.strongholds = infStrongholds;
        this.mineshafts = infMineshafts;
        this.alpha = a;
        this.chunkManager = (ManagerOWGHell) this.field_907_p.getWorldChunkManager();

        DungeonLoot.init(l);

        this.field_913_j = new Random(l);
        this.field_912_k = new NoiseOctavesInfdev(this.field_913_j, 16);
        this.field_911_l = new NoiseOctavesInfdev(this.field_913_j, 16);
        this.field_910_m = new NoiseOctavesInfdev(this.field_913_j, 8);
        this.field_909_n = new NoiseOctavesInfdev(this.field_913_j, 4);
        this.field_908_o = new NoiseOctavesInfdev(this.field_913_j, 4);
        this.field_922_a = new NoiseOctavesInfdev(this.field_913_j, 10);
        this.field_921_b = new NoiseOctavesInfdev(this.field_913_j, 16);
        this.field_920_c = new NoiseOctavesInfdev(this.field_913_j, 8);
    }

    public void generateTerrain(int i, int j, ChunkPrimer chunk)
    {
        byte byte0 = 4;
        byte byte1 = 64;
        int k = byte0 + 1;
        byte byte2 = 17;
        int l = byte0 + 1;
        this.field_906_q = this.initializeNoiseField(this.field_906_q, i * byte0, 0, j * byte0, k, byte2, l);
        for (int i1 = 0; i1 < byte0; i1++)
        {
            for (int j1 = 0; j1 < byte0; j1++)
            {
                for (int k1 = 0; k1 < 16; k1++)
                {
                    double d = 0.125D;
                    double d1 = this.field_906_q[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d2 = this.field_906_q[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d3 = this.field_906_q[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 0)];
                    double d4 = this.field_906_q[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 0)];
                    double d5 = (this.field_906_q[((i1 + 0) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d1) * d;
                    double d6 = (this.field_906_q[((i1 + 0) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d2) * d;
                    double d7 = (this.field_906_q[((i1 + 1) * l + (j1 + 0)) * byte2 + (k1 + 1)] - d3) * d;
                    double d8 = (this.field_906_q[((i1 + 1) * l + (j1 + 1)) * byte2 + (k1 + 1)] - d4) * d;
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
                                Block l2 = null;
                                if (k1 * 8 + l1 < byte1)
                                {
                                    l2 = Blocks.water;
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

    public void replaceBlocksForBiome(int i, int j, ChunkPrimer chunk)
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
                boolean flag = this.field_905_r[k + l * 16] + this.field_913_j.nextDouble() * 0.20000000000000001D > 0.0D;
                boolean flag1 = this.field_904_s[k + l * 16] + this.field_913_j.nextDouble() * 0.20000000000000001D > 3D;
                int i1 = (int) (this.field_903_t[k + l * 16] / 3D + 3D + this.field_913_j.nextDouble() * 0.25D);
                int j1 = -1;
                Block byte1 = Blocks.grass;
                Block byte2 = Blocks.dirt;
                for (int k1 = 127; k1 >= 0; k1--)
                {
                    int l1 = k << 12 | l << 8 | k1;
                    if (k1 <= (0 + this.field_913_j.nextInt(6)) - 1)
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
                            byte1 = Blocks.grass;
                            byte2 = Blocks.dirt;
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
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.generateTerrain(i, j, chunkprimer);
        this.replaceBlocksForBiome(i, j, chunkprimer);
        this.field_902_u.generate(this.field_907_p, i, j, chunkprimer);
        this.biomesForGeneration = this.chunkManager.loadBlockGeneratorData(this.biomesForGeneration, i * 16, j * 16, 16, 16);

        if (this.mineshafts == 0)
        {
            this.mineshaftGenerator.generate(this, this.field_907_p, i, j, chunkprimer);
        }

        if (this.strongholds == 0)
        {
            this.strongholdGenerator.generate(this, this.field_907_p, i, j, chunkprimer);
        }

        Chunk chunk = new Chunk(this.field_907_p, chunkprimer, i, j);
        byte abyte1[] = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; k++)
        {
            abyte1[k] = (byte) this.biomesForGeneration[k].biomeID;
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
        this.field_916_g = this.field_922_a.func_807_a(this.field_916_g, i, j, k, l, 1, j1, 1.0D, 0.0D, 1.0D);
        this.field_915_h = this.field_921_b.func_807_a(this.field_915_h, i, j, k, l, 1, j1, 100D, 0.0D, 100D);
        this.field_919_d = this.field_910_m.func_807_a(this.field_919_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        this.field_918_e = this.field_912_k.func_807_a(this.field_918_e, i, j, k, l, i1, j1, d, d1, d);
        this.field_917_f = this.field_911_l.func_807_a(this.field_917_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        for (int i2 = 0; i2 < l; i2++)
        {
            for (int j2 = 0; j2 < j1; j2++)
            {
                double d2 = (this.field_916_g[l1] + 256D) / 512D;
                if (d2 > 1.0D)
                {
                    d2 = 1.0D;
                }
                double d3 = 0.0D;
                double d4 = this.field_915_h[l1] / 8000D;
                if (d4 < 0.0D)
                {
                    d4 = -d4;
                }
                d4 = d4 * 3D - 3D;
                if (d4 < 0.0D)
                {
                    d4 /= 2D;
                    if (d4 < -1D)
                    {
                        d4 = -1D;
                    }
                    d4 /= 1.3999999999999999D;
                    d4 /= 2D;
                    d2 = 0.0D;
                }
                else
                {
                    if (d4 > 1.0D)
                    {
                        d4 = 1.0D;
                    }
                    d4 /= 6D;
                }
                d2 += 0.5D;
                d4 = (d4 * (double) i1) / 16D;
                double d5 = (double) i1 / 2D + d4 * 4D;
                l1++;
                for (int k2 = 0; k2 < i1; k2++)
                {
                    double d6 = 0.0D;
                    double d7 = (((double) k2 - d5) * 12D) / d2;
                    if (d7 < 0.0D)
                    {
                        d7 *= 4D;
                    }
                    double d8 = this.field_918_e[k1] / 512D;
                    double d9 = this.field_917_f[k1] / 512D;
                    double d10 = (this.field_919_d[k1] / 10D + 1.0D) / 2D;
                    if (d10 < 0.0D)
                    {
                        d6 = d8;
                    }
                    else if (d10 > 1.0D)
                    {
                        d6 = d9;
                    }
                    else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }
                    d6 -= d7;
                    if (k2 > i1 - 4)
                    {
                        double d11 = (float) (k2 - (i1 - 4)) / 3F;
                        d6 = d6 * (1.0D - d11) + -10D * d11;
                    }
                    if ((double) k2 < d3)
                    {
                        double d12 = (d3 - (double) k2) / 4D;
                        if (d12 < 0.0D)
                        {
                            d12 = 0.0D;
                        }
                        if (d12 > 1.0D)
                        {
                            d12 = 1.0D;
                        }
                        d6 = d6 * (1.0D - d12) + -10D * d12;
                    }
                    ad[k1] = d6;
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
        this.field_913_j.setSeed(this.field_907_p.getSeed());
        long l1 = (this.field_913_j.nextLong() / 2L) * 2L + 1L;
        long l2 = (this.field_913_j.nextLong() / 2L) * 2L + 1L;
        this.field_913_j.setSeed((long) i * l1 + (long) j * l2 ^ this.field_907_p.getSeed());
        double d = 0.25D;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, this.field_907_p, this.field_913_j, i, j, false));

        if (this.mineshafts == 0)
        {
            this.mineshaftGenerator.generateStructure(this.field_907_p, this.field_913_j, chunkcoordintpair);
        }

        if (this.strongholds == 0)
        {
            this.strongholdGenerator.generateStructure(this.field_907_p, this.field_913_j, chunkcoordintpair);
        }

        for (int i1 = 0; i1 < 8; i1++)
        {
            int i4 = k + this.field_913_j.nextInt(16) + 8;
            int j6 = this.field_913_j.nextInt(128);
            int i11 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenDungeons()).generate(this.field_907_p, this.field_913_j, i4, j6, i11);
        }

        if (this.alpha)
        {
            for (int j1 = 0; j1 < 10; j1++)
            {
                int j4 = k + this.field_913_j.nextInt(16);
                int k6 = this.field_913_j.nextInt(128);
                int j11 = l + this.field_913_j.nextInt(16);
                (new OldGenClay(32, 0)).generate(this.field_907_p, this.field_913_j, j4, k6, j11);
            }
        }

        for (int k1 = 0; k1 < 20; k1++)
        {
            int k4 = k + this.field_913_j.nextInt(16);
            int l6 = this.field_913_j.nextInt(128);
            int k11 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.dirt, 32, 0)).generate(this.field_907_p, this.field_913_j, k4, l6, k11);
        }

        for (int i2 = 0; i2 < 10; i2++)
        {
            int l4 = k + this.field_913_j.nextInt(16);
            int i7 = this.field_913_j.nextInt(128);
            int l11 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.gravel, 32, 0)).generate(this.field_907_p, this.field_913_j, l4, i7, l11);
        }

        for (int j2 = 0; j2 < 20; j2++)
        {
            int i5 = k + this.field_913_j.nextInt(16);
            int j7 = this.field_913_j.nextInt(128);
            int i12 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.coal_ore, 16, 0)).generate(this.field_907_p, this.field_913_j, i5, j7, i12);
        }

        for (int k2 = 0; k2 < 20; k2++)
        {
            int j5 = k + this.field_913_j.nextInt(16);
            int k7 = this.field_913_j.nextInt(64);
            int j12 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.iron_ore, 8, 0)).generate(this.field_907_p, this.field_913_j, j5, k7, j12);
        }

        for (int i3 = 0; i3 < 2; i3++)
        {
            int k5 = k + this.field_913_j.nextInt(16);
            int l7 = this.field_913_j.nextInt(32);
            int k12 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.gold_ore, 8, 0)).generate(this.field_907_p, this.field_913_j, k5, l7, k12);
        }

        for (int j3 = 0; j3 < 8; j3++)
        {
            int l5 = k + this.field_913_j.nextInt(16);
            int i8 = this.field_913_j.nextInt(16);
            int l12 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.redstone_ore, 7, 0)).generate(this.field_907_p, this.field_913_j, l5, i8, l12);
        }

        for (int k3 = 0; k3 < 1; k3++)
        {
            int i6 = k + this.field_913_j.nextInt(16);
            int j8 = this.field_913_j.nextInt(16);
            int i13 = l + this.field_913_j.nextInt(16);
            (new OldGenMinable(Blocks.diamond_ore, 7, 0)).generate(this.field_907_p, this.field_913_j, i6, j8, i13);
        }

        d = 0.5D;
        int l3 = (int) ((this.field_920_c.func_806_a((double) k * d, (double) l * d) / 8D + this.field_913_j.nextDouble() * 4D + 4D) / 3D);
        if (l3 < 0)
        {
            l3 = 0;
        }
        if (this.field_913_j.nextInt(10) == 0)
        {
            l3++;
        }
        Object obj = new OldGenTrees(0);
        if (this.field_913_j.nextInt(10) == 0 && this.alpha)
        {
            obj = new OldGenBigTree(0);
        }
        for (int k8 = 0; k8 < l3; k8++)
        {
            int j13 = k + this.field_913_j.nextInt(16) + 8;
            int l15 = l + this.field_913_j.nextInt(16) + 8;
            WorldGenerator worldgenerator = new OldGenTrees(0);
            worldgenerator.func_517_a(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(this.field_907_p, this.field_913_j, j13, OWGGenHelper.getHeightValue(this.field_907_p, j13, l15), l15);
        }

        for (int l8 = 0; l8 < 2; l8++)
        {
            int k13 = k + this.field_913_j.nextInt(16) + 8;
            int i16 = this.field_913_j.nextInt(128);
            int j18 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenFlowers(Blocks.yellow_flower)).generate(this.field_907_p, this.field_913_j, k13, i16, j18);
        }

        if (this.field_913_j.nextInt(2) == 0)
        {
            int i9 = k + this.field_913_j.nextInt(16) + 8;
            int l13 = this.field_913_j.nextInt(128);
            int j16 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenFlowers(Blocks.red_flower)).generate(this.field_907_p, this.field_913_j, i9, l13, j16);
        }

        if (this.alpha)
        {
            if (this.field_913_j.nextInt(4) == 0)
            {
                int j9 = k + this.field_913_j.nextInt(16) + 8;
                int i14 = this.field_913_j.nextInt(128);
                int k16 = l + this.field_913_j.nextInt(16) + 8;
                (new OldGenFlowers(Blocks.brown_mushroom)).generate(this.field_907_p, this.field_913_j, j9, i14, k16);
            }
            if (this.field_913_j.nextInt(8) == 0)
            {
                int k9 = k + this.field_913_j.nextInt(16) + 8;
                int j14 = this.field_913_j.nextInt(128);
                int l16 = l + this.field_913_j.nextInt(16) + 8;
                (new OldGenFlowers(Blocks.red_mushroom)).generate(this.field_907_p, this.field_913_j, k9, j14, l16);
            }
        }
        else
        {
            if (this.field_913_j.nextInt(6) == 0)
            {
                int j9 = k + this.field_913_j.nextInt(16) + 8;
                int i14 = this.field_913_j.nextInt(64);
                int k16 = l + this.field_913_j.nextInt(16) + 8;
                (new OldGenFlowers(Blocks.brown_mushroom)).generate(this.field_907_p, this.field_913_j, j9, i14, k16);
            }
            if (this.field_913_j.nextInt(12) == 0)
            {
                int k9 = k + this.field_913_j.nextInt(16) + 8;
                int j14 = this.field_913_j.nextInt(64);
                int l16 = l + this.field_913_j.nextInt(16) + 8;
                (new OldGenFlowers(Blocks.red_mushroom)).generate(this.field_907_p, this.field_913_j, k9, j14, l16);
            }
        }

        if (this.alpha)
        {
            for (int l9 = 0; l9 < 10; l9++)
            {
                int k14 = k + this.field_913_j.nextInt(16) + 8;
                int i17 = this.field_913_j.nextInt(128);
                int k18 = l + this.field_913_j.nextInt(16) + 8;
                (new OldGenReed()).generate(this.field_907_p, this.field_913_j, k14, i17, k18);
            }
        }

        if (this.alpha)
        {
            for (int i10 = 0; i10 < 1; i10++)
            {
                int l14 = k + this.field_913_j.nextInt(16) + 8;
                int j17 = this.field_913_j.nextInt(128);
                int l18 = l + this.field_913_j.nextInt(16) + 8;
                (new OldGenCactus()).generate(this.field_907_p, this.field_913_j, l14, j17, l18);
            }
        }

        for (int j10 = 0; j10 < 50; j10++)
        {
            int i15 = k + this.field_913_j.nextInt(16) + 8;
            int k17 = this.field_913_j.nextInt(this.field_913_j.nextInt(120) + 8);
            int i19 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenLiquids(Blocks.flowing_water)).generate(this.field_907_p, this.field_913_j, i15, k17, i19);
        }

        for (int k10 = 0; k10 < 20; k10++)
        {
            int j15 = k + this.field_913_j.nextInt(16) + 8;
            int l17 = this.field_913_j.nextInt(this.field_913_j.nextInt(this.field_913_j.nextInt(112) + 8) + 8);
            int j19 = l + this.field_913_j.nextInt(16) + 8;
            (new OldGenLiquids(Blocks.flowing_lava)).generate(this.field_907_p, this.field_913_j, j15, l17, j19);
        }

        SpawnerAnimals.performWorldGenSpawning(this.field_907_p, this.chunkManager.getBiomeGenAtChunkCoord(chunkcoordintpair), k + 8, l + 8, 16, 16,
                this.field_913_j);
        k += 8;
        l += 8;

        for (int sn1 = 0; sn1 < 16; ++sn1)
        {
            for (int sn2 = 0; sn2 < 16; ++sn2)
            {
                BlockPos blockpos1 = this.field_907_p.getPrecipitationHeight(new BlockPos(k + sn1, 0, l + sn2));
                BlockPos blockpos2 = blockpos1.down();

                if (this.field_907_p.canBlockFreezeWater(blockpos2))
                {
                    this.field_907_p.setBlockState(blockpos2, Blocks.ice.getDefaultState(), 2);
                }

                if (this.field_907_p.canSnowAt(blockpos1, true))
                {
                    this.field_907_p.setBlockState(blockpos1, Blocks.snow_layer.getDefaultState(), 2);
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, this.field_907_p, this.field_913_j, i, j, false));

        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean func_177460_a(IChunkProvider ichunkprovider, Chunk chunkIn, int x, int z)
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
        return this.field_907_p.getBiomeGenForCoords(pos).getSpawnableList(creatureType);
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
        if (this.mineshafts == 0)
        {
            this.mineshaftGenerator.generate(this, this.field_907_p, i, j, (ChunkPrimer) null);
        }

        if (this.strongholds == 0)
        {
            this.strongholdGenerator.generate(this, this.field_907_p, i, j, (ChunkPrimer) null);
        }
    }
}
