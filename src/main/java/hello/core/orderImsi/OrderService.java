package hello.core.orderImsi;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

} // Class 끝
