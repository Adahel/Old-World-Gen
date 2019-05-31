package owg.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSettingsButton
{
    public GuiButton button;
    public String[] textarray;
    public int[] valuearray;
    public int selected;
    public int dependencie;
    public int[] depvalues;

    public GuiSettingsButton(String[] text, int[] values, int buttonID, int posY, int width)
    {
        this(new GuiButton(buttonID, width / 2 + 5, posY, 150, 20, text[0]), text, values, buttonID, -1, new int[0]);
    }

    public GuiSettingsButton(String[] text, int[] values, int buttonID, int posY, int width, int dep, int[] vel)
    {
        this(new GuiButton(buttonID, width / 2 + 5, posY, 150, 20, text[0]), text, values, buttonID, dep, vel);
    }

    public GuiSettingsButton(GuiButton b, String[] text, int[] values, int buttonID, int dep, int[] vel)
    {
        this.button = b;
        this.textarray = text;
        this.valuearray = values;
        this.selected = 0;
        this.dependencie = dep;
        this.depvalues = vel;
    }

    public void click()
    {
        this.selected++;
        if (this.selected >= this.textarray.length)
        {
            this.selected = 0;
        }
        this.button.displayString = this.textarray[this.selected];
    }

    public void setOldValue(int oldValue)
    {
        for (int i = 0; i < this.valuearray.length; i++)
        {
            if (this.valuearray[i] == oldValue)
            {
                this.selected = i;
                this.button.displayString = this.textarray[this.selected];
                break;
            }
        }
    }
}
