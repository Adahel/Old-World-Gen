package owg.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public interface OWGGenBiome
{
    String name();

    BiomeGenBase getHandle();

    void setHandle(BiomeGenBase var1);
}
