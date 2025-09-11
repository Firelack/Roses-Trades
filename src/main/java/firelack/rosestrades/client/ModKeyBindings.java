package firelack.rosestrades.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ModKeyBindings {
    public static KeyBinding OPEN_MENU;

    public static void registerKeyBindings() {
        OPEN_MENU = new KeyBinding(
                "key.rosestrades.open_menu", // id
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_M,             // par défaut: touche M
                "category.rosestrades.keys"  // catégorie dans les options
        );

        net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper.registerKeyBinding(OPEN_MENU);
    }
}
