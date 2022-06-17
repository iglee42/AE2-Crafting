package fr.iglee42.ae2crafting;

import appeng.core.CreativeTab;
import appeng.core.definitions.ItemDefinition;
import com.google.common.base.Preconditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class AE2CraftingItems {

    private static final List<ItemDefinition<?>> ITEMS = new ArrayList<>();


    public static List<ItemDefinition<?>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }


    static <T extends Item> ItemDefinition<T> item(String name, ResourceLocation id,
                                                   Function<Item.Properties, T> factory) {
        return item(name, id, factory, CreativeTab.INSTANCE);
    }

    static <T extends Item> ItemDefinition<T> item(String name, ResourceLocation id,
                                                   Function<Item.Properties, T> factory,
                                                   CreativeModeTab group) {

        Item.Properties p = new Item.Properties().tab(group);

        T item = factory.apply(p);

        item.setRegistryName(id);

        ItemDefinition<T> definition = new ItemDefinition<>(name, id, item);

        if (group == CreativeTab.INSTANCE) {
            CreativeTab.add(definition);
        }

        ITEMS.add(definition);

        return definition;
    }

    // Used to control in which order static constructors are called
    public static void init(IForgeRegistry<Item> registry) {

        for (var definition : getItems()) {
            registry.register(definition.asItem());
        }
    }

}
