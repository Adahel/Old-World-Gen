package owg.support;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.Loader;

public class SupportBOP
{
    private static final BiomeGenBase[] biomeList = new BiomeGenBase[256];

    public static BiomeGenBase[] getBOPBiomes()
    {
        BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();

        for (int i = 0, j = 0; Loader.isModLoaded("BiomesOPlenty") && i < 256; i++)
        {
            if (b[i] != null)
            {
                if (b[i].biomeName == "Alps" || b[i].biomeName == "Bamboo Forest" || b[i].biomeName == "Bayou" || b[i].biomeName == "Bog"
                        || b[i].biomeName == "Boreal Forest" || b[i].biomeName == "Brushland" || b[i].biomeName == "Chaparral"
                        || b[i].biomeName == "Cherry Blossom Grove" || b[i].biomeName == "Cold Desert" || b[i].biomeName == "Coniferous Forest"
                        || b[i].biomeName == "Crag" || b[i].biomeName == "Dead Forest" || b[i].biomeName == "Dead Swamp"
                        || b[i].biomeName == "Eucalyptus Forest" || b[i].biomeName == "Fen" || b[i].biomeName == "Flower Field" || b[i].biomeName == "Grassland"
                        || b[i].biomeName == "Grove" || b[i].biomeName == "Heathland" || b[i].biomeName == "Highland" || b[i].biomeName == "Land Of Lakes"
                        || b[i].biomeName == "Lavender Fields" || b[i].biomeName == "Lush Desert" || b[i].biomeName == "Lush Swamp"
                        || b[i].biomeName == "Maple Woods" || b[i].biomeName == "Marsh" || b[i].biomeName == "Meadow" || b[i].biomeName == "Moor"
                        || b[i].biomeName == "Mountain" || b[i].biomeName == "Mystic Grove" || b[i].biomeName == "Ominous Woods" || b[i].biomeName == "Orchard"
                        || b[i].biomeName == "Outback" || b[i].biomeName == "Overgrown Cliffs" || b[i].biomeName == "Prairie" || b[i].biomeName == "Quagmire"
                        || b[i].biomeName == "Rainforest" || b[i].biomeName == "Redwood Forest" || b[i].biomeName == "Sacred Springs"
                        || b[i].biomeName == "Seasonal Forest" || b[i].biomeName == "Shield" || b[i].biomeName == "Shrubland"
                        || b[i].biomeName == "Snowy Coniferous Forest" || b[i].biomeName == "Snowy Forest" || b[i].biomeName == "Steppe"
                        || b[i].biomeName == "Temperate Rainforest" || b[i].biomeName == "Tropical Rainforest" || b[i].biomeName == "Tundra"
                        || b[i].biomeName == "Wasteland" || b[i].biomeName == "Wetland" || b[i].biomeName == "Woodland" || b[i].biomeName == "Xeric Shrubland")
                {
                    biomeList[j] = b[i];
                    j++;
                }
            }
        }
        if (Loader.isModLoaded("BiomesOPlenty"))
        {
            return biomeList;
        }
        else
        {
            return b;
        }
    }

    public static void init()
    {
        try
        {
            addBOPBiomes();
        }
        catch (Exception e)
        {
            System.out.println("Biomes O' Plenty support is broken!");
        }
    }

    public static void addBOPBiomes()
    {
        BiomeGenBase alps = getBOPBiomes()[0];
        BiomeGenBase bamboo_forest = getBOPBiomes()[1];
        BiomeGenBase bayou = getBOPBiomes()[2];
        BiomeGenBase bog = getBOPBiomes()[3];
        BiomeGenBase boreal_forest = getBOPBiomes()[4];
        BiomeGenBase brushland = getBOPBiomes()[5];
        BiomeGenBase chaparral = getBOPBiomes()[6];
        BiomeGenBase cherry_blossom_grove = getBOPBiomes()[7];
        BiomeGenBase cold_desert = getBOPBiomes()[8];
        BiomeGenBase coniferous_forest = getBOPBiomes()[9];
        BiomeGenBase crag = getBOPBiomes()[10];
        BiomeGenBase dead_forest = getBOPBiomes()[11];
        BiomeGenBase dead_swamp = getBOPBiomes()[12];
        BiomeGenBase eucalyptus_forest = getBOPBiomes()[13];
        BiomeGenBase fen = getBOPBiomes()[14];
        BiomeGenBase flower_field = getBOPBiomes()[15];
        BiomeGenBase grassland = getBOPBiomes()[16];
        BiomeGenBase grove = getBOPBiomes()[17];
        BiomeGenBase heathland = getBOPBiomes()[18];
        BiomeGenBase highland = getBOPBiomes()[19];
        BiomeGenBase land_of_lakes = getBOPBiomes()[20];
        BiomeGenBase lavender_fields = getBOPBiomes()[21];
        BiomeGenBase lush_desert = getBOPBiomes()[22];
        BiomeGenBase lush_swamp = getBOPBiomes()[23];
        BiomeGenBase maple_woods = getBOPBiomes()[24];
        BiomeGenBase marsh = getBOPBiomes()[25];
        BiomeGenBase meadow = getBOPBiomes()[26];
        BiomeGenBase moor = getBOPBiomes()[27];
        BiomeGenBase mountain = getBOPBiomes()[28];
        BiomeGenBase mystic_grove = getBOPBiomes()[29];
        BiomeGenBase ominous_woods = getBOPBiomes()[30];
        BiomeGenBase orchard = getBOPBiomes()[31];
        BiomeGenBase outback = getBOPBiomes()[32];
        BiomeGenBase overgrown_cliffs = getBOPBiomes()[33];
        BiomeGenBase prairie = getBOPBiomes()[34];
        BiomeGenBase quagmire = getBOPBiomes()[35];
        BiomeGenBase rainforest = getBOPBiomes()[36];
        BiomeGenBase redwood_forest = getBOPBiomes()[37];
        BiomeGenBase sacred_springs = getBOPBiomes()[38];
        BiomeGenBase seasonal_forest = getBOPBiomes()[39];
        BiomeGenBase shield = getBOPBiomes()[40];
        BiomeGenBase shrubland = getBOPBiomes()[41];
        BiomeGenBase snowy_coniferous_forest = getBOPBiomes()[42];
        BiomeGenBase snowy_forest = getBOPBiomes()[43];
        BiomeGenBase steppe = getBOPBiomes()[44];
        BiomeGenBase temperate_rainforest = getBOPBiomes()[45];
        BiomeGenBase tropical_rainforest = getBOPBiomes()[46];
        BiomeGenBase tundra = getBOPBiomes()[47];
        BiomeGenBase wasteland = getBOPBiomes()[48];
        BiomeGenBase wetland = getBOPBiomes()[49];
        BiomeGenBase woodland = getBOPBiomes()[50];
        BiomeGenBase xeric_shrubland = getBOPBiomes()[51];
        System.out.println("OWG: Biomes O' Plenty support is loading");
        Support.biomes_snow.add(alps);
        System.out.println("Biomes O' Plenty biome added: " + alps.biomeName);
        Support.biomes_wet.add(bamboo_forest);
        System.out.println("Biomes O' Plenty biome added: " + bamboo_forest.biomeName);
        Support.biomes_wet.add(bayou);
        System.out.println("Biomes O' Plenty biome added: " + bayou.biomeName);
        Support.biomes_cold.add(bog);
        System.out.println("Biomes O' Plenty biome added: " + bog.biomeName);
        Support.biomes_cold.add(boreal_forest);
        System.out.println("Biomes O' Plenty biome added: " + boreal_forest.biomeName);
        Support.biomes_hot.add(brushland);
        System.out.println("Biomes O' Plenty biome added: " + brushland.biomeName);
        Support.biomes_hot.add(chaparral);
        System.out.println("Biomes O' Plenty biome added: " + chaparral.biomeName);
        Support.biomes_cold.add(cherry_blossom_grove);
        System.out.println("Biomes O' Plenty biome added: " + cherry_blossom_grove.biomeName);
        Support.biomes_snow.add(cold_desert);
        System.out.println("Biomes O' Plenty biome added: " + cold_desert.biomeName);
        Support.biomes_cold.add(coniferous_forest);
        System.out.println("Biomes O' Plenty biome added: " + coniferous_forest.biomeName);
        Support.biomes_cold.add(crag);
        System.out.println("Biomes O' Plenty biome added: " + crag.biomeName);
        Support.biomes_cold.add(dead_forest);
        System.out.println("Biomes O' Plenty biome added: " + dead_forest.biomeName);
        Support.biomes_cold.add(dead_swamp);
        System.out.println("Biomes O' Plenty biome added: " + dead_swamp.biomeName);
        Support.biomes_wet.add(eucalyptus_forest);
        System.out.println("Biomes O' Plenty biome added: " + eucalyptus_forest.biomeName);
        Support.biomes_cold.add(fen);
        System.out.println("Biomes O' Plenty biome added: " + fen.biomeName);
        Support.biomes_cold.add(flower_field);
        System.out.println("Biomes O' Plenty biome added: " + flower_field.biomeName);
        Support.biomes_cold.add(grassland);
        System.out.println("Biomes O' Plenty biome added: " + grassland.biomeName);
        Support.biomes_cold.add(grove);
        System.out.println("Biomes O' Plenty biome added: " + grove.biomeName);
        Support.biomes_hot.add(heathland);
        System.out.println("Biomes O' Plenty biome added: " + heathland.biomeName);
        Support.biomes_cold.add(highland);
        System.out.println("Biomes O' Plenty biome added: " + highland.biomeName);
        Support.biomes_wet.add(land_of_lakes);
        System.out.println("Biomes O' Plenty biome added: " + land_of_lakes.biomeName);
        Support.biomes_hot.add(lavender_fields);
        System.out.println("Biomes O' Plenty biome added: " + lavender_fields.biomeName);
        Support.biomes_hot.add(lush_desert);
        System.out.println("Biomes O' Plenty biome added: " + lush_desert.biomeName);
        Support.biomes_wet.add(lush_swamp);
        System.out.println("Biomes O' Plenty biome added: " + lush_swamp.biomeName);
        Support.biomes_cold.add(maple_woods);
        System.out.println("Biomes O' Plenty biome added: " + maple_woods.biomeName);
        Support.biomes_wet.add(marsh);
        System.out.println("Biomes O' Plenty biome added: " + marsh.biomeName);
        Support.biomes_cold.add(meadow);
        System.out.println("Biomes O' Plenty biome added: " + meadow.biomeName);
        Support.biomes_cold.add(moor);
        System.out.println("Biomes O' Plenty biome added: " + moor.biomeName);
        Support.biomes_cold.add(mountain);
        System.out.println("Biomes O' Plenty biome added: " + mountain.biomeName);
        Support.biomes_wet.add(mystic_grove);
        System.out.println("Biomes O' Plenty biome added: " + mystic_grove.biomeName);
        Support.biomes_wet.add(ominous_woods);
        System.out.println("Biomes O' Plenty biome added: " + ominous_woods.biomeName);
        Support.biomes_cold.add(orchard);
        System.out.println("Biomes O' Plenty biome added: " + orchard.biomeName);
        Support.biomes_hot.add(outback);
        System.out.println("Biomes O' Plenty biome added: " + outback.biomeName);
        Support.biomes_wet.add(overgrown_cliffs);
        System.out.println("Biomes O' Plenty biome added: " + overgrown_cliffs.biomeName);
        Support.biomes_hot.add(prairie);
        System.out.println("Biomes O' Plenty biome added: " + prairie.biomeName);
        Support.biomes_wet.add(quagmire);
        System.out.println("Biomes O' Plenty biome added: " + quagmire.biomeName);
        Support.biomes_wet.add(rainforest);
        System.out.println("Biomes O' Plenty biome added: " + rainforest.biomeName);
        Support.biomes_cold.add(redwood_forest);
        System.out.println("Biomes O' Plenty biome added: " + redwood_forest.biomeName);
        Support.biomes_wet.add(sacred_springs);
        System.out.println("Biomes O' Plenty biome added: " + sacred_springs.biomeName);
        Support.biomes_hot.add(seasonal_forest);
        System.out.println("Biomes O' Plenty biome added: " + seasonal_forest.biomeName);
        Support.biomes_cold.add(shield);
        System.out.println("Biomes O' Plenty biome added: " + shield.biomeName);
        Support.biomes_hot.add(shrubland);
        System.out.println("Biomes O' Plenty biome added: " + shrubland.biomeName);
        Support.biomes_snow.add(snowy_coniferous_forest);
        System.out.println("Biomes O' Plenty biome added: " + snowy_coniferous_forest.biomeName);
        Support.biomes_snow.add(snowy_forest);
        System.out.println("Biomes O' Plenty biome added: " + snowy_forest.biomeName);
        Support.biomes_hot.add(steppe);
        System.out.println("Biomes O' Plenty biome added: " + steppe.biomeName);
        Support.biomes_wet.add(temperate_rainforest);
        System.out.println("Biomes O' Plenty biome added: " + temperate_rainforest.biomeName);
        Support.biomes_wet.add(tropical_rainforest);
        System.out.println("Biomes O' Plenty biome added: " + tropical_rainforest.biomeName);
        Support.biomes_cold.add(tundra);
        System.out.println("Biomes O' Plenty biome added: " + tundra.biomeName);
        Support.biomes_cold.add(wasteland);
        System.out.println("Biomes O' Plenty biome added: " + wasteland.biomeName);
        Support.biomes_wet.add(wetland);
        System.out.println("Biomes O' Plenty biome added: " + wetland.biomeName);
        Support.biomes_cold.add(woodland);
        System.out.println("Biomes O' Plenty biome added: " + woodland.biomeName);
        Support.biomes_hot.add(xeric_shrubland);
        System.out.println("Biomes O' Plenty biome added: " + xeric_shrubland.biomeName);
        System.out.println("OWG: Biomes O' Plenty support was loaded");
    }
}
