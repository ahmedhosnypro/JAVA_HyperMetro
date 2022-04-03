package metro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> stations = new LinkedList<>();

        if (args.length > 0) {
            File readFile = new File(args[0]);
            if (readFile.exists()) {
                try (Scanner scanner = new Scanner(readFile)) {
                    while (scanner.hasNext()) {
                        stations.add(scanner.nextLine());
                    }
                    printStations(stations);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error! Such a file doesn't exist!");
            }
        }
    }

    private static void printStations(LinkedList<String> stations) {
        final String separator = " - ";
        final String depot = "depot";
        for (int i = 0; i < stations.size(); i++) {
            StringBuilder print = new StringBuilder();
            if (i == 0) {
                print.append(depot).append(separator).append(stations.get(i)).append(separator).append(stations.get(i + 1));
            } else if (i == stations.size() - 1) {
                print.append(stations.get(i - 1)).append(separator).append(stations.get(i)).append(separator).append(depot);
            } else {
                print.append(stations.get(i - 1)).append(separator).append(stations.get(i)).append(separator).append(stations.get(i + 1));
            }
            System.out.println(print);
        }
    }
}
