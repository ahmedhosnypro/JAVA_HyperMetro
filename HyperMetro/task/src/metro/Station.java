package metro;

import java.util.ArrayList;
import java.util.Objects;

public class Station {
    private String name;
    private ArrayList<Connection> transfer;
    private int position;
    private int time;

    public Station(String name, ArrayList<Connection> connections) {
        this.name = name;
        if (Objects.isNull(connections)) {
            transfer = new ArrayList<>();
        } else {
            this.transfer = connections;
        }
    }

    public Station(String name, ArrayList<Connection> transfer, int time) {
        this.name = name;
        this.transfer = transfer;
        this.time = time;
    }

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public ArrayList<Connection> getTransfer() {
        return transfer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransfer(ArrayList<Connection> transfer) {
        this.transfer = transfer;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        final String separator = " - ";
        if (Objects.isNull(transfer) || transfer.isEmpty()) {
            return name;
        } else {
            return name + separator + transfer.get(0).getStation() + "(" + transfer.get(0).getLine() + " line)";
        }
    }

    boolean isIntersection() {
        return !Objects.isNull(transfer) && !transfer.isEmpty();
    }

    boolean isInLine(String line) {
        if (Objects.isNull(transfer) || transfer.isEmpty()) {
            return false;
        } else {
            for (var connection : transfer) {
                if (connection.getLine().equals(line)) {
                    return true;
                }
            }
        }
        return false;
    }

}
