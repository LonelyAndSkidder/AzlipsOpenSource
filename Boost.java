/*
 * Decompiled with CFR 0_132.
 */
package cn.Azlips.module.modules.movement;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.Timer;
import cn.Azlips.api.EventHandler;
import cn.Azlips.api.events.world.EventPreUpdate;
import cn.Azlips.module.Module;
import cn.Azlips.module.ModuleType;
import cn.Azlips.utils.PlayerUtil;
import cn.Azlips.utils.TimerUtil;

public class Boost
extends Module {
    private TimerUtil timer = new TimerUtil();

    public Boost() {
        super("Boost", new String[]{"boost"}, ModuleType.Movement,"段加速");
        this.setColor(new Color(216, 253, 100).getRGB());
    }

    @EventHandler
    public void onUpdate(EventPreUpdate event) {
        this.mc.timer.timerSpeed =  2.5f;
        if (this.mc.thePlayer.ticksExisted % 30 == 0) {
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        this.timer.reset();
        this.mc.timer.timerSpeed = 1.0f;
    }
}

