package metro;

import java.util.ArrayList;
import java.util.List;

public class RouteFinder {

    ArrayList<Station> visitedStations = new ArrayList<>();
    ArrayList<ArrayList<Station>> possibleRoutes = new ArrayList<>();

    void findRoute(MetroSystem metroSystem, MetroLine fromLine, MetroLine toLine, Station fromStation, Station toStation) {
        for (var transferStation : fromLine.getStations().stream().filter(s -> !s.getTransfer().isEmpty()).toList()) {
            visitedStations.add(transferStation);
//            shortestRoute(metroSystem, transferStation, toLine, toStation, new ArrayList<>(List.of(fromLine.getName())));
        }
    }

//    void shortestRoute(MetroSystem metroSystem, Station transferStation, MetroLine toLine, Station toStation, ArrayList<String> passedLines) {
//        if (transferStation.getTransfer().get(0).getLine().equals(toLine.getName())) {
//
//        } else {
//            for (var nestedTransferStation : metroSystem.getLineByName(transferStation.getTransfer().get(0).getLine())
//                    .getStations().stream().filter(s -> !s.getTransfer().isEmpty())
//                    .filter(s -> !passedLines.contains(s.getTransfer().get(0).getLine()))
//                    .toList()) {
//                ArrayList<String> nestedPassedLines = new ArrayList<>(passedLines);
//                nestedPassedLines.add(metroSystem.getLineByName(transferStation.getTransfer().get(0).getLine()).getName());
//                shortestRoute(metroSystem, nestedTransferStation, toLine, toStation, nestedPassedLines);
//            }
//        }
//    }

    void findPossibleRoutes(MetroSystem metroSystem, MetroLine fromLine, MetroLine toLine, Station fromStation, Station toStation,ArrayList<String> passedLines) {

        for (var transferStation : fromLine.getStations().stream().filter(s -> !s.getTransfer().isEmpty()).toList()) {
            ArrayList<Station> tmpRoute = new ArrayList<>();
            tmpRoute.add(fromStation);
            tmpRoute.add(transferStation);

            if (transferStation.getTransfer().get(0).getLine().equals(toLine.getName())) {

            } else {
                for (var nestedTransferStation : metroSystem.getLineByName(transferStation.getTransfer().get(0).getLine())
                        .getStations().stream().filter(s -> !s.getTransfer().isEmpty())
                        .filter(s -> !passedLines.contains(s.getTransfer().get(0).getLine()))
                        .toList()) {
                    ArrayList<String> nestedPassedLines = new ArrayList<>(passedLines);
                    nestedPassedLines.add(metroSystem.getLineByName(transferStation.getTransfer().get(0).getLine()).getName());
//                    shortestRoute(metroSystem, nestedTransferStation, toLine, toStation, nestedPassedLines);
                }
            }

            possibleRoutes.add(tmpRoute);
        }
    }
}
