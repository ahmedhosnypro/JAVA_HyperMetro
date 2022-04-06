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
        if (containsLine(inputArgs.get(1)) && containsLine(inputArgs.get(3))
                && containsStation(inputArgs.get(1), inputArgs.get(2))
                && containsStation(inputArgs.get(3), inputArgs.get(4))) {
            Objects.requireNonNull(getLineByName(inputArgs.get(1))).getStationByName(inputArgs.get(2))
                    .getTransfer().add(new Connection(inputArgs.get(3), inputArgs.get(4)));
            Objects.requireNonNull(getLineByName(inputArgs.get(3))).getStationByName(inputArgs.get(4))
                    .getTransfer().add(new Connection(inputArgs.get(1), inputArgs.get(2)));
        }
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

    private MetroLine getLineByName(String line) {
        for (var l : lines) {
            if (l.getName().equals(line)) {
                return l;
            }
        }
        return null;
    }


}
