package sugiforest.api;

import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;

public interface ISugiForestAPI
{
    public String getVersion();

    public Configuration getConfig();

    public BiomeGenBase getBiome();

    public int getDimension();

    public void addFallenSeed(ItemStack stack, int weight);
}
