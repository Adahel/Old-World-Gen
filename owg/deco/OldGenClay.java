package owg.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import owg.OWGGenHelper;

public class OldGenClay extends WorldGenerator
{
    private Block clayBlock;
    private int numberOfBlocks;
    private int generatortype;

    public OldGenClay(int i, int type)
    {
        this.clayBlock = Blocks.clay;
        this.numberOfBlocks = i;
        this.generatortype = type;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if (this.generatortype == 0 || this.generatortype == 1)
        {
            if (OWGGenHelper.getBlockMaterial(world, i, j, k) != Material.water)
            {
                return false;
            }
            float f = random.nextFloat() * 3.141593F;
            double d = (float) (i + 8) + (MathHelper.sin(f) * (float) this.numberOfBlocks) / 8F;
            double d1 = (float) (i + 8) - (MathHelper.sin(f) * (float) this.numberOfBlocks) / 8F;
            double d2 = (float) (k + 8) + (MathHelper.cos(f) * (float) this.numberOfBlocks) / 8F;
            double d3 = (float) (k + 8) - (MathHelper.cos(f) * (float) this.numberOfBlocks) / 8F;
            double d4 = j + random.nextInt(3) + 2;
            double d5 = j + random.nextInt(3) + 2;
            for (int l = 0; l <= this.numberOfBlocks; l++)
            {
                double d6 = d + ((d1 - d) * (double) l) / (double) this.numberOfBlocks;
                double d7 = d4 + ((d5 - d4) * (double) l) / (double) this.numberOfBlocks;
                double d8 = d2 + ((d3 - d2) * (double) l) / (double) this.numberOfBlocks;
                double d9 = (random.nextDouble() * (double) this.numberOfBlocks) / 16D;
                double d10 = (double) (MathHelper.sin(((float) l * 3.141593F) / (float) this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
                double d11 = (double) (MathHelper.sin(((float) l * 3.141593F) / (float) this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
                for (int i1 = (int) (d6 - d10 / 2D); i1 <= (int) (d6 + d10 / 2D); i1++)
                {
                    for (int j1 = (int) (d7 - d11 / 2D); j1 <= (int) (d7 + d11 / 2D); j1++)
                    {
                        for (int k1 = (int) (d8 - d10 / 2D); k1 <= (int) (d8 + d10 / 2D); k1++)
                        {
                            double d12 = (((double) i1 + 0.5D) - d6) / (d10 / 2D);
                            double d13 = (((double) j1 + 0.5D) - d7) / (d11 / 2D);
                            double d14 = (((double) k1 + 0.5D) - d8) / (d10 / 2D);
                            if (d12 * d12 + d13 * d13 + d14 * d14 >= 1.0D)
                            {
                                continue;
                            }
                            Block l1 = OWGGenHelper.getBlock(world, i1, j1, k1);
                            if (l1 == Blocks.sand)
                            {
                                OWGGenHelper.setBlock(world, i1, j1, k1, this.clayBlock);
                            }
                        }

                    }

                }

            }

            return true;
        }
        else if (this.generatortype == 2)
        {
            if (OWGGenHelper.getBlockMaterial(world, i, j, k) != Material.water)
            {
                return false;
            }
            float f = random.nextFloat() * 3.141593F;
            double d = (float) (i + 8) + (MathHelper.sin(f) * (float) this.numberOfBlocks) / 8F;
            double d1 = (float) (i + 8) - (MathHelper.sin(f) * (float) this.numberOfBlocks) / 8F;
            double d2 = (float) (k + 8) + (MathHelper.cos(f) * (float) this.numberOfBlocks) / 8F;
            double d3 = (float) (k + 8) - (MathHelper.cos(f) * (float) this.numberOfBlocks) / 8F;
            double d4 = j + random.nextInt(3) + 2;
            double d5 = j + random.nextInt(3) + 2;
            for (int l = 0; l <= this.numberOfBlocks; l++)
            {
                double d6 = d + ((d1 - d) * (double) l) / (double) this.numberOfBlocks;
                double d7 = d4 + ((d5 - d4) * (double) l) / (double) this.numberOfBlocks;
                double d8 = d2 + ((d3 - d2) * (double) l) / (double) this.numberOfBlocks;
                double d9 = (random.nextDouble() * (double) this.numberOfBlocks) / 16D;
                double d10 = (double) (MathHelper.sin(((float) l * 3.141593F) / (float) this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
                double d11 = (double) (MathHelper.sin(((float) l * 3.141593F) / (float) this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
                int i1 = MathHelper.floor_double(d6 - d10 / 2D);
                int j1 = MathHelper.floor_double(d6 + d10 / 2D);
                int k1 = MathHelper.floor_double(d7 - d11 / 2D);
                int l1 = MathHelper.floor_double(d7 + d11 / 2D);
                int i2 = MathHelper.floor_double(d8 - d10 / 2D);
                int j2 = MathHelper.floor_double(d8 + d10 / 2D);
                for (int k2 = i1; k2 <= j1; k2++)
                {
                    for (int l2 = k1; l2 <= l1; l2++)
                    {
                        for (int i3 = i2; i3 <= j2; i3++)
                        {
                            double d12 = (((double) k2 + 0.5D) - d6) / (d10 / 2D);
                            double d13 = (((double) l2 + 0.5D) - d7) / (d11 / 2D);
                            double d14 = (((double) i3 + 0.5D) - d8) / (d10 / 2D);
                            if (d12 * d12 + d13 * d13 + d14 * d14 >= 1.0D)
                            {
                                continue;
                            }
                            Block j3 = OWGGenHelper.getBlock(world, k2, l2, i3);
                            if (j3 == Blocks.sand)
                            {
                                OWGGenHelper.setBlock(world, k2, l2, i3, this.clayBlock);
                            }
                        }

                    }

                }

            }

            return true;
        }
        else
        {
            float f = random.nextFloat() * 3.141593F;
            double d = (float) (i + 8) + (MathHelper.sin(f) * (float) this.numberOfBlocks) / 8F;
            double d1 = (float) (i + 8) - (MathHelper.sin(f) * (float) this.numberOfBlocks) / 8F;
            double d2 = (float) (k + 8) + (MathHelper.cos(f) * (float) this.numberOfBlocks) / 8F;
            double d3 = (float) (k + 8) - (MathHelper.cos(f) * (float) this.numberOfBlocks) / 8F;
            double d4 = j + random.nextInt(3) + 2;
            double d5 = j + random.nextInt(3) + 2;
            for (int l = 0; l <= this.numberOfBlocks; l++)
            {
                double d6 = d + ((d1 - d) * (double) l) / (double) this.numberOfBlocks;
                double d7 = d4 + ((d5 - d4) * (double) l) / (double) this.numberOfBlocks;
                double d8 = d2 + ((d3 - d2) * (double) l) / (double) this.numberOfBlocks;
                double d9 = (random.nextDouble() * (double) this.numberOfBlocks) / 16D;
                double d10 = (double) (MathHelper.sin(((float) l * 3.141593F) / (float) this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
                double d11 = (double) (MathHelper.sin(((float) l * 3.141593F) / (float) this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
                int i1 = MathHelper.floor_double(d6 - d10 / 2D);
                int j1 = MathHelper.floor_double(d6 + d10 / 2D);
                int k1 = MathHelper.floor_double(d7 - d11 / 2D);
                int l1 = MathHelper.floor_double(d7 + d11 / 2D);
                int i2 = MathHelper.floor_double(d8 - d10 / 2D);
                int j2 = MathHelper.floor_double(d8 + d10 / 2D);
                for (int k2 = i1; k2 <= j1; k2++)
                {
                    for (int l2 = k1; l2 <= l1; l2++)
                    {
                        for (int i3 = i2; i3 <= j2; i3++)
                        {
                            double d12 = (((double) k2 + 0.5D) - d6) / (d10 / 2D);
                            double d13 = (((double) l2 + 0.5D) - d7) / (d11 / 2D);
                            double d14 = (((double) i3 + 0.5D) - d8) / (d10 / 2D);
                            if (d12 * d12 + d13 * d13 + d14 * d14 >= 1.0D)
                            {
                                continue;
                            }
                            Block j3 = OWGGenHelper.getBlock(world, k2, l2, i3);
                            if (j3 == Blocks.sand)
                            {
                                OWGGenHelper.setBlock(world, k2, l2, i3, this.clayBlock);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
