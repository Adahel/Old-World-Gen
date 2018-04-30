package owg.generatortype;

import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import owg.biomes.BiomeList;
import owg.generator.ChunkGeneratorAlpha;
import owg.world.ManagerOWG;
import owg.world.ManagerOWGHell;

public class GeneratorTypeAlpha12 extends GeneratorType
{
    public GeneratorTypeAlpha12(int id, int cat, String name, boolean c)
    {
        super(id, cat, name, c);
    }

    @Override
    public WorldChunkManager getServerWorldChunkManager(World world)
    {
        return new ManagerOWG(world, true, 0);
    }

    @Override
    public WorldChunkManager getClientWorldChunkManager(World world)
    {
        return new ManagerOWGHell(BiomeList.OLDplains, 0.5F);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world)
    {
        return new ChunkGeneratorAlpha(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
}
