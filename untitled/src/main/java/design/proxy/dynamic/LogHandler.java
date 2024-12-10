package design.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 日志增强器，需要实现InvocationHandler接口，并重写invoke方法，再对invoke方法中的method进行增强
 *
 * @author xiezhiping
 *
 */
public class LogHandler implements InvocationHandler {
    private Object obj;
    public LogHandler(Object obj) {
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(method);
        Object result = method.invoke(obj, args);
        after(method);
        return result;
    }

    private void before(Method method) {
        System.out.println("Begin to invoke " + method.getName());
    }

    private void after(Method method) {
        System.out.println("End to invoke" + method.getName());
    }
}
