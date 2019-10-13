package owg.events;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Events
{
    @SideOnly(Side.CLIENT)
    public static void initClient()
    {
        MinecraftForge.EVENT_BUS.register(new GuiCreateWorldEvents());
    }

    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new LoadWorldEvents());
    }
}
