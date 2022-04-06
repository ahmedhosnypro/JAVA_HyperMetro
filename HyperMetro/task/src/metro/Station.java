package metro;

import java.util.ArrayList;
import java.util.Objects;

public class Station {
    private String name;
    private ArrayList<Connection> transfer;

    public Station(String name, ArrayList<Connection> connections) {
        this.name = name;
        this.transfer = connections;
    }

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        final String separator = " - ";
        if (Objects.isNull(transfer) || transfer.isEmpty()) {
            return name;
        } else {
            return name + separator + transfer.get(0).getStation() + "(" + transfer.get(0).getLine() + " line)";
        }
    }
}
