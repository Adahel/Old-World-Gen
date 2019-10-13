package owg.biomes;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeClassic extends BiomeList
{
    public BiomeClassic(int id)
    {
        super(id);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 2, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 2, 1, 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xABFF67;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x4FFF2B;
    }

    @Override
    public BiomeList createMutatedBiome(int id)
    {
        BiomeClassic biomeclassic = new BiomeClassic(id);
        biomeclassic.setBiomeName("owg_Classic_Snow");
        biomeclassic.setColor(353825);
        biomeclassic.setEnableSnow();
        biomeclassic.setTemperatureRainfall(0.0F, 0.5F);
        return biomeclassic;
    }
}
