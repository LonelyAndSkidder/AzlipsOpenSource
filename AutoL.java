
package cn.Azlips.module.modules.world;
import net.minecraft.client.Minecraft;
import cn.Azlips.Client;
import cn.Azlips.api.EventHandler;
import cn.Azlips.api.events.misc.EventChat;
import cn.Azlips.api.events.world.EventPreUpdate;
import cn.Azlips.api.value.Option;
import cn.Azlips.module.Module;
import cn.Azlips.module.ModuleType;
import cn.Azlips.module.modules.combat.Killaura;
import cn.Azlips.module.modules.movement.Flight;
import cn.Azlips.module.modules.movement.Flight.FlightMode;
import cn.Azlips.module.modules.render.HUD;
import cn.Azlips.ui.Notification.Type;
import cn.Azlips.utils.TimerUtil;

public class AutoL
extends Module {
	public static Option<Boolean> autowdr = new Option<Boolean>("AutoWDR", "AutoWDR", false);
    public AutoL() {
        super("AutoL", new String[]{"AutoL"}, ModuleType.World,"自动扣L");
        this.addValues(this.autowdr);
    }
	private TimerUtil timer = new TimerUtil();
    int num=0;
    public boolean Start =false;
    
    private void onUpdate(EventPreUpdate event) {
    	if(Start == true) {
    		Flight Flight = new Flight();
    				Flight.setEnabled(true);
    		Flight.mode.setValue(FlightMode.Vanilla);
    		if(timer.hasReached(1000L)) {
    			num++;
    			if(num>=4.9) {
    				new Flight().setEnabled(false);
    			}
    			timer.reset();
    		}
    	}
    	
    	
    }
    

    
@EventHandler
private void onChat(EventChat e)
{

      
  if (e.getMessage().contains(Killaura.target.getName()+"��"))
  {
    Minecraft.thePlayer.sendChatMessage("["+Client.name+"]"+Killaura.target.getName() + " Azlips免费客户端,加群:863238677获取" );
    if(this.autowdr.getValue().booleanValue()) {
        Minecraft.thePlayer.sendChatMessage("/wdr "+Killaura.target.getName()+" ka fly speed nokb reach jesus");
    }
    e.setCancelled(true);
  }
  }
}


