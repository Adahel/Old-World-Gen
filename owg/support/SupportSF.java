package owg.support;

import sugiforest.api.SugiForestAPI;

public class SupportSF
{
    public static void init()
    {
        try
        {
            addSFBiomes();
        }
        catch (Exception e)
        {
            System.out.println("SugiForest support is broken!");
        }
    }

    private static void addSFBiomes()
    {
        System.out.println("OWG: SugiForest support is loading");
        if (SugiForestAPI.getBiome() != null)
        {
            Support.biomes_cold.add(SugiForestAPI.getBiome());
            System.out.println("SugiForest biome added: " + SugiForestAPI.getBiome().biomeName);
        }
        System.out.println("OWG: SugiForest support was loaded");
    }
}