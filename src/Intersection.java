import java.util.ArrayList;
import java.util.HashSet;

public class Intersection {
    int id;

    ArrayList<String> incomingStreets;
    ArrayList<String> outgoingStreets;

    HashSet<Street> usedStreets;

    public Intersection(int id){
        this.id = id;
        incomingStreets = new ArrayList<>();
        outgoingStreets = new ArrayList<>();
        usedStreets = new HashSet<>();
    }
}
