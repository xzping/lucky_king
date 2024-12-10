package design.proxy.staticp;

/**
 * 火车站售票点的代理，同样需要实现ticket接口
 * 作用：可以在原来的被代理对象的基础上实现增强
 * @author xiezhiping
 *
 */
public class TrainStationProxy implements TicketSubject {
    private TrainStation trainStation;

    public TrainStationProxy(TrainStation trainStation) {
        this.trainStation = trainStation;
    }

    @Override
    public void sell(int n) {
        before();
        trainStation.sell(n);
        after();
    }

    private void before() {
        System.out.println("欢迎来到火车站代售点");
    }

    private void after() {
        System.out.println("请支付代售手续费");
    }
}
