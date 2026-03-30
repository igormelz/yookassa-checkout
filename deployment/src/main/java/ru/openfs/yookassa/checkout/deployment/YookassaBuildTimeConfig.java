package ru.openfs.yookassa.checkout.deployment;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.util.Optional;

@ConfigMapping(prefix = "yookassa.checkout")
@ConfigRoot(phase = ConfigPhase.BUILD_TIME)
public interface YookassaBuildTimeConfig {
    /**
     * set yookassa StoreId
     */
    Optional<String> storeId();

    /**
     * set yookassa StoreKey
     */
    Optional<String> storeKey();
}
