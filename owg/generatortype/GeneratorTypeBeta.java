package owg.generatortype;

import net.minecraft.client.gui.GuiButton;
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
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.stronghold") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                        StatCollector.translateToLocal("owg.setting.stronghold") + ": " + StatCollector.translateToLocal("owg.setting.off") },
                new int[] { 0, 1 }, 21, 70, gui.width));
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.mineshaft") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                        StatCollector.translateToLocal("owg.setting.mineshaft") + ": " + StatCollector.translateToLocal("owg.setting.off") },
                new int[] { 0, 1 }, 22, 90, gui.width));
        String[] villages = new String[] { StatCollector.translateToLocal("owg.setting.village") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                StatCollector.translateToLocal("owg.setting.village") + ": " + StatCollector.translateToLocal("owg.setting.off") };
        gui.settings.add(new GuiSettingsButton(new GuiButton(23, gui.width / 2 + 5, 110, 75, 20, villages[0]), villages, new int[] { 0, 1 }, 23, 20,
                new int[] { 1, 2 }));
        String[] temples = new String[] { StatCollector.translateToLocal("owg.setting.temple") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                StatCollector.translateToLocal("owg.setting.temple") + ": " + StatCollector.translateToLocal("owg.setting.off") };
        gui.settings.add(
                new GuiSettingsButton(new GuiButton(24, gui.width / 2 + 81, 110, 75, 20, temples[0]), temples, new int[] { 0, 1 }, 24, 20, new int[] { 1, 2 }));
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.ravine") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                        StatCollector.translateToLocal("owg.setting.ravine") + ": " + StatCollector.translateToLocal("owg.setting.off") },
                new int[] { 0, 1 }, 25, 130, gui.width, 20, new int[] { 1, 2 }));
        gui.settings.add(new GuiSettingsButton(
                new String[] { StatCollector.translateToLocal("owg.setting.monument") + ": " + StatCollector.translateToLocal("owg.setting.on"),
                        StatCollector.translateToLocal("owg.setting.monument") + ": " + StatCollector.translateToLocal("owg.setting.off") },
                new int[] { 0, 1 }, 26, 150, gui.width, 20, new int[] { 1, 2 }));
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
        int villages = trySetting(3, 1);
        int temples = trySetting(4, 1);
        int ravines = trySetting(5, 1);
        int monuments = trySetting(6, 1);
        return new ChunkGeneratorBeta(world, world.getSeed(), biomes, strongholds, mineshafts, villages, temples, ravines, monuments);
    }
}
