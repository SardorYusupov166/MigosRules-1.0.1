package dev.migosrules;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public final class RulesAddon implements ClientModInitializer {
    private static final KeyBinding.Category CATEGORY = KeyBinding.Category.create(
        Identifier.of("migosrules", "main")
    );

    private static final KeyBinding OPEN_RULES = KeyBindingHelper.registerKeyBinding(
        new KeyBinding("key.migosrules.open", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, CATEGORY)
    );

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_RULES.wasPressed()) {
                if (client.currentScreen == null) {
                    RulesRepository.load();
                    client.setScreen(new RulesScreen(null));
                }
            }
        });
    }
}
