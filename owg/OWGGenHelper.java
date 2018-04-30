package owg;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public final class OWGGenHelper
{
    public static boolean isAirBlock(World world, int x, int y, int z)
    {
        return world.isAirBlock(new BlockPos(x, y, z));
    }

    public static IBlockState getBlockState(World world, int x, int y, int z)
    {
        return world.getBlockState(new BlockPos(x, y, z));
    }

    public static Block getBlock(World world, int x, int y, int z)
    {
        return getBlockState(world, x, y, z).getBlock();
    }

    public static Material getBlockMaterial(World world, int x, int y, int z)
    {
        return getBlock(world, x, y, z).getMaterial();
    }

    public static TileEntity getBlockTileEntity(World world, int x, int y, int z)
    {
        return world.getTileEntity(new BlockPos(x, y, z));
    }

    public static int getSavedLightValue(World world, EnumSkyBlock type, int x, int y, int z)
    {
        return world.getLightFor(type, new BlockPos(x, y, z));
    }

    public static boolean canBlockStay(World world, int x, int y, int z, BlockBush block)
    {
        return canBlockStay(world, x, y, z, block, block.getDefaultState());
    }

    public static boolean canBlockStay(World world, int x, int y, int z, BlockBush block, IBlockState state)
    {
        return block.canBlockStay(world, new BlockPos(x, y, z), state);
    }

    public static void setBlock(World world, int x, int y, int z, Block block)
    {
        setBlockWithNotify(world, x, y, z, block);
    }

    public static void setBlock(World world, int x, int y, int z, Block block, BlockPlanks.EnumType type)
    {
        setBlockState(world, x, y, z, block.getStateFromMeta(type.getMetadata()));
    }

    public static void setBlockWithNotify(World world, int x, int y, int z, Block block)
    {
        setBlockState(world, x, y, z, block.getDefaultState());
    }

    public static void setBlockWithNotify(World world, BlockPos pos, Block block)
    {
        setBlockState(world, pos, block.getDefaultState());
    }

    public static void setBlockState(World world, int x, int y, int z, IBlockState state)
    {
        setBlockState(world, new BlockPos(x, y, z), state);
    }

    public static void setBlockState(World world, BlockPos pos, IBlockState state)
    {
        world.setBlockState(pos, state, 2);
    }

    public static int getHeightValue(World world, int x, int z)
    {
        return world.getHeight(new BlockPos(x, 0, z)).getY();
    }
}
