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
%      calls  total ms  ms/call  min ms  max ms  method       class
42.89      1     27.57    27.57   27.57   27.57  doLongTask   Bar
41.76    100     26.84     0.27    0.25    0.97  doShortTask  Foo
14.94      1      9.60     9.60    9.60    9.60  start        YourApp
 0.16      1      0.10     0.10    0.10    0.10  ctor         YourApp
 0.13      1      0.09     0.09    0.09    0.09  ctor         Bar
 0.12      1      0.08     0.08    0.08    0.08  ctor         Foo
```

If you would like to set a options that you can use `@Profile` attributes:

```
@Profile(includeMethods = ["*.ctor"], excludeMethods = ["java.*", "groovy.*"])
```

Please see [this page](http://nagaimasato.blogspot.jp/2013/04/gprof-020-is-out.html) for the option details.