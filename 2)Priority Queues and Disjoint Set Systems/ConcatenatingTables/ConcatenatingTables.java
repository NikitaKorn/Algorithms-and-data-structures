import java.util.*;

class Quantity{
    public Node[] parent;
    public int[] rank;
    public int max = -1;

    public Quantity(int size){
        parent = new Node[size];
        for (int i = 0; i < size; i++) {
            parent[i] = new Node(i, 0);
        }
        rank = new int[size];
    }

    public void makeSet(int id, int value){
        if(value > max) {
            max = value;
        }
        parent[id].value = value;
        rank[id] = 0;
    }


    public int find(int i){
        if(i != parent[i].id){
            parent[i].id = find(parent[i].id);
        }

        return parent[i].id;
    }

    public void union(int i, int j){
        int i_id = find(i);
        int j_id = find(j);

        if(i_id == j_id){
            return;
        }

        if(rank[i_id] > rank[j_id]){
            parent[j_id].id = i_id;
            parent[i_id].value += parent[j_id].value;
            if(parent[i_id].value > max){
                max = parent[i_id].value;
            }
        } else {
            parent[i_id].id = j_id;
            parent[j_id].value += parent[i_id].value;
            if(parent[j_id].value > max){
                max = parent[j_id].value;
            }
        }

        if(rank[i_id] == rank[j_id]){
            rank[j_id]++;
        }
    }

    class Node {
        public int id;
        public int value;

        public Node(int i, int value){
            id = i;
            this.value = value;
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tableCounter = scanner.nextInt();
        Quantity quantity = new Quantity(tableCounter);
        int requestCounter = scanner.nextInt();

        for (int i = 0; i < tableCounter; i++) {
            quantity.makeSet(i, scanner.nextInt());
        }

        System.out.println();
        for (int i = 0; i < requestCounter; i++) {
            int val1 = scanner.nextInt() -1;
            int val2 = scanner.nextInt() - 1;
            quantity.union(val1, val2);
            System.out.println(quantity.max);
        }

    }
}