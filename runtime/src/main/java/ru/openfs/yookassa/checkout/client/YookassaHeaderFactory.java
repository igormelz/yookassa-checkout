package ru.openfs.yookassa.checkout.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import ru.openfs.yookassa.checkout.config.YookassaCheckoutConfig;

import java.util.Base64;
import java.util.UUID;

@ApplicationScoped
public class YookassaHeaderFactory implements ClientHeadersFactory {

    @Inject
    YookassaCheckoutConfig config;

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> arg0, MultivaluedMap<String, String> arg1) {
        if (config.storeId().isPresent() && config.storeKey().isPresent()) {
            MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
            result.add("Idempotence-Key", UUID.randomUUID().toString());
            result.add("Authorization",
                    "Basic " + Base64.getEncoder().encodeToString((config.storeId().get() + ":" + config.storeKey().get()).getBytes()));
            return result;
        } else {
            return arg0;
        }
    }

}