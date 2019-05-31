package owg.support;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

public class SupportAC
{
    public static void init()
    {
        try
        {
            addACBiomes();
        }
        catch (Exception e)
        {
            System.out.println("AbyssalCraft support is broken!");
        }
    }

    private static void addACBiomes()
    {
        System.out.println("OWG: AbyssalCraft support is loading");
        if (ACBiomes.darklands != null)
        {
            Support.biomes_cold.add(ACBiomes.darklands);
            System.out.println("AbyssalCraft biome added: " + ACBiomes.darklands.biomeName);
        }

        if (ACBiomes.darklands_forest != null)
        {
            Support.biomes_cold.add(ACBiomes.darklands_forest);
            System.out.println("AbyssalCraft biome added: " + ACBiomes.darklands_forest.biomeName);
        }

        if (ACBiomes.darklands_plains != null)
        {
            Support.biomes_cold.add(ACBiomes.darklands_plains);
            System.out.println("AbyssalCraft biome added: " + ACBiomes.darklands_plains.biomeName);
        }

        if (ACBiomes.darklands_hills != null)
        {
            Support.biomes_cold.add(ACBiomes.darklands_hills);
            System.out.println("AbyssalCraft biome added: " + ACBiomes.darklands_hills.biomeName);
        }

        if (ACBiomes.coralium_infested_swamp != null)
        {
            Support.biomes_wet.add(ACBiomes.coralium_infested_swamp);
            System.out.println("AbyssalCraft biome added: " + ACBiomes.coralium_infested_swamp.biomeName);
        }
        System.out.println("OWG: AbyssalCraft support was loaded");
    }
}