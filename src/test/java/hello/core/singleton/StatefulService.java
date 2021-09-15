package hello.core.singleton;

public class StatefulService {

    /* 문제 해결 코드 */

    public int order(String name, int price) {
        System.out.println("name = " + name + "\n price = " + price);
        return price;
    }

    /* 문제가 발생하는 코드 */
//    private int price;
//
//    public void order(String name, int price) {
//        System.out.println("name = " + name + "\n price = " + price);
//
//        this.price = price;  // 문제가 여기서 발생!
//    }

//    public int getPrice() {
//        return price;
//    }
}
