package owg.simulator;

import java.util.HashMap;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Maps;

import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import owg.noise.NoiseOctavesBeta;
import owg.world.ManagerOWG;

public class ChunkSimulatorBeta
{
    private Random rand;

    private NoiseOctavesBeta field_912_k;
    private NoiseOctavesBeta field_911_l;
    private NoiseOctavesBeta field_910_m;
    private NoiseOctavesBeta field_909_n;
    public NoiseOctavesBeta field_922_a;
    public NoiseOctavesBeta field_921_b;

    private double field_4180_q[];
    private double sandNoise[];
    private double gravelNoise[];

    double field_4185_d[];
    double field_4184_e[];
    double field_4183_f[];
    double field_4182_g[];
    double field_4181_h[];
    private final ManagerOWG managerOWG;

    private HashMap<ChunkCoordIntPair, Pair<Integer, Boolean>> cacheOceanHash = Maps.<ChunkCoordIntPair, Pair<Integer, Boolean>> newHashMap();
    private HashMap<ChunkCoordIntPair, Pair<boolean[][], Boolean>> cacheBeachHash = Maps.<ChunkCoordIntPair, Pair<boolean[][], Boolean>> newHashMap();
    private HashMap<ChunkCoordIntPair, Pair<boolean[][], Boolean>> cacheGravelHash = Maps.<ChunkCoordIntPair, Pair<boolean[][], Boolean>> newHashMap();

    public ChunkSimulatorBeta(ManagerOWG managerOWGIn, long l)
    {
        this.rand = new Random(l);
        this.field_912_k = new NoiseOctavesBeta(this.rand, 16);
        this.field_911_l = new NoiseOctavesBeta(this.rand, 16);
        this.field_910_m = new NoiseOctavesBeta(this.rand, 8);
        this.field_909_n = new NoiseOctavesBeta(this.rand, 4);
        new NoiseOctavesBeta(this.rand, 4);
        this.field_922_a = new NoiseOctavesBeta(this.rand, 10);
        this.field_921_b = new NoiseOctavesBeta(this.rand, 16);
        new NoiseOctavesBeta(this.rand, 8);
        this.managerOWG = managerOWGIn;
    }

    private double[] func_4061_a(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if (ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        double ad1[] = this.managerOWG.temperature;
        double ad2[] = this.managerOWG.humidity;
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

    private Pair<int[][], Boolean> simulateTerrain(ChunkCoordIntPair chunkcoordintpair)
    {
        int[][] list = new int[4][4];
        byte byte0 = 4;
        byte byte1 = 64;
        int i = byte0 + 1;
        byte byte2 = 17;
        int j = byte0 + 1;
        this.field_4180_q = this.func_4061_a(this.field_4180_q, chunkcoordintpair.chunkXPos * byte0, 0, chunkcoordintpair.chunkZPos * byte0, i, byte2, j);
        for (int k = 0; k < byte0; k++)
        {
            for (int l = 0; l < byte0; l++)
            {
                for (int i1 = 0; i1 < 16; i1++)
                {
                    double d0 = 0.125D;
                    double d1 = this.field_4180_q[(k * j + l) * byte2 + i1];
                    double d2 = (this.field_4180_q[(k * j + l) * byte2 + i1 + 1] - d1) * d0;
                    for (int j1 = 0; j1 < 8; j1++)
                    {
                        double d3 = d1;
                        int k1 = i1 * 8 + j1;
                        if (d3 > 0.0)
                        {
                            list[k][l] = k1;
                        }
                        d1 += d2;
                    }

                }

            }

        }

        return Pair.of(list, this.landAboveSeaLevel(list));
    }

    private Pair<Integer, Boolean> simulateYTerrain(ChunkCoordIntPair chunkCoordIntPairIn)
    {
        Pair<int[][], Boolean> pair = this.simulateTerrain(chunkCoordIntPairIn);
        int i = 0;
        int j = 0;
        for (int[] aint : pair.getLeft())
        {
            for (int k : aint)
            {
                i += k;
                j++;
            }
        }
        int l = Math.floorDiv(i, j);

        return Pair.of(l, pair.getRight());
    }

    public Pair<Integer, Boolean> getSimulatedTerrain(BlockPos blockpos)
    {
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(blockpos.getX() >> 4, blockpos.getZ() >> 4);
        if (this.cacheOceanHash.containsKey(chunkcoordintpair))
        {
            return this.cacheOceanHash.get(chunkcoordintpair);
        }
        else
        {
            int i = 0;
            int j = 0;
            int k = 1;
            boolean flag = false;
            for (int l = chunkcoordintpair.chunkXPos - k; l <= chunkcoordintpair.chunkXPos + k; l++)
            {
                for (int i1 = chunkcoordintpair.chunkZPos - k; i1 <= chunkcoordintpair.chunkZPos + k; i1++)
                {
                    Pair<Integer, Boolean> pair = this.simulateYTerrain(new ChunkCoordIntPair(l, i1));
                    i += pair.getLeft();
                    if (pair.getRight())
                    {
                        flag = true;
                    }
                    j++;
                }
            }
            int j1 = Math.floorDiv(i, j) + 1;
            Pair<Integer, Boolean> pair = Pair.of(j1, flag);
            this.cacheOceanHash.put(chunkcoordintpair, pair);
            return pair;
        }
    }

    public boolean getSimulatedBeach(BlockPos blockpos)
    {
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(blockpos.getX() >> 4, blockpos.getZ() >> 4);
        int i = blockpos.getX() & 15;
        int j = blockpos.getZ() & 15;
        if (this.cacheBeachHash.containsKey(chunkcoordintpair))
        {
            return this.cacheBeachHash.get(chunkcoordintpair).getLeft()[i][j];
        }
        else
        {
            Pair<boolean[][], Boolean> pair = this.simulateBeach(chunkcoordintpair);
            this.cacheBeachHash.put(chunkcoordintpair, pair);
            return pair.getLeft()[i][j];
        }
    }

    public boolean getSimulatedGravel(BlockPos blockpos)
    {
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(blockpos.getX() >> 4, blockpos.getZ() >> 4);
        int i = blockpos.getX() & 15;
        int j = blockpos.getZ() & 15;
        if (this.cacheGravelHash.containsKey(chunkcoordintpair))
        {
            return this.cacheGravelHash.get(chunkcoordintpair).getLeft()[i][j];
        }
        else
        {
            Pair<boolean[][], Boolean> pair = this.simulateGravel(chunkcoordintpair);
            this.cacheGravelHash.put(chunkcoordintpair, pair);
            return pair.getLeft()[i][j];
        }
    }

    private Pair<boolean[][], Boolean> simulateBeach(ChunkCoordIntPair chunkCoordIntPairIn)
    {
        boolean[][] list = new boolean[16][16];
        double d = 0.03125D;
        this.sandNoise = this.field_909_n.generateNoiseOctaves(this.sandNoise, chunkCoordIntPairIn.chunkXPos * 16, chunkCoordIntPairIn.chunkZPos * 16, 0.0D, 16,
                16, 1, d, d, 1.0D);
        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                boolean flag = this.sandNoise[i + j * 16] + this.rand.nextDouble() * 0.20000000000000001D > 0.0D;
                list[j][i] = flag;
            }
        }
        Pair<boolean[][], Boolean> pair = Pair.of(list, this.isBeachBlock(list));
        this.cacheBeachHash.put(chunkCoordIntPairIn, pair);
        return pair;
    }

    private Pair<boolean[][], Boolean> simulateGravel(ChunkCoordIntPair chunkCoordIntPairIn)
    {
        boolean[][] list = new boolean[16][16];
        double d = 0.03125D;
        this.gravelNoise = this.field_909_n.generateNoiseOctaves(this.gravelNoise, chunkCoordIntPairIn.chunkXPos * 16, 109.0134D,
                chunkCoordIntPairIn.chunkZPos * 16, 16, 1, 16, d, 1.0D, d);
        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                boolean flag = this.gravelNoise[i + j * 16] + this.rand.nextDouble() * 0.20000000000000001D > 3D;
                list[j][i] = flag;
            }
        }
        Pair<boolean[][], Boolean> pair = Pair.of(list, this.isBeachBlock(list));
        this.cacheGravelHash.put(chunkCoordIntPairIn, pair);
        return pair;
    }

    private boolean landAboveSeaLevel(int[][] list)
    {
        for (int[] aint : list)
        {
            for (int i : aint)
            {
                if (i >= 60)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBeachBlock(boolean[][] list)
    {
        for (boolean[] aflag : list)
        {
            for (boolean flag : aflag)
            {
                if (flag)
                {
                    return true;
                }
            }
        }
        return false;
    }
}