package design.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LogHandler implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before(method);
        Object result = proxy.invokeSuper(obj, args);
        after(method);
        return result;
    }

    private void before(Method method) {
        System.out.println("Begin to invoke:" + method.getName());
    }

    private void after(Method method) {
        System.out.println("End to invoke:" + method.getName());
    }
}
