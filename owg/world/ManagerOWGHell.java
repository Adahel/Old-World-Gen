package owg.world;

import java.util.Arrays;

import net.minecraft.world.ChunkCoordIntPair;
import owg.biomes.BiomeGenBase;

public class ManagerOWGHell extends ManagerOWG
{
    private BiomeGenBase field_4201_e;
    private double field_4200_f;
    private double field_4199_g;

    public ManagerOWGHell(BiomeGenBase par1, double par2, double par4)
    {
        this.field_4201_e = par1;
        this.field_4200_f = par2;
        this.field_4199_g = par4;
    }

    @Override
    public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair par1ChunkCoordIntPair)
    {
        return this.field_4201_e;
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        return this.field_4201_e;
    }

    @Override
    public double getTemperature(int par1, int par2)
    {
        return this.field_4200_f;
    }

    @Override
    public BiomeGenBase[] func_4069_a(int par1, int par2, int par3, int par4)
    {
        this.field_4195_d = this.loadBlockGeneratorData(this.field_4195_d, par1, par2, par3, par4);
        return this.field_4195_d;
    }

    @Override
    public double[] getColdTemperatures(double ad[], int i, int j, int k, int l)
    {
        if (ad == null || ad.length < k * l)
        {
            ad = new double[k * l];
        }

        Arrays.fill(ad, 0, k * l, this.field_4200_f);
        return ad;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        if (this.temperature == null || this.temperature.length < par4 * par5)
        {
            this.temperature = new double[par4 * par5];
            this.humidity = new double[par4 * par5];
        }

        Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, this.field_4201_e);
        Arrays.fill(this.humidity, 0, par4 * par5, this.field_4199_g);
        Arrays.fill(this.temperature, 0, par4 * par5, this.field_4200_f);
        return par1ArrayOfBiomeGenBase;
    }
}
