package owg.gui;

import net.minecraftforge.fml.client.config.GuiCheckBox;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSettingsCheckBox extends GuiSettingsButton
{
    private boolean checked;

    public GuiSettingsCheckBox(String[] text, int buttonID, int posY, int width, boolean checked)
    {
        this(text, buttonID, posY, width, checked, -1, new int[0]);
    }

    public GuiSettingsCheckBox(String[] text, int buttonID, int posY, int width, boolean checked, int dep, int[] vel)
    {
        super(new GuiCheckBox(buttonID, width / 2 + 5, posY, text[0], checked), text, new int[] { 0, 1 }, buttonID, dep, vel);
        this.checked = checked;
    }

    @Override
    public void setOldValue(int oldValue)
    {
        for (int i = 0; i < this.valuearray.length; i++)
        {
            if (this.valuearray[i] == oldValue)
            {
                this.selected = i;
                this.button.displayString = this.textarray[this.selected];
                ((GuiCheckBox) this.button).setIsChecked(i == 0 ? this.checked : !this.checked);
                break;
            }
        }
    }
}