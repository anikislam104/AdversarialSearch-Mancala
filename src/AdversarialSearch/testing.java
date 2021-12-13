package AdversarialSearch;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class testing {
    public static void main(String[] args) {
        int[] ar= { 1, 2, 3, 4, 5, 6, 16, 15, 14, 13, 12, 11 };
        ar=shuffleArray(ar);
        for (int i=0;i<ar.length;i++){
            System.out.println(ar[i]);
        }
    }

    static int[] shuffleArray(int[] ar){
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
}
