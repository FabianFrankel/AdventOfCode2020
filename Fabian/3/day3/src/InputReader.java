import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputReader {

    public InputReader(){
        //adassd
    }
    
    public String[] getArrayOfinputLines(String filename){
        try {
            return Files
                    .readAllLines(Paths.get("./src/" + filename + ".txt"))
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    };
};
