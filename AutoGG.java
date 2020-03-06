
package cn.Azlips.module.modules.world;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import cn.Azlips.Client;
import cn.Azlips.api.EventHandler;
import cn.Azlips.api.events.misc.EventChat;
import cn.Azlips.api.value.Option;
import cn.Azlips.module.Module;
import cn.Azlips.module.ModuleType;
import cn.Azlips.module.modules.movement.Speed;

public class AutoGG
extends Module {
    public AutoGG() {
        super("AutoGG", new String[]{"AutoGG"}, ModuleType.World,"自动发GG");
    }
@EventHandler
private void onChat(EventChat e)
{
  if (e.getMessage().contains("Winner"))
  {
    e.setCancelled(true);
    Minecraft.thePlayer.sendChatMessage("["+Client.name+"]Azlips完全免费客户端 无付费版本,加群:86323867获取,完全免费!" );
    }
}
  }


