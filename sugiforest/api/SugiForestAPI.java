package sugiforest.api;

import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

/**
 * NOTE: Do NOT access to this class fields.<br>
 * You should use this API from this class methods.
 */
public class SugiForestAPI
{
    public static ISugiForestAPI instance;

    private SugiForestAPI()
    {
    }

    /**
     * Returns the current mod version of SugiForest mod.
     */
    public static String getVersion()
    {
        return instance == null ? "" : instance.getVersion();
    }

    /**
     * Returns the configuration of SugiForest mod.
     */
    public static Configuration getConfig()
    {
        return instance == null ? null : instance.getConfig();
    }

    /**
     * Returns the Sugi Forest biome.
     */
    public static BiomeGenBase getBiome()
    {
        return instance == null ? null : instance.getBiome();
    }

    /**
     * Returns the dimension id of the Sugi Forest dimension.
     */
    public static int getDimension()
    {
        return instance == null ? DimensionManager.getNextFreeDimId() : instance.getDimension();
    }

    /**
     * Adds a fallen seed. It'll get randomly when break a Sugi Fallen Leaves.
     * 
     * @param stack
     *            The seed
     * @param weight
     *            The weight
     */
    public static void addFallenSeed(ItemStack stack, int weight)
    {
        if (instance != null)
        {
            instance.addFallenSeed(stack, weight);
        }
    }
}
