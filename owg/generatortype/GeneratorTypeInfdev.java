package owg.generatortype;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.biomes.BiomeList;
import owg.generator.ChunkGeneratorInfdev;
import owg.gui.GuiGeneratorSettings;
import owg.gui.GuiSettingsCheckBox;
import owg.world.ManagerOWGHell;

public class GeneratorTypeInfdev extends GeneratorType
{
    public GeneratorTypeInfdev(int id, int cat, String name, boolean c)
    {
        super(id, cat, name, c);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean getSettings(GuiGeneratorSettings gui)
    {
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.snow"), StatCollector.translateToLocal("owg.setting.snow") }, 20, 50, gui.width,
                false));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.stronghold"), StatCollector.translateToLocal("owg.setting.stronghold") }, 21, 60,
                gui.width, true));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.mineshaft"), StatCollector.translateToLocal("owg.setting.mineshaft") }, 22, 70,
                gui.width, true));
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
    @SideOnly(Side.CLIENT)
    public WorldChunkManager getClientWorldChunkManager(World world)
    {
        return new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world)
    {
        int stronghold = trySetting(1, 1);
        int mineshaft = trySetting(2, 1);
        return new ChunkGeneratorInfdev(world, world.getSeed(), stronghold, mineshaft, false);
    }
}
