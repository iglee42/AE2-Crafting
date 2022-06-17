package fr.iglee42.ae2crafting;

import static fr.iglee42.ae2crafting.AE2CraftingItems.item;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.api.parts.PartModels;
import appeng.core.CreativeTab;
import appeng.core.definitions.AEItems;
import appeng.core.definitions.ItemDefinition;
import appeng.items.parts.PartItem;
import appeng.items.parts.PartModelsHelper;
import fr.iglee42.ae2crafting.part.reporting.ProcessorTerminal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class AE2CraftingPartsDefinition {

    public static final ItemDefinition<PartItem<ProcessorTerminal>> PROCESSOR_TERMINAL = createPart("ME Processor Terminal",AE2PartIds.PROCESSOR_TERMINAL,ProcessorTerminal.class,ProcessorTerminal::new);


    private static <T extends IPart> ItemDefinition<PartItem<T>> createPart(
            String englishName,
            ResourceLocation id,
            Class<T> partClass,
            Function<IPartItem<T>, T> factory) {

        PartModels.registerModels(PartModelsHelper.createModels(partClass));
        return item(englishName, id, props -> new PartItem<>(props, partClass, factory));
    }


    public static void init() {
    }
}
