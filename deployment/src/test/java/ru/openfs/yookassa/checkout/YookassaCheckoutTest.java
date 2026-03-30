package ru.openfs.yookassa.checkout;

import io.quarkus.test.QuarkusUnitTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import ru.openfs.yookassa.checkout.config.YookassaCheckoutConfig;

public class YookassaCheckoutTest {
    @RegisterExtension
    static final QuarkusUnitTest TEST = new QuarkusUnitTest()
            .withEmptyApplication();

    @Inject
    YookassaCheckoutConfig config;

    @Test
    void success() {
        Assertions.assertTrue(config.enabled());
    }
}
