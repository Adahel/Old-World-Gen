package owg.biomes;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import owg.config.ConfigOWG;
import owg.deco.OldGenBigTree;
import owg.deco.OldGenTrees;
import owg.deco.WorldGenerator;

public abstract class BiomeList extends BiomeGenBase
{
    public static final BiomeList OLDrainforest = (new BiomeBeta(ConfigOWG.biomeIDs[3], 0)).setColor(353825).setBiomeName("owg_rainforest")
            .setTemperatureRainfall(0.95F, 0.95F).setFillerBlockMetadata(2094168);
    public static final BiomeList OLDswampland = (new BiomeBeta(ConfigOWG.biomeIDs[7], 1)).setColor(353825).setBiomeName("owg_swampland")
            .setTemperatureRainfall(0.55F, 0.65F).setFillerBlockMetadata(9154376);
    public static final BiomeList OLDseasonalForest = (new BiomeBeta(ConfigOWG.biomeIDs[5], 2)).setColor(353825).setBiomeName("owg_seasonalForest")
            .setTemperatureRainfall(0.95F, 0.7F);
    public static final BiomeList OLDforest = (new BiomeBeta(ConfigOWG.biomeIDs[1], 3)).setColor(353825).setBiomeName("owg_forest")
            .setTemperatureRainfall(0.8F, 0.6F).setFillerBlockMetadata(5159473);
    public static final BiomeList OLDsavanna = (new BiomeBeta(ConfigOWG.biomeIDs[4], 4)).setColor(16421912).setBiomeName("owg_savanna")
            .setTemperatureRainfall(0.7F, 0.1F);
    public static final BiomeList OLDshrubland = (new BiomeBeta(ConfigOWG.biomeIDs[6], 5)).setColor(353825).setBiomeName("owg_shrubland")
            .setTemperatureRainfall(0.7F, 0.3F);
    public static final BiomeList OLDtaiga = (new BiomeBeta(ConfigOWG.biomeIDs[8], 6)).setColor(16777215).setBiomeName("owg_taiga")
            .setTemperatureRainfall(0.1F, 0.35F).setEnableSnow().setFillerBlockMetadata(8107825);
    public static final BiomeList OLDdesert = (new BiomeBeta(ConfigOWG.biomeIDs[0], 7)).setColor(16421912).setBiomeName("owg_desert")
            .setTemperatureRainfall(0.95F, 0.1F).setDisableRain();
    public static final BiomeList OLDplains = (new BiomeBeta(ConfigOWG.biomeIDs[2], 8)).setColor(353825).setBiomeName("owg_plains")
            .setTemperatureRainfall(0.95F, 0.35F);
    public static final BiomeList OLDtundra = (new BiomeBeta(ConfigOWG.biomeIDs[9], 9)).setColor(16777215).setBiomeName("owg_tundra")
            .setTemperatureRainfall(0.1F, 0.1F).setEnableSnow().setFillerBlockMetadata(12899129);
    public static final BiomeList CLASSICnormal = (new BiomeClassic(ConfigOWG.biomeIDs[10])).setColor(353825).setBiomeName("owg_Classic");
    public static final BiomeList CLASSICsnow = BiomeList.CLASSICnormal.createMutatedBiome(ConfigOWG.biomeIDs[11]);

    public Block topBlock;
    public Block fillerBlock;
    private static int[] biomeLookupTable = new int[4096];

    public BiomeList(int id)
    {
        this(id, true);
    }

    public BiomeList(int id, boolean register)
    {
        super(id, register);
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
    }

    public static void init()
    {
        BiomeDictionary.registerBiomeType(OLDdesert, Type.HOT, Type.SPARSE, Type.DRY, Type.SANDY);
        BiomeDictionary.registerBiomeType(OLDforest, Type.DENSE, Type.FOREST);
        BiomeDictionary.registerBiomeType(OLDplains, Type.SPARSE, Type.PLAINS, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(OLDrainforest, Type.DENSE, Type.WET, Type.JUNGLE, Type.FOREST, Type.SWAMP);
        BiomeDictionary.registerBiomeType(OLDsavanna, Type.HOT, Type.SPARSE, Type.SAVANNA);
        BiomeDictionary.registerBiomeType(OLDseasonalForest, Type.DENSE, Type.FOREST);
        BiomeDictionary.registerBiomeType(OLDshrubland, Type.HOT, Type.SPARSE, Type.DRY, Type.PLAINS);
        BiomeDictionary.registerBiomeType(OLDswampland, Type.DENSE, Type.WET, Type.SWAMP);
        BiomeDictionary.registerBiomeType(OLDtaiga, Type.COLD, Type.DENSE, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
        BiomeDictionary.registerBiomeType(OLDtundra, Type.COLD, Type.SPARSE, Type.PLAINS, Type.MOUNTAIN, Type.SNOWY);

        BiomeDictionary.registerBiomeType(CLASSICnormal, Type.HOT, Type.PLAINS);
        BiomeDictionary.registerBiomeType(CLASSICsnow, Type.COLD, Type.SNOWY);
    }

    @Override
    public BiomeList setDisableRain()
    {
        return (BiomeList) super.setDisableRain();
    }

    public static void generateBiomeLookup()
    {
        for (int i = 0; i < 64; ++i)
        {
            for (int j = 0; j < 64; ++j)
            {
                biomeLookupTable[i + j * 64] = getBiome((float) i / 63.0F, (float) j / 63.0F);
            }
        }

        OLDdesert.topBlock = OLDdesert.fillerBlock = Blocks.sand;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        return (WorldGenerator) (random.nextInt(10) == 0 ? new OldGenBigTree(2) : new OldGenTrees(2));
    }

    @Override
    public BiomeList setEnableSnow()
    {
        return (BiomeList) super.setEnableSnow();
    }

    @Override
    public BiomeList setBiomeName(String name)
    {
        return (BiomeList) super.setBiomeName(name);
    }

    @Override
    public BiomeList setFillerBlockMetadata(int meta)
    {
        return (BiomeList) super.setFillerBlockMetadata(meta);
    }

    @Override
    public BiomeList setColor(int colorIn)
    {
        return (BiomeList) super.setColor(colorIn);
    }

    public static int getBiomeFromLookup(double d, double d1)
    {
        int i = (int) (d * 63D);
        int j = (int) (d1 * 63D);
        return biomeLookupTable[i + j * 64];
    }

    public static int getBiome(float f, float f1)
    {
        f1 *= f;
        if (f < 0.1F)
        {
            return BiomeList.OLDtundra.biomeID;
        }
        if (f1 < 0.2F)
        {
            if (f < 0.5F)
            {
                return BiomeList.OLDtundra.biomeID;
            }
            if (f < 0.95F)
            {
                return BiomeList.OLDsavanna.biomeID;
            }
            else
            {
                return BiomeList.OLDdesert.biomeID;
            }
        }
        if (f1 > 0.5F && f < 0.7F)
        {
            return BiomeList.OLDswampland.biomeID;
        }
        if (f < 0.5F)
        {
            return BiomeList.OLDtaiga.biomeID;
        }
        if (f < 0.97F)
        {
            if (f1 < 0.35F)
            {
                return BiomeList.OLDshrubland.biomeID;
            }
            else
            {
                return BiomeList.OLDforest.biomeID;
            }
        }
        if (f1 < 0.45F)
        {
            return BiomeList.OLDplains.biomeID;
        }
        if (f1 < 0.9F)
        {
            return BiomeList.OLDseasonalForest.biomeID;
        }
        else
        {
            return BiomeList.OLDrainforest.biomeID;
        }
    }

    @Override
    public int getSkyColorByTemp(float p_76731_1_)
    {
        return super.getSkyColorByTemp(p_76731_1_);
    }

    @Override
    public List getSpawnableList(EnumCreatureType creatureType)
    {
        return super.getSpawnableList(creatureType);
    }

    @Override
    public boolean getEnableSnow()
    {
        return super.getEnableSnow();
    }

    @Override
    public boolean canSpawnLightningBolt()
    {
        return super.canSpawnLightningBolt();
    }

    @Override
    public BiomeList setTemperatureRainfall(float temperatureIn, float rainfallIn)
    {
        return (BiomeList) super.setTemperatureRainfall(temperatureIn, rainfallIn);
    }

    @Override
    public BiomeList createMutatedBiome(int p_180277_1_)
    {
        return (BiomeList) super.createMutatedBiome(p_180277_1_);
    }

    static
    {
        generateBiomeLookup();
    }
}
