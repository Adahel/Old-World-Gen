package owg.support;

import java.util.ArrayList;

import net.minecraftforge.fml.common.Loader;
import owg.biomes.BiomeGenBase;

public class Support
{
    public static ArrayList<BiomeGenBase> biomes_snow;
    public static ArrayList<BiomeGenBase> biomes_cold;
    public static ArrayList<BiomeGenBase> biomes_wet;
    public static ArrayList<BiomeGenBase> biomes_hot;
    public static ArrayList<BiomeGenBase> biomes_small;

    public static void init()
    {
        biomes_snow = new ArrayList<BiomeGenBase>();
        biomes_cold = new ArrayList<BiomeGenBase>();
        biomes_wet = new ArrayList<BiomeGenBase>();
        biomes_hot = new ArrayList<BiomeGenBase>();
        biomes_small = new ArrayList<BiomeGenBase>();

        addDefaultBiomes();

        if (Loader.isModLoaded("BiomesOPlenty"))
        {
            SupportBOP.init();
        }

        if (Loader.isModLoaded("Thaumcraft"))
        {
            SupportTC.init();
        }
    }

    public static void addDefaultBiomes()
    {
        biomes_snow.add(BiomeGenBase.icePlains);
        biomes_snow.add(BiomeGenBase.iceMountains);
        biomes_snow.add(BiomeGenBase.coldTaiga);
        biomes_snow.add(BiomeGenBase.coldTaigaHills);

        biomes_cold.add(BiomeGenBase.plains);
        biomes_cold.add(BiomeGenBase.extremeHills);
        biomes_cold.add(BiomeGenBase.forest);
        biomes_cold.add(BiomeGenBase.taiga);
        biomes_cold.add(BiomeGenBase.forestHills);
        biomes_cold.add(BiomeGenBase.taigaHills);
        biomes_cold.add(BiomeGenBase.extremeHillsEdge);
        biomes_cold.add(BiomeGenBase.birchForest);
        biomes_cold.add(BiomeGenBase.birchForestHills);
        biomes_cold.add(BiomeGenBase.megaTaiga);
        biomes_cold.add(BiomeGenBase.megaTaigaHills);
        biomes_cold.add(BiomeGenBase.extremeHillsPlus);

        biomes_hot.add(BiomeGenBase.desert);
        biomes_hot.add(BiomeGenBase.desertHills);
        biomes_hot.add(BiomeGenBase.savanna);
        biomes_hot.add(BiomeGenBase.savannaPlateau);
        biomes_hot.add(BiomeGenBase.mesa);
        biomes_hot.add(BiomeGenBase.mesaPlateau_F);
        biomes_hot.add(BiomeGenBase.mesaPlateau);

        biomes_wet.add(BiomeGenBase.swampland);
        biomes_wet.add(BiomeGenBase.jungle);
        biomes_wet.add(BiomeGenBase.jungleHills);
        biomes_wet.add(BiomeGenBase.jungleEdge);
        biomes_wet.add(BiomeGenBase.roofedForest);
    }
}
