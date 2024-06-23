import java.util.List;

public class YouTubeSort {
    public List<YouTubeVideo> sortByDate(List<YouTubeVideo> video) {
        video.sort(new YouTubeVideoDateComparator());
        return video;
    }

    public List<YouTubeVideo> sortByChannel(List<YouTubeVideo> video) {
        video.sort(new YouTubeVideoChannelComparator());
        return video;
    }

    public List<YouTubeVideo> sortByViewCount(List<YouTubeVideo> video) {
        video.sort(new YouTubeVideoViewComparator());
        return video;
    }

    public List<YouTubeVideo> sortByDescriptionLength(List<YouTubeVideo> video) {
        video.sort(new YouTubeVideoDescriptionComparator());
        return video;
    }
}
