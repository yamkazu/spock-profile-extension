package org.spockframework.runtime.extension.profile;

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.FeatureInfo;
import org.spockframework.runtime.model.MethodInfo;
import org.spockframework.runtime.model.SpecInfo;
import spock.lang.Profile;

public class ProfileExtension extends AbstractAnnotationDrivenExtension<Profile> {

    @Override
    public void visitSpecAnnotation(Profile profile, SpecInfo spec) {
        for (FeatureInfo feature : spec.getFeatures()) {
            if (!feature.getFeatureMethod().getReflection().isAnnotationPresent(Profile.class)) {
                visitFeatureAnnotation(profile, feature);
            }
        }
    }

    @Override
    public void visitFeatureAnnotation(Profile profile, FeatureInfo feature) {
        feature.getFeatureMethod().addInterceptor(new ProfileInterceptor());
    }

    @Override
    public void visitFixtureAnnotation(Profile profile, MethodInfo fixtureMethod) {
        fixtureMethod.addInterceptor(new ProfileInterceptor());
    }
}
