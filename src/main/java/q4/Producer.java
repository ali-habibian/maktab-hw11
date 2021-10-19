package q4;

public class Producer extends Thread {
    private Item item;

    public Producer(Item item) {
        this.item = item;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            item.put(i);
            System.out.println("Producer put: " + i);
        }
    }
}
