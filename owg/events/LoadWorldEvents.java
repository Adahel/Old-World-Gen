package owg.events;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import owg.OWG;
import owg.generatortype.GeneratorType;

public class LoadWorldEvents
{
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        if (event.world.getWorldType() == OWG.worldtype)
        {
            if (GeneratorType.INFDEV.equals(GeneratorType.currentGenerator) || GeneratorType.ALPHA11.equals(GeneratorType.currentGenerator))
            {
                if (GeneratorType.trySetting(0, 1) == 1)
                {
                    event.world.getWorldInfo().setRaining(true);
                    event.world.getWorldInfo().setThundering(false);
                }
            }
            else if (GeneratorType.INDEV.equals(GeneratorType.currentGenerator))
            {
                if (GeneratorType.trySetting(0, 4) == 4)
                {
                    event.world.getWorldInfo().setRaining(true);
                    event.world.getWorldInfo().setThundering(false);
                }
            }
        }
    }
}