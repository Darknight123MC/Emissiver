package me.oganesson.emissiver;

import me.oganesson.emissiver.utils.BloomEffectUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = "emissiver"
)
public class Emissiver {

    public static ResourceLocation asId(String path) {
        return new ResourceLocation("emissiver", path);
    }

    @SidedProxy(
            clientSide = "me.oganesson.emissiver.ClientProxy",
            modId = "emissiver"
    )
    public static ClientProxy proxy;

    public Emissiver() {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            BloomEffectUtil.init();
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.onPreLoad();
    }

}
