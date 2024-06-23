import javax.json.*;
import javax.json.stream.JsonParsingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class YouTubeDataParser {
    public List<YouTubeVideo> parse(String filename) throws YouTubeDataParserException {
        List<YouTubeVideo> list = new ArrayList<>();
        try {
//            read the data
            JsonReader data = Json.createReader(new FileInputStream(filename));
            JsonObject jobj = data.readObject();
            // read the values of the item field
            JsonArray items = jobj.getJsonArray("items");
//            System.out.println("item size = " + items.size());

            // loop for running all JSON objects in JSON array
            for (int i=0; i<items.size(); i++) {
                JsonObject video = items.getJsonObject(i);
                YouTubeVideo videoObject = new YouTubeVideo();

                JsonObject snippet = video.getJsonObject("snippet");
                videoObject.setChannel(snippet.getString("channelTitle"));
                videoObject.setDate(snippet.getString("publishedAt"));
                videoObject.setDescription(snippet.getString("description"));
                videoObject.setTitle(snippet.getString("title"));
                videoObject.setId(snippet.getString("channelId"));
                // create obj for data in 'statistics'
                // json file: item -> statistics
                JsonObject statistics = video.getJsonObject("statistics");
                // parse to 'viewCount' variable of YouTubeVideo object
                videoObject.setViewCount(Integer.parseInt(statistics.getString("viewCount")));
                JsonValue viewCountValue = statistics.get("viewCount");
                if (viewCountValue != null && viewCountValue.getValueType() == JsonValue.ValueType.NUMBER) {
                    videoObject.setViewCount(((JsonNumber) viewCountValue).intValue());
                }
                // add the parsed YouTubeVideo object to list
                list.add(videoObject);

            } // end for loop


        } catch (FileNotFoundException e) {
            throw new YouTubeDataParserException("File not found: " + filename);
        } catch (JsonParsingException e){
            throw new YouTubeDataParserException("Parsing Error >>> Malformed Data at "+ e.getLocation());
        }
        return list;
    }
}
