import java.util.*;

class Values {
    public int value1;
    public int value2;

    public Values(int val1, int val2){
        value1 = val1;
        value2 = val2;
    }
}

public class Main {
    public static int counter = 0;
    public static List<Values> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        double[] mas = new double[size+1];
        for (int i = 0; i < size; i++) {
            mas[i+1] = scanner.nextInt();
        }

        sort(mas, size/2 + 1, size);
    }

    public static void sort(double[] mas, int lists, int size){
        int parentIndex;
//        while (parentIndex >= 1){
//            siftDown(parentIndex, mas, size);
//            parentIndex = parent(parentIndex);
//        }
        for (int i = size; i > 0; i--) {
            parentIndex = parent(i);
            if(leftChild(parentIndex) <= size || rightChild(parentIndex) <= size){
                siftDown(parentIndex, mas, size);
            }
        }

        System.out.println(counter);
        for(Values i : list){
            System.out.println(i.value1 + " " + i.value2);
        }
    }

    public static void swap(int firsIndex, int secondIndex, double[] H){
        counter++;
        double temp = H[firsIndex];
        H[firsIndex] = H[secondIndex];
        H[secondIndex] = temp;

        firsIndex--;
        secondIndex--;
        list.add(new Values(firsIndex, secondIndex));
        //System.out.println(firsIndex + " " + secondIndex);
    }

    public static void siftUp(int i, double[] H){
        while(i > 1 && H[parent(i)] > H[i]){
            swap(parent(i), i, H);
            i = parent(i);
        }
    }

    public static void siftDown(int i, double[] H, int size){

        int minIndex = i;
        int l = leftChild(i);
        if(l <= size && H[l] < H[minIndex]){
            minIndex = l;
        }

        int r = rightChild(i);
        if(r <= size && H[r] < H[minIndex]){
            minIndex = r;
        }

        if(i != minIndex){
            swap(i, minIndex, H);
            siftDown(minIndex, H, size);
        }

    }

    public static int leftChild(int i){
        return 2*i;
    }

    public static int parent(int i){
        return i/2;
    }

    public static int rightChild(int i){
        return 2*i+1;
    }
}