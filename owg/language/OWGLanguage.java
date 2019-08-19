package owg.language;

import net.minecraftforge.fml.common.registry.LanguageRegistry;

@Deprecated
public class OWGLanguage
{
    @Deprecated
    public static void init()
    {
        LanguageRegistry.instance().addStringLocalization("generator.OWG", "Old World Gen");

        LanguageRegistry.instance().addStringLocalization("gui.title", "Old World Generator Settings");
        LanguageRegistry.instance().addStringLocalization("gui.selectGenerator", "Select a world generator:");
        LanguageRegistry.instance().addStringLocalization("gui.generatorSettings", "Generator settings:");
        LanguageRegistry.instance().addStringLocalization("gui.serverConfig", "Server config:");
        LanguageRegistry.instance().addStringLocalization("gui.copystring", "Copy generator-settings to Clipboard");

        LanguageRegistry.instance().addStringLocalization("owg.setting.size", "Size");
        LanguageRegistry.instance().addStringLocalization("owg.setting.small", "Small");
        LanguageRegistry.instance().addStringLocalization("owg.setting.default", "Default");
        LanguageRegistry.instance().addStringLocalization("owg.setting.large", "Large");
        LanguageRegistry.instance().addStringLocalization("owg.setting.snow", "Snow World");
        LanguageRegistry.instance().addStringLocalization("owg.setting.water", "Water");
        LanguageRegistry.instance().addStringLocalization("owg.setting.on", "ON");
        LanguageRegistry.instance().addStringLocalization("owg.setting.off", "OFF");
        LanguageRegistry.instance().addStringLocalization("owg.setting.theme", "Theme");
        LanguageRegistry.instance().addStringLocalization("owg.setting.type", "Type");
        LanguageRegistry.instance().addStringLocalization("owg.setting.layer", "Layer");
        LanguageRegistry.instance().addStringLocalization("owg.setting.village", "Villages");
        LanguageRegistry.instance().addStringLocalization("owg.setting.temple", "Temples");
        LanguageRegistry.instance().addStringLocalization("owg.setting.dungeon", "Dungeons");
        LanguageRegistry.instance().addStringLocalization("owg.setting.stronghold", "Strongholds");
        LanguageRegistry.instance().addStringLocalization("owg.setting.mineshaft", "Mineshafts");
        LanguageRegistry.instance().addStringLocalization("owg.setting.ravine", "Ravines");
        LanguageRegistry.instance().addStringLocalization("owg.setting.monument", "Ocean Monuments");
        LanguageRegistry.instance().addStringLocalization("owg.setting.end", "End portal only");

        LanguageRegistry.instance().addStringLocalization("owg.biomes.biomes", "Biomes");
        LanguageRegistry.instance().addStringLocalization("owg.biomes.original", "Original");
        LanguageRegistry.instance().addStringLocalization("owg.biomes.vanilla", "Vanilla");
        LanguageRegistry.instance().addStringLocalization("owg.biomes.all", "Vanilla + Mods");

        LanguageRegistry.instance().addStringLocalization("owg.theme.default", "Default");
        LanguageRegistry.instance().addStringLocalization("owg.theme.snow", "Snow");
        LanguageRegistry.instance().addStringLocalization("owg.theme.jungle", "Jungle");
        LanguageRegistry.instance().addStringLocalization("owg.theme.tropical", "Tropical");
        LanguageRegistry.instance().addStringLocalization("owg.theme.paradise", "Paradise");
        LanguageRegistry.instance().addStringLocalization("owg.theme.hell", "Hell");
        LanguageRegistry.instance().addStringLocalization("owg.theme.woods", "Woods");

        LanguageRegistry.instance().addStringLocalization("owg.type.island", "Island");
        LanguageRegistry.instance().addStringLocalization("owg.type.floating", "Floating");
        LanguageRegistry.instance().addStringLocalization("owg.type.inland", "Inland");

        LanguageRegistry.instance().addStringLocalization("owg.BETA173", "Beta 1.7.3");
        LanguageRegistry.instance().addStringLocalization("owg.ALPHA12", "Alpha 1.2.0");
        LanguageRegistry.instance().addStringLocalization("owg.ALPHA11", "Alpha 1.1.0");
        LanguageRegistry.instance().addStringLocalization("owg.INFDEV", "Infdev");
        LanguageRegistry.instance().addStringLocalization("owg.INDEV", "Indev");
    }
}