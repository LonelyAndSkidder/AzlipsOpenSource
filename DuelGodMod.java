package cn.Azlips.module.modules.player;

import java.awt.Color;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import cn.Azlips.Client;
import cn.Azlips.api.EventHandler;
import cn.Azlips.api.events.world.EventMove;
import cn.Azlips.api.value.Numbers;
import cn.Azlips.api.value.Option;
import cn.Azlips.module.Module;
import cn.Azlips.module.ModuleType;
import cn.Azlips.module.modules.combat.Killaura;
import cn.Azlips.module.modules.movement.Timer;



public class DuelGodMod
extends Module {
    private Timer timer = new Timer();
    public DuelGodMod() {
        super("AntiMoreDamage", new String[]{"DuelGodMod", "AntiDamage"}, ModuleType.World,"竞技场打不死的那种哦");
    }


	public void damagePlayer(int damage) {
		if (damage < 1)
			damage = 1;
		if (damage > MathHelper.floor_double(mc.thePlayer.getMaxHealth()))
			damage = MathHelper.floor_double(mc.thePlayer.getMaxHealth());

		  for (int i = 0; i < 10; i++) 
              mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));

          double fallDistance = 3.0125; 
          while (fallDistance > 0) {
              mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0624986421, mc.thePlayer.posZ, false));
              mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0625      , mc.thePlayer.posZ, false));
              mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0624986421, mc.thePlayer.posZ, false));
              mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0000013579, mc.thePlayer.posZ, false));
              fallDistance -= 0.0624986421;
          }
          mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
	}
	
    @EventHandler
    private void onUpdate(EventMove e) {
    	if(mc.thePlayer.onGround && mc.thePlayer.hurtTime==0 && Killaura.target != null) {
    		damagePlayer(1);
    	}
    	
    }


}

