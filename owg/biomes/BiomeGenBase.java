package owg.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import owg.deco.OldGenBigTree;
import owg.deco.OldGenForest;
import owg.deco.OldGenTaiga1;
import owg.deco.OldGenTaiga2;
import owg.deco.OldGenTrees;
import owg.deco.WorldGenerator;
import owg.support.SupportBOP;
import owg.support.SupportTC;

public enum BiomeGenBase implements OWGGenBiome
{
    CLASSICnormal(
            BiomeList.CLASSICnormal
    ), CLASSICsnow(
            BiomeList.CLASSICsnow
    ),

    OLDrainforest(
            BiomeList.OLDrainforest
    )
    {
        @Override
        public WorldGenerator getRandomWorldGenForTrees(Random random)
        {
            return (WorldGenerator) (random.nextInt(3) == 0 ? new OldGenBigTree(2) : new OldGenTrees(2));
        }
    },
    OLDswampland(
            BiomeList.OLDswampland
    ), OLDseasonalForest(
            BiomeList.OLDseasonalForest
    ), OLDforest(
            BiomeList.OLDforest
    )
    {
        @Override
        public WorldGenerator getRandomWorldGenForTrees(Random random)
        {
            return (WorldGenerator) (random.nextInt(5) == 0 ? new OldGenForest() : (random.nextInt(3) == 0 ? new OldGenBigTree(2) : new OldGenTrees(2)));
        }
    },
    OLDsavanna(
            BiomeList.OLDsavanna
    ), OLDshrubland(
            BiomeList.OLDshrubland
    ), OLDtaiga(
            BiomeList.OLDtaiga
    )
    {
        @Override
        public WorldGenerator getRandomWorldGenForTrees(Random random)
        {
            return (WorldGenerator) (random.nextInt(3) == 0 ? new OldGenTaiga1() : new OldGenTaiga2());
        }
    },
    OLDdesert(
            BiomeList.OLDdesert, Blocks.sand,
            Blocks.sand
    ), OLDplains(
            BiomeList.OLDplains
    ), OLDtundra(
            BiomeList.OLDtundra
    ),

    coldTaiga(
            net.minecraft.world.biome.BiomeGenBase.coldTaiga
    ), coldTaigaHills(
            net.minecraft.world.biome.BiomeGenBase.coldTaigaHills
    ), icePlains(
            net.minecraft.world.biome.BiomeGenBase.icePlains
    ), iceMountains(
            net.minecraft.world.biome.BiomeGenBase.iceMountains
    ),

    plains(
            net.minecraft.world.biome.BiomeGenBase.plains
    ), extremeHills(
            net.minecraft.world.biome.BiomeGenBase.extremeHills
    ), forest(
            net.minecraft.world.biome.BiomeGenBase.forest
    ), taiga(
            net.minecraft.world.biome.BiomeGenBase.taiga
    ), forestHills(
            net.minecraft.world.biome.BiomeGenBase.forestHills
    ), taigaHills(
            net.minecraft.world.biome.BiomeGenBase.taigaHills
    ), extremeHillsEdge(
            net.minecraft.world.biome.BiomeGenBase.extremeHillsEdge
    ), birchForest(
            net.minecraft.world.biome.BiomeGenBase.birchForest
    ), birchForestHills(
            net.minecraft.world.biome.BiomeGenBase.birchForestHills
    ), megaTaiga(
            net.minecraft.world.biome.BiomeGenBase.megaTaiga
    ), megaTaigaHills(
            net.minecraft.world.biome.BiomeGenBase.megaTaigaHills
    ), extremeHillsPlus(
            net.minecraft.world.biome.BiomeGenBase.extremeHillsPlus
    ),

    desert(
            net.minecraft.world.biome.BiomeGenBase.desert
    ), desertHills(
            net.minecraft.world.biome.BiomeGenBase.desertHills
    ), savanna(
            net.minecraft.world.biome.BiomeGenBase.savanna
    ), savannaPlateau(
            net.minecraft.world.biome.BiomeGenBase.savannaPlateau
    ), mesa(
            net.minecraft.world.biome.BiomeGenBase.mesa
    ), mesaPlateau_F(
            net.minecraft.world.biome.BiomeGenBase.mesaPlateau_F
    ), mesaPlateau(
            net.minecraft.world.biome.BiomeGenBase.mesaPlateau
    ),

    swampland(
            net.minecraft.world.biome.BiomeGenBase.swampland
    ), jungle(
            net.minecraft.world.biome.BiomeGenBase.jungle
    ), jungleHills(
            net.minecraft.world.biome.BiomeGenBase.jungleHills
    ), jungleEdge(
            net.minecraft.world.biome.BiomeGenBase.jungleEdge
    ), roofedForest(
            net.minecraft.world.biome.BiomeGenBase.roofedForest
    ),

    alps(
            SupportBOP.getBOPBiomes()[0]
    ), bamboo_forest(
            SupportBOP.getBOPBiomes()[1]
    ), bayou(
            SupportBOP.getBOPBiomes()[2]
    ), bog(
            SupportBOP.getBOPBiomes()[3]
    ), boreal_forest(
            SupportBOP.getBOPBiomes()[4]
    ), brushland(
            SupportBOP.getBOPBiomes()[5]
    ), chaparral(
            SupportBOP.getBOPBiomes()[6]
    ), cherry_blossom_grove(
            SupportBOP.getBOPBiomes()[7]
    ), cold_desert(
            SupportBOP.getBOPBiomes()[8]
    ), coniferous_forest(
            SupportBOP.getBOPBiomes()[9]
    ), crag(
            SupportBOP.getBOPBiomes()[10]
    ), dead_forest(
            SupportBOP.getBOPBiomes()[11]
    ), dead_swamp(
            SupportBOP.getBOPBiomes()[12]
    ), eucalyptus_forest(
            SupportBOP.getBOPBiomes()[13]
    ), fen(
            SupportBOP.getBOPBiomes()[14]
    ), flower_field(
            SupportBOP.getBOPBiomes()[15]
    ), grassland(
            SupportBOP.getBOPBiomes()[16]
    ), grove(
            SupportBOP.getBOPBiomes()[17]
    ), heathland(
            SupportBOP.getBOPBiomes()[18]
    ), highland(
            SupportBOP.getBOPBiomes()[19]
    ), land_of_lakes(
            SupportBOP.getBOPBiomes()[20]
    ), lavender_fields(
            SupportBOP.getBOPBiomes()[21]
    ), lush_desert(
            SupportBOP.getBOPBiomes()[22]
    ), lush_swamp(
            SupportBOP.getBOPBiomes()[23]
    ), maple_woods(
            SupportBOP.getBOPBiomes()[24]
    ), marsh(
            SupportBOP.getBOPBiomes()[25]
    ), meadow(
            SupportBOP.getBOPBiomes()[26]
    ), moor(
            SupportBOP.getBOPBiomes()[27]
    ), mountain(
            SupportBOP.getBOPBiomes()[28]
    ), mystic_grove(
            SupportBOP.getBOPBiomes()[29]
    ), ominous_woods(
            SupportBOP.getBOPBiomes()[30]
    ), orchard(
            SupportBOP.getBOPBiomes()[31]
    ), outback(
            SupportBOP.getBOPBiomes()[32]
    ), overgrown_cliffs(
            SupportBOP.getBOPBiomes()[33]
    ), prairie(
            SupportBOP.getBOPBiomes()[34]
    ), quagmire(
            SupportBOP.getBOPBiomes()[35]
    ), rainforest(
            SupportBOP.getBOPBiomes()[36]
    ), redwood_forest(
            SupportBOP.getBOPBiomes()[37]
    ), sacred_springs(
            SupportBOP.getBOPBiomes()[38]
    ), seasonal_forest(
            SupportBOP.getBOPBiomes()[39]
    ), shield(
            SupportBOP.getBOPBiomes()[40]
    ), shrubland(
            SupportBOP.getBOPBiomes()[41]
    ), snowy_coniferous_forest(
            SupportBOP.getBOPBiomes()[42]
    ), snowy_forest(
            SupportBOP.getBOPBiomes()[43]
    ), steppe(
            SupportBOP.getBOPBiomes()[44]
    ), temperate_rainforest(
            SupportBOP.getBOPBiomes()[45]
    ), tropical_rainforest(
            SupportBOP.getBOPBiomes()[46]
    ), tundra(
            SupportBOP.getBOPBiomes()[47]
    ), wasteland(
            SupportBOP.getBOPBiomes()[48]
    ), wetland(
            SupportBOP.getBOPBiomes()[49]
    ), woodland(
            SupportBOP.getBOPBiomes()[50]
    ), xeric_shrubland(
            SupportBOP.getBOPBiomes()[51]
    ),

    biomeMagicalForest(
            SupportTC.biomeMagicalForest()
    );

    public net.minecraft.world.biome.BiomeGenBase handle;
    public final Block topBlock;
    public final Block fillerBlock;
    private static final BiomeGenBase[] biomeLookupTable = new BiomeGenBase[4096];

    private BiomeGenBase(net.minecraft.world.biome.BiomeGenBase handle)
    {
        this(handle, Blocks.grass, Blocks.dirt);
    }

    private BiomeGenBase(net.minecraft.world.biome.BiomeGenBase handle, Block topBlock, Block fillerBlock)
    {
        this.handle = handle;
        this.topBlock = topBlock;
        this.fillerBlock = fillerBlock;
    }

    @Override
    public net.minecraft.world.biome.BiomeGenBase getHandle()
    {
        return this.handle;
    }

    @Override
    public void setHandle(net.minecraft.world.biome.BiomeGenBase handle)
    {
        this.handle = handle;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        return (WorldGenerator) (random.nextInt(10) == 0 ? new OldGenBigTree(2) : new OldGenTrees(2));
    }

    public static void generateBiomeLookup()
    {
        for (int i = 0; i < 64; ++i)
        {
            for (int j = 0; j < 64; ++j)
            {
                biomeLookupTable[i + j * 64] = getBetaBiomes((float) i / 63.0F, (float) j / 63.0F);
            }
        }
    }

    public static BiomeGenBase getBiomeFromLookup(double d, double d1)
    {
        int i = (int) (d * 63D);
        int j = (int) (d1 * 63D);
        return biomeLookupTable[i + j * 64];
    }

    public static BiomeGenBase getBetaBiomes(float f, float f1)
    {
        f1 *= f;
        if (f < 0.1F)
        {
            return BiomeGenBase.OLDtundra;
        }
        if (f1 < 0.2F)
        {
            if (f < 0.5F)
            {
                return BiomeGenBase.OLDtundra;
            }
            if (f < 0.95F)
            {
                return BiomeGenBase.OLDsavanna;
            }
            else
            {
                return BiomeGenBase.OLDdesert;
            }
        }
        if (f1 > 0.5F && f < 0.7F)
        {
            return BiomeGenBase.OLDswampland;
        }
        if (f < 0.5F)
        {
            return BiomeGenBase.OLDtaiga;
        }
        if (f < 0.97F)
        {
            if (f1 < 0.35F)
            {
                return BiomeGenBase.OLDshrubland;
            }
            else
            {
                return BiomeGenBase.OLDforest;
            }
        }
        if (f1 < 0.45F)
        {
            return BiomeGenBase.OLDplains;
        }
        if (f1 < 0.9F)
        {
            return BiomeGenBase.OLDseasonalForest;
        }
        else
        {
            return BiomeGenBase.OLDrainforest;
        }
    }

    static
    {
        generateBiomeLookup();
    }
}
