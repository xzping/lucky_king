package design.proxy.dynamic;

import java.lang.reflect.Proxy;

public class bizService {
    public static void main(String[] args) {
        SubjectA subjectA = (SubjectA) Proxy.newProxyInstance(
                SubjectA.class.getClassLoader(),
                new Class[]{SubjectA.class},
                new LogHandler(new ReadSubjectA())
        );
        subjectA.setUser("xzp", "111111");

        SubjectB subjectB = (SubjectB) Proxy.newProxyInstance(
                SubjectB.class.getClassLoader(),
                new Class[]{SubjectB.class},
                new LogHandler(new RealSubjectB())
        );
        subjectB.sayHello("xzp", "hello");
    }
}
