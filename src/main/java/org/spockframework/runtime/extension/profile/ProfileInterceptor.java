package org.spockframework.runtime.extension.profile;

import org.spockframework.runtime.extension.IMethodInterceptor;
import org.spockframework.runtime.extension.IMethodInvocation;

import java.util.concurrent.Callable;

public class ProfileInterceptor implements IMethodInterceptor {

    public void intercept(final IMethodInvocation invocation) throws Throwable {
        Throwable saved = null;

        Profiled profiled = new Profiled(invocation);

        new gprof.Profiler().run(profiled).prettyPrint();

        if (profiled.saved != null) {
            throw saved;
        }
    }

    private static class Profiled implements Callable {

        IMethodInvocation invocation;

        Throwable saved;

        Profiled(IMethodInvocation invocation) {
            this.invocation = invocation;
        }

        @Override
        public Object call() throws Exception {
            try {
                invocation.proceed();
            } catch (Throwable t) {
                saved = t;
            }
            return null;
        }
    }
}
