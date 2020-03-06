package cn.Azlips.module.modules.player;

import java.awt.Color;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import cn.Azlips.Client;
import cn.Azlips.api.EventHandler;
import cn.Azlips.api.events.world.EventMove;
import cn.Azlips.api.value.Numbers;
import cn.Azlips.api.value.Option;
import cn.Azlips.module.Module;
import cn.Azlips.module.ModuleType;
import cn.Azlips.module.modules.movement.Timer;



public class AntiFall
extends Module {
    private Timer timer = new Timer();
    private boolean saveMe;
    public static Numbers<Double> distance = new Numbers<Double>("Distance", "Distance", 5.0, 4.0, 10.0, 1.0);
    public static Option<Boolean> Void = new Option<Boolean>("Void", "Void", true);
    public AntiFall() {
        super("AntiFall", new String[]{"novoid", "antifall"}, ModuleType.World,"虚空回弹");
        this.addValues(this.distance,this.Void);
    }


	    
    @EventHandler
    private void onUpdate(EventMove e) {
        if ((saveMe && timer.delay(150)) || mc.thePlayer.isCollidedVertically) {
            saveMe = false;
            timer.reset();
        }        
        int dist =this.distance.getValue().intValue();
        if (mc.thePlayer.fallDistance >= dist && !Client.getModuleManager().getModuleByName("AirWalk").isEnabled()) {
            if (!((Boolean) this.Void.getValue()) || !isBlockUnder()) {
                if (!saveMe) {
                    saveMe = true;
                    timer.reset();
                }
                mc.thePlayer.fallDistance = 0;
                	 mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX,mc.thePlayer.posY + 12,mc.thePlayer.posZ, false));
                }
            }
    }
    private boolean isBlockUnder() {
    	if(mc.thePlayer.posY < 0)
    		return false;
    	for(int off = 0; off < (int)mc.thePlayer.posY+2; off += 2){
    		AxisAlignedBB bb = mc.thePlayer.boundingBox.offset(0, -off, 0);
    		if(!mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, bb).isEmpty()){
    			return true;
    		}
    	}
    	return false;
    }

}

