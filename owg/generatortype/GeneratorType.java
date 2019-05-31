package owg.generatortype;

import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.biomes.BiomeList;
import owg.data.DecodeGeneratorString;
import owg.gui.GuiGeneratorSettings;
import owg.world.ManagerOWGHell;

public class GeneratorType
{
    public static GeneratorType currentGenerator;
    public static int[] currentSettings;

    public static final GeneratorType[] generatortypes = new GeneratorType[5];
    public static final GeneratorType BETA173 = new GeneratorTypeBeta(0, 0, "BETA173", true);
    public static final GeneratorType ALPHA12 = new GeneratorTypeAlpha12(1, 0, "ALPHA12", true);
    public static final GeneratorType ALPHA11 = new GeneratorTypeAlpha11(2, 0, "ALPHA11", true);
    public static final GeneratorType INFDEV = new GeneratorTypeInfdev(3, 0, "INFDEV", true);
    public static final GeneratorType INDEV = new GeneratorTypeIndev(4, 0, "INDEV", true);

    private final int GeneratorTypeId;
    private final String GeneratorName;
    private final boolean Creatable;
    private final int category;

    public GeneratorType(int id, int cat, String name, boolean c, boolean fog, boolean angle)
    {
        generatortypes[id] = this;
        this.GeneratorTypeId = id;
        this.GeneratorName = name;
        this.Creatable = c;
        this.category = cat;
    }

    public GeneratorType(int id, int cat, String name, boolean c)
    {
        this(id, cat, name, c, false, false);
    }

    public int GetID()
    {
        return this.GeneratorTypeId;
    }

    public int GetCategory()
    {
        return this.category;
    }

    public String GetName()
    {
        return this.GeneratorName;
    }

    public boolean CanBeCreated()
    {
        return this.Creatable;
    }

    @SideOnly(Side.CLIENT)
    public boolean getSettings(GuiGeneratorSettings gui)
    {
        return false;
    }

    public WorldChunkManager getServerWorldChunkManager(World world)
    {
        return new ManagerOWGHell(BiomeList.OLDplains, 0.5F);
    }

    @SideOnly(Side.CLIENT)
    public WorldChunkManager getClientWorldChunkManager(World world)
    {
        return new ManagerOWGHell(BiomeList.OLDplains, 0.5F);
    }

    public IChunkProvider getChunkGenerator(World world)
    {
        return null;
    }

    public static int trySetting(int pos, int max)
    {
        if (currentSettings != null)
        {
            if (currentSettings.length > pos)
            {
                if (currentSettings[pos] <= max)
                {
                    return currentSettings[pos];
                }
            }
        }
        return 0;
    }

    public static boolean exists(String gen)
    {
        DecodeGeneratorString.decode(gen);

        if ((currentGenerator != null) && (currentSettings != null))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
