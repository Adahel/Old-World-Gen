package owg;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OWGEventHandler
{
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void actionPerformed(ActionPerformedEvent.Post event)
    {
        if (event.gui instanceof GuiCreateWorld)
        {
            GuiCreateWorld gui = (GuiCreateWorld) event.gui;
            GuiButton btnMapFeatures = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, gui, "field_146325_B", "btnMapFeatures");
            int selectedIndex = ReflectionHelper.getPrivateValue(GuiCreateWorld.class, gui, "field_146331_K", "selectedIndex");
            if (event.button.id == 3)
            {
                if (WorldType.worldTypes[selectedIndex] == OWG.worldtype)
                {
                    btnMapFeatures.visible = false;
                }
            }
            else if (event.button.id == 5)
            {
                if (WorldType.worldTypes[selectedIndex] == OWG.worldtype)
                {
                    btnMapFeatures.visible = false;
                }
            }
        }
    }
}