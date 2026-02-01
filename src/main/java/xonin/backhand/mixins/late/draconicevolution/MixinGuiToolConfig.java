package xonin.backhand.mixins.late.draconicevolution;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import com.brandon3055.draconicevolution.client.gui.componentguis.GUIToolConfig;
import com.brandon3055.draconicevolution.client.utils.guicomponents.GUIBase;

@Mixin(value = GUIToolConfig.class, remap = false)
public abstract class MixinGuiToolConfig extends GUIBase {

    @Shadow(remap = false)
    public EntityPlayer player;

    public MixinGuiToolConfig(Container container, int xSize, int ySize) {
        super(container, xSize, ySize);
    }

    @ModifyConstant(method = "addDependentComponents", constant = @Constant(intValue = 39), remap = false)
    private int backhand$addDependentComponents(int constant) {
        if (player != null && player.inventory != null) {
            return player.inventory.getSizeInventory() - 1;
        }
        return constant;
    }
}
