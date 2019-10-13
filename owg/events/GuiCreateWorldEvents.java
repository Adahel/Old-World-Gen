package owg.events;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.OWG;

@SideOnly(Side.CLIENT)
public class GuiCreateWorldEvents
{
    @SubscribeEvent
    public void initGui(InitGuiEvent.Post event)
    {
        if (event.gui instanceof GuiCreateWorld)
        {
            GuiButton btnMapFeatures = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, (GuiCreateWorld) event.gui, "field_146325_B", "btnMapFeatures");
            int selectedIndex = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, (GuiCreateWorld) event.gui, "field_146331_K", "selectedIndex");
            if (WorldType.worldTypes[selectedIndex] == OWG.worldtype)
            {
                btnMapFeatures.visible = false;
            }
        }
    }

    @SubscribeEvent
    public void actionPerformed(ActionPerformedEvent.Post event)
    {
        if (event.gui instanceof GuiCreateWorld)
        {
            GuiButton btnMapFeatures = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, (GuiCreateWorld) event.gui, "field_146325_B", "btnMapFeatures");
            int selectedIndex = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, (GuiCreateWorld) event.gui, "field_146331_K", "selectedIndex");
            if (WorldType.worldTypes[selectedIndex] == OWG.worldtype)
            {
                btnMapFeatures.visible = false;
            }
        }
    }
}