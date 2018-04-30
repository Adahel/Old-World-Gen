package owg.data;

import owg.generatortype.GeneratorType;

public class DecodeGeneratorString
{
    public static void decode(String generatorString)
    {
        String[] genstring = generatorString.split("#");
        GeneratorType gentype = getGeneratorFromName(genstring[0]);

        if (gentype != null) // GENERATOR NAME
        {
            GeneratorType.currentGenerator = gentype;
        }
        if (genstring.length > 1 && genstring[1].length() > 0) // GENERATOR SETTINGS
        {
            String[] settingstring = genstring[1].split("&");
            GeneratorType.currentSettings = new int[settingstring.length];
            for (int i = 0; i < settingstring.length; i++)
            {
                GeneratorType.currentSettings[i] = Integer.parseInt(settingstring[i]);
            }
        }
    }

    public static GeneratorType getGeneratorFromName(String name)
    {
        for (GeneratorType generatortype : GeneratorType.generatortypes)
        {
            if (generatortype != null)
            {
                if (generatortype.GetName().equals(name))
                {
                    return generatortype;
                }
            }
        }
        return null;
    }

    public static int getGeneratorIDFromName(String name)
    {
        for (GeneratorType generatortype : GeneratorType.generatortypes)
        {
            if (generatortype != null)
            {
                if (generatortype.GetName().equals(name))
                {
                    return generatortype.GetID();
                }
            }
        }
        return -1;
    }
}