package fr.iglee42.ae2crafting.part.reporting;


import appeng.api.config.SecurityPermissions;
import appeng.api.parts.IPartItem;
import appeng.api.parts.IPartModel;
import appeng.items.parts.PartModels;
import appeng.menu.me.common.MEStorageMenu;
import appeng.menu.me.crafting.CraftingStatusMenu;
import appeng.menu.me.items.CraftingTermMenu;
import appeng.parts.PartModel;
import appeng.parts.reporting.AbstractTerminalPart;
import appeng.util.Platform;
import fr.iglee42.ae2crafting.AE2Crafting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

public class ProcessorTerminal extends AbstractTerminalPart {

    @PartModels
    public static final ResourceLocation MODEL_OFF = new ResourceLocation(AE2Crafting.MODID, "part/processor_terminal_off");
    @PartModels
    public static final ResourceLocation MODEL_ON = new ResourceLocation(AE2Crafting.MODID, "part/processor_terminal_on");

    public static final IPartModel MODELS_OFF = new PartModel(MODEL_BASE, MODEL_OFF, MODEL_STATUS_OFF);
    public static final IPartModel MODELS_ON = new PartModel(MODEL_BASE, MODEL_ON, MODEL_STATUS_ON);
    public static final IPartModel MODELS_HAS_CHANNEL = new PartModel(MODEL_BASE, MODEL_ON, MODEL_STATUS_HAS_CHANNEL);

    public ProcessorTerminal(IPartItem<?> partItem) {
        super(partItem);
    }

    @Override
    public MenuType<?> getMenuType(Player p) {
        return CraftingStatusMenu.TYPE;
    }

    @Override
    public IPartModel getStaticModels() {
        return this.selectModel(MODELS_OFF, MODELS_ON, MODELS_HAS_CHANNEL);
    }

}
