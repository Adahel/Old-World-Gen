package owg.world;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.biomes.BiomeList;
import owg.config.ConfigOWG;
import owg.data.DecodeGeneratorString;
import owg.generatortype.GeneratorType;
import owg.gui.GuiGeneratorSettings;

public class WorldTypeOWG extends WorldType
{
    public WorldTypeOWG(String n)
    {
        super(n);
    }

    @Override
    public WorldChunkManager getChunkManager(World world)
    {
        if (!world.isRemote)
        {
            System.out.println("OLD WORLD GEN - GENERATORSTRING: " + world.getWorldInfo().getGeneratorOptions());
            if (world.getWorldInfo().getGeneratorOptions().length() > 2)
            {
                DecodeGeneratorString.decode(world.getWorldInfo().getGeneratorOptions());
            }
            else
            {
                DecodeGeneratorString.decode(ConfigOWG.defaultGen);
            }

            return GeneratorType.currentGenerator.getServerWorldChunkManager(world);
        }
        else
        {
            if (GeneratorType.currentGenerator == null)
            {
                return new ManagerOWGHell(BiomeList.plains, 0.5F);
            }
            else
            {
                return GeneratorType.currentGenerator.getClientWorldChunkManager(world);
            }
        }
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return GeneratorType.currentGenerator.getChunkGenerator(world);
    }

    @Override
    public float getCloudHeight()
    {
        return 107F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onCustomizeButton(Minecraft instance, GuiCreateWorld guiCreateWorld)
    {
        instance.displayGuiScreen(new GuiGeneratorSettings(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
    }

    @Override
    public boolean isCustomizable()
    {
        return true;
    }
}