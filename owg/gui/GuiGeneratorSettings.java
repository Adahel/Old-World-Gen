package owg.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import owg.OWG;
import owg.config.ConfigOWG;
import owg.data.DecodeGeneratorString;
import owg.generatortype.GeneratorType;

@SideOnly(Side.CLIENT)
public class GuiGeneratorSettings extends GuiScreen
{
    private final GuiCreateWorld createWorldGui;

    public GuiButton BUTTON_DONE;

    public int generatorSelected = -1;
    public ArrayList<GuiGeneratorButton> generators;
    public ArrayList<GuiSettingsButton> settings;

    public boolean decodebool;
    public boolean setremember;
    public int[] rememberSettings;

    public boolean hasSettings = false;

    public String[] translatedDrawStrings;

    public GuiGeneratorSettings(GuiCreateWorld gcw, String gs)
    {
        this.createWorldGui = gcw;
        this.decodebool = true;

        this.translatedDrawStrings = new String[4];
        this.translatedDrawStrings[0] = StatCollector.translateToLocal("gui.title");
        this.translatedDrawStrings[1] = StatCollector.translateToLocal("gui.selectGenerator");
        this.translatedDrawStrings[2] = StatCollector.translateToLocal("gui.generatorSettings");
        this.translatedDrawStrings[3] = StatCollector.translateToLocal("gui.serverConfig");
    }

    @Override
    public void initGui()
    {
        this.buttonList.add(this.BUTTON_DONE = new GuiButton(0, this.width / 2 - 155, this.height - 24, 150, 20, StatCollector.translateToLocal("gui.done")));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 24, 150, 20, StatCollector.translateToLocal("gui.cancel")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 155, this.height - 48, 310, 20, StatCollector.translateToLocal("gui.copystring")));

        System.out.println("INIT");

        if (this.decodebool)
        {
            this.decodebool = false;
            this.decodeString(this.createWorldGui.chunkProviderSettingsJson);
            this.dependencies();
        }
        else
        {
            this.createList();

            for (int i = 0; i < this.generators.size(); i++)
            {
                this.generators.get(i).button.enabled = true;
                if (this.generators.get(i).generatorID == this.generatorSelected)
                {
                    this.generators.get(i).button.enabled = false;
                }
            }
            this.selectGenerator();
        }
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 0) // DONE
        {
            this.createWorldGui.chunkProviderSettingsJson = this.createString();
            this.mc.displayGuiScreen(this.createWorldGui);
        }
        else if (button.id == 1) // CANCEL
        {
            this.mc.displayGuiScreen(this.createWorldGui);
        }
        else if (button.id == 2) // COPY SETTINGS
        {
            String copy = this.createString();
            System.out.println(copy);
            setClipboardString(copy);
        }
        else if (button.id >= 10 && button.id < 20)
        {
            for (int i = 0; i < this.generators.size(); i++)
            {
                this.generators.get(i).button.enabled = true;
                if (this.generators.get(i).button.id == button.id)
                {
                    this.generators.get(i).button.enabled = false;
                    this.generatorSelected = this.generators.get(i).generatorID;
                }
            }
            this.selectGenerator();
        }
        else if (button.id >= 20 && button.id < 30)
        {
            for (int i = 0; i < this.settings.size(); i++)
            {
                if (this.settings.get(i).button.id == button.id)
                {
                    this.settings.get(i).click();
                }
            }
            this.dependencies();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();

        // title
        String title = this.translatedDrawStrings[0];
        this.drawString(this.fontRendererObj, title, (int) Math.floor(this.width / 2) - (int) Math.floor(this.fontRendererObj.getStringWidth(title) / 2), 10,
                16777215);

        // select generator
        this.drawString(this.fontRendererObj, this.translatedDrawStrings[1], this.width / 2 - 155 + 1, 40, 10526880);

        if (this.hasSettings)
        {
            this.drawString(this.fontRendererObj, this.translatedDrawStrings[2], this.width / 2 + 6, 40, 10526880);
        }

        this.drawString(this.fontRendererObj, this.translatedDrawStrings[3], this.width / 2 - 155 + 1, 155, 16777215);
        this.drawString(this.fontRendererObj, "level-type=" + OWG.MODID, this.width / 2 - 155 + 1, 172, 10526880);
        this.drawString(this.fontRendererObj, "generator-settings=" + this.createString(), this.width / 2 - 155 + 1, 182, 10526880);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void createList()
    {
        if (this.generators != null)
        {
            for (int i = 0; i < this.generators.size(); i++)
            {
                this.buttonList.remove(this.generators.get(i).button);
            }
        }

        this.generators = new ArrayList<GuiGeneratorButton>();
        int count = 0;
        for (int g = 0; g < GeneratorType.generatortypes.length; g++)
        {
            if (GeneratorType.generatortypes[g] != null)
            {
                this.generators.add(new GuiGeneratorButton(StatCollector.translateToLocal("owg." + GeneratorType.generatortypes[g].GetName()), g, count + 10,
                        50 + (20 * count), this.width));
                this.buttonList.add(this.generators.get(this.generators.size() - 1).button);
                count++;
            }
        }
    }

    public void dependencies()
    {
        for (int i = 0; i < this.settings.size(); i++)
        {
            if (this.settings.get(i).dependencie > -1)
            {
                this.settings.get(i).button.visible = false;
                for (int depvalue : this.settings.get(i).depvalues)
                {
                    if (this.settings.get(this.settings.get(i).dependencie - 20).selected == depvalue)
                    {
                        this.settings.get(i).button.visible = true;
                    }
                }
            }
        }
    }

    public void selectGenerator()
    {
        if (this.generatorSelected > -1)
        {
            this.BUTTON_DONE.enabled = true;
        }
        else
        {
            this.BUTTON_DONE.enabled = false;
        }

        if (this.settings != null)
        {
            for (int i = 0; i < this.settings.size(); i++)
            {
                this.buttonList.remove(this.settings.get(i).button);
            }
        }
        this.settings = new ArrayList<GuiSettingsButton>();

        if (this.generatorSelected > -1)
        {
            this.hasSettings = GeneratorType.generatortypes[this.generatorSelected].getSettings(this);
        }

        for (int s = 0; s < this.settings.size(); s++)
        {
            this.buttonList.add(this.settings.get(s).button);
        }

        this.dependencies();

        if (this.setremember)
        {
            for (int rs = 0; rs < this.settings.size(); rs++)
            {
                this.settings.get(rs).setOldValue(this.rememberSettings[rs]);
            }
            this.setremember = false;
        }
    }

    public void decodeString(String decodestring)
    {
        String[] genstring = decodestring.split("#");
        String[] gensettings = new String[0];
        if (genstring.length > 1 && genstring[1].length() > 0)
        {
            gensettings = genstring[1].split("&");
        }
        else
        {
            gensettings = new String[0];
        }

        int n = DecodeGeneratorString.getGeneratorIDFromName(genstring[0]);
        if (n > -1)
        {
            this.createList();
            this.generatorSelected = n;

            for (int i = 0; i < this.generators.size(); i++)
            {
                this.generators.get(i).button.enabled = true;
                if (this.generators.get(i).generatorID == this.generatorSelected)
                {
                    this.generators.get(i).button.enabled = false;
                }
            }
            this.selectGenerator();

            for (int i = 0; i < this.settings.size(); i++)
            {
                if (i < gensettings.length)
                {
                    this.settings.get(i).setOldValue(Integer.parseInt(gensettings[i]));
                }
            }
        }
        else
        {
            this.createList();
            this.generatorSelected = -1;
            this.selectGenerator();
        }
    }

    public String createString()
    {
        if (this.generatorSelected > -1 && this.generatorSelected < GeneratorType.generatortypes.length)
        {
            String genstring = GeneratorType.generatortypes[this.generatorSelected].GetName() + "#";
            for (int s = 0; s < this.settings.size(); s++)
            {
                genstring += s == 0 ? "" : "&";
                genstring += this.settings.get(s).valuearray[this.settings.get(s).selected];
            }

            return genstring;
        }
        else
        {
            return ConfigOWG.defaultGen;
        }
    }
}