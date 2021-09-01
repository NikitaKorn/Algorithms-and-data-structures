import java.util.*;

class Processor {
    public int index;
    public long currentTime;

    public Processor(int index){
        this.index = index;
        currentTime = 0;
    }

    public boolean less(Processor otherElement){
        if(this.currentTime < otherElement.currentTime){
            return true;
        } else if(this.currentTime > otherElement.currentTime){
            return false;
        } else {
            if(this.index < otherElement.index){
                return true;
            } else {
                return false;
            }
        }
    }
}

public class Main {
    public static Processor[] heap;
    public static List<Integer> processTime = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfProcessors = scanner.nextInt();
        int numberOfProcess = scanner.nextInt();

        heap = new Processor[numberOfProcessors + 1];
        for (int i = 1; i <= numberOfProcessors; i++) {
            heap[i] = new Processor(i-1);
        }

        for (int i = 0; i < numberOfProcess; i++) {
            processTime.add(scanner.nextInt());
        }

        logic(numberOfProcess);
    }

    public static void logic(int size){
        for (int i = 0; i < size; i++) {
            System.out.println(heap[1].index + " " + heap[1].currentTime);
            heap[1].currentTime += processTime.get(i);
            siftDown(1);
        }
    }

    public static void swap(int firsIndex, int secondIndex){
        Processor temp = heap[firsIndex];
        heap[firsIndex] = heap[secondIndex];
        heap[secondIndex] = temp;

//        firsIndex--;
//        secondIndex--;
//        list.add(new Values(firsIndex, secondIndex));
//        //System.out.println(firsIndex + " " + secondIndex);
    }

    public static void siftDown(int i){

        int minIndex = i;
        int l = leftChild(i);
        if(l < heap.length && heap[l].less(heap[minIndex])){
            minIndex = l;
        }

        int r = rightChild(i);
        if(r < heap.length && heap[r].less(heap[minIndex])){
            minIndex = r;
        }

        if(i != minIndex){
            swap(i, minIndex);
            siftDown(minIndex);
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