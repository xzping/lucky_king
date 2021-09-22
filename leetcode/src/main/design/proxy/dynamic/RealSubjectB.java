package main.design.proxy.dynamic;

public class RealSubjectB implements SubjectB {
    @Override
    public void sayHello(String name, String msg) {
        System.out.println("向" + name + "说:" + msg);
    }
}
