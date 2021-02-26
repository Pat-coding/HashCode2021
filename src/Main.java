

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Main {
    static int D, I, S, V, F;

    static String f1 = "src\\a.txt";
    static String f2 = "src\\b.txt";
    static String f3 = "src\\c.txt";
    static String f4 = "src\\d.txt";
    static String f5 = "src\\e.txt";
    static String f6 = "src\\f.txt";

    static String output1 = "1.in";
    static String output2 = "2.in";
    static String output3 = "3.in";
    static String output4 = "4.in";
    static String output5 = "5.in";
    static String output6 = "6.in";

    static HashMap<Integer, Intersection> intersections = new HashMap<>(); //new
    static HashMap<String, Street> streetNames = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Parser parser = new Parser(new File(f3)); //change input name here

        String[][] data = parser.getData();

        D = Integer.parseInt(data[0][0]);
        I = Integer.parseInt(data[0][1]);
        S = Integer.parseInt(data[0][2]);
        V = Integer.parseInt(data[0][3]);
        F = Integer.parseInt(data[0][4]);

        ArrayList<Street> streets = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();

        for (int i = 0; i < I; i++) {
            intersections.put(i, new Intersection(i));
        }

        for (int i = 1; i <= S; i++) {
            int start = Integer.parseInt(data[i][0]);
            int end = Integer.parseInt(data[i][1]);
            String name = data[i][2];
            int time = Integer.parseInt(data[i][3]);
            Street s = new Street(start, end, name, time);
            streets.add(s);
            streetNames.put(s.name, s);
            intersections.get(start).outgoingStreets.add(name); // new
            intersections.get(end).incomingStreets.add(name); // new
        }

        for (int i = 1; i <= V; i++) {
            int streetsNum = Integer.parseInt(data[i + S][0]);
            ArrayList<String> streetNames = new ArrayList<>();
            for (int j = 1; j <= streetsNum; j++) {
                streetNames.add(data[i + S][j]);
            }
            cars.add(new Car(streetsNum, streetNames));
        }

        HashSet<Intersection> usedIntersection = new HashSet<>();

        HashMap<Street, Integer> Snums = new HashMap<>();
        HashSet<Street> usedStreet = new HashSet<>();
        HashSet<Street> startingS = new HashSet<>();

        for (Car c : cars) {
            startingS.add(streetNames.get(c.names.get(0)));
            for (String s : c.names) {
                if (!Snums.containsKey(streetNames.get(s))) {
                    Snums.put(streetNames.get(s), 1);
                } else {
                    Snums.put(streetNames.get(s), Snums.get(streetNames.get(s)) + 1);
                }
            }
        }

        for (Street s : Snums.keySet()) {
            s.carsPassing = Snums.get(s);
            System.out.println(s.name + " num of cars passing: " + Snums.get(s));
        }

        for (Street k : Snums.keySet()) {
            usedStreet.add(k);
        }

        HashMap<Integer, Integer> hashT = new HashMap<>();
        for (Street s : startingS) {
            if (hashT.containsKey(s.end)) {
                hashT.put(s.end, hashT.get(s.end) + 1);
            } else {
                hashT.put(s.end, 1);
            }
        }

        for (Integer i : hashT.keySet()) {
            System.out.println("Starting intersection: " + i + " " + hashT.get(i));
        }

        for (Street s : streets) {
            if (Snums.get(s) != null) {
                System.out.println(s.name + " num of cars: " + Snums.get(s) + " time: " + s.time);
            }
        }
        System.out.println("startingS: " + startingS.size() + " usedStreets: " + usedStreet.size() + " streets: " + S + " duration: " + D + " cars: " + V + " inter: " + I + " bonus: " + F);


//        output6
//        HashMap<Integer, Integer> hashT = new HashMap<>();
//        for(Street s : startingS){
//            if(hashT.containsKey(s.end)){
//                hashT.put(s.end, hashT.get(s.end) + 1);
//            } else {
//                hashT.put(s.end, 1);
//            }
//        }
//
//        HashMap<Integer, Integer> inters = new HashMap<>();
//        for(Street s : streets){
//            if(Snums.containsKey(s)){
//                if(inters.containsKey(s.end)){
//                    inters.put(s.end, inters.get(s.end) + 1);
//                } else {
//                    inters.put(s.end, 1);
//                }
//            }
//        }
//
//        for(Street s : startingS){
//            if(hashT.get(s.end) > 1){
//                s.lightTime = 2;
//            } else {
//                s.lightTime = 1;
//            }
//            usedIntersection.add(intersections.get(s.end));
//            intersections.get(s.end).usedStreets.add(s);
//        }
//
//        for(Street s : streets){
//            if(Snums.containsKey(s)){
//                if(s.lightTime == 0) {
//                    s.lightTime = 1;
//                    usedIntersection.add(intersections.get(s.end));
//                    intersections.get(s.end).usedStreets.add(s);
//                }
//            }
//        }

        //output5.1


        HashMap<Street, Integer> fu = new HashMap<>();

        for (Car c : cars) {
            Street s = streetNames.get(c.names.get(0));
            if (usedStreet.contains(s)) {
                if (s.carsPassing > 1) {
                    if (fu.containsKey(s)) {
                        fu.put(s, fu.get(s) + 1);
                    } else {
                        fu.put(s, 1);
                    }

                }
            }
            s = streetNames.get(c.names.get(1));
            if (usedStreet.contains(s)) {
                if (s.carsPassing > 2) {
                    if (fu.containsKey(s)) {
                        fu.put(s, fu.get(s) + 1);
                    } else {
                        fu.put(s, 1);
                    }
                }
            }
        }


        HashMap<Integer, Integer> te = new HashMap<>();

        for (Car c : cars) {
            if (c.names.size() > 3) {
                Street s = streetNames.get(c.names.get(3));

                if (te.containsKey(s.end)) {
                    te.put(s.end, te.get(s.end) + 1);
                } else {
                    te.put(s.end, 1);
                }
            }
        }
        for (Integer i : te.keySet()) {
            System.out.println(i + " " + te.get(i));
        }


        for (Street s : fu.keySet()) {
            if(s.lightTime == 0){
                s.lightTime = fu.get(s);
                usedIntersection.add(intersections.get(s.end));
                intersections.get(s.end).usedStreets.add(s);
            }

        }


        for (Street s : streets) {
            if (Snums.containsKey(s)) {
                if (s.lightTime == 0) {
                    s.lightTime = Snums.get(s);
                    usedIntersection.add(intersections.get(s.end));
                    intersections.get(s.end).usedStreets.add(s);
                }
            }
        }


        //output5
//        Collections.sort(streets);
//        for(Street s: streets){
//            if(Snums.containsKey(s)){
//                s.lightTime = 1;
//                usedIntersection.add(intersections.get(s.end));
//                intersections.get(s.end).usedStreets.add(s);
//            }
//        }

//Output4
//        for(Street key : Snums.keySet()){
//            key.lightTime = 1;
//            usedIntersection.add(intersections.get(key.end));
//            intersections.get(key.end).usedStreets.add(key);
//        }

//Output3
//            for(Street key : Snums.keySet()){
//                usedIntersection.add(intersections.get(key.end));
//                intersections.get(key.end).usedStreets.add(key);
//                key.lightTime = Snums.get(key);
//            }


        //Output
        try {
            PrintStream out = new PrintStream(new FileOutputStream(output3)); //change output name here
            System.setOut(out);
        } catch (FileNotFoundException e) {
            System.out.println("Print error!");
        }

        System.out.println(usedIntersection.size());
        for (Intersection i : usedIntersection) {
            System.out.println(i.id);
            System.out.println(i.usedStreets.size());
            for (Street s : i.usedStreets) {
                System.out.println(s.name + " " + s.lightTime);
            }
        }
    }
}
