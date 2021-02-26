public class Street implements Comparable<Street>{
    int start, end;
    String name;
    int time;

    int lightTime = 0;
    int carsPassing = 0;

    public Street(int start, int end, String name, int time){
        this.start = start;
        this.end = end;
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString(){
        return  "" + start + " " + end + " " + name + " " + time;
    }

    @Override
    public int compareTo(Street street) {
        return carsPassing - street.carsPassing;
    }
}
