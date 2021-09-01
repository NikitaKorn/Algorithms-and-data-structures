import java.util.*;

public class Main {
    public static List<List<Integer>> adjList;
    public static int rootIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer size = Integer.parseInt(scanner.nextLine());
        String[] line = scanner.nextLine().split(" ");

        adjList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            adjList.add(new LinkedList<>());
        }

        for (int i = 0; i < size; i++) {
            int vertex = Integer.parseInt(line[i]);
            if(!(vertex == -1)) {
                addInAdj(vertex, i);
            } else {
                rootIndex = i;
            }
        }

        //printADJ();
        System.out.println(treeTravel());
    }

    public static void printADJ(){
        for (int i = 0; i < adjList.size(); i++) {
            List<Integer> temp = adjList.get(i);
            if(!temp.isEmpty()) {
                System.out.print(i + " ");
                for (Integer j : temp) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
        }
    }

    public static int treeTravel(){
        int currentDepth = 1;
        Queue<Integer> queue = new LinkedList<>();

        for(Integer i : adjList.get(rootIndex)){
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            currentDepth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();

                for (Integer j : adjList.get(index)) {
                    queue.add(j);
                }
            }
        }

        return currentDepth;
    }

    public static void addInAdj(int index, int value){
        adjList.get(index).add(value);
    }
}