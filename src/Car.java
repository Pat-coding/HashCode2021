import java.util.ArrayList;

public class Car implements Comparable<Car>{
    int streets;
    public ArrayList<String> names;

    public Car(int streets, ArrayList<String> names){
        this.streets = streets;
        this.names = names;
    }

    @Override
    public String toString(){
        return  "" + streets + " " + names.toString();
    }

    @Override
    public int compareTo(Car car) {
        return names.size() - car.names.size();
    }
}
