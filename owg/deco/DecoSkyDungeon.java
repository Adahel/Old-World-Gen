package owg.deco;

import java.util.Random;

import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import owg.OWGGenHelper;
import owg.data.DungeonLoot;

public class DecoSkyDungeon extends WorldGenerator
{
    public boolean isEndDungeon = false;

    public DecoSkyDungeon(boolean end)
    {
        this.isEndDungeon = end;
    }

    @Override
    public boolean generate(World worldIn, Random rand, int i, int j, int k)
    {
        boolean chain1 = false, chain2 = false, chain3 = false, chain4 = false;

        // Check for chains
        for (int ch1 = j; ch1 < 60; ch1++)
        {
            if (!OWGGenHelper.isAirBlock(worldIn, i + 4, ch1 + 5, k + 4))
            {
                chain1 = true;
                break;
            }
        }
        for (int ch2 = j; ch2 < 60; ch2++)
        {
            if (!OWGGenHelper.isAirBlock(worldIn, i + 4, ch2 + 5, k - 4))
            {
                chain2 = true;
                break;
            }
        }
        for (int ch3 = j; ch3 < 60; ch3++)
        {
            if (!OWGGenHelper.isAirBlock(worldIn, i - 4, ch3 + 5, k + 4))
            {
                chain3 = true;
                break;
            }
        }
        for (int ch4 = j; ch4 < 60; ch4++)
        {
            if (!OWGGenHelper.isAirBlock(worldIn, i - 4, ch4 + 5, k - 4))
            {
                chain4 = true;
                break;
            }
        }

        if (chain1 == false || chain2 == false || chain3 == false || chain4 == false)
        {
            return false;
        }

        // Build Chains
        for (int chy1 = j + 5; chy1 < 70; chy1++)
        {
            if (!OWGGenHelper.isAirBlock(worldIn, i + 4, chy1, k + 4))
            {
                break;
            }
            OWGGenHelper.setBlockWithNotify(worldIn, i + 4, chy1, k + 4, Blocks.iron_bars);
        }
        for (int chy2 = j + 5; chy2 < 70; chy2++)
        {
            if (!OWGGenHelper.isAirBlock(worldIn, i + 4, chy2, k - 4))
            {
                break;
            }
            OWGGenHelper.setBlockWithNotify(worldIn, i + 4, chy2, k - 4, Blocks.iron_bars);
        }
        for (int chy3 = j + 5; chy3 < 70; chy3++)
        {
            if (!OWGGenHelper.isAirBlock(worldIn, i - 4, chy3, k + 4))
            {
                break;
            }
            OWGGenHelper.setBlockWithNotify(worldIn, i - 4, chy3, k + 4, Blocks.iron_bars);
        }
        for (int chy4 = j + 5; chy4 < 70; chy4++)
        {
            if (!OWGGenHelper.isAirBlock(worldIn, i - 4, chy4, k - 4))
            {
                break;
            }
            OWGGenHelper.setBlockWithNotify(worldIn, i - 4, chy4, k - 4, Blocks.iron_bars);
        }

        OWGGenHelper.setBlockWithNotify(worldIn, i + 4, j + 6, k + 4, Blocks.mossy_cobblestone);
        OWGGenHelper.setBlockWithNotify(worldIn, i + 4, j + 6, k - 4, Blocks.mossy_cobblestone);
        OWGGenHelper.setBlockWithNotify(worldIn, i - 4, j + 6, k + 4, Blocks.mossy_cobblestone);
        OWGGenHelper.setBlockWithNotify(worldIn, i - 4, j + 6, k - 4, Blocks.mossy_cobblestone);

        // Build SkyDungeon
        for (int x1 = i - 4; x1 < i + 5; x1++)
        {
            for (int y1 = j; y1 < j + 6; y1++)
            {
                for (int z1 = k - 4; z1 < k + 5; z1++)
                {
                    if (rand.nextInt(10) == 0)
                    {
                        OWGGenHelper.setBlockWithNotify(worldIn, x1, y1, z1, Blocks.air);
                    }
                    else
                    {
                        if (rand.nextInt(2) != 0)
                        {
                            OWGGenHelper.setBlockWithNotify(worldIn, x1, y1, z1, Blocks.mossy_cobblestone);
                        }
                        else
                        {
                            OWGGenHelper.setBlockWithNotify(worldIn, x1, y1, z1, Blocks.cobblestone);
                        }
                    }
                }
            }
        }

        // Fill with air
        for (int x2 = i - 3; x2 < i + 4; x2++)
        {
            for (int y2 = j + 1; y2 < j + 5; y2++)
            {
                for (int z2 = k - 3; z2 < k + 4; z2++)
                {
                    OWGGenHelper.setBlockWithNotify(worldIn, x2, y2, z2, Blocks.air);
                }
            }
        }

        // Chests and Spawners
        if (this.isEndDungeon)
        {
            IBlockState iblockstate4 = Blocks.end_portal_frame.getDefaultState().withProperty(BlockEndPortalFrame.FACING, EnumFacing.WEST);
            IBlockState iblockstate = Blocks.end_portal_frame.getDefaultState().withProperty(BlockEndPortalFrame.FACING, EnumFacing.NORTH);
            IBlockState iblockstate1 = Blocks.end_portal_frame.getDefaultState().withProperty(BlockEndPortalFrame.FACING, EnumFacing.EAST);
            IBlockState iblockstate2 = Blocks.end_portal_frame.getDefaultState().withProperty(BlockEndPortalFrame.FACING, EnumFacing.SOUTH);

            OWGGenHelper.setBlockState(worldIn, i + 2, j + 1, k - 1,
                    iblockstate4.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(worldIn, i + 2, j + 1, k + 0,
                    iblockstate4.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(worldIn, i + 2, j + 1, k + 1,
                    iblockstate4.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));

            OWGGenHelper.setBlockState(worldIn, i - 1, j + 1, k + 2,
                    iblockstate.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(worldIn, i + 0, j + 1, k + 2,
                    iblockstate.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(worldIn, i + 1, j + 1, k + 2,
                    iblockstate.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));

            OWGGenHelper.setBlockState(worldIn, i - 2, j + 1, k - 1,
                    iblockstate1.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(worldIn, i - 2, j + 1, k + 0,
                    iblockstate1.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(worldIn, i - 2, j + 1, k + 1,
                    iblockstate1.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));

            OWGGenHelper.setBlockState(worldIn, i - 1, j + 1, k - 2,
                    iblockstate2.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(worldIn, i + 0, j + 1, k - 2,
                    iblockstate2.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(worldIn, i + 1, j + 1, k - 2,
                    iblockstate2.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(rand.nextFloat() > 0.9F)));

            OWGGenHelper.setBlockWithNotify(worldIn, i, j + 6, k, Blocks.mob_spawner);
            TileEntityMobSpawner spawn3 = (TileEntityMobSpawner) OWGGenHelper.getBlockTileEntity(worldIn, i, j + 6, k);
            if (spawn3 != null)
            {
                spawn3.getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(rand));
            }
        }
        else
        {
            OWGGenHelper.setBlockWithNotify(worldIn, i, j + 1, k, Blocks.mob_spawner);
            OWGGenHelper.setBlockWithNotify(worldIn, i, j + 3, k, Blocks.mob_spawner);
            OWGGenHelper.setBlockWithNotify(worldIn, i, j + 6, k, Blocks.mob_spawner);
            TileEntityMobSpawner spawn1 = (TileEntityMobSpawner) OWGGenHelper.getBlockTileEntity(worldIn, i, j + 1, k);
            TileEntityMobSpawner spawn2 = (TileEntityMobSpawner) OWGGenHelper.getBlockTileEntity(worldIn, i, j + 3, k);
            TileEntityMobSpawner spawn3 = (TileEntityMobSpawner) OWGGenHelper.getBlockTileEntity(worldIn, i, j + 6, k);
            if (spawn1 != null)
            {
                spawn1.getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(rand));
            }
            if (spawn2 != null)
            {
                spawn2.getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(rand));
            }
            if (spawn3 != null)
            {
                spawn3.getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(rand));
            }

            OWGGenHelper.setBlockWithNotify(worldIn, i, j + 2, k, Blocks.chest);
            TileEntityChest var16 = (TileEntityChest) OWGGenHelper.getBlockTileEntity(worldIn, i, j + 2, k);

            for (int var17 = 0; var17 < 14; ++var17)
            {
                ItemStack var18 = this.pickCheckLootItem(rand);

                if (var18 != null)
                {
                    var16.setInventorySlotContents(rand.nextInt(var16.getSizeInventory()), var18);
                }
            }
        }
        return false;
    }

    private ItemStack pickCheckLootItem(Random random)
    {
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
