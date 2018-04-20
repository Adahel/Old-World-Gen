package owg.generatortype;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import owg.generator.ChunkGeneratorInfdev;
import owg.gui.GuiGeneratorSettings;
import owg.gui.GuiSettingsButton;

public class GeneratorTypeAlpha11 extends GeneratorType
{
    public GeneratorTypeAlpha11(int id, int cat, String name, boolean c)
    {
        super(id, cat, name, c);
    }

    @Override
    public boolean getSettings(GuiGeneratorSettings gui)
    {
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.snow") + ": " + StatCollector.translateToLocal("owg.setting.off"),
                        StatCollector.translateToLocal("owg.setting.snow") + ": " + StatCollector.translateToLocal("owg.setting.on") },
                new int[] { 0, 1 }, 20, 50, gui.width));
        return true;
    }

    @Override
    public IChunkProvider getChunkGenerator(World world)
    {
        int biomes = trySetting(0, 1);
        return new ChunkGeneratorInfdev(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), true, biomes);
    }
}
