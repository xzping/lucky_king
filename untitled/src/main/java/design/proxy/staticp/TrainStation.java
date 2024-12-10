package design.proxy.staticp;

public class TrainStation implements TicketSubject {
    @Override
    public void sell(int n) {
        System.out.println("出售" + n + "张车票");
    }
}
