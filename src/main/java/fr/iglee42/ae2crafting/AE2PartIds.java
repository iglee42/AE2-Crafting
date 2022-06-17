package fr.iglee42.ae2crafting;

import net.minecraft.resources.ResourceLocation;

public final class AE2PartIds {

    private static ResourceLocation id(String id) {
        return new ResourceLocation(AE2Crafting.MODID, id);
    }

    public static final ResourceLocation PROCESSOR_TERMINAL = id("processor_terminal");
}
