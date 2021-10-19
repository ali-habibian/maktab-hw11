package q4;

public class Consumer extends Thread {
    private Item item;

    public Consumer(Item item) {
        this.item = item;
    }

    public void run() {
        int value;
        for (int i = 0; i < 10; i++) {
            value = item.get();
            System.out.println("Consumer got: " + value);
        }
    }
}
