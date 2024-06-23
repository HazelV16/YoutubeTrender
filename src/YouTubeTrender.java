import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class YouTubeTrender {

    public static void test1() throws FileNotFoundException {

        System.out.println("Performing Test 1");
        String filename = "data/youtubedata.json";
        int expectedSize = 50;

        System.out.println("Testing the file: " + filename);
        System.out.println("Expecting size of: " + expectedSize);

        // Read data
        JsonReader jsonReader = Json.createReader(new FileInputStream(filename));
        JsonObject jobj = jsonReader.readObject();

        // read the values of the item field
        JsonArray items = jobj.getJsonArray("items");

        System.out.println("Size of input: " + items.size());
        System.out.println("Sucess: " + (expectedSize == items.size()));
    }

/*    public static void test2() throws YouTubeDataParserException {

        System.out.println("Performing Test 2");
        String filename = "data/youtubedata_loremipsum.json";
        int expectedSize = 10;

        System.out.println("Testing the file: " + filename);
        System.out.println("Expecting size of: " + expectedSize);

        YouTubeDataParser parser = new YouTubeDataParser();

        List<YouTubeVideo> list = parser.parse(filename);

        System.out.println("Found size: " + list.size());

    }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, YouTubeDataParserException {
        System.out.println("YouTube Trender Application");

//        test1();

        Scanner in = new Scanner(System.in);
        YouTubeApp cli = new YouTubeApp();

        boolean runMenu = true;
//        boolean runFile = true;

            cli.parsingYouTubeVideo();

            do {
                System.out.println("\n---ACTION MENU---\n" +
                        "1. Sort videos by different features\n" +
                        "2. Analyse trending topics\n" +
                        "3. Change new JSON file\n" +
                        "4. Terminate the program");


                switch (in.nextInt()) {
                    case 1:
                        cli.sorting();
                        break;
                    case 2:
                        cli.trending();
                        break;
                    case 3:
                        cli.parsingYouTubeVideo();
                        break;
                    case 4:
                        System.exit(0);
                }

            }
            while (runMenu);


    }
}
