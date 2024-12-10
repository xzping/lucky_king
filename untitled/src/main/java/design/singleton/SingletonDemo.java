package design.singleton;

public class SingletonDemo {
}

// 恶汉模式，线程安全
class Singleton1 {
    private static final Singleton1 INSTANCE = new Singleton1();
    private Singleton1() {}
    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}

// 懒汉模式，非线程安全
class Singleton2 {
    private static Singleton2 instance;
    private Singleton2() {}
    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

// 懒汉模式，线程安全
class Singleton3 {
    private static Singleton3 instance;
    private Singleton3() {}
    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}

// 懒汉模式，DCL模式
class Singleton4 {
    // 这里的volatile为了防止指令重排
    private static volatile Singleton4 instance;
    private Singleton4() {}
    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

// 静态内部类 -> 类加载顺序？
class Singleton5 {
    private Singleton5() {}
    private static class SingletonHolder {
        private static final Singleton5 INSTANCE = new Singleton5();
    }
    public static Singleton5 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
