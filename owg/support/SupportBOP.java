package owg.support;

import biomesoplenty.api.biome.BOPBiomes;

public class SupportBOP
{
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
        System.out.println("OWG: Biomes O' Plenty support is loading");
        if (BOPBiomes.alps.isPresent())
        {
            Support.biomes_snow.add(BOPBiomes.alps.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.alps.get().biomeName);
        }

        if (BOPBiomes.bamboo_forest.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.bamboo_forest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.bamboo_forest.get().biomeName);
        }

        if (BOPBiomes.bayou.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.bayou.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.bayou.get().biomeName);
        }

        if (BOPBiomes.bog.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.bog.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.bog.get().biomeName);
        }

        if (BOPBiomes.boreal_forest.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.boreal_forest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.boreal_forest.get().biomeName);
        }

        if (BOPBiomes.brushland.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.brushland.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.brushland.get().biomeName);
        }

        if (BOPBiomes.chaparral.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.chaparral.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.chaparral.get().biomeName);
        }

        if (BOPBiomes.cherry_blossom_grove.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.cherry_blossom_grove.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.cherry_blossom_grove.get().biomeName);
        }

        if (BOPBiomes.cold_desert.isPresent())
        {
            Support.biomes_snow.add(BOPBiomes.cold_desert.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.cold_desert.get().biomeName);
        }

        if (BOPBiomes.coniferous_forest.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.coniferous_forest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.coniferous_forest.get().biomeName);
        }

        if (BOPBiomes.crag.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.crag.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.crag.get().biomeName);
        }

        if (BOPBiomes.dead_forest.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.dead_forest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.dead_forest.get().biomeName);
        }

        if (BOPBiomes.dead_swamp.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.dead_swamp.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.dead_swamp.get().biomeName);
        }

        if (BOPBiomes.eucalyptus_forest.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.eucalyptus_forest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.eucalyptus_forest.get().biomeName);
        }

        if (BOPBiomes.fen.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.fen.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.fen.get().biomeName);
        }

        if (BOPBiomes.flower_field.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.flower_field.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.flower_field.get().biomeName);
        }

        if (BOPBiomes.grassland.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.grassland.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.grassland.get().biomeName);
        }

        if (BOPBiomes.grove.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.grove.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.grove.get().biomeName);
        }

        if (BOPBiomes.heathland.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.heathland.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.heathland.get().biomeName);
        }

        if (BOPBiomes.highland.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.highland.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.highland.get().biomeName);
        }

        if (BOPBiomes.land_of_lakes.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.land_of_lakes.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.land_of_lakes.get().biomeName);
        }

        if (BOPBiomes.lavender_fields.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.lavender_fields.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.lavender_fields.get().biomeName);
        }

        if (BOPBiomes.lush_desert.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.lush_desert.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.lush_desert.get().biomeName);
        }

        if (BOPBiomes.lush_swamp.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.lush_swamp.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.lush_swamp.get().biomeName);
        }

        if (BOPBiomes.maple_woods.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.maple_woods.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.maple_woods.get().biomeName);
        }

        if (BOPBiomes.marsh.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.marsh.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.marsh.get().biomeName);
        }

        if (BOPBiomes.meadow.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.meadow.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.meadow.get().biomeName);
        }

        if (BOPBiomes.moor.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.moor.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.moor.get().biomeName);
        }

        if (BOPBiomes.mountain.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.mountain.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.mountain.get().biomeName);
        }

        if (BOPBiomes.mystic_grove.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.mystic_grove.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.mystic_grove.get().biomeName);
        }

        if (BOPBiomes.ominous_woods.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.ominous_woods.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.ominous_woods.get().biomeName);
        }

        if (BOPBiomes.orchard.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.orchard.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.orchard.get().biomeName);
        }

        if (BOPBiomes.outback.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.outback.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.outback.get().biomeName);
        }

        if (BOPBiomes.overgrown_cliffs.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.overgrown_cliffs.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.overgrown_cliffs.get().biomeName);
        }

        if (BOPBiomes.prairie.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.prairie.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.prairie.get().biomeName);
        }

        if (BOPBiomes.quagmire.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.quagmire.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.quagmire.get().biomeName);
        }

        if (BOPBiomes.rainforest.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.rainforest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.rainforest.get().biomeName);
        }

        if (BOPBiomes.redwood_forest.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.redwood_forest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.redwood_forest.get().biomeName);
        }

        if (BOPBiomes.sacred_springs.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.sacred_springs.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.sacred_springs.get().biomeName);
        }

        if (BOPBiomes.seasonal_forest.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.seasonal_forest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.seasonal_forest.get().biomeName);
        }

        if (BOPBiomes.shield.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.shield.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.shield.get().biomeName);
        }

        if (BOPBiomes.shrubland.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.shrubland.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.shrubland.get().biomeName);
        }

        if (BOPBiomes.snowy_coniferous_forest.isPresent())
        {
            Support.biomes_snow.add(BOPBiomes.snowy_coniferous_forest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.snowy_coniferous_forest.get().biomeName);
        }

        if (BOPBiomes.snowy_forest.isPresent())
        {
            Support.biomes_snow.add(BOPBiomes.snowy_forest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.snowy_forest.get().biomeName);
        }

        if (BOPBiomes.steppe.isPresent())
        {
            Support.biomes_hot.add(BOPBiomes.steppe.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.steppe.get().biomeName);
        }

        if (BOPBiomes.temperate_rainforest.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.temperate_rainforest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.temperate_rainforest.get().biomeName);
        }

        if (BOPBiomes.tropical_rainforest.isPresent())
        {
            Support.biomes_wet.add(BOPBiomes.tropical_rainforest.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.tropical_rainforest.get().biomeName);
        }

        if (BOPBiomes.tundra.isPresent())
        {
            Support.biomes_cold.add(BOPBiomes.tundra.get());
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.tundra.get().biomeName);
        }

        if (BOPBiomes.wasteland.isPresent())
        {
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.tundra.get().biomeName);
            Support.biomes_cold.add(BOPBiomes.wasteland.get());
        }

        if (BOPBiomes.wetland.isPresent())
        {
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.wasteland.get().biomeName);
            Support.biomes_wet.add(BOPBiomes.wetland.get());
        }

        if (BOPBiomes.woodland.isPresent())
        {
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.wetland.get().biomeName);
            Support.biomes_cold.add(BOPBiomes.woodland.get());
        }

        if (BOPBiomes.xeric_shrubland.isPresent())
        {
            System.out.println("Biomes O' Plenty biome added: " + BOPBiomes.woodland.get().biomeName);
            Support.biomes_hot.add(BOPBiomes.xeric_shrubland.get());
        }
        System.out.println("OWG: Biomes O' Plenty support was loaded");
    }
}