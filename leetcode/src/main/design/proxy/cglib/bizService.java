package design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class bizService {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LoginServiceImpl.class);
        enhancer.setCallback(new LogHandler());
        LoginServiceImpl loginServiceImpl = (LoginServiceImpl) enhancer.create();
        loginServiceImpl.login("Jack", "123456");
    }
}
