package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    } // getInstance() 끝

    private SingletonService() {

    } // SingletonService() 끝

    public void logic() {
        System.out.println("Singleton Entity Logic Call");
    } // logic() 끝
} // Class 끝
