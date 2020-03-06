package cn.Azlips.module.modules.render;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lwjgl.opengl.GL11;

import com.ibm.icu.text.MessageFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.ResourceLocation;
import cn.Azlips.Client;
import cn.Azlips.api.EventHandler;
import cn.Azlips.api.events.rendering.EventRender2D;
import cn.Azlips.api.value.Numbers;
import cn.Azlips.module.Module;
import cn.Azlips.module.ModuleType;
import cn.Azlips.module.modules.combat.Killaura;
import cn.Azlips.ui.font.FontLoaders;
import cn.Azlips.ui.fontRenderer.UnicodeFontRenderer;
import cn.Azlips.utils.R3DUtil;
import cn.Azlips.utils.RenderUtils;
import cn.Azlips.utils.render.Colors;
import cn.Azlips.utils.render.RenderUtil;

public class TargetHUD
extends Module {
    private static EntityLivingBase lastEnt = null;
	public static boolean shouldMove;
    public static boolean useFont;
	public static Numbers<Double> X = new Numbers<Double>("X", "X", 200.0, 0.0, 1920.0, 10.0);
	public static Numbers<Double> Y = new Numbers<Double>("Y", "Y", 300.0, 0.0, 1080.0, 10.0);
	
    public TargetHUD() {
        super("TargetHud", new String[]{"gui"}, ModuleType.Render,"目标显示");
        this.addValues(this.X, this .Y);
    }
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    float width = 0;
	private float lastHealth;
	private float damageDelt;
	private float damageDeltToPlayer;
	private float lastPlayerHealth;
	private MessageFormat format;
    public void onUpdate() {
    	
    }
    
 @EventHandler
	public void onScreenDraw(EventRender2D er) {
	   if (Killaura.target != null) {
           ScaledResolution scaledResolution = new ScaledResolution(this.mc);
           if (Killaura.target != null) {
               EntityLivingBase target = Killaura.target;
               if (target != this.lastEnt) {
                   this.lastEnt = target;
                   this.lastHealth = target.getHealth();
                   this.damageDelt = 0.0f;
                   this.damageDeltToPlayer = 0.0f;
               }
               if (this.lastHealth != target.getHealth() && target.getHealth() - this.lastHealth < 1.0f) {
                   this.damageDelt = target.getHealth() - this.lastHealth;
                   this.lastHealth = target.getHealth();
               }
               if (!this.mc.thePlayer.isEntityAlive()) {
                   this.lastPlayerHealth = -1.0f;
               }
               if (this.lastPlayerHealth == -1.0f && this.mc.thePlayer.isEntityAlive()) {
                   this.lastPlayerHealth = this.mc.thePlayer.getHealth();
               }
               if (this.lastPlayerHealth != this.mc.thePlayer.getHealth()) {
                   this.damageDeltToPlayer = this.mc.thePlayer.getHealth() - this.lastPlayerHealth;
                   this.lastPlayerHealth = this.mc.thePlayer.getHealth();
               }
               String replaceAll = target.getName().replaceAll("��.", "");
               String string = "HP: " + String.valueOf(this.format.format(target.getHealth()));
               String string2 = "In: " + this.format.format(this.damageDeltToPlayer).replace("-", "") + "/Out: " + this.format.format(this.damageDelt).replace("-", "");
               GL11.glPushMatrix();
               GL11.glTranslatef((float)(scaledResolution.getScaledWidth() / 2), (float)(scaledResolution.getScaledHeight() / 2), 0.0f);
               if (!target.isDead) {
                   float n = Killaura.target.getHealth() / Killaura.target.getMaxHealth() * 100.0f;
                   this.animation = RenderUtil.getAnimationState(this.animation, n, Math.max(10.0, Math.abs(this.animation - n) * 30.0) * 0.3);
                   //RenderUtil.drawArc(1.0f, 1.0f, 15.0, Colors.RED.c, 180, 180.0 + 3.5999999046325684 * this.animation, 5);
                   //RenderUtil.drawArc(1.0f, 1.0f, 16.0, Colors.BLUE.c, 180, 180.0f + 3.6f * (Killaura.target.getTotalArmorValue() * 4), 3);
                   mc.fontRendererObj.drawCenteredString(replaceAll, 0, -30, Colors.WHITE.c);
                   FontLoaders.kiona16.drawCenteredString(string2, 0.0f, 20.0f, Colors.WHITE.c);
                   FontLoaders.kiona16.drawCenteredString(string, 0.0f, 30.0f, Colors.WHITE.c);
               }
               GL11.glPopMatrix();
           }
       }
 }


}
