package me.oganesson.emissiver;

import net.minecraftforge.common.config.Config;

@Config(modid = "emissiver", name = "Emissiver")
public class ConfigHandler {

    @Config.Comment("Config options for Shaders and Post-processing Effects")
    @Config.Name("Shader Options")
    public static ShaderOptions shader = new ShaderOptions();

    @Config.Comment({"Whether to hook depth texture. Has no effect on performance, but if there is a problem with rendering, try disabling it.", "Default: true"})
    public static boolean hookDepthTexture = true;

    public static class ShaderOptions {


        @Config.Comment({"Whether or not to enable Emissive Textures with bloom effect.", "Default: true"})
        public boolean emissiveTexturesBloom = true;

        @Config.Comment({"Bloom Algorithm", "0 - Simple Gaussian Blur Bloom (Fast)", "1 - Unity Bloom", "2 - Unreal Bloom", "Default: 2"})
        @Config.RangeInt(min = 0, max = 2)
        @Config.SlidingOption
        public int bloomStyle = 2;

        @Config.Comment({"The brightness after bloom should not exceed this value. It can be used to limit the brightness of highlights " +
                "(e.g., daytime).", "OUTPUT = BACKGROUND + BLOOM * strength * (base + LT + (1 - BACKGROUND_BRIGHTNESS)*({HT}-LT)))", "This value should be greater than lowBrightnessThreshold.", "Default: 0.5"})
        @Config.RangeDouble(min = 0)
        public double highBrightnessThreshold = 0.5;

        @Config.Comment({"The brightness after bloom should not smaller than this value. It can be used to limit the brightness of dusky parts " +
                "(e.g., night/caves).", "OUTPUT = BACKGROUND + BLOOM * strength * (base + {LT} + (1 - BACKGROUND_BRIGHTNESS)*(HT-{LT})))", "This value should be smaller than highBrightnessThreshold.", "Default: 0.2"})
        @Config.RangeDouble(min = 0)
        public double lowBrightnessThreshold = 0.2;

        @Config.Comment({"The base brightness of the bloom.", "It is similar to strength", "This value should be smaller than highBrightnessThreshold.", "OUTPUT = BACKGROUND + BLOOM * strength * ({base} + LT + (1 - BACKGROUND_BRIGHTNESS)*(HT-LT)))", "Default: 0.1"})
        @Config.RangeDouble(min = 0)
        public double baseBrightness = 0.1;

        @Config.Comment({"Mipmap Size.", "Higher values increase quality, but are slower to render.", "Default: 5"})
        @Config.RangeInt(min = 2, max = 5)
        @Config.SlidingOption
        public int nMips = 5;

        @Config.Comment({"Bloom Strength", "OUTPUT = BACKGROUND + BLOOM * {strength} * (base + LT + (1 - BACKGROUND_BRIGHTNESS)*(HT-LT)))", "Default: 2"})
        @Config.RangeDouble(min = 0)
        public double strength = 1.5;

        @Config.Comment({"Blur Step (bloom range)", "Default: 1"})
        @Config.RangeDouble(min = 0)
        public double step = 1;
    }
}
