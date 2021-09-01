import java.util.*;

public class Main {
    public static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            String[] splitStr = scanner.nextLine().split(" ");

            int number;
            switch (splitStr[0]){
                case "add" :
                    number = Integer.parseInt(splitStr[1]);
                    map.put(number, splitStr[2]);
                    break;
                case "del" :
                    number = Integer.parseInt(splitStr[1]);
                    map.remove(number);
                    break;
                case "find" :
                    number = Integer.parseInt(splitStr[1]);
                    if(map.containsKey(number)){
                        System.out.println(map.get(number));
                    } else {
                        System.out.println("not found");
                    }
            }
        }

    }

}