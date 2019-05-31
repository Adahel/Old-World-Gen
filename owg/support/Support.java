package owg.support;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.Loader;

public class Support
{
    public static List<BiomeGenBase> biomes_snow;
    public static List<BiomeGenBase> biomes_cold;
    public static List<BiomeGenBase> biomes_wet;
    public static List<BiomeGenBase> biomes_hot;
    public static List<BiomeGenBase> biomes_small;
    public static BiomeGenBase gravelBeach;

    public static void init()
    {
        biomes_snow = Lists.<BiomeGenBase> newArrayList();
        biomes_cold = Lists.<BiomeGenBase> newArrayList();
        biomes_wet = Lists.<BiomeGenBase> newArrayList();
        biomes_hot = Lists.<BiomeGenBase> newArrayList();
        biomes_small = Lists.<BiomeGenBase> newArrayList();

        addDefaultBiomes();

        if (Loader.isModLoaded("BiomesOPlenty"))
        {
            SupportBOP.init();
        }

        if (Loader.isModLoaded("highlands"))
        {
            SupportHL.init();
        }

        if (Loader.isModLoaded("Thaumcraft"))
        {
            SupportTC.init();
        }

        if (Loader.isModLoaded("abyssalcraft"))
        {
            SupportAC.init();
        }

        if (Loader.isModLoaded("sugiforest"))
        {
            SupportSF.init();
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
