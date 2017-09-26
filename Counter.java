import java.util.Random;


public class Counter implements Runnable {

    @Override
    public void run() {
        CalculatePi calc = new CalculatePi();

        //randomly generate 1000 points
        Random rand1 = new Random();
        float x = rand1.nextFloat();
        Random rand2 = new Random();
        float y = rand2.nextFloat();

        //x^2+y^2 = 1
        float area = x*x + y*y;

        //keep count of the number of hits
        if(area <= 1)
            calc.setHits(calc.getHits()+1);
    }
}
