package org.spockframework.runtime.extension

import gprof.Profiler
import spock.lang.Profile

class ProfileInterceptor implements IMethodInterceptor {

    static EXCLUDE_SPCOK_METHODS = ["org.spockframework.*", "spock.*"]

    Profile profile

    ProfileInterceptor(Profile profile) {
        this.profile = profile
    }

    @Override
    void intercept(IMethodInvocation invocation) throws Throwable {
        Throwable saved = null

        new Profiler().run(options, {
            try {
                invocation.proceed()
            } catch (Throwable t) {
                saved = t
            }
        }).prettyPrint()

        if (saved != null) {
            throw saved
        }
    }

    def getOptions() {
        [
            includeMethods: profile.includeMethods().toList(),
            excludeMethods: profile.excludeMethods().toList() + EXCLUDE_SPCOK_METHODS,
            includeThreads: profile.includeThreads().toList(),
            excludeThreads: profile.excludeThreads().toList(),
        ]
    }
}
