package cn.Azlips.module.modules.world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cn.Azlips.Client;
import cn.Azlips.api.EventHandler;
import cn.Azlips.api.events.rendering.EventRender3D;
import cn.Azlips.api.events.world.EventPreUpdate;
import cn.Azlips.module.Module;
import cn.Azlips.module.ModuleType;
import cn.Azlips.module.modules.combat.AntiBot;
import cn.Azlips.module.modules.player.Teams;
import cn.Azlips.ui.Notification.Type;
import cn.Azlips.utils.Helper;
import cn.Azlips.utils.PlayerUtil;
import cn.Azlips.utils.TimerUtil;
import cn.Azlips.utils.render.Colors;
import cn.Azlips.utils.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class MurderFinder extends Module{
	public MurderFinder() {
		super("MurderFinder",new String[] {},ModuleType.World,"杀手寻找");
	}
	
    public static ArrayList<EntityPlayer> Murders;
    public static List<EntityPlayer> invalid = new ArrayList();
    private static List<EntityPlayer> removed = new ArrayList();
	public EntityPlayer Killer=null;
	
    @EventHandler
    public void onRender(EventRender3D event) {
    	if(Killer != null) {
    		doBoxESP(event);
    	}
    }
	
	
    @EventHandler
    public void onUpdate(final EventPreUpdate event) {
        for (final Entity entity2 : Minecraft.theWorld.getLoadedEntityList()) {
            if (entity2 instanceof EntityPlayer) {
            	EntityPlayer Player = (EntityPlayer) entity2;
            	if(hasSword(Player)) {
            		if(Killer == null) {
            		Helper.sendMessageWithoutPrefix("找到杀手:"+Player.getName());
            		}
            		Killer = Player;
            		
            	}
            }
        }
      }
    
    private void doBoxESP(EventRender3D event) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        for (Object o2 : this.mc.theWorld.loadedEntityList) {
            if (!(o2 instanceof EntityPlayer) || o2 == Minecraft.thePlayer) continue;
            EntityPlayer ent = (EntityPlayer)o2;
            
            if(ent == Killer) {
            RenderUtil.entityESPBox2(ent, new Color(255,5,5,255), event);
            }
        }
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    
		private boolean hasSword(EntityPlayer en) {
			ItemStack handItem = en.getCurrentEquippedItem();
			Item handItem2 = en.getCurrentEquippedItem().getItem();
			if (!handItem.equals(Item.getItemById(266)) && 
					!handItem.equals(Item.getItemById(261)) && 
					!handItem.equals(Item.getItemById(262)) && 
					!handItem.equals(Item.getItemById(345)) && 
					!handItem.equals(Item.getItemById(402)) && 
					!(handItem2 instanceof ItemPotion) && 
					!(handItem2 instanceof ItemBlock) && 
					!handItem.equals(Item.getItemById(355))) {
				return true;
			}
			
		return false;
			}
}


