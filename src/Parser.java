import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    File file;

    public Parser(File file){
        this.file = file;
    }

    public String[][] getData() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> dataList = new ArrayList<>();
        while (scanner.hasNextLine())
        {
            dataList.add(lineSplit(scanner.nextLine()));
        }

        String[][] data = new String[dataList.size()][];
        for(int i = 0; i < data.length; i++){
            data[i] = dataList.get(i);
        }
        return data;
    }

    public String[] lineSplit(String input){
        String[] tokens = input.split(" ");
        return tokens;
    }


}
