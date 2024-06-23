import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class YouTubeWordItem implements Comparable<YouTubeWordItem> {
    private String word;
    private int count;
    private Set<YouTubeVideo> videos = new HashSet<>();

    public void add(YouTubeVideo a) {
        videos.add(a);
    }

    public int getCount() {
        return count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void incrementCount(){
        count++;
    }

    public Set<YouTubeVideo> getVideos() {
        return videos;
    }

    @Override
    public String toString() {
        return word+": "+count;
    }

    @Override
    public int compareTo(YouTubeWordItem o) {
        return Integer.compare(o.getCount(), this.getCount());
    }

}
