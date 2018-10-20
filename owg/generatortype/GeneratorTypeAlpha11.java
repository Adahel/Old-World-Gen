package owg.generatortype;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import owg.biomes.BiomeList;
import owg.generator.ChunkGeneratorInfdev;
import owg.gui.GuiGeneratorSettings;
import owg.gui.GuiSettingsButton;
import owg.world.ManagerOWGHell;

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
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.stronghold") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                        StatCollector.translateToLocal("owg.setting.stronghold") + ": " + StatCollector.translateToLocal("owg.setting.off") },
                new int[] { 0, 1 }, 21, 70, gui.width));
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.mineshaft") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                        StatCollector.translateToLocal("owg.setting.mineshaft") + ": " + StatCollector.translateToLocal("owg.setting.off") },
                new int[] { 0, 1 }, 22, 90, gui.width));
        return true;
    }

    @Override
    public WorldChunkManager getServerWorldChunkManager(World world)
    {
        if (trySetting(0, 1) == 0)
        {
            return new ManagerOWGHell(BiomeList.CLASSICnormal, 0.5F);
        }
        return new ManagerOWGHell(BiomeList.CLASSICsnow, 0.5F);
    }

    @Override
    public WorldChunkManager getClientWorldChunkManager(World world)
    {
        return new ManagerOWGHell(BiomeList.CLASSICnormal, 0.5F);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world)
    {
        int strongholds = trySetting(1, 1);
        int mineshafts = trySetting(2, 1);
        return new ChunkGeneratorInfdev(world, world.getSeed(), strongholds, mineshafts, true);
    }
}
