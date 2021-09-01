import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
    public static int p = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        carpRabin(pattern, text);

    }

    public static void carpRabin(String pattern, String text){
        int[] hashMas = new int[text.length() - pattern.length() + 1];
        int hashOfLastElement;
        int x = 1;
        //p = (int) ((text.length() + 115) * Math.pow(text.length(), text.length()));

        char[] patternChar = pattern.toCharArray();
        char[] textChar = text.toCharArray();
        int patternLength = pattern.length();
        int textLength = text.length();

        for (int i = 0; i < patternLength; i++) {
            hashMas[textLength - patternLength] += ((int) textChar[textLength - patternLength  + i] % p
                    * pow(x, i) % p) % p;
        }

        for (int i = textLength - patternLength - 1, j = 1; i >= 0; i--, j++) {
            hashOfLastElement = (int) (((int) textChar[textLength - j] % p * pow(x, patternLength - 1) % p) % p);
            hashMas[i] = ((hashMas[i+1] % p - hashOfLastElement % p )% p * x %p + (int) textChar[i] %p) % p ;
        }

        int patternHash = 0;
        for (int i = 0; i < patternLength; i++) {
            patternHash += ((int) patternChar[i] % p * pow(x, i) % p) % p;
        }

        List<Integer> indexOfComing = new ArrayList<>();

        for (int i = 0; i < hashMas.length; i++) {
            if(hashMas[i] == patternHash){
                boolean flag = true;
                for (int j = 0; j < patternLength; j++) {
                    if(!(textChar[i + j] == patternChar[j])){
                        flag = false;
                        break;
                    }
                }
                if(flag == true){
                    indexOfComing.add(i);
                }
            }
        }

        for(Integer i : indexOfComing){
            System.out.print(i + " ");
        }

    }

    public static long pow(int x, int i){
        if(i == 0){
            return 1;
        }

        if(i == 1){
            return x;
        }

        return x * pow(x, i-1) % p;
    }

}