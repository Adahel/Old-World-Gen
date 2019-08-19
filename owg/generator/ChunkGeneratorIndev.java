package owg.generator;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.world.gen.GeneratorBushFeature;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import owg.data.DungeonLoot;
import owg.deco.DecoIndevHouse;
import owg.deco.DecoSkyDungeon;
import owg.deco.OldGenDungeons;
import owg.deco.OldGenMinable;
import owg.noise.NoiseOctavesIndev;
import owg.noise.NoiseOctavesInfdev;
import owg.noise.NoisePerlinIndev;
import owg.world.ManagerOWGHell;

public class ChunkGeneratorIndev implements IChunkProvider
{
    private Random rand;
    private NoiseOctavesIndev noiseGen1;
    private NoiseOctavesIndev noiseGen2;
    private NoiseOctavesIndev noiseGen3;
    private NoiseOctavesIndev noiseGen4;
    public NoiseOctavesIndev noiseGen5;
    public NoiseOctavesIndev noiseGen6;
    public NoiseOctavesInfdev mobSpawnerNoise;
    public NoiseOctavesIndev noiseGen10;
    public NoiseOctavesIndev noiseGen11;
    public NoisePerlinIndev perlinGen1;

    private World worldObj;
    public final int strongholds;
    public final int mineshafts;
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private BiomeGenBase[] biomesForGeneration;

    private boolean themeHELL = false;
    private boolean themePARADISE = false;
    private boolean themeWOODS = false;
    private boolean themeSNOW = false;
    private boolean typeIsland = false;
    private boolean typeFloating = false;
    private int size = 1;
    private double width = 1;
    private int layers = 1;
    private int dungeons = 0;
    private int dungeonRate = 15;
    private final ManagerOWGHell chunkManager;

    public ChunkGeneratorIndev(World worldIn, long l, int type, int theme, int devStrongholds, int devMineshafts, int dungeons, int layers, int size)
    {
        this.worldObj = worldIn;
        this.worldObj.getWorldInfo().setSpawn(new BlockPos(0, 256, 0));

        this.strongholds = devStrongholds;
        this.mineshafts = devMineshafts;
        this.rand = new Random(l);
        this.noiseGen1 = new NoiseOctavesIndev(this.rand, 16);
        this.noiseGen2 = new NoiseOctavesIndev(this.rand, 16);
        this.noiseGen3 = new NoiseOctavesIndev(this.rand, 8);
        this.noiseGen4 = new NoiseOctavesIndev(this.rand, 4);
        this.noiseGen5 = new NoiseOctavesIndev(this.rand, 4);
        this.noiseGen6 = new NoiseOctavesIndev(this.rand, 5);
        this.mobSpawnerNoise = new NoiseOctavesInfdev(this.rand, 8);
        this.noiseGen10 = new NoiseOctavesIndev(this.rand, 6);
        this.noiseGen11 = new NoiseOctavesIndev(this.rand, 8);
        this.perlinGen1 = new NoisePerlinIndev(this.rand);

        if (theme == 2)
        {
            this.themeHELL = true;
        }
        if (theme == 3)
        {
            this.themePARADISE = true;
        }
        if (theme == 4)
        {
            this.themeWOODS = true;
        }
        if (theme == 5)
        {
            this.themeSNOW = true;
        }
        if (type == 1)
        {
            this.typeIsland = true;
        }
        if (type == 2)
        {
            this.typeFloating = true;
        }
        if (type == 3)
        {
        }
        if (type == 4)
        {
        }

        this.dungeons = dungeons;
        this.chunkManager = (ManagerOWGHell) this.worldObj.getWorldChunkManager();
        DungeonLoot.init(l);

        this.size = size;
        if (this.typeFloating)
        {
            if (size == 1)
            {
                this.size = 6;
                this.width = 1.2D;
                this.dungeonRate = 8;
            }
            if (size == 2)
            {
                this.size = 12;
                this.width = 2D;
                this.dungeonRate = 10;
            }
            if (size == 3)
            {
                this.size = 18;
                this.width = 3D;
                this.dungeonRate = 12;
            }
        }
        if (this.typeIsland)
        {
            if (size == 1)
            {
                this.size = 3;
            }
            if (size == 2)
            {
                this.size = 5;
            }
            if (size == 3)
            {
                this.size = 7;
            }
        }
        this.layers = layers;
    }

    public void generateSkylands(int i, int j, ChunkPrimer chunk)
    {
        if (this.worldObj.getWorldInfo().getSpawnX() != 0)
        {
            this.worldObj.getWorldInfo().setSpawn(new BlockPos(0, 256, 0));
        }

        int seaLevel = 64;
        int x = i << 4;
        int z = j << 4;
        int jj = 0;
        int lx = 0;
        int lz = 0;

        if (i > -this.size && i < this.size && j > -this.size && j < this.size)
        {
            for (int layer = 0; layer < this.layers; layer++)
            {
                jj = 0;
                for (int k = x; k < x + 16; k++)
                {
                    for (int m = z; m < z + 16; m++)
                    {
                        float f2 = (float) this.noiseGen5.a((k + (layer * 2000F)) / 4.0F, (m + (layer * 2000F)) / 4.0F);
                        int i2 = 35 + (layer * 45) + ((int) f2);

                        if (i2 < 1)
                        {
                            i2 = 1;
                        }

                        if ((float) this.noiseGen5.a(k, m) < 0.0F)
                        {
                            i2 = i2 / 2 << 1;
                            if ((float) this.noiseGen5.a(k / 5, m / 5) < 0.0F)
                            {
                                i2++;
                            }
                        }

                        int thickness = -25;
                        int less = (int) Math.floor(Math.sqrt((k - 0) * (k - 0) + (m - 0) * (m - 0)) / this.width);
                        if (less > 150)
                        {
                            less = 150;
                        }
                        thickness += less;

                        double ovar32 = this.clamp(this.getNoise(8, k + (layer * 2000), m + (layer * 2000), 50, 50, 0));
                        int var77 = (int) (ovar32 * (seaLevel / 2)) + 20 + (layer * 45) + thickness;

                        boolean flagSand = this.noiseGen3.a(k + (layer * 2000F), m + (layer * 2000F)) > 52D + (less / 3D);
                        boolean flagGravel = this.noiseGen11.a(k + (layer * 2000F), m + (layer * 2000F)) > 62D + (less / 3D);

                        for (int i3 = 0; i3 < 256; i3++)
                        {
                            jj++;
                            if (i3 == i2)
                            {
                                if (flagGravel)
                                {
                                    chunk.setBlockState(jj, Blocks.gravel.getDefaultState());
                                }
                                else if (flagSand)
                                {
                                    chunk.setBlockState(jj, Blocks.sand.getDefaultState());
                                }
                                else if (i3 > var77)
                                {
                                    chunk.setBlockState(jj, Blocks.stone.getDefaultState());
                                }
                            }
                            else if (i3 > var77 && i3 < i2)
                            {
                                chunk.setBlockState(jj, Blocks.stone.getDefaultState());
                            }
                        }
                    }
                }
            }
        }
    }

    public void generateSurface(int i, int j, ChunkPrimer chunk)
    {
        if (this.worldObj.getWorldInfo().getSpawnX() != 0)
        {
            this.worldObj.getWorldInfo().setSpawn(new BlockPos(0, 256, 0));
        }

        int jj = 0;

        for (int x = 0; x < 16; x++)
        {
            for (int z = 0; z < 16; z++)
            {
                int t = -1;
                boolean air = true;

                jj += 256;
                for (int y = 255; y > -1; y--)
                {
                    Block b = Blocks.air;

                    IBlockState iblockstate2 = chunk.getBlockState(x, y, z);

                    if (iblockstate2.getBlock().getMaterial() == Material.air)
                    {
                        t = -1;
                    }
                    else if (iblockstate2.getBlock() == Blocks.stone)
                    {
                        t++;
                        if (t == 0 && air)
                        {
                            b = Blocks.grass;
                        }
                        else if (t < 3)
                        {
                            b = Blocks.dirt;
                        }
                        else
                        {
                            b = Blocks.stone;
                        }
                        air = false;
                    }
                    else
                    {
                        t++;
                        b = iblockstate2.getBlock();
                    }
                    jj--;
                    chunk.setBlockState(jj, b.getDefaultState());
                }
                jj += 256;
            }
        }
    }

    public void generateTerrain(int i, int j, ChunkPrimer chunk)
    {
        if (this.worldObj.getWorldInfo().getSpawnX() != 0)
        {
            this.worldObj.getWorldInfo().setSpawn(new BlockPos(0, 256, 0));
        }

        int height = 128;
        int seaLevel = 64;
        int x = i << 4;
        int z = j << 4;
        int jj = 0;
        int lx = 0;
        int lz = 0;

        for (int k = x; k < x + 16; k++)
        {
            for (int m = z; m < z + 16; m++)
            {
                int n = k / 1024;
                int i1 = m / 1024;

                int i2 = 64;
                if (this.typeIsland)
                {
                    float f2 = (float) this.noiseGen5.a(k / 4.0F, m / 4.0F);
                    i2 = 74 - ((int) Math.floor(Math.sqrt((0D - k) * (0D - k) + (0D - m) * (0D - m)) / (double) this.size));
                    if (i2 < 50)
                    {
                        i2 = 50;
                    }
                    i2 += ((int) f2);
                }
                else
                {
                    float f1 = (float) (this.noiseGen1.a(k / 0.03125F, 0.0D, m / 0.03125F) - this.noiseGen2.a(k / 0.015625F, 0.0D, m / 0.015625F)) / 512.0F
                            / 4.0F;
                    float f2 = (float) this.noiseGen5.a(k / 4.0F, m / 4.0F);
                    float f3 = (float) this.noiseGen6.a(k / 8.0F, m / 8.0F) / 8.0F;
                    f2 = f2 > 0.0F ? (float) (this.noiseGen3.a(k * 0.2571428F * 2.0F, m * 0.2571428F * 2.0F) * f3 / 4.0D)
                            : (float) (this.noiseGen4.a(k * 0.2571428F, m * 0.2571428F) * f3);
                    i2 = (int) (f1 + 64.0F + f2);
                }

                if ((float) this.noiseGen5.a(k, m) < 0.0F)
                {
                    i2 = i2 / 2 << 1;
                    if ((float) this.noiseGen5.a(k / 5, m / 5) < 0.0F)
                    {
                        i2++;
                    }
                }

                // BEACH SETTINGS
                boolean flagSand = this.noiseGen3.a(k, m) > 8D;
                boolean flagGravel = this.noiseGen11.a(k, m) > 18D;
                if (this.themePARADISE)
                {
                    flagSand = this.noiseGen3.a(k, m) > -32D;
                }
                else if (this.themeHELL || this.themeWOODS)
                {
                    flagSand = this.noiseGen3.a(k, m) > -8D;
                }

                if (this.typeIsland)
                {
                    flagSand = true;
                }

                // CREATE WORLD
                for (int i3 = 0; i3 < 256; i3++)
                {
                    Block i4 = Blocks.air;
                    int i4m = 0;
                    int beachHeight = seaLevel + 1;
                    if (this.themePARADISE)
                    {
                        beachHeight = seaLevel + 3;
                    }

                    // GENERATE BEDROCK
                    if (i3 == 0)
                    {
                        i4 = Blocks.bedrock;
                    }

                    // GENERATE GRASS
                    else if ((i3 == i2) && i2 >= beachHeight)
                    {
                        if (this.themeHELL)
                        {
                            i4 = Blocks.dirt;
                            i4m = 1;
                        }
                        else
                        {
                            i4 = Blocks.grass;
                        }
                    }

                    // BEACH GEN
                    else if (i3 == i2)
                    {
                        if (flagGravel)
                        {
                            i4 = Blocks.gravel;
                            if (this.themeHELL)
                            {
                                i4 = Blocks.grass;
                            }
                        }
                        else if (flagSand)
                        {
                            i4 = Blocks.sand;
                            if (this.themeHELL)
                            {
                                i4 = Blocks.grass;
                            }
                        }
                        else if (i2 > seaLevel - 1)
                        {
                            i4 = Blocks.grass;
                        }
                        else
                        {
                            i4 = Blocks.dirt;
                        }
                    }

                    // GENERATE STONE
                    else if (i3 <= i2 - 2)
                    {
                        i4 = Blocks.stone;
                    }

                    // GENERATE DIRT
                    else if (i3 < i2)
                    {
                        i4 = Blocks.dirt;
                    }

                    // GENERATE LIQUIDS
                    else if (i3 <= 64 && !this.typeFloating)
                    {
                        if (this.themeHELL)
                        {
                            if (i3 == 64)
                            {
                                i4 = Blocks.flowing_lava;
                            }
                            else
                            {
                                i4 = Blocks.lava;
                            }
                        }
                        else
                        {
                            i4 = Blocks.water;
                        }
                    }

                    this.rand.setSeed(n + i1 * 13871);
                    int i5 = (n << 10) + 128 + this.rand.nextInt(512);
                    int i6 = (i1 << 10) + 128 + this.rand.nextInt(512);
                    i5 = k - i5;
                    int i7 = m - i6;
                    if (i5 < 0)
                    {
                        i5 = -i5;
                    }
                    if (i7 < 0)
                    {
                        i7 = -i7;
                    }
                    if (i7 > i5)
                    {
                        i5 = i7;
                    }
                    if ((i5 = 127 - i5) == 255)
                    {
                        i5 = 1;
                    }
                    if (i5 < i2)
                    {
                        i5 = i2;
                    }
                    if ((i3 <= i5) && ((i4 == Blocks.air) || (i4 == Blocks.water) || (i4 == Blocks.lava)))
                    {
                        i4 = Blocks.brick_block;
                    }

                    chunk.setBlockState(jj, i4.getDefaultState());
                    jj++;
                }
            }
        }
    }

    private double clamp(double input)
    {
        if (input > 1.0D)
        {
            return 1.0D;
        }
        if (input < -1.0D)
        {
            return -1.0D;
        }
        return input;
    }

    private double getNoise(int level, int x, int y, double xfact, double yfact, double zstart)
    {
        double output = 0;
        for (double l = 1; l <= level * level; l *= 2)
        {
            output += this.perlinGen1.a((x / xfact) * l, (y / yfact) * l) / l;
        }
        return output;
    }

    @Override
    public Chunk provideChunk(BlockPos blockPosIn)
    {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }

    @Override
    public Chunk provideChunk(int cx, int cy)
    {
        this.rand.setSeed((long) cx * 341873128712L + (long) cy * 132897987541L);
        ChunkPrimer var3 = new ChunkPrimer();

        if (this.typeFloating)
        {
            this.generateSkylands(cx, cy, var3);
            this.generateSurface(cx, cy, var3);
        }
        else
        {
            this.generateTerrain(cx, cy, var3);
        }
        this.biomesForGeneration = this.chunkManager.loadBlockGeneratorData(this.biomesForGeneration, cx * 16, cy * 16, 16, 16);

        if (!this.typeFloating)
        {
            this.caveGenerator.generate(this, this.worldObj, cx, cy, var3);

            if (this.mineshafts == 0)
            {
                this.mineshaftGenerator.generate(this, this.worldObj, cx, cy, var3);
            }

            if (this.strongholds == 0)
            {
                this.strongholdGenerator.generate(this, this.worldObj, cx, cy, var3);
            }
        }

        Chunk var4 = new Chunk(this.worldObj, var3, cx, cy);
        byte[] var5 = var4.getBiomeArray();

        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte) this.biomesForGeneration[var6].biomeID;
        }

        var4.generateSkylightMap();
        return var4;
    }

    @Override
    public boolean chunkExists(int i, int j)
    {
        return true;
    }

    @Override
    public void populate(IChunkProvider ichunkrovider, int i, int j)
    {
        BlockFalling.fallInstantly = true;
        int var4 = i * 16;
        int var5 = j * 16;
        BiomeGenBase var6 = this.chunkManager.getBiomeGenAt(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) i * var7 + (long) j * var9 ^ this.worldObj.getSeed());
        double d = 0.25D;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkrovider, this.worldObj, this.rand, i, j, false));

        if (!this.typeFloating)
        {
            if (this.mineshafts == 0)
            {
                this.mineshaftGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
            }

            if (this.strongholds == 0)
            {
                this.strongholdGenerator.generateStructure(this.worldObj, this.rand, chunkcoordintpair);
            }
        }

        if (i == (int) Math.floor(this.worldObj.getWorldInfo().getSpawnX() / 16) && j == (int) Math.floor(this.worldObj.getWorldInfo().getSpawnZ() / 16))
        {
            int ix = this.worldObj.getWorldInfo().getSpawnX();
            int iz = this.worldObj.getWorldInfo().getSpawnZ();
            int iy = this.worldObj.getTopSolidOrLiquidBlock(new BlockPos(ix, 0, iz)).getY();
            iy = iy < 60 ? 60 : iy;

            (new DecoIndevHouse(this.themeHELL ? 2 : 1)).generate(this.worldObj, this.rand, ix, iy, iz);
        }

        int extraheight = 128;
        int extradeco = 1;
        if (this.typeFloating)
        {
            extradeco = 2;
            extraheight = 256;
        }

        // ORES
        if (this.typeFloating)
        {
            if (i == 0 && j == 0 && this.dungeons < 2)
            {
                (new DecoSkyDungeon(true)).generate(this.worldObj, this.rand, 0, 2, 0);
            }
            else if (this.dungeons == 0)
            {
                if (this.rand.nextInt(this.dungeonRate) == 0)
                {
                    int j5 = var4 + this.rand.nextInt(16) + 8;
                    int k88 = this.rand.nextInt(15);
                    int j11 = var5 + this.rand.nextInt(16) + 8;
                    (new DecoSkyDungeon(false)).generate(this.worldObj, this.rand, j5, k88, j11);
                }
            }
        }
        else
        {
            for (int k1 = 0; k1 < 12; k1++)
            {
                int j5 = var4 + this.rand.nextInt(16) + 8;
                int k88 = this.rand.nextInt(128);
                int j11 = var5 + this.rand.nextInt(16) + 8;
                (new OldGenDungeons()).generate(this.worldObj, this.rand, j5, k88, j11);
            }
        }

        for (int k2 = 0; k2 < 5; k2++)
        {
            int i6 = var4 + this.rand.nextInt(16);
            int j9 = this.rand.nextInt(64);
            int i12 = var5 + this.rand.nextInt(16);
            (new OldGenMinable(Blocks.gravel, 32, 2)).generate(this.worldObj, this.rand, i6, j9, i12);
        }

        for (int i3 = 0; i3 < 20 * extradeco; i3++)
        {
            int j6 = var4 + this.rand.nextInt(16);
            int k9 = this.rand.nextInt(extraheight);
            int j12 = var5 + this.rand.nextInt(16);
            (new OldGenMinable(Blocks.coal_ore, 16, 2)).generate(this.worldObj, this.rand, j6, k9, j12);
        }

        for (int j3 = 0; j3 < 20 * extradeco; j3++)
        {
            int k6 = var4 + this.rand.nextInt(16);
            int l9 = this.rand.nextInt(64 * extradeco);
            int k12 = var5 + this.rand.nextInt(16);
            (new OldGenMinable(Blocks.iron_ore, 8, 2)).generate(this.worldObj, this.rand, k6, l9, k12);
        }

        int floatingore = 0;
        if (this.typeFloating)
        {
            floatingore = 16;
        }

        for (int k3 = 0; k3 < 2; k3++)
        {
            int l6 = var4 + this.rand.nextInt(16);
            int i10 = this.rand.nextInt(32) + floatingore;
            int l12 = var5 + this.rand.nextInt(16);
            (new OldGenMinable(Blocks.gold_ore, 8, 2)).generate(this.worldObj, this.rand, l6, i10, l12);
        }

        for (int l33 = 0; l33 < 8; l33++)
        {
            int i7 = var4 + this.rand.nextInt(16);
            int j10 = this.rand.nextInt(16) + floatingore;
            int i13 = var5 + this.rand.nextInt(16);
            (new OldGenMinable(Blocks.redstone_ore, 7, 2)).generate(this.worldObj, this.rand, i7, j10, i13);
        }

        for (int i4 = 0; i4 < 1; i4++)
        {
            int j7 = var4 + this.rand.nextInt(16);
            int k10 = this.rand.nextInt(16) + floatingore;
            int j13 = var5 + this.rand.nextInt(16);
            (new OldGenMinable(Blocks.diamond_ore, 7, 2)).generate(this.worldObj, this.rand, j7, k10, j13);
        }

        for (int j4 = 0; j4 < 1; j4++)
        {
            int k7 = var4 + this.rand.nextInt(16);
            int l10 = this.rand.nextInt(16) + floatingore;
            int k13 = var5 + this.rand.nextInt(16);
            (new OldGenMinable(Blocks.lapis_ore, 6, 2)).generate(this.worldObj, this.rand, k7, l10, k13);
        }

        // TREES
        d = 0.5D;
        int l333 = (int) ((this.mobSpawnerNoise.func_806_a((double) var4 * d, (double) var5 * d) / 8D + this.rand.nextDouble() * 4D + 4D) / 3D);
        if (l333 < 0)
        {
            l333 = 0;
        }
        if (this.rand.nextInt(10) == 0)
        {
            l333++;
        }
        if (this.themeWOODS)
        {
            l333 += 8;
        }
        else if (this.typeIsland)
        {
            l333 += 1;
        }
        Object obj = new WorldGenTrees(false, 5, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState(), false);
        for (int k88 = 0; k88 < l333; k88++)
        {
            int j133 = var4 + this.rand.nextInt(16) + 8;
            int l155 = var5 + this.rand.nextInt(16) + 8;
            ((WorldGenerator) (obj)).func_175904_e();
            ((WorldGenerator) (obj)).generate(this.worldObj, this.rand, this.worldObj.getHeight(new BlockPos(j133, 0, l155)));
        }

        // FLOWERS
        int amount1 = 2;
        if (this.themePARADISE)
        {
            amount1 = 8;
        }
        for (int i34 = 0; i34 < amount1; i34++)
        {
            for (int i14 = 0; i14 < 2 * extradeco; i14++)
            {
                int k14 = var4 + this.rand.nextInt(16) + 8;
                int l16 = this.rand.nextInt(extraheight);
                int k19 = var5 + this.rand.nextInt(16) + 8;
                (new WorldGenFlowers(Blocks.yellow_flower, BlockFlower.EnumFlowerType.DANDELION)).generate(this.worldObj, this.rand,
                        new BlockPos(k14, l16, k19));
            }
            if (this.rand.nextInt(2 / extradeco) == 0)
            {
                int j15 = var4 + this.rand.nextInt(16) + 8;
                int j17 = this.rand.nextInt(extraheight);
                int j20 = var5 + this.rand.nextInt(16) + 8;
                (new WorldGenFlowers(Blocks.red_flower, BlockFlower.EnumFlowerType.POPPY)).generate(this.worldObj, this.rand, new BlockPos(j15, j17, j20));
            }
        }

        // MUSHROOMS
        if (this.themeHELL || this.themeWOODS)
        {
            int k15 = var4 + this.rand.nextInt(16) + 8;
            int k17 = this.rand.nextInt(extraheight);
            int k20 = var5 + this.rand.nextInt(16) + 8;
            (new GeneratorBushFeature(Blocks.brown_mushroom)).generate(this.worldObj, this.rand, new BlockPos(k15, k17, k20));

            if (this.rand.nextInt(2 / extradeco) == 0)
            {
                int l15 = var4 + this.rand.nextInt(16) + 8;
                int l17 = this.rand.nextInt(extraheight);
                int l20 = var5 + this.rand.nextInt(16) + 8;
                (new GeneratorBushFeature(Blocks.red_mushroom)).generate(this.worldObj, this.rand, new BlockPos(l15, l17, l20));
            }
        }
        else
        {
            if (this.rand.nextInt(4 / extradeco) == 0)
            {
                int k15 = var4 + this.rand.nextInt(16) + 8;
                int k17 = this.rand.nextInt(extraheight);
                int k20 = var5 + this.rand.nextInt(16) + 8;
                (new GeneratorBushFeature(Blocks.brown_mushroom)).generate(this.worldObj, this.rand, new BlockPos(k15, k17, k20));
            }
            if (this.rand.nextInt(8 / extradeco) == 0)
            {
                int l15 = var4 + this.rand.nextInt(16) + 8;
                int l17 = this.rand.nextInt(extraheight);
                int l20 = var5 + this.rand.nextInt(16) + 8;
                (new GeneratorBushFeature(Blocks.red_mushroom)).generate(this.worldObj, this.rand, new BlockPos(l15, l17, l20));
            }
        }

        SpawnerAnimals.performWorldGenSpawning(this.worldObj, this.chunkManager.getBiomeGenAtChunkCoord(chunkcoordintpair), var4 + 8, var5 + 8, 16, 16,
                this.rand);
        BlockFalling.fallInstantly = false;

        if (this.themeSNOW)
        {
            var4 += 8;
            var5 += 8;

            for (int var85 = 0; var85 < 16; ++var85)
            {
                for (int var86 = 0; var86 < 16; ++var86)
                {
                    BlockPos blockpos1 = this.worldObj.getPrecipitationHeight(new BlockPos(var4 + var85, 0, var5 + var86));
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
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkrovider, this.worldObj, this.rand, i, j, false));
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
        return this.worldObj.getBiomeGenForCoords(pos).getSpawnableList(creatureType);
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position)
    {
        if ("Stronghold".equals(structureName) && this.typeFloating && this.dungeons < 2)
        {
            return new BlockPos(0, 2, 0);
        }
        else
        {
            return "Stronghold".equals(structureName) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(worldIn, position)
                    : null;
        }
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
        if (!this.typeFloating)
        {
            if (this.mineshafts == 0)
            {
                this.strongholdGenerator.generate(this, this.worldObj, i, j, (ChunkPrimer) null);
            }

            if (this.strongholds == 0)
            {
                this.mineshaftGenerator.generate(this, this.worldObj, i, j, (ChunkPrimer) null);
            }
        }
    }
}