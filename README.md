Spock Profile Extention
-----------------------

The [GProf](https://code.google.com/p/gprof/) is a profiler for Groovy. This extention make to use the GProf in spock.

To use we can do the following:

```groovy
class ProfileSpec extends Specification {

    @Profile
    def "with profile"() {
        when:
        new YourApp().start()

        then:
        noExceptionThrown()
    }

}

class YourApp {
    void start() {
        def foo = new Foo()
        for (int i = 0; i < 100; i++) {
            foo.doShortTask()
        }
        def bar = new Bar()
        bar.doLongTask()
    }
}

class Bar {
    void doLongTask() {
        for (int i = 0; i < 1000000; i++);
    }
}

class Foo {
    void doShortTask() {
        for (int i = 0; i < 10000; i++);
    }
}
```

output such as:

```
%      calls  total ms  ms/call  min ms  max ms  method              class                                          
40.19      1     93.18    93.18   93.18   93.18  doLongTask          Bar                                            
37.55    100     87.05     0.87    0.42   10.61  doShortTask         Foo                                            
21.97      1     50.94    50.94   50.94   50.94  start               YourApp                                        
 0.07      1      0.17     0.17    0.17    0.17  ctor                YourApp                                        
 0.07      1      0.16     0.16    0.16    0.16  setThrownException  org.spockframework.runtime.SpecificationContext
 0.05      1      0.13     0.13    0.13    0.13  ctor                Bar                                            
 0.05      1      0.12     0.12    0.12    0.12  ctor                Foo                                            
 0.03      1      0.07     0.07    0.07    0.07  leaveScope          org.spockframework.mock.runtime.MockController 
 0.02      1      0.04     0.04    0.04    0.04  getMockController   org.spockframework.runtime.SpecificationContext
```

