import java.util.*;

class MyType{
    private List<String> list;

    public MyType(String str){
        list = new LinkedList<>();
        list.add(str);
    }

    public boolean find(String str){
        if(str != null) {
            return list.contains(str);
        } else{
            return false;
        }
    }

    public void add(String str){
        list.add(0, str);
    }

    public void del(String str){
        if(list.contains(str)){
            int index = list.indexOf(str);
            list.remove(index);
        }
    }

    public void printList(){
        if(!list.isEmpty()){
            for(String i : list){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

}

class MyHashMap{
    private MyType[] myHash;
    int size;

    public MyHashMap(int size){
        myHash = new MyType[size];
        this.size = size;
    }

    public void put(String str){
        int index = hash(str);
        if(str == null){
            return;
        }

        if(myHash[index] == null){
            myHash[index] = new MyType(str);
        } else {
            if(find(str).equals("no")){
                myHash[index].add(str);
            }
        }
    }

    public void check(int index){
        if(myHash[index] != null){
            myHash[index].printList();
        } else {
            System.out.println();
        }
    }

    public void del(String str){
        int index = hash(str);

        if(myHash[index] != null){
            myHash[index].del(str);
        }
    }

    public String find(String str){
        int index = hash(str);

        if(myHash[index] != null){
            if(myHash[index].find(str) == true){
                return "yes";
            } else {
                return "no";
            }
        } else {
            return "no";
        }
    }

    public int hash(String str){
        int p = 1_000_000_007;
        int x = 263;
        char[] strToChar = str.toCharArray();
        double value = 0;

        for (int i = 0; i < strToChar.length; i++) {
            value = (value + (int) strToChar[i] * pow(x, i)) % p;
        }
        value %= size;

        return (int) value;
    }

    public long pow(int x, int i){
        if(i == 0){
            return 1;
        }

        if(i == 1){
            return x;
        }

        return x * pow(x, i-1) % 1000000007;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int count = scanner.nextInt();
        MyHashMap myHash = new MyHashMap(size);

        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            String[] splitStr = scanner.nextLine().split(" ");

            switch (splitStr[0]){
                case "add" :
                    myHash.put(splitStr[1]);
                    break;
                case "del" :
                    myHash.del(splitStr[1]);
                    break;
                case "find" :
                    System.out.println(myHash.find(splitStr[1]) );
                    break;
                case "check" :
                    myHash.check(Integer.parseInt(splitStr[1]));
                    break;
            }
        }

//        System.out.println(myHash.find("world"));
//        myHash.put("world");
//        System.out.println(myHash.find("world"));
//        myHash.put("HellO");

    }

}