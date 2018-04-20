package owg.support;

import net.minecraftforge.fml.common.Loader;
import owg.biomes.BiomeGenBase;

public class SupportBOP
{
    private static final net.minecraft.world.biome.BiomeGenBase[] biomeList = new net.minecraft.world.biome.BiomeGenBase[256];

    public static net.minecraft.world.biome.BiomeGenBase[] getBOPBiomes()
    {
        net.minecraft.world.biome.BiomeGenBase[] b = net.minecraft.world.biome.BiomeGenBase.getBiomeGenArray();

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
        BiomeGenBase alps = BiomeGenBase.alps;
        BiomeGenBase bamboo_forest = BiomeGenBase.bamboo_forest;
        BiomeGenBase bayou = BiomeGenBase.bayou;
        BiomeGenBase bog = BiomeGenBase.bog;
        BiomeGenBase boreal_forest = BiomeGenBase.boreal_forest;
        BiomeGenBase brushland = BiomeGenBase.brushland;
        BiomeGenBase chaparral = BiomeGenBase.chaparral;
        BiomeGenBase cherry_blossom_grove = BiomeGenBase.cherry_blossom_grove;
        BiomeGenBase cold_desert = BiomeGenBase.cold_desert;
        BiomeGenBase coniferous_forest = BiomeGenBase.coniferous_forest;
        BiomeGenBase crag = BiomeGenBase.crag;
        BiomeGenBase dead_forest = BiomeGenBase.dead_forest;
        BiomeGenBase dead_swamp = BiomeGenBase.dead_swamp;
        BiomeGenBase eucalyptus_forest = BiomeGenBase.eucalyptus_forest;
        BiomeGenBase fen = BiomeGenBase.fen;
        BiomeGenBase flower_field = BiomeGenBase.flower_field;
        BiomeGenBase grassland = BiomeGenBase.grassland;
        BiomeGenBase grove = BiomeGenBase.grove;
        BiomeGenBase heathland = BiomeGenBase.heathland;
        BiomeGenBase highland = BiomeGenBase.highland;
        BiomeGenBase land_of_lakes = BiomeGenBase.land_of_lakes;
        BiomeGenBase lavender_fields = BiomeGenBase.lavender_fields;
        BiomeGenBase lush_desert = BiomeGenBase.lush_desert;
        BiomeGenBase lush_swamp = BiomeGenBase.lush_swamp;
        BiomeGenBase maple_woods = BiomeGenBase.maple_woods;
        BiomeGenBase marsh = BiomeGenBase.marsh;
        BiomeGenBase meadow = BiomeGenBase.meadow;
        BiomeGenBase moor = BiomeGenBase.moor;
        BiomeGenBase mountain = BiomeGenBase.mountain;
        BiomeGenBase mystic_grove = BiomeGenBase.mystic_grove;
        BiomeGenBase ominous_woods = BiomeGenBase.ominous_woods;
        BiomeGenBase orchard = BiomeGenBase.orchard;
        BiomeGenBase outback = BiomeGenBase.outback;
        BiomeGenBase overgrown_cliffs = BiomeGenBase.overgrown_cliffs;
        BiomeGenBase prairie = BiomeGenBase.prairie;
        BiomeGenBase quagmire = BiomeGenBase.quagmire;
        BiomeGenBase rainforest = BiomeGenBase.rainforest;
        BiomeGenBase redwood_forest = BiomeGenBase.redwood_forest;
        BiomeGenBase sacred_springs = BiomeGenBase.sacred_springs;
        BiomeGenBase seasonal_forest = BiomeGenBase.seasonal_forest;
        BiomeGenBase shield = BiomeGenBase.shield;
        BiomeGenBase shrubland = BiomeGenBase.shrubland;
        BiomeGenBase snowy_coniferous_forest = BiomeGenBase.snowy_coniferous_forest;
        BiomeGenBase snowy_forest = BiomeGenBase.snowy_forest;
        BiomeGenBase steppe = BiomeGenBase.steppe;
        BiomeGenBase temperate_rainforest = BiomeGenBase.temperate_rainforest;
        BiomeGenBase tropical_rainforest = BiomeGenBase.tropical_rainforest;
        BiomeGenBase tundra = BiomeGenBase.tundra;
        BiomeGenBase wasteland = BiomeGenBase.wasteland;
        BiomeGenBase wetland = BiomeGenBase.wetland;
        BiomeGenBase woodland = BiomeGenBase.woodland;
        BiomeGenBase xeric_shrubland = BiomeGenBase.xeric_shrubland;
        System.out.println("OWG: Biomes O' Plenty support is loading");
        Support.biomes_snow.add(alps);
        System.out.println("Biomes O' Plenty biome added: " + alps.handle.biomeName);
        Support.biomes_wet.add(bamboo_forest);
        System.out.println("Biomes O' Plenty biome added: " + bamboo_forest.handle.biomeName);
        Support.biomes_wet.add(bayou);
        System.out.println("Biomes O' Plenty biome added: " + bayou.handle.biomeName);
        Support.biomes_cold.add(bog);
        System.out.println("Biomes O' Plenty biome added: " + bog.handle.biomeName);
        Support.biomes_cold.add(boreal_forest);
        System.out.println("Biomes O' Plenty biome added: " + boreal_forest.handle.biomeName);
        Support.biomes_hot.add(brushland);
        System.out.println("Biomes O' Plenty biome added: " + brushland.handle.biomeName);
        Support.biomes_hot.add(chaparral);
        System.out.println("Biomes O' Plenty biome added: " + chaparral.handle.biomeName);
        Support.biomes_cold.add(cherry_blossom_grove);
        System.out.println("Biomes O' Plenty biome added: " + cherry_blossom_grove.handle.biomeName);
        Support.biomes_snow.add(cold_desert);
        System.out.println("Biomes O' Plenty biome added: " + cold_desert.handle.biomeName);
        Support.biomes_cold.add(coniferous_forest);
        System.out.println("Biomes O' Plenty biome added: " + coniferous_forest.handle.biomeName);
        Support.biomes_cold.add(crag);
        System.out.println("Biomes O' Plenty biome added: " + crag.handle.biomeName);
        Support.biomes_cold.add(dead_forest);
        System.out.println("Biomes O' Plenty biome added: " + dead_forest.handle.biomeName);
        Support.biomes_cold.add(dead_swamp);
        System.out.println("Biomes O' Plenty biome added: " + dead_swamp.handle.biomeName);
        Support.biomes_wet.add(eucalyptus_forest);
        System.out.println("Biomes O' Plenty biome added: " + eucalyptus_forest.handle.biomeName);
        Support.biomes_cold.add(fen);
        System.out.println("Biomes O' Plenty biome added: " + fen.handle.biomeName);
        Support.biomes_cold.add(flower_field);
        System.out.println("Biomes O' Plenty biome added: " + flower_field.handle.biomeName);
        Support.biomes_cold.add(grassland);
        System.out.println("Biomes O' Plenty biome added: " + grassland.handle.biomeName);
        Support.biomes_cold.add(grove);
        System.out.println("Biomes O' Plenty biome added: " + grove.handle.biomeName);
        Support.biomes_hot.add(heathland);
        System.out.println("Biomes O' Plenty biome added: " + heathland.handle.biomeName);
        Support.biomes_cold.add(highland);
        System.out.println("Biomes O' Plenty biome added: " + highland.handle.biomeName);
        Support.biomes_wet.add(land_of_lakes);
        System.out.println("Biomes O' Plenty biome added: " + land_of_lakes.handle.biomeName);
        Support.biomes_hot.add(lavender_fields);
        System.out.println("Biomes O' Plenty biome added: " + lavender_fields.handle.biomeName);
        Support.biomes_hot.add(lush_desert);
        System.out.println("Biomes O' Plenty biome added: " + lush_desert.handle.biomeName);
        Support.biomes_wet.add(lush_swamp);
        System.out.println("Biomes O' Plenty biome added: " + lush_swamp.handle.biomeName);
        Support.biomes_cold.add(maple_woods);
        System.out.println("Biomes O' Plenty biome added: " + maple_woods.handle.biomeName);
        Support.biomes_wet.add(marsh);
        System.out.println("Biomes O' Plenty biome added: " + marsh.handle.biomeName);
        Support.biomes_cold.add(meadow);
        System.out.println("Biomes O' Plenty biome added: " + meadow.handle.biomeName);
        Support.biomes_cold.add(moor);
        System.out.println("Biomes O' Plenty biome added: " + moor.handle.biomeName);
        Support.biomes_cold.add(mountain);
        System.out.println("Biomes O' Plenty biome added: " + mountain.handle.biomeName);
        Support.biomes_wet.add(mystic_grove);
        System.out.println("Biomes O' Plenty biome added: " + mystic_grove.handle.biomeName);
        Support.biomes_wet.add(ominous_woods);
        System.out.println("Biomes O' Plenty biome added: " + ominous_woods.handle.biomeName);
        Support.biomes_cold.add(orchard);
        System.out.println("Biomes O' Plenty biome added: " + orchard.handle.biomeName);
        Support.biomes_hot.add(outback);
        System.out.println("Biomes O' Plenty biome added: " + outback.handle.biomeName);
        Support.biomes_wet.add(overgrown_cliffs);
        System.out.println("Biomes O' Plenty biome added: " + overgrown_cliffs.handle.biomeName);
        Support.biomes_hot.add(prairie);
        System.out.println("Biomes O' Plenty biome added: " + prairie.handle.biomeName);
        Support.biomes_wet.add(quagmire);
        System.out.println("Biomes O' Plenty biome added: " + quagmire.handle.biomeName);
        Support.biomes_wet.add(rainforest);
        System.out.println("Biomes O' Plenty biome added: " + rainforest.handle.biomeName);
        Support.biomes_cold.add(redwood_forest);
        System.out.println("Biomes O' Plenty biome added: " + redwood_forest.handle.biomeName);
        Support.biomes_wet.add(sacred_springs);
        System.out.println("Biomes O' Plenty biome added: " + sacred_springs.handle.biomeName);
        Support.biomes_hot.add(seasonal_forest);
        System.out.println("Biomes O' Plenty biome added: " + seasonal_forest.handle.biomeName);
        Support.biomes_cold.add(shield);
        System.out.println("Biomes O' Plenty biome added: " + shield.handle.biomeName);
        Support.biomes_hot.add(shrubland);
        System.out.println("Biomes O' Plenty biome added: " + shrubland.handle.biomeName);
        Support.biomes_snow.add(snowy_coniferous_forest);
        System.out.println("Biomes O' Plenty biome added: " + snowy_coniferous_forest.handle.biomeName);
        Support.biomes_snow.add(snowy_forest);
        System.out.println("Biomes O' Plenty biome added: " + snowy_forest.handle.biomeName);
        Support.biomes_hot.add(steppe);
        System.out.println("Biomes O' Plenty biome added: " + steppe.handle.biomeName);
        Support.biomes_wet.add(temperate_rainforest);
        System.out.println("Biomes O' Plenty biome added: " + temperate_rainforest.handle.biomeName);
        Support.biomes_wet.add(tropical_rainforest);
        System.out.println("Biomes O' Plenty biome added: " + tropical_rainforest.handle.biomeName);
        Support.biomes_cold.add(tundra);
        System.out.println("Biomes O' Plenty biome added: " + tundra.handle.biomeName);
        Support.biomes_cold.add(wasteland);
        System.out.println("Biomes O' Plenty biome added: " + wasteland.handle.biomeName);
        Support.biomes_wet.add(wetland);
        System.out.println("Biomes O' Plenty biome added: " + wetland.handle.biomeName);
        Support.biomes_cold.add(woodland);
        System.out.println("Biomes O' Plenty biome added: " + woodland.handle.biomeName);
        Support.biomes_hot.add(xeric_shrubland);
        System.out.println("Biomes O' Plenty biome added: " + xeric_shrubland.handle.biomeName);
        System.out.println("OWG: Biomes O' Plenty support was loaded");
    }
}
