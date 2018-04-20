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
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        boolean chain1 = false, chain2 = false, chain3 = false, chain4 = false;

        // Check for chains
        for (int ch1 = par4; ch1 < 60; ch1++)
        {
            if (!OWGGenHelper.isAirBlock(par1World, par3 + 4, ch1 + 5, par5 + 4))
            {
                chain1 = true;
                break;
            }
        }
        for (int ch2 = par4; ch2 < 60; ch2++)
        {
            if (!OWGGenHelper.isAirBlock(par1World, par3 + 4, ch2 + 5, par5 - 4))
            {
                chain2 = true;
                break;
            }
        }
        for (int ch3 = par4; ch3 < 60; ch3++)
        {
            if (!OWGGenHelper.isAirBlock(par1World, par3 - 4, ch3 + 5, par5 + 4))
            {
                chain3 = true;
                break;
            }
        }
        for (int ch4 = par4; ch4 < 60; ch4++)
        {
            if (!OWGGenHelper.isAirBlock(par1World, par3 - 4, ch4 + 5, par5 - 4))
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
        for (int chy1 = par4 + 5; chy1 < 70; chy1++)
        {
            if (!OWGGenHelper.isAirBlock(par1World, par3 + 4, chy1, par5 + 4))
            {
                break;
            }
            OWGGenHelper.setBlockWithNotify(par1World, par3 + 4, chy1, par5 + 4, Blocks.iron_bars);
        }
        for (int chy2 = par4 + 5; chy2 < 70; chy2++)
        {
            if (!OWGGenHelper.isAirBlock(par1World, par3 + 4, chy2, par5 - 4))
            {
                break;
            }
            OWGGenHelper.setBlockWithNotify(par1World, par3 + 4, chy2, par5 - 4, Blocks.iron_bars);
        }
        for (int chy3 = par4 + 5; chy3 < 70; chy3++)
        {
            if (!OWGGenHelper.isAirBlock(par1World, par3 - 4, chy3, par5 + 4))
            {
                break;
            }
            OWGGenHelper.setBlockWithNotify(par1World, par3 - 4, chy3, par5 + 4, Blocks.iron_bars);
        }
        for (int chy4 = par4 + 5; chy4 < 70; chy4++)
        {
            if (!OWGGenHelper.isAirBlock(par1World, par3 - 4, chy4, par5 - 4))
            {
                break;
            }
            OWGGenHelper.setBlockWithNotify(par1World, par3 - 4, chy4, par5 - 4, Blocks.iron_bars);
        }

        OWGGenHelper.setBlockWithNotify(par1World, par3 + 4, par4 + 6, par5 + 4, Blocks.mossy_cobblestone);
        OWGGenHelper.setBlockWithNotify(par1World, par3 + 4, par4 + 6, par5 - 4, Blocks.mossy_cobblestone);
        OWGGenHelper.setBlockWithNotify(par1World, par3 - 4, par4 + 6, par5 + 4, Blocks.mossy_cobblestone);
        OWGGenHelper.setBlockWithNotify(par1World, par3 - 4, par4 + 6, par5 - 4, Blocks.mossy_cobblestone);

        // Build SkyDungeon
        for (int x1 = par3 - 4; x1 < par3 + 5; x1++)
        {
            for (int y1 = par4; y1 < par4 + 6; y1++)
            {
                for (int z1 = par5 - 4; z1 < par5 + 5; z1++)
                {
                    if (par2Random.nextInt(10) == 0)
                    {
                        OWGGenHelper.setBlockWithNotify(par1World, x1, y1, z1, Blocks.air);
                    }
                    else
                    {
                        if (par2Random.nextInt(2) != 0)
                        {
                            OWGGenHelper.setBlockWithNotify(par1World, x1, y1, z1, Blocks.mossy_cobblestone);
                        }
                        else
                        {
                            OWGGenHelper.setBlockWithNotify(par1World, x1, y1, z1, Blocks.cobblestone);
                        }
                    }
                }
            }
        }

        // Fill with air
        for (int x2 = par3 - 3; x2 < par3 + 4; x2++)
        {
            for (int y2 = par4 + 1; y2 < par4 + 5; y2++)
            {
                for (int z2 = par5 - 3; z2 < par5 + 4; z2++)
                {
                    OWGGenHelper.setBlockWithNotify(par1World, x2, y2, z2, Blocks.air);
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

            OWGGenHelper.setBlockState(par1World, par3 + 2, par4 + 1, par5 - 1,
                    iblockstate4.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(par1World, par3 + 2, par4 + 1, par5 + 0,
                    iblockstate4.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(par1World, par3 + 2, par4 + 1, par5 + 1,
                    iblockstate4.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));

            OWGGenHelper.setBlockState(par1World, par3 - 1, par4 + 1, par5 + 2,
                    iblockstate.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(par1World, par3 + 0, par4 + 1, par5 + 2,
                    iblockstate.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(par1World, par3 + 1, par4 + 1, par5 + 2,
                    iblockstate.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));

            OWGGenHelper.setBlockState(par1World, par3 - 2, par4 + 1, par5 - 1,
                    iblockstate1.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(par1World, par3 - 2, par4 + 1, par5 + 0,
                    iblockstate1.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(par1World, par3 - 2, par4 + 1, par5 + 1,
                    iblockstate1.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));

            OWGGenHelper.setBlockState(par1World, par3 - 1, par4 + 1, par5 - 2,
                    iblockstate2.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(par1World, par3 + 0, par4 + 1, par5 - 2,
                    iblockstate2.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));
            OWGGenHelper.setBlockState(par1World, par3 + 1, par4 + 1, par5 - 2,
                    iblockstate2.withProperty(BlockEndPortalFrame.EYE, Boolean.valueOf(par2Random.nextFloat() > 0.9F)));

            OWGGenHelper.setBlockWithNotify(par1World, par3, par4 + 6, par5, Blocks.mob_spawner);
            TileEntityMobSpawner spawn3 = (TileEntityMobSpawner) OWGGenHelper.getBlockTileEntity(par1World, par3, par4 + 6, par5);
            if (spawn3 != null)
            {
                spawn3.getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(par2Random));
            }
        }
        else
        {
            OWGGenHelper.setBlockWithNotify(par1World, par3, par4 + 1, par5, Blocks.mob_spawner);
            OWGGenHelper.setBlockWithNotify(par1World, par3, par4 + 3, par5, Blocks.mob_spawner);
            OWGGenHelper.setBlockWithNotify(par1World, par3, par4 + 6, par5, Blocks.mob_spawner);
            TileEntityMobSpawner spawn1 = (TileEntityMobSpawner) OWGGenHelper.getBlockTileEntity(par1World, par3, par4 + 1, par5);
            TileEntityMobSpawner spawn2 = (TileEntityMobSpawner) OWGGenHelper.getBlockTileEntity(par1World, par3, par4 + 3, par5);
            TileEntityMobSpawner spawn3 = (TileEntityMobSpawner) OWGGenHelper.getBlockTileEntity(par1World, par3, par4 + 6, par5);
            if (spawn1 != null)
            {
                spawn1.getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(par2Random));
            }
            if (spawn2 != null)
            {
                spawn2.getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(par2Random));
            }
            if (spawn3 != null)
            {
                spawn3.getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(par2Random));
            }

            OWGGenHelper.setBlockWithNotify(par1World, par3, par4 + 2, par5, Blocks.chest);
            TileEntityChest var16 = (TileEntityChest) OWGGenHelper.getBlockTileEntity(par1World, par3, par4 + 2, par5);

            for (int var17 = 0; var17 < 14; ++var17)
            {
                ItemStack var18 = this.pickCheckLootItem(par2Random);

                if (var18 != null)
                {
                    var16.setInventorySlotContents(par2Random.nextInt(var16.getSizeInventory()), var18);
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
