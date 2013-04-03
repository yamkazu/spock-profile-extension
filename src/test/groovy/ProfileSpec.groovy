import spock.lang.Profile
import spock.lang.Specification

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
