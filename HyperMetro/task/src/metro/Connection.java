package metro;

public class Connection {
    private String line;
    private String station;

    public Connection(String line, String station) {
        this.line = line;
        this.station = station;
    }

    public String getLine() {
        return line;
    }

    public String getStation() {
        return station;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
