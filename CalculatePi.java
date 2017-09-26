import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;


public class CalculatePi {
    public static volatile int hits;
    public static ArrayList<Counter> count = new ArrayList<>();

    public static int getHits() {
        return hits;
    }

    public static void setHits(int hits) {
        CalculatePi.hits = hits;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("\t Calculate Pi \n**********************\nSet number of threads: ");
        int numThreads = scan.nextInt();

        for(int i=0; i<numThreads; i++)
            count.add(new Counter());

        //set up an executor service to handle the pool of threads
        ExecutorService executor = Executors.newCachedThreadPool();

        //start off the threads
        for(int i=0; i<numThreads; i++)
            executor.execute(count.get(i));

        executor.shutdown();
        //had an issue printing the value AFTER everything executed
        //found this solution on stackoverflow
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            //exception caught
            throw new RuntimeException(e);
        }

        float pi = getHits()/(float)numThreads*4;
        System.out.printf("The value of Pi is: %.6f", pi);

        pi*=3;
        System.out.println("\n"+pi);
    }
}
