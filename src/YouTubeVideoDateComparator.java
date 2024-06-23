import java.util.Comparator;

public class YouTubeVideoDateComparator  implements Comparator<YouTubeVideo> {

    @Override
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        return v1.getDate().compareTo(v2.getDate());
    }


}
