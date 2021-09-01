import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

class Pocket{
    private int duration;
    private int arrival;
    private int condition;

    public Pocket(int arrival, int duration){
        this.arrival = arrival;
        this.duration = duration;
    }

    public int getDuration(){
        return duration;
    }

    public int getArrival(){
        return arrival;
    }

    public int getCondition(){
        return condition;
    }

    public void setCondition(int condition){
        this.condition = condition;
    }
}

public class Main {
    public static Queue<Pocket> queue;
    public static int time = 0;
    public static List<Pocket> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstString = scanner.nextLine().split(" ");

        queue = new LinkedBlockingQueue<>(Integer.parseInt(firstString[0]));

        for (int i = 0; i < Integer.parseInt(firstString[1]); i++) {
            String[] newString = scanner.nextLine().split(" ");
            getPocket(Integer.parseInt(newString[0]), Integer.parseInt(newString[1]));
        }
        cashElements();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getCondition());
        }

    }

    public static void getPocket(int arrival, int duration){
        Pocket pocket = new Pocket(arrival, duration);
        //При проступлении первого элемента, устанавливаем глобальное время на время прибытия 1-го элемента
        if(list.isEmpty()){
            time = arrival;
        }
        //Вспомогательный list для хранения всех пакетов
        list.add(pocket);
        //Пытаемся вставить пакет в очередь, при этом проверяя заполнена она или нет
        if(queue.offer(pocket)){

        } else {
            //Если очередь полная, то смотрим, успеет ли выполниться 1-ый пакет в очереди к приходу следующего
            //Если да, то достаем первый пакет, смотрим не опережает ли время его прибытия текущее время
            //Устанавлеваем время начала выполнения для пакета и добавляем к глобальному времени время выполнения этого пакета
            if(pocket.getArrival() >= (time + queue.peek().getDuration()) ){
                Pocket acceptedPocket = queue.poll();
                if(time < acceptedPocket.getArrival()) {
                    time = acceptedPocket.getArrival();
                }
                acceptedPocket.setCondition(time);
                time += acceptedPocket.getDuration();

                queue.add(pocket);
            } else {
                pocket.setCondition(-1);
            }

        }
    }

    //Метод для обработки пакетов, оставшихся в очереди
    public static void cashElements(){
        while (!queue.isEmpty()){
            Pocket acceptedPocket = queue.poll();

            if(acceptedPocket.getArrival() > time) {
                time = acceptedPocket.getArrival();
            }

            acceptedPocket.setCondition(time);
            time += acceptedPocket.getDuration();
        }
    }

}