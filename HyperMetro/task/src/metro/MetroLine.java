package metro;

import java.util.LinkedList;

public class MetroLine {
    private final String name;
    private final LinkedList<Station> stations = new LinkedList<>();

    public MetroLine(String name) {
        this.name = name;
    }

    public LinkedList<Station> getStations() {
        return stations;
    }

    public String getName() {
        return name;
    }

    void printStations() {

        final String depot = "depot";
        System.out.println(depot);
        for (var station : stations) {
            System.out.println(station.toString());
        }
        System.out.println(depot);
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    Station getStationByName(String station) {
        for (var s : stations) {
            if (s.getName().equals(station)) {
                return s;
            }
        }
        return null;
    }

    Station getStationByPosition(int position) {
        for (var s : stations) {
            if (s.getPosition() == position) {
                return s;
            }
        }
        return null;
    }
}
