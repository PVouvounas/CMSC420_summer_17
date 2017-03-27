package edu.umd.cs.datastructures.bags.clients;

import edu.umd.cs.datastructures.bags.*;

import java.util.Random;
import java.util.stream.IntStream;

/** <b>IntegerTimingClient</b> tests all implementations of {@link Bag} in the task of retrieving all
 * of their Integer contents.
 * @author jason
 * @since 1.1
 */
public class IntegerTimingClient {

    private static Random r = new Random();
    private static int NUM_INTS = 100000;
    private static int[] ints = IntStream.rangeClosed(1, NUM_INTS).toArray();

    public static void main(String[] args){
        Bag[] bags = {
                new StaticallyPerturbedBag<Integer>(),
                new DynamicallyShuffledBag<Integer>(),
                new RandomAccessBag<Integer>()
        };

        for(Bag b: bags){
            System.out.println("Running Integer experiments for " + b + ".");
            insertAll(ints, b);
            b.shake(); // Not counting that for now. TODO: Do so if the results on timing accessing match intuition.
            long startingMillis = System.currentTimeMillis();
            loopthroughAll(b);
            long endingMillis = System.currentTimeMillis();
            System.out.println("For a " + b + ", looping through all elements took "
                    + (endingMillis - startingMillis) + " ms.");
            System.out.println("Finished Integer experiments for " + b + ".");
        }
    }

    private static void insertAll(int[] ints,  Bag<Integer> b){
        for(Integer i : ints)
            b.add(i);
    }

    private static void loopthroughAll(Bag<Integer> b){
        for(Integer ignored : b) // IntelliJ told me to change the name of the looping reference to "ignored", and it actually is. Thanks, IntelliJ
            ; // We don't really want anything to happen; this is just busy-waiting.
    }
}
