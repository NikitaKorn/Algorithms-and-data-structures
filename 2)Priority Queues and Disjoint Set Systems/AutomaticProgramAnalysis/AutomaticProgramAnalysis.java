import java.util.Scanner;

public class Main {
    static int[] parent;
    static int[] rank;
    static int flag = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int valueCount = scanner.nextInt();
        int val1 = scanner.nextInt();
        int val2 = scanner.nextInt();

        parent = new int[valueCount];
        rank = new int[valueCount];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for (int i = 0; i < val1; i++) {
            union(scanner.nextInt()-1, scanner.nextInt()-1);
        }

        for (int i = 0; i < val2; i++) {
            check(scanner.nextInt()-1, scanner.nextInt()-1);
        }

        System.out.println(flag);
    }

    public static int find(int i){
        if(i != parent[i]){
            parent[i] = find(parent[i]);
        }

        return parent[i];
    }

    public static void check(int i, int j){
        if(parent[i] == parent[j]){
            flag = 0;
        }
    }

    public static void union(int i, int j){
        int i_id = find(i);
        int j_id = find(j);

        if(i_id == j_id){
            return;
        }

        if(rank[i_id] > rank[j_id]){
            parent[j_id] = i_id;
        } else {
            parent[i_id] = j_id;
        }

        if(rank[i_id] == rank[j_id]){
            rank[j_id]++;
        }
    }
}