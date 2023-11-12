package me.oganesson.emissiver;

import me.oganesson.emissiver.model.MetadataSectionCTM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy {

    public void onPreLoad() {

        if (!Loader.isModLoaded("ctm")) {
            Minecraft.getMinecraft().metadataSerializer.registerMetadataSectionType(new MetadataSectionCTM.Serializer(), MetadataSectionCTM.class);
            //MinecraftForge.EVENT_BUS.register(CustomTextureModelHandler.INSTANCE);
            //((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(CustomTextureModelHandler.INSTANCE);
        }
    }

}
