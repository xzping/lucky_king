package main.design.proxy.staticp;

/**
 * 静态代理：缺点是需要为每一个被代理对象新增一个代理类，繁琐易错
 */
public class bizService {
    public static void main(String[] args) {
        TrainStation trainStation = new TrainStation();
        TrainStationProxy trainStationProxy = new TrainStationProxy(trainStation); // 构造传入
        trainStationProxy.sell(10);
    }
}
