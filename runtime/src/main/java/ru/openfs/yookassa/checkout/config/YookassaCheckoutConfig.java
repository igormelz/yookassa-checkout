package ru.openfs.yookassa.checkout.config;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.util.Optional;

@ConfigMapping(prefix = "yookassa.checkout")
@ConfigRoot(phase = ConfigPhase.RUN_TIME)
public interface YookassaCheckoutConfig {

    /**
     * is service enable, default: true
     */
    @WithDefault("true")
    boolean enabled();

    /**
     * set yookassa StoreId
     */
    Optional<String> storeId();

    /**
     * set yookassa StoreKey
     */
    Optional<String> storeKey();

}
