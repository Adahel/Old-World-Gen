package owg.support;

import net.minecraftforge.fml.common.Loader;
import owg.biomes.BiomeGenBase;

public class SupportTC
{
    public static net.minecraft.world.biome.BiomeGenBase biomeMagicalForest()
    {
        net.minecraft.world.biome.BiomeGenBase[] b = net.minecraft.world.biome.BiomeGenBase.getBiomeGenArray();

        for (int i = 0; Loader.isModLoaded("Thaumcraft") && i < 256; i++)
        {
            if (b[i] != null)
            {
                if (b[i].biomeName == "Magical Forest")
                {
                    return b[i];
                }
            }
        }
        return net.minecraft.world.biome.BiomeGenBase.field_180279_ad;
    }

    public static void init()
    {
        try
        {
            addTCBiomes();
        }
        catch (Exception e)
        {
            System.out.println("Thaumcraft support is broken!");
        }
    }

    public static void addTCBiomes()
    {
        System.out.println("OWG: Thaumcraft support is loading");
        BiomeGenBase biomeMagicalForest = BiomeGenBase.biomeMagicalForest;
        System.out.println("Thaumcraft biome added: " + biomeMagicalForest.handle.biomeName);
        Support.biomes_small.add(biomeMagicalForest);
        System.out.println("OWG: Thaumcraft support was loaded");
    }
}