package ru.openfs.yookassa.checkout.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanDefiningAnnotationBuildItem;
import io.quarkus.arc.processor.DotNames;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.AdditionalIndexedClassesBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import org.jboss.jandex.DotName;
import ru.openfs.yookassa.checkout.client.YookassaClient;
import ru.openfs.yookassa.checkout.config.YookassaCheckoutConfig;
import ru.openfs.yookassa.checkout.service.YookassaCheckoutService;

class YookassaCheckoutProcessor {
    private static final DotName YOOKASSA_CLIENT_CONFIG = DotName.createSimple(YookassaCheckoutConfig.class.getName());
    private static final String FEATURE = "yookassa-checkout";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    AdditionalIndexedClassesBuildItem addAdditionalClasses() {
        return new AdditionalIndexedClassesBuildItem(YookassaClient.class.getName());
    }

    @BuildStep
    AdditionalBeanBuildItem build() {
        return AdditionalBeanBuildItem.unremovableOf(YookassaCheckoutService.class);
    }

    @BuildStep
    void clientConfigSupport(BuildProducer<AdditionalBeanBuildItem> additionalBeans,
                             BuildProducer<BeanDefiningAnnotationBuildItem> beanDefiningAnnotations) {
        additionalBeans.produce(AdditionalBeanBuildItem.builder().addBeanClass(YookassaCheckoutConfig.class).build());
        beanDefiningAnnotations.produce(new BeanDefiningAnnotationBuildItem(YOOKASSA_CLIENT_CONFIG, DotNames.APPLICATION_SCOPED, false));
    }
}
