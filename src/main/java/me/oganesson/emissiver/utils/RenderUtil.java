package me.oganesson.emissiver.utils;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

@SideOnly(Side.CLIENT)
public class RenderUtil {

    public static boolean updateFBOSize(Framebuffer fbo, int width, int height) {
        if (fbo.framebufferWidth != width || fbo.framebufferHeight != height) {
            fbo.createBindFramebuffer(width, height);
            return true;
        }
        return false;
    }

    public static void hookDepthBuffer(Framebuffer fbo, int depthBuffer) {
        //Hook DepthBuffer
        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, fbo.framebufferObject);
        if (fbo.isStencilEnabled()) {
            OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, org.lwjgl.opengl.EXTFramebufferObject.GL_DEPTH_ATTACHMENT_EXT, OpenGlHelper.GL_RENDERBUFFER, depthBuffer);
            OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, org.lwjgl.opengl.EXTFramebufferObject.GL_STENCIL_ATTACHMENT_EXT, OpenGlHelper.GL_RENDERBUFFER, depthBuffer);
        } else {
            OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, depthBuffer);
        }
    }

    public static void hookDepthTexture(Framebuffer fbo, int depthTexture) {
        //Hook DepthTexture
        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, fbo.framebufferObject);
        if (fbo.isStencilEnabled()) {
            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, GL30.GL_DEPTH_STENCIL_ATTACHMENT, GL11.GL_TEXTURE_2D, depthTexture, 0);
        } else {
            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, depthTexture, 0);
        }
    }
}
