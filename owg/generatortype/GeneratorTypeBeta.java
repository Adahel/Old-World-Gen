package owg.generatortype;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import owg.generator.ChunkGeneratorBeta;
import owg.gui.GuiGeneratorSettings;
import owg.gui.GuiSettingsButton;;

public class GeneratorTypeBeta extends GeneratorType
{
    public GeneratorTypeBeta(int id, int cat, String name, boolean c)
    {
        super(id, cat, name, c);
    }

    @Override
    public boolean getSettings(GuiGeneratorSettings gui)
    {
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.biomes.biomes") + ": " + StatCollector.translateToLocal("owg.biomes.original"),
                        StatCollector.translateToLocal("owg.biomes.biomes") + ": " + StatCollector.translateToLocal("owg.biomes.vanilla"),
                        StatCollector.translateToLocal("owg.biomes.biomes") + ": " + StatCollector.translateToLocal("owg.biomes.all") },
                new int[] { 0, 1, 2 }, 20, 50, gui.width));
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.ravine") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                        StatCollector.translateToLocal("owg.setting.ravine") + ": " + StatCollector.translateToLocal("owg.setting.off") },
                new int[] { 0, 1 }, 21, 70, gui.width, 20, new int[] { 1, 2 }));
        return true;
    }

    @Override
    public IChunkProvider getChunkGenerator(World world)
    {
        int biomes = trySetting(0, 2);
        int ravine = trySetting(1, 1);
        return new ChunkGeneratorBeta(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), biomes, ravine);
    }
}
