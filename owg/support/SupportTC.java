package owg.support;

import thaumcraft.common.lib.world.biomes.BiomeHandler;

public class SupportTC
{
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
        if (BiomeHandler.biomeMagicalForest != null)
        {
            Support.biomes_small.add(BiomeHandler.biomeMagicalForest);
            System.out.println("Thaumcraft biome added: " + BiomeHandler.biomeMagicalForest.biomeName);
        }
        System.out.println("OWG: Thaumcraft support was loaded");
    }
}