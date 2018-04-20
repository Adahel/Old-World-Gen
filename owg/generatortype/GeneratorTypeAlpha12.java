package owg.generatortype;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import owg.generator.ChunkGeneratorAlpha;

public class GeneratorTypeAlpha12 extends GeneratorType
{
    public GeneratorTypeAlpha12(int id, int cat, String name, boolean c)
    {
        super(id, cat, name, c);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world)
    {
        return new ChunkGeneratorAlpha(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
}
