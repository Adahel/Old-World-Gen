package owg.deco;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import owg.OWGGenHelper;
import owg.data.DungeonLoot;

public class OldGenDungeons extends WorldGenerator
{
    public OldGenDungeons()
    {
    }

    @Override
    public boolean generate(World worldIn, Random rand, int x, int y, int z)
    {
        byte byte0 = 3;
        int i = rand.nextInt(2) + 2;
        int j = rand.nextInt(2) + 2;
        int k = 0;

        for (int l = x - i - 1; l <= x + i + 1; l++)
        {
            for (int k1 = y - 1; k1 <= y + byte0 + 1; k1++)
            {
                for (int j2 = z - j - 1; j2 <= z + j + 1; j2++)
                {
                    Material material = OWGGenHelper.getBlockMaterial(worldIn, l, k1, j2);

                    if (k1 == y - 1 && !material.isSolid())
                    {
                        return false;
                    }

                    if (k1 == y + byte0 + 1 && !material.isSolid())
                    {
                        return false;
                    }

                    if ((l == x - i - 1 || l == x + i + 1 || j2 == z - j - 1 || j2 == z + j + 1) && k1 == y
                            && OWGGenHelper.isAirBlock(worldIn, l, k1, j2) && OWGGenHelper.isAirBlock(worldIn, l, k1 + 1, j2))
                    {
                        k++;
                    }
                }
            }
        }

        if (k < 1 || k > 5)
        {
            return false;
        }

        for (int i1 = x - i - 1; i1 <= x + i + 1; i1++)
        {
            for (int l1 = y + byte0; l1 >= y - 1; l1--)
            {
                for (int k2 = z - j - 1; k2 <= z + j + 1; k2++)
                {
                    if (i1 == x - i - 1 || l1 == y - 1 || k2 == z - j - 1 || i1 == x + i + 1 || l1 == y + byte0 + 1 || k2 == z + j + 1)
                    {
                        if (l1 >= 0 && !OWGGenHelper.getBlockMaterial(worldIn, i1, l1 - 1, k2).isSolid())
                        {
                            OWGGenHelper.setBlockWithNotify(worldIn, i1, l1, k2, Blocks.air);
                            continue;
                        }

                        if (!OWGGenHelper.getBlockMaterial(worldIn, i1, l1, k2).isSolid())
                        {
                            continue;
                        }

                        if (l1 == y - 1 && rand.nextInt(4) != 0)
                        {
                            OWGGenHelper.setBlockWithNotify(worldIn, i1, l1, k2, Blocks.mossy_cobblestone);
                        }
                        else
                        {
                            OWGGenHelper.setBlockWithNotify(worldIn, i1, l1, k2, Blocks.cobblestone);
                        }
                    }
                    else
                    {
                        OWGGenHelper.setBlockWithNotify(worldIn, i1, l1, k2, Blocks.air);
                    }
                }
            }
        }

        for (int j1 = 0; j1 < 2; j1++)
        {
            label0:

            for (int i2 = 0; i2 < 3; i2++)
            {
                int l2 = (x + rand.nextInt(i * 2 + 1)) - i;
                int i3 = y;
                int j3 = (z + rand.nextInt(j * 2 + 1)) - j;

                if (!OWGGenHelper.isAirBlock(worldIn, l2, i3, j3))
                {
                    continue;
                }

                int k3 = 0;

                if (OWGGenHelper.getBlockMaterial(worldIn, l2 - 1, i3, j3).isSolid())
                {
                    k3++;
                }

                if (OWGGenHelper.getBlockMaterial(worldIn, l2 + 1, i3, j3).isSolid())
                {
                    k3++;
                }

                if (OWGGenHelper.getBlockMaterial(worldIn, l2, i3, j3 - 1).isSolid())
                {
                    k3++;
                }

                if (OWGGenHelper.getBlockMaterial(worldIn, l2, i3, j3 + 1).isSolid())
                {
                    k3++;
                }

                if (k3 != 1)
                {
                    continue;
                }

                OWGGenHelper.setBlockWithNotify(worldIn, l2, i3, j3, Blocks.chest);
                TileEntityChest tileentitychest = (TileEntityChest) OWGGenHelper.getBlockTileEntity(worldIn, l2, i3, j3);

                if (tileentitychest == null)
                {
                    break;
                }

                int l3 = 0;

                do
                {

                    if (l3 >= 8)
                    {
                        break label0;
                    }

                    ItemStack itemstack = this.pickCheckLootItem(rand);

                    if (itemstack != null)
                    {
                        tileentitychest.setInventorySlotContents(rand.nextInt(tileentitychest.getSizeInventory()), itemstack);
                    }

                    l3++;
                }
                while (true);
            }
        }

        OWGGenHelper.setBlockWithNotify(worldIn, x, y, z, Blocks.mob_spawner);
        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) OWGGenHelper.getBlockTileEntity(worldIn, x, y, z);

        if (tileentitymobspawner != null)
        {
            tileentitymobspawner.getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(rand));
        }
        else
        {
            System.err.println((new StringBuilder()).append("Failed to fetch mob spawner entity at (").append(x).append(", ").append(y).append(", ")
                    .append(z).append(")").toString());
        }

        return true;
    }

    private ItemStack pickCheckLootItem(Random random)
    {
        // fix for world gen seed
        int i = random.nextInt(11), r = 0;
        if (i == 1)
        {
            r = random.nextInt(4) + 1;
        }
        else if (i == 3)
        {
            r = random.nextInt(4) + 1;
        }
        else if (i == 4)
        {
            r = random.nextInt(4) + 1;
        }
        else if (i == 5)
        {
            r = random.nextInt(4) + 1;
        }
        else if (i == 7 && random.nextInt(100) == 0)
        {
        }
        else if (i == 8 && random.nextInt(2) == 0)
        {
            r = random.nextInt(4) + 1;
        }
        else if (i == 9 && random.nextInt(10) == 0)
        {
            r = random.nextInt(2);
        }

        // getting item from DungeonLoot array
        return DungeonLoot.pickItem();
    }

    private String pickMobSpawner(Random random)
    {
        int i = random.nextInt(4);
        if (i == 0)
        {
            return "Skeleton";
        }
        if (i == 1)
        {
            return "Zombie";
        }
        if (i == 2)
        {
            return "Zombie";
        }
        if (i == 3)
        {
            return "Spider";
        }
        else
        {
            return "";
        }
    }
}
