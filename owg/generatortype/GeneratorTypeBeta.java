package owg.generatortype;

import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.biomes.BiomeList;
import owg.generator.ChunkGeneratorBeta;
import owg.gui.GuiGeneratorSettings;
import owg.gui.GuiSettingsButton;
import owg.gui.GuiSettingsCheckBox;
import owg.world.ManagerOWG;
import owg.world.ManagerOWGHell;;

public class GeneratorTypeBeta extends GeneratorType
{
    public GeneratorTypeBeta(int id, int cat, String name, boolean c)
    {
        super(id, cat, name, c);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean getSettings(GuiGeneratorSettings gui)
    {
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.biomes.biomes") + ": " + StatCollector.translateToLocal("owg.biomes.original"),
                        StatCollector.translateToLocal("owg.biomes.biomes") + ": " + StatCollector.translateToLocal("owg.biomes.vanilla"),
                        StatCollector.translateToLocal("owg.biomes.biomes") + ": " + StatCollector.translateToLocal("owg.biomes.all") },
                new int[] { 0, 1, 2 }, 20, 50, gui.width));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.stronghold"), StatCollector.translateToLocal("owg.setting.stronghold") }, 21, 70,
                gui.width, true));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.mineshaft"), StatCollector.translateToLocal("owg.setting.mineshaft") }, 22, 80,
                gui.width, true));
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.cave") + ": " + StatCollector.translateToLocal("owg.biomes.vanilla"),
                        StatCollector.translateToLocal("owg.setting.cave") + ": " + StatCollector.translateToLocal("owg.biomes.original") },
                new int[] { 0, 1 }, 23, 90, gui.width, 20, new int[] { 1, 2 }));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.village"), StatCollector.translateToLocal("owg.setting.village") }, 24, 110,
                gui.width, true, 20, new int[] { 1, 2 }));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.temple"), StatCollector.translateToLocal("owg.setting.temple") }, 25, 120, gui.width,
                true, 20, new int[] { 1, 2 }));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.ravine"), StatCollector.translateToLocal("owg.setting.ravine") }, 26, 130, gui.width,
                true, 20, new int[] { 1, 2 }));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.ocean"), StatCollector.translateToLocal("owg.setting.ocean") }, 27, 140, gui.width,
                true, 20, new int[] { 1, 2 }));
        gui.settings.add(new GuiSettingsCheckBox(
                new String[] { StatCollector.translateToLocal("owg.setting.monument"), StatCollector.translateToLocal("owg.setting.monument") }, 28, 150,
                gui.width, true, 27, new int[] { 0 }));
        return true;
    }

    @Override
    public WorldChunkManager getServerWorldChunkManager(World world)
    {
        int biomes = trySetting(0, 2);
        return new ManagerOWG(world, true, biomes);
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
        int biomes = trySetting(0, 2);
        int strongholds = trySetting(1, 1);
        int mineshafts = trySetting(2, 1);
        int caves = trySetting(3, 1);
        int villages = trySetting(4, 1);
        int temples = trySetting(5, 1);
        int ravines = trySetting(6, 1);
        int ocean = trySetting(7, 1);
        int monuments = trySetting(8, 1);
        return new ChunkGeneratorBeta(world, world.getSeed(), biomes, strongholds, mineshafts, caves, villages, temples, ravines, ocean, monuments);
    }
}
