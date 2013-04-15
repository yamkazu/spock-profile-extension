package org.spockframework.runtime.extension

import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.MethodInfo
import org.spockframework.runtime.model.SpecInfo
import spock.lang.Profile

class ProfileExtension extends AbstractAnnotationDrivenExtension<Profile> {

    @Override
    void visitSpecAnnotation(Profile profile, SpecInfo spec) {
        spec.features.each { FeatureInfo feature ->
            if (!feature.getFeatureMethod().getReflection().isAnnotationPresent(Profile.class)) {
                visitFeatureAnnotation(profile, feature);
            }
        }
    }

    @Override
    void visitFeatureAnnotation(Profile profile, FeatureInfo feature) {
        feature.getFeatureMethod().addInterceptor(new ProfileInterceptor(profile))
    }

    @Override
    void visitFixtureAnnotation(Profile profile, MethodInfo fixtureMethod) {
        fixtureMethod.addInterceptor(new ProfileInterceptor(profile))
    }
}
