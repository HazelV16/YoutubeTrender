import java.util.Comparator;

public class YouTubeVideoDescriptionComparator implements Comparator<YouTubeVideo> {
    @Override
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        return v1.getDescription().toLowerCase().length() - v2.getDescription().toLowerCase().length();
    }
}
