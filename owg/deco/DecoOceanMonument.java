package owg.deco;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.StructureStart;
import owg.world.ManagerOWG;

public class DecoOceanMonument extends MapGenStructure
{
    private int spacing;
    private int separation;
    public static final List<BiomeGenBase> WATER_BIOMES = Arrays.<BiomeGenBase> asList(
            new BiomeGenBase[] { BiomeGenBase.ocean, BiomeGenBase.deepOcean, BiomeGenBase.river, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver });
    public static final List<BiomeGenBase> SPAWN_BIOMES = Arrays.<BiomeGenBase> asList(BiomeGenBase.deepOcean);
    private static final List<BiomeGenBase.SpawnListEntry> MONUMENT_ENEMIES = Lists.<BiomeGenBase.SpawnListEntry> newArrayList();

    public DecoOceanMonument()
    {
        this.spacing = 32;
        this.separation = 5;
    }

    public DecoOceanMonument(Map<String, String> p_i45608_1_)
    {
        this();

        for (Entry<String, String> entry : p_i45608_1_.entrySet())
        {
            if (((String) entry.getKey()).equals("spacing"))
            {
                this.spacing = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.spacing, 1);
            }
            else if (((String) entry.getKey()).equals("separation"))
            {
                this.separation = MathHelper.parseIntWithDefaultAndMax((String) entry.getValue(), this.separation, 1);
            }
        }
    }

    @Override
    public String getStructureName()
    {
        return "Monument";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.spacing - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.spacing - 1;
        }

        int k = chunkX / this.spacing;
        int l = chunkZ / this.spacing;
        Random random = this.worldObj.setRandomSeed(k, l, 10387313);
        k = k * this.spacing;
        l = l * this.spacing;
        k = k + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
        l = l + (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;

        if (i == k && j == l)
        {
            if (this.worldObj.getWorldChunkManager() instanceof ManagerOWG)
            {
                if (!((ManagerOWG) this.worldObj.getWorldChunkManager()).areViableOceanMonumentBiomes(i * 16 + 8, j * 16 + 8, 16, SPAWN_BIOMES))
                {
                    return false;
                }

                boolean flag = ((ManagerOWG) this.worldObj.getWorldChunkManager()).areViableOceanMonumentBiomes(i * 16 + 8, j * 16 + 8, 29, WATER_BIOMES);

                if (flag)
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ)
    {
        return new StructureOceanMonument.StartMonument(this.worldObj, this.rand, chunkX, chunkZ);
    }

    public List<BiomeGenBase.SpawnListEntry> getScatteredFeatureSpawnList()
    {
        return MONUMENT_ENEMIES;
    }

    static
    {
        MONUMENT_ENEMIES.add(new BiomeGenBase.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
    }
}
