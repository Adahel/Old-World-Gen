package owg.generator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkPrimerBeta extends ChunkPrimer
{
    private final IBlockState defaultState = Blocks.air.getDefaultState();

    @Override
    public IBlockState getBlockState(int x, int y, int z)
    {
        // THIS IS THE SHITTIEST SOLUTION EVER, DEAL WITH IT!
        if (y < 128)
        {
            return super.getBlockState(x, y, z);
        }
        else
        {
            return this.defaultState;
        }
    }

    @Override
    public void setBlockState(int x, int y, int z, IBlockState state)
    {
        // THIS IS THE SHITTIEST SOLUTION EVER, DEAL WITH IT!
        if (y < 128)
        {
            super.setBlockState(x, y, z, state);
        }
        else
        {
            super.setBlockState(x, y, z, this.defaultState);
        }
    }
}
