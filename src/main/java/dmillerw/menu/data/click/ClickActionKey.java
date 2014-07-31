package dmillerw.menu.data.click;

import dmillerw.menu.handler.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

/**
 * @author dmillerw
 */
public class ClickActionKey implements ClickAction.IClickAction {

    public final String key;

    public final boolean toggle;

    public ClickActionKey(String key, boolean toggle) {
        this.key = key;
        this.toggle = toggle;
    }

    public KeyBinding getKeyBinding() {
        for (KeyBinding binding : Minecraft.getMinecraft().gameSettings.keyBindings) {
            if (binding.getKeyDescription().equalsIgnoreCase(key)) {
                return binding;
            }
        }
        return null;
    }

    @Override
    public ClickAction getClickAction() {
        return ClickAction.KEYBIND;
    }

    @Override
    public boolean onClicked() {
        KeyBinding binding = getKeyBinding();
        if (binding != null) {
            if (toggle) {
                KeyboardHandler.INSTANCE.toggleKey(binding);
            } else {
                KeyboardHandler.INSTANCE.fireKey(binding);
            }
        }
        return true;
    }
}
