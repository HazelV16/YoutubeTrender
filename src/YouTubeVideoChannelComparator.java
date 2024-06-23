import java.util.Comparator;

public class YouTubeVideoChannelComparator implements Comparator<YouTubeVideo> {

    @Override
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        return v1.getChannel().toLowerCase().compareTo(v2.getChannel().toLowerCase());
    }
}
