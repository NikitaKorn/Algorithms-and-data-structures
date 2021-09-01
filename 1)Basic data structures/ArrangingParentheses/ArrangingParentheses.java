import java.util.*;

class MyData{
    private int position;
    private Character symbol;

    public MyData(int position, Character symbol){
        this.position = position;
        this.symbol = symbol;
    }

    public int getPosition() {
        return position;
    }

    public Character getSymbol(){
        return symbol;
    }
}

public class Main {
    public static Stack<MyData> stack = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] str = scanner.nextLine().toCharArray();

        System.out.println(check(str));
    }

    public static String check(char[] str){
        int iterator = 1;

        for(Character ch : str){
            if(!ch.equals('(') && !ch.equals('[') && !ch.equals('{') && !ch.equals(']') &&
                    !ch.equals(')') && !ch.equals('}')){
                iterator++;
                continue;
            }

            if(stack.empty()){
                if(ch.equals(')') || ch.equals('}') || ch.equals(']')){
                    return String.valueOf(iterator);
                } else {
                    stack.push(new MyData(iterator, ch));
                }
            } else {
                if( (ch.equals(')') && stack.peek().getSymbol().equals('(')) ||
                        (ch.equals('}') && stack.peek().getSymbol().equals('{')) ||
                        (ch.equals(']') && stack.peek().getSymbol().equals('['))){
                    stack.pop();
                } else if(ch.equals(')') || ch.equals('}') || ch.equals(']')){
                    return String.valueOf(iterator);
                } else {
                    stack.push(new MyData(iterator, ch));
                }
            }

            iterator++;
        }

        if(stack.empty()){
            return "Success";
        } else {
            return String.valueOf(stack.peek().getPosition());
        }
    }


}