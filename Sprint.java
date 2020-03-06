
package cn.Azlips.module.modules.movement;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.FoodStats;
import cn.Azlips.api.EventHandler;
import cn.Azlips.api.events.world.EventPreUpdate;
import cn.Azlips.api.value.Option;
import cn.Azlips.api.value.Value;
import cn.Azlips.module.Module;
import cn.Azlips.module.ModuleType;

public class Sprint
extends Module {
    private Option<Boolean> omni = new Option<Boolean>("Omni-Directional", "omni", true);

    public Sprint() {
        super("Sprint", new String[]{"run"}, ModuleType.Movement,"强制疾跑");
        this.setColor(new Color(158, 205, 125).getRGB());
        this.addValues(this.omni);
    }

    @EventHandler
    private void onUpdate(EventPreUpdate event) {
        if (this.mc.thePlayer.getFoodStats().getFoodLevel() > 6 && this.omni.getValue() != false ? this.mc.thePlayer.moving() : this.mc.thePlayer.moveForward > 0.0f) {
            this.mc.thePlayer.setSprinting(true);
        }
    }
}

