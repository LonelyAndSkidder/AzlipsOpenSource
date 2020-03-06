package cn.Azlips.module.modules.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import cn.Azlips.api.EventHandler;
import cn.Azlips.api.events.world.EventPreUpdate;
import cn.Azlips.module.Module;
import cn.Azlips.module.ModuleType;

public class InvMove extends Module{

	public InvMove() {
		super("InvMove", new String[] {}, ModuleType.Movement,"物品栏行走");
	}
	
	@EventHandler
	public void onUpdate(EventPreUpdate event) {
		mc.thePlayer.isUsingItem();
		if (this.mc.currentScreen != null && !(this.mc.currentScreen instanceof GuiChat)) {
			KeyBinding[] key = { this.mc.gameSettings.keyBindForward, this.mc.gameSettings.keyBindBack, this.mc.gameSettings.keyBindLeft, this.mc.gameSettings.keyBindRight, this.mc.gameSettings.keyBindSprint, this.mc.gameSettings.keyBindJump };
			KeyBinding[] array;
			for (int length = (array = key).length, i = 0; i < length; ++i) {
				KeyBinding b = array[i];
				KeyBinding.setKeyBindState(b.getKeyCode(), Keyboard.isKeyDown(b.getKeyCode()));
			}
		}
	}
}
