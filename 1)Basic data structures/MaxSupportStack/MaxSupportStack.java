import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

class MyStack{
    private List<Integer> list = new LinkedList<>();
    private List<Integer> listForMax = new LinkedList<>();

    public void push(int element){
        list.add(0, element);

        if(listForMax.isEmpty()){
            listForMax.add(0, element);
        } else {
            if(listForMax.get(0) < element){
                listForMax.add(0, element);
            } else {
                listForMax.add(0, listForMax.get(0));
            }
        }
    }

    public void pop(){
        list.remove(0);
        listForMax.remove(0);
    }

    public int max(){
        return listForMax.get(0);
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        List<Integer> listForOutput = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        MyStack stack = new MyStack();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int counter = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < counter; i++) {
            String line = bufferedReader.readLine();

            if(line.contains("push")){
                String[] split = line.split(" ");
                stack.push(Integer.parseInt(split[1]));
            } else if(line.contains("pop")){
                stack.pop();
            } else if(line.contains("max")){
                stringBuilder.append(stack.max() + "\n");
            }
        }

        System.out.println(stringBuilder);
    }
}