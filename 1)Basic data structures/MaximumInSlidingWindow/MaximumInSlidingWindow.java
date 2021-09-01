import java.io.IOException;
import java.util.*;

class MyData{
    private int value;
    private int maxValue;

    public MyData(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMaxValue(){
        return maxValue;
    }
}

class MyStack{
    private List<MyData> stack;
    private List<MyData> reverseStack;
    private int size;

    public MyStack(int size){
        stack = new LinkedList<>();
        reverseStack = new LinkedList<>();
        this.size = size;
    }

    public void push(int value){
        MyData newElement = new MyData(value);

        if(!reverseStack.isEmpty()){
            reverseStack.remove(0);
        }

        if (stack.size() < size){
            if(stack.isEmpty()){
                newElement.setMaxValue(value);
            } else {
                int currentMaxValue = stack.get(0).getMaxValue();
                if(currentMaxValue > value){
                    newElement.setMaxValue(currentMaxValue);
                } else {
                    newElement.setMaxValue(value);
                }
            }
        } else {
            replace();
            reverseStack.remove(0);
            newElement.setMaxValue(newElement.getValue());
        }

        stack.add(0, newElement);
    }

    public void replace(){

        for (int i = 0; i < size; i++) {
            if(!reverseStack.isEmpty()){
                if(reverseStack.get(0).getMaxValue() > stack.get(0).getValue()){
                    stack.get(0).setMaxValue(reverseStack.get(0).getMaxValue());
                    reverseStack.add(0, stack.get(0));
                } else {
                    stack.get(0).setMaxValue(stack.get(0).getValue());
                    reverseStack.add(0, stack.get(0));
                }
            } else {
                stack.get(0).setMaxValue(stack.get(0).getValue());
                reverseStack.add(0, stack.get(0));
            }
            stack.remove(0);
        }
    }

    public int getMax(){

        if(stack.isEmpty() && !reverseStack.isEmpty()){
            return reverseStack.get(0).getMaxValue();
        } else if(!stack.isEmpty() && reverseStack.isEmpty()){
            return stack.get(0).getMaxValue();
        } else {
            return stack.get(0).getMaxValue() > reverseStack.get(0).getMaxValue() ?
                    stack.get(0).getMaxValue() : reverseStack.get(0).getMaxValue();
        }
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        MyStack stack;
        List<Integer> list;

        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(scanner.nextInt());
        }
        int windowSize = scanner.nextInt();
        stack = new MyStack(windowSize);
        for (int i = 0; i < windowSize; i++) {
            stack.push(list.get(i));
        }

        System.out.print(stack.getMax() + " ");
        for (int i = windowSize; i < list.size(); i++) {
            stack.push(list.get(i));
            System.out.print(stack.getMax() + " ");
        }
    }
}