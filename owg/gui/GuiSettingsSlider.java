package owg.gui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSettingsSlider extends GuiSettingsButton
{
    public GuiSettingsSlider(String[] text, int[] values, int def, int buttonID, int posY, int width)
    {
        super(new GuiSlider(buttonID, width / 2 + 5, posY), text, values, buttonID, -1, new int[0]);
        ((GuiSlider) this.button).setSlider(this, def);
    }

    public GuiSettingsSlider(String[] text, int[] values, int def, int buttonID, int posY, int width, int dep, int[] vel)
    {
        super(new GuiSlider(buttonID, width / 2 + 5, posY), text, values, buttonID, dep, vel);
        ((GuiSlider) this.button).setSlider(this, def);
    }

    @Override
    public void click()
    {
    }

    @Override
    public void setOldValue(int oldValue)
    {
        for (int element : this.valuearray)
        {
            if (element == oldValue)
            {
                ((GuiSlider) this.button).sliderValue = Math.round(oldValue) / (float) (this.valuearray.length - 1);
                ((GuiSlider) this.button).setText();
                break;
            }
        }
    }
}
