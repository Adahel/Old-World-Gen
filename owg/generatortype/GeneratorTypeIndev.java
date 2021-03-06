package owg.generatortype;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.biomes.BiomeList;
import owg.generator.ChunkGeneratorIndev;
import owg.gui.GuiGeneratorSettings;
import owg.gui.GuiSettingsButton;
import owg.gui.GuiSettingsCheckBox;
import owg.gui.GuiSettingsSlider;
import owg.world.ManagerOWGHell;

public class GeneratorTypeIndev extends GeneratorType
{
    public GeneratorTypeIndev(int id, int cat, String name, boolean c)
    {
        super(id, cat, name, c);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean getSettings(GuiGeneratorSettings gui)
    {
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.theme") + ": " + StatCollector.translateToLocal("owg.theme.default"),
                        StatCollector.translateToLocal("owg.setting.theme") + ": " + StatCollector.translateToLocal("owg.theme.hell"),
                        StatCollector.translateToLocal("owg.setting.theme") + ": " + StatCollector.translateToLocal("owg.theme.paradise"),
                        StatCollector.translateToLocal("owg.setting.theme") + ": " + StatCollector.translateToLocal("owg.theme.woods"),
                        StatCollector.translateToLocal("owg.setting.theme") + ": " + StatCollector.translateToLocal("owg.theme.snow") },
                new int[] { 0, 1, 2, 3, 4 }, 20, 50, gui.width));
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.type") + ": " + StatCollector.translateToLocal("owg.type.island"),
                        StatCollector.translateToLocal("owg.setting.type") + ": " + StatCollector.translateToLocal("owg.type.floating"),
                        StatCollector.translateToLocal("owg.setting.type") + ": " + StatCollector.translateToLocal("owg.type.inland") },
                new int[] { 0, 1, 2 }, 21, 70, gui.width));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.stronghold"), StatCollector.translateToLocal("owg.setting.stronghold") }, 22, 90,
                gui.width, true, 21, new int[] { 0, 2 }));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.mineshaft"), StatCollector.translateToLocal("owg.setting.mineshaft") }, 23, 100,
                gui.width, true, 21, new int[] { 0, 2 }));
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.dungeon") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                        StatCollector.translateToLocal("owg.setting.dungeon") + ": " + StatCollector.translateToLocal("owg.setting.end"),
                        StatCollector.translateToLocal("owg.setting.dungeon") + ": " + StatCollector.translateToLocal("owg.setting.off") },
                new int[] { 0, 1, 2 }, 24, 90, gui.width, 21, new int[] { 1 }));
        gui.settings.add(new GuiSettingsSlider(
                new String[] { StatCollector.translateToLocal("owg.setting.size") + ": " + StatCollector.translateToLocal("owg.setting.small"),
                        StatCollector.translateToLocal("owg.setting.size") + ": " + StatCollector.translateToLocal("owg.setting.default"),
                        StatCollector.translateToLocal("owg.setting.size") + ": " + StatCollector.translateToLocal("owg.setting.large") },
                new int[] { 0, 1, 2 }, 1, 25, 110, gui.width, 21, new int[] { 0, 1 }));
        gui.settings.add(new GuiSettingsSlider(
                new String[] { StatCollector.translateToLocal("owg.setting.layer") + ": 1", StatCollector.translateToLocal("owg.setting.layer") + ": 2",
                        StatCollector.translateToLocal("owg.setting.layer") + ": 3", StatCollector.translateToLocal("owg.setting.layer") + ": 4",
                        StatCollector.translateToLocal("owg.setting.layer") + ": 5" },
                new int[] { 0, 1, 2, 3, 4 }, 1, 26, 130, gui.width, 21, new int[] { 1 }));
        return true;
    }

    @Override
    public WorldChunkManager getServerWorldChunkManager(World world)
    {
        if (trySetting(0, 4) == 4)
        {
            return new ManagerOWGHell(BiomeList.CLASSICsnow, 0.5F);
        }
        return new ManagerOWGHell(BiomeList.CLASSICnormal, 0.5F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public WorldChunkManager getClientWorldChunkManager(World world)
    {
        return new ManagerOWGHell(BiomeList.CLASSICnormal, 0.5F);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world)
    {
        int themeID = trySetting(0, 4) + 1;
        int typeID = trySetting(1, 2) + 1;
        int strongholds = trySetting(2, 1);
        int mineshafts = trySetting(3, 1);
        int dungeons = trySetting(4, 2);
        int size = trySetting(5, 2) + 1;
        int layers = trySetting(6, 4) + 1;
        return new ChunkGeneratorIndev(world, world.getSeed(), typeID, themeID, strongholds, mineshafts, dungeons, size, layers);
    }
}
