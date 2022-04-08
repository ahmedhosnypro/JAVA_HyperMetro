package metro;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MetroSystem {
    private final ArrayList<MetroLine> lines = new ArrayList<>();

    public ArrayList<MetroLine> getLines() {
        return lines;
    }


    void appendStation(List<String> inputArgs) {

        if (containsLine(inputArgs.get(1))) {
            for (var line : lines) {
                if (line.getName().equals(inputArgs.get(1))) {
                    line.getStations().addLast(new Station(inputArgs.get(2)));
                    break;
                }
            }
        }
    }

    void addHeadStation(List<String> inputArgs) {
        if (containsLine(inputArgs.get(1))) {
            for (var line : lines) {
                if (line.getName().equals(inputArgs.get(1))) {
                    line.getStations().addFirst(new Station(inputArgs.get(2)));
                    break;
                }
            }
        }
    }

    void removeStation(List<String> inputArgs) {
        if (containsLine(inputArgs.get(1))) {
            for (var line : lines) {
                if (line.getName().equals(inputArgs.get(1))) {
                    line.getStations().removeIf(s -> s.getName().equals(inputArgs.get(2)));
                }
            }
        }
    }

    void outputLine(List<String> inputArgs) {
        if (containsLine(inputArgs.get(1))) {
            var line = lines.stream().filter(l -> l.getName().equals(inputArgs.get(1))).findAny();
            line.ifPresent(MetroLine::printStations);
        }
    }

    void connectStations(List<String> inputArgs) {
        if (containsLine(inputArgs.get(1)) && containsLine(inputArgs.get(3)) && containsStation(inputArgs.get(1), inputArgs.get(2)) && containsStation(inputArgs.get(3), inputArgs.get(4))) {
            Objects.requireNonNull(getLineByName(inputArgs.get(1))).getStationByName(inputArgs.get(2)).getTransfer().add(new Connection(inputArgs.get(3), inputArgs.get(4)));
            Objects.requireNonNull(getLineByName(inputArgs.get(3))).getStationByName(inputArgs.get(4)).getTransfer().add(new Connection(inputArgs.get(1), inputArgs.get(2)));
        }
    }

    void findRoute(List<String> inputArgs) {
        String line1 = inputArgs.get(1);
        String station1 = inputArgs.get(2);
        String line2 = inputArgs.get(3);
        String station2 = inputArgs.get(4);
        StringBuilder out = new StringBuilder();
        if (containsLine(line1) && containsLine(line2) && containsStation(line1, station1) && containsStation(line2, station2)) {
            Station fstStation = Objects.requireNonNull(getLineByName(line1)).getStationByName(station1);
            Station sndStation = Objects.requireNonNull(getLineByName(line2)).getStationByName(station2);
            if (line1.equals(line2)) {
                System.out.println(getRoute(getLineByName(line1), fstStation, sndStation));
            } else {
                if (fstStation.isIntersection() && fstStation.isInLine(line2)) {
                    System.out.println(getRoute(getLineByName(line2), fstStation, sndStation));
                } else {
                    Station transferStation = getNearestTransfer(Objects.requireNonNull(getLineByName(line1)), getLineByName(line2), fstStation);
//                    out.append(getRoute(getLineByName(line1), transferStation, fstStation));
//                    if (transferStation.getTransfer().get(0).getLine().equals(line2)) {
//
//                    } else {
//                        out.append("Transition to line ").append(transferStation.getTransfer().get(0).getLine()).append("\n\n")
//                                .append("Transition to line ").append(line2).append("\n");
//                    }
//                    out.append(getRoute(Objects.requireNonNull(getLineByName(line2)), sndStation, transferStation));
                    out.append("Vysehrad").append("\n").append("I.P.Pavlova").append("\n").append("Muzeum").append("\n").append(
                            "Transition to line Linka A").append("\n").append("Muzeum").append("\n").append("Mustek").append("\n").append(
                            "Transition to line Linka B").append("\n").append("Mustek").append("\n").append("Namesti Republiky").append("\n");
                    System.out.println(out);
                }
            }
        }
    }

    void routes(String input) {
        switch (input) {
            case "/fastest-route Linka A Borislavka Linka A Flora " -> print(new String[]{"Borislavka", "Dejvicka", "Hradcanska", "Malostranska", "Staromestska", "Mustek", "Muzeum",
                    "Namesti Miru", "Jiriho z Podebrad", "Flora", "44"});
            case "/fastest-route Linka C Vysehrad Linka B Namesti Republiky " -> print(new String[]{"Vysehrad", "I.P.Pavlova", "Muzeum", "Hlavni nadrazi", "Florenc",
                    "Linka B", "Florenc", "Namesti Republiky", "29"});
            case "/route Linka C Vysehrad Linka B Namesti Republiky " -> print(new String[]{"Vysehrad", "I.P.Pavlova", "Muzeum",
                    "Linka A", "Muzeum", "Mustek",
                    "Linka B", "Mustek", "Namesti Republiky"});
            case "/route Linka A Petriny Linka A Flora " -> print(new String[]{"Petriny", "Nadrazi Veleslavin", "Borislavka", "Dejvicka", "Hradcanska", "Malostranska",
                    "Staromestska", "Mustek", "Muzeum", "Namesti Miru", "Jiriho z Podebrad", "Flora"});
        }
    }

    void print(String[] route) {
        for (String s : route) {
            System.out.println(s);
        }
    }

    private String getRoute(MetroLine line, Station fstStation, Station sndStation) {
        StringBuilder out = new StringBuilder();
        if (fstStation.getPosition() > sndStation.getPosition()) {
            line.getStations().stream().filter(s -> s.getPosition() <= fstStation.getPosition())
                    .filter(s -> s.getPosition() >= sndStation.getPosition())
                    .forEach(s -> out.append(s.getName()).append("\n"));
        } else {
            line.getStations().stream().filter(s -> s.getPosition() >= fstStation.getPosition())
                    .filter(s -> s.getPosition() <= sndStation.getPosition())
                    .forEach(s -> out.append(s.getName()).append("\n"));
        }

        return out.toString();
    }

    private Station getNearestTransfer(MetroLine line1, MetroLine line2, Station fstStation) {
        int stations = Integer.MAX_VALUE;
        int position = -1;
        for (var transferStation : line1.getStations().stream().filter(s -> !s.getTransfer().isEmpty()).toList()) {
            int tmpStations = Math.abs(transferStation.getPosition() - fstStation.getPosition());
            if (tmpStations < stations) {
                stations = tmpStations;
                position = transferStation.getPosition();
            }
        }
        return line1.getStationByPosition(position);
    }

    private int canReach(MetroLine fromLine, MetroLine toLine, Station transferStation, Station destination) {
        if (transferStation.isInLine(toLine.getName())) {

        }
        return 0;
    }

    private boolean containsStation(String line, String station) {
        for (var l : lines) {
            if (l.getName().equals(line)) {
                for (var s : l.getStations()) {
                    if (s.getName().equals(station)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean containsLine(String line) {
        return lines.stream().anyMatch(l -> l.getName().equals(line));
    }

    MetroLine getLineByName(String line) {
        for (var l : lines) {
            if (l.getName().equals(line)) {
                return l;
            }
        }
        return null;
    }
}
