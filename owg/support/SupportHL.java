package owg.support;

import com.sdj64.highlands.biome.HighlandsBiomes;

public class SupportHL
{
    public static void init()
    {
        try
        {
            addHLBiomes();
        }
        catch (Exception e)
        {
            System.out.println("Highlands support is broken!");
        }
    }

    private static void addHLBiomes()
    {
        System.out.println("OWG: Highlands support is loading");
        if (HighlandsBiomes.highlandsBiome != null)
        {
            Support.biomes_cold.add(HighlandsBiomes.highlandsBiome);
            System.out.println("Highlands biome added: " + HighlandsBiomes.highlandsBiome.biomeName);
        }

        if (HighlandsBiomes.pinelands != null)
        {
            Support.biomes_cold.add(HighlandsBiomes.pinelands);
            System.out.println("Highlands biome added: " + HighlandsBiomes.pinelands.biomeName);
        }

        if (HighlandsBiomes.autumnForest != null)
        {
            Support.biomes_cold.add(HighlandsBiomes.autumnForest);
            System.out.println("Highlands biome added: " + HighlandsBiomes.autumnForest.biomeName);
        }

        if (HighlandsBiomes.alps != null)
        {
            Support.biomes_snow.add(HighlandsBiomes.alps);
            System.out.println("Highlands biome added: " + HighlandsBiomes.alps.biomeName);
        }

        if (HighlandsBiomes.meadow != null)
        {
            Support.biomes_cold.add(HighlandsBiomes.meadow);
            System.out.println("Highlands biome added: " + HighlandsBiomes.meadow.biomeName);
        }

        if (HighlandsBiomes.tropicDryForest != null)
        {
            Support.biomes_hot.add(HighlandsBiomes.tropicDryForest);
            System.out.println("Highlands biome added: " + HighlandsBiomes.tropicDryForest.biomeName);
        }

        if (HighlandsBiomes.redwoodForest != null)
        {
            Support.biomes_cold.add(HighlandsBiomes.redwoodForest);
            System.out.println("Highlands biome added: " + HighlandsBiomes.redwoodForest.biomeName);
        }

        if (HighlandsBiomes.lowlands != null)
        {
            Support.biomes_cold.add(HighlandsBiomes.lowlands);
            System.out.println("Highlands biome added: " + HighlandsBiomes.lowlands.biomeName);
        }

        if (HighlandsBiomes.mojave != null)
        {
            Support.biomes_hot.add(HighlandsBiomes.mojave);
            System.out.println("Highlands biome added: " + HighlandsBiomes.mojave.biomeName);
        }

        if (HighlandsBiomes.poplarHills != null)
        {
            Support.biomes_cold.add(HighlandsBiomes.poplarHills);
            System.out.println("Highlands biome added: " + HighlandsBiomes.poplarHills.biomeName);
        }

        if (HighlandsBiomes.badlands != null)
        {
            Support.biomes_hot.add(HighlandsBiomes.badlands);
            System.out.println("Highlands biome added: " + HighlandsBiomes.badlands.biomeName);
        }

        if (HighlandsBiomes.greyMtns != null)
        {
            Support.biomes_cold.add(HighlandsBiomes.greyMtns);
            System.out.println("Highlands biome added: " + HighlandsBiomes.greyMtns.biomeName);
        }

        if (HighlandsBiomes.tropHills != null)
        {
            Support.biomes_wet.add(HighlandsBiomes.tropHills);
            System.out.println("Highlands biome added: " + HighlandsBiomes.tropHills.biomeName);
        }

        if (HighlandsBiomes.dryForest != null)
        {
            Support.biomes_hot.add(HighlandsBiomes.dryForest);
            System.out.println("Highlands biome added: " + HighlandsBiomes.dryForest.biomeName);
        }

        if (HighlandsBiomes.adirondack != null)
        {
            Support.biomes_cold.add(HighlandsBiomes.adirondack);
            System.out.println("Highlands biome added: " + HighlandsBiomes.adirondack.biomeName);
        }

        if (HighlandsBiomes.bambooForest != null)
        {
            Support.biomes_wet.add(HighlandsBiomes.bambooForest);
            System.out.println("Highlands biome added: " + HighlandsBiomes.bambooForest.biomeName);
        }

        if (HighlandsBiomes.dunes != null)
        {
            Support.biomes_hot.add(HighlandsBiomes.dunes);
            System.out.println("Highlands biome added: " + HighlandsBiomes.dunes.biomeName);
        }
        System.out.println("OWG: Highlands support was loaded");
    }
}