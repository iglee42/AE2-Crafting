package fr.iglee42.ae2crafting.datagen;

import fr.iglee42.ae2crafting.AE2Crafting;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = AE2Crafting.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AE2CraftingData {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent dataEvent) {
        onGatherData(dataEvent.getGenerator(), dataEvent.getExistingFileHelper());
    }

    public static void onGatherData(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        var localization = new AE2CraftingLocalizationProvider(generator);

        generator.addProvider(localization);
    }
}
