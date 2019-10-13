package owg.biomes;

import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.deco.OldGenBigTree;
import owg.deco.OldGenForest;
import owg.deco.OldGenTaiga1;
import owg.deco.OldGenTaiga2;
import owg.deco.OldGenTrees;
import owg.deco.WorldGenerator;

public class BiomeBeta extends BiomeList
{
    private int id;

    public BiomeBeta(int id, int i)
    {
        super(id);
        this.id = i;

        if (this.id == 0)
        {
            this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        }

        if (this.id == 1 || this.id == 2 || this.id == 3 || this.id == 6)
        {
            this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
        }

        if (this.id == 1 || this.id == 2 || this.id == 3 || this.id == 6)
        {
            this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        }

        if (this.id == 7)
        {
            this.spawnableCreatureList.clear();
        }
    }

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if (this.id == 0)
        {
            if (random.nextInt(3) == 0)
            {
                return new OldGenBigTree(2);
            }
            else
            {
                return new OldGenTrees(2);
            }
        }
        else if (this.id == 3)
        {
            if (random.nextInt(5) == 0)
            {
                return new OldGenForest();
            }
            if (random.nextInt(3) == 0)
            {
                return new OldGenBigTree(2);
            }
            else
            {
                return new OldGenTrees(2);
            }
        }
        else if (this.id == 6)
        {
            if (random.nextInt(3) == 0)
            {
                return new OldGenTaiga1();
            }
            else
            {
                return new OldGenTaiga2();
            }
        }
        else
        {
            if (random.nextInt(10) == 0)
            {
                return new OldGenBigTree(2);
            }
            else
            {
                return new OldGenTrees(2);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        if (this.id == 6 || this.id == 9)
        {
            return ColorizerGrass.getGrassColor(0.6F, 0.6F);
        }
        else if (this.id == 7)
        {
            return ColorizerFoliage.getFoliageColor(0.8F, 0.2F);
        }
        else
        {
            double d = MathHelper.clamp_float(this.getFloatTemperature(pos), 0.0F, 1.0F);
            double d1 = MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
            return ColorizerGrass.getGrassColor(d, d1);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        if (this.id == 6 || this.id == 9)
        {
            return ColorizerFoliage.getFoliageColor(0.6F, 0.6F);
        }
        else if (this.id == 7)
        {
            return ColorizerFoliage.getFoliageColor(0.8F, 0.2F);
        }
        else
        {
            double d = MathHelper.clamp_float(this.getFloatTemperature(pos), 0.0F, 1.0F);
            double d1 = MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
            return ColorizerFoliage.getFoliageColor(d, d1);
        }
    }
}
