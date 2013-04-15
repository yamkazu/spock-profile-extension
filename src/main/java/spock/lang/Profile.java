package spock.lang;

import org.spockframework.runtime.extension.ExtensionAnnotation;
import org.spockframework.runtime.extension.ProfileExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@ExtensionAnnotation(ProfileExtension.class)
public @interface Profile {

    String[] excludeMethods() default {};

    String[] includeMethods() default {};

    String[] includeThreads() default {};

    String[] excludeThreads() default {};
}
