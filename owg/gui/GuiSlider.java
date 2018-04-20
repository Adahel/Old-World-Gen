package owg.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSlider extends GuiButton
{
    public float sliderValue = 1.0F;
    public boolean dragging;

    public GuiSettingsSlider settings;
    public int max;

    public GuiSlider(int id, int x, int y)
    {
        super(id, x, y, 150, 20, "");
    }

    public void setSlider(GuiSettingsSlider s, int def)
    {
        this.settings = s;
        this.max = this.settings.valuearray.length - 1;
        this.sliderValue = Math.round(def) / (float) this.max;

        this.setText();
    }

    public void setText()
    {
        int pos = Math.round(this.sliderValue * this.max);

        this.displayString = this.settings.textarray[pos];
        this.settings.selected = this.settings.valuearray[pos];
    }

    @Override
    public int getHoverState(boolean par1)
    {
        return 0;
    }

    @Override
    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.visible)
        {
            if (this.dragging)
            {
                this.sliderValue = (float) (par2 - (this.xPosition + 4)) / (float) (this.width - 8);

                if (this.sliderValue < 0.0F)
                {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F)
                {
                    this.sliderValue = 1.0F;
                }
            }

            this.setText();

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    @Override
    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
        if (super.mousePressed(par1Minecraft, par2, par3))
        {
            this.sliderValue = (float) (par2 - (this.xPosition + 4)) / (float) (this.width - 8);

            if (this.sliderValue < 0.0F)
            {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F)
            {
                this.sliderValue = 1.0F;
            }

            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void mouseReleased(int par1, int par2)
    {
        this.dragging = false;

        this.sliderValue = Math.round(this.sliderValue * this.max) / (float) this.max;

        this.setText();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)), this.yPosition, 0, 66, 4, 20);
        this.drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
    }
}
