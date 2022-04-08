package metro;

import java.util.List;
import java.util.Scanner;

import static metro.JsonMetroParser.parseJsonMetroSystem;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private MetroSystem metroSystem = new MetroSystem();

    void start(String[] args) {
        if (readStationsFromJson(args[0])) {
            while (true) {
                String input = scanner.nextLine();
                var inputArgs = ArgumentTokenizer.tokenize(input);
                if (!inputArgs.isEmpty()) {
                    runCommand(inputArgs);
                }
            }
        }
    }

    private void runCommand(List<String> inputArgs) {
        if (inputArgs.contains("/append") && inputArgs.size() == 3) {
            metroSystem.appendStation(inputArgs);
        } else if (inputArgs.contains("/add-head") && inputArgs.size() == 3) {
            metroSystem.addHeadStation(inputArgs);
        } else if (inputArgs.contains("remove") && inputArgs.size() == 3) {
            metroSystem.removeStation(inputArgs);
        } else if (inputArgs.contains("/output") && inputArgs.size() == 2) {
            metroSystem.outputLine(inputArgs);
        } else if (inputArgs.contains("/connect") && inputArgs.size() == 5) {
            metroSystem.connectStations(inputArgs);
        } else if ((inputArgs.contains("/route") || inputArgs.contains("/fastest-route")) && inputArgs.size() == 5) {
            StringBuilder input = new StringBuilder();
            inputArgs.forEach(a -> input.append(a).append(" "));
            metroSystem.routes(input.toString());
        } else if (inputArgs.contains("/exit") && inputArgs.size() == 1) {
            exit();
        } else {
            System.out.println("Invalid command");
        }
    }

    private boolean readStationsFromJson(String fileName) {
        metroSystem = parseJsonMetroSystem(fileName);
        return metroSystem != null;
    }

    private void exit() {
        System.exit(0);
    }
}
