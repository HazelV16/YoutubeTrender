import java.util.Comparator;

public class YouTubeVideoViewComparator implements Comparator<YouTubeVideo> {

    @Override
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        return v1.getViewCount()-v2.getViewCount();
    }
}
