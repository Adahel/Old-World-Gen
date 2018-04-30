package owg;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import owg.biomes.BiomeList;
import owg.config.ConfigOWG;
import owg.support.Support;
import owg.world.WorldTypeOWG;

@Mod(modid = "OWG", name = "OldWorldGen", version = "1.0.4", acceptedMinecraftVersions = "[1.8.9]")
public class OWG
{
    @Instance("OWG")
    public static OWG instance;

    public static final WorldTypeOWG worldtype = (new WorldTypeOWG("OWG"));

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;

        ConfigOWG.init(event);
        BiomeList.init();
    }

    @EventHandler
    public void Init(FMLInitializationEvent event)
    {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        Support.init();
    }
}