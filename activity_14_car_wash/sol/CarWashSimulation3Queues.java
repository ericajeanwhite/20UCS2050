/*
 * CS2050 - Computer Science II - Summer 2020
 * Instructor: Thyago Mota
 * Student Names:
 * Description: Activity 14 - CarWashSimulation Class
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class CarWashSimulation3Queues {

    private static final int    MIN_TIME_WASH = 5;  // minutes
    private static final int    MAX_TIME_WASH = 15;  // minutes
    private static final int    CHANCE_NEW_CAR = 50; // 1-100% chance
    private static final String DATA_FILENAME = "cars.csv";
    private static String[] data = null;
    private static final int    NUMBER_QUEUES = 5;

    public static void loadData() {
        try {
            Scanner in = new Scanner(new FileInputStream(DATA_FILENAME));
            // 1st line has the number of cars
            int total = Integer.parseInt(in.nextLine());
            data = new String[total];
            int i = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                data[i++] = line;
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Car getRandomCar() {
        Random r = new Random();
        int i = r.nextInt(data.length);
        String fields[] = data[i].split(",");
        return new Car(fields[0], fields[1], Integer.parseInt(fields[2]));
    }

    public static void run(int minutes) {

        // TODOd: instantiate a dynamic queue; name the reference variable "queue"
        Queue<Car> queues[] = new Queue[NUMBER_QUEUES];
        for (int i = 0; i < NUMBER_QUEUES; i++)
            queues[i] = new DynamicQueueLinkedList<>();

        Random r    = new Random();
        Car car[]   = new Car[NUMBER_QUEUES]; // this points to the car being washed
        int timer[] = new int[NUMBER_QUEUES]; // counts the minutes to wash the car
        System.out.println("Simulation starts now for " + minutes + " minutes!");
        for (int i = 0; i < minutes; i++) {
            String padded = String.format("%03d" , i);
            System.out.println(padded + ". ");
            for (int j = 0; j < NUMBER_QUEUES; j++)
                System.out.println("Queue #" + j + ": " + queues[j]);

            // TODOd: simulate that a new car arrived using CHANCE_NEW_CAR
            if (r.nextInt(100) + 1 <= CHANCE_NEW_CAR) {
                Car newCar = getRandomCar();

                // push to the shorter queue
                int j = 0; // assume it is the 1st one
                for (int k = 1; k < NUMBER_QUEUES; k++)
                    if (queues[k].size() < queues[j].size())
                        j = k;
                queues[j].push(newCar);
            }

            // TODOd: if no cars is being washed, get the next car to be served from the queue and
            // start a timer
            for (int j = 0; j < NUMBER_QUEUES; j++) {
                if (car[j] == null) {
                    try {
                        car[j] = queues[j].pop();
                        System.out.println(car[j] + " started to be served on queue #" + (j) + " now!");
                        timer[j] = r.nextInt(MAX_TIME_WASH - MIN_TIME_WASH + 1) + MIN_TIME_WASH;
                    } catch (NoSuchElementException ex) {
                        System.out.println("Queue is empty!");
                    }
                }
            }

            // TODOd: if a car is currently being served, decrease the timer and finish servicing the car if timer reaches 0
            for (int j = 0; j < NUMBER_QUEUES; j++) {
                if (car[j] != null) {
                    timer[j]--;
                    if (timer[j] == 0)
                        car[j] = null;
                }
            }
        }
        System.out.println("Simulation completed!");
    }

    public static void main(String[] args) {
        loadData();
        run(100);
    }
}
