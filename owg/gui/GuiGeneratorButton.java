package owg.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGeneratorButton
{
    public GuiButton button;
    public int generatorID;

    public GuiGeneratorButton(String name, int genID, int buttonID, int posY, int width)
    {
        this.button = new GuiButton(buttonID, width / 2 - 155, posY, 150, 20, name);
        this.generatorID = genID;
    }
}