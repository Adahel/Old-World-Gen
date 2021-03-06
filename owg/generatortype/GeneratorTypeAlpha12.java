package owg.generatortype;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.biomes.BiomeList;
import owg.generator.ChunkGeneratorAlpha;
import owg.gui.GuiGeneratorSettings;
import owg.gui.GuiSettingsCheckBox;
import owg.world.ManagerOWG;
import owg.world.ManagerOWGHell;

public class GeneratorTypeAlpha12 extends GeneratorType
{
    public GeneratorTypeAlpha12(int id, int cat, String name, boolean c)
    {
        super(id, cat, name, c);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean getSettings(GuiGeneratorSettings gui)
    {
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.stronghold"), StatCollector.translateToLocal("owg.setting.stronghold") }, 20, 50,
                gui.width, true));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.mineshaft"), StatCollector.translateToLocal("owg.setting.mineshaft") }, 21, 60,
                gui.width, true));
        return true;
    }

    @Override
    public WorldChunkManager getServerWorldChunkManager(World world)
    {
        return new ManagerOWG(world, true, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public WorldChunkManager getClientWorldChunkManager(World world)
    {
        return new ManagerOWGHell(BiomeList.OLDplains, 0.5F);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world)
    {
        int stronghold = trySetting(0, 1);
        int mineshaft = trySetting(1, 1);
        return new ChunkGeneratorAlpha(world, world.getSeed(), stronghold, mineshaft);
    }
}
