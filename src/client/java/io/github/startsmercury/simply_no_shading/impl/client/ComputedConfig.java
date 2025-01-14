package io.github.startsmercury.simply_no_shading.impl.client;

import io.github.startsmercury.simply_no_shading.api.client.Config;
import java.util.Objects;

public final class ComputedConfig {
    // config values frequently used such as in rendering
    public static boolean blockShadingEnabled;
    public static boolean cloudShadingEnabled;
    public static boolean entityShadingEnabled;

    public static void set(final Config config) {
        Objects.requireNonNull(config, "Parameter config is null");

        ComputedConfig.blockShadingEnabled = config.blockShadingEnabled();
        ComputedConfig.cloudShadingEnabled = config.cloudShadingEnabled();
        ComputedConfig.entityShadingEnabled = config.entityShadingEnabled();
    }

    private ComputedConfig() {
    }
}
