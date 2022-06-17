package fr.iglee42.ae2crafting.datagen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import fr.iglee42.ae2crafting.AE2CraftingItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEEntities;
import appeng.core.definitions.AEItems;
import appeng.core.localization.ButtonToolTips;
import appeng.core.localization.GuiText;
import appeng.core.localization.InGameTooltip;
import appeng.core.localization.ItemModText;
import appeng.core.localization.LocalizationEnum;
import appeng.core.localization.PlayerMessages;
import appeng.datagen.providers.IAE2DataProvider;

public class AE2CraftingLocalizationProvider implements IAE2DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Map<String, String> localizations = new HashMap<>();

    private final DataGenerator generator;

    private boolean wasSaved = false;

    public AE2CraftingLocalizationProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public final void run(HashCache cache) {

        for (var item : AE2CraftingItems.getItems()) {
            add("item.ae2." + item.id().getPath(), item.getEnglishName());
        }

        generateLocalizations();

        save(cache, localizations);
    }

    public <T extends Enum<T> & LocalizationEnum> void addEnum(Class<T> localizedEnum) {
        for (var enumConstant : localizedEnum.getEnumConstants()) {
            add(enumConstant.getTranslationKey(), enumConstant.getEnglishText());
        }
    }

    public Component component(String key, String text) {
        add(key, text);
        return new TranslatableComponent(key);
    }

    public void add(String key, String text) {
        Preconditions.checkState(!wasSaved, "Cannot add more translations after they were already saved");
        var previous = localizations.put(key, text);
        if (previous != null) {
            throw new IllegalStateException("Localization key " + key + " is already translated to: " + previous);
        }
    }

    private void generateLocalizations() {

    }

    private void save(HashCache cache, Map<String, String> localizations) {
        wasSaved = true;

        try {
            var path = this.generator.getOutputFolder().resolve("assets/ae2crafting/lang/en_us.json");

            // Dump the translation in ascending order
            var sorted = new TreeMap<>(localizations);
            var jsonLocalization = new JsonObject();
            for (var entry : sorted.entrySet()) {
                jsonLocalization.addProperty(entry.getKey(), entry.getValue());
            }

            DataProvider.save(GSON, cache, jsonLocalization, path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "Localization (en_us)";
    }
}