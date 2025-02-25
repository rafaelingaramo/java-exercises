package thread;

public class VirtualThreadNoJoin {
    public static void main(String[] args) {
        Thread.ofVirtual().start(() -> {
            long startTime = System.currentTimeMillis();
            System.out.println("Start time in milliseconds: " + startTime);
            int index = 0;
            for (int i = 0; i < 10000; i++) {
                for (int j = 0; j < 1000; j++) {
                    System.out.println(++index);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Difference in seconds: " + (endTime - startTime) / 1000.0);
        });
        System.out.println(" EXIT ");
    }
}
