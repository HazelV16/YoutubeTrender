import java.util.*;

public class YouTubeVideo {
    private String channel; //channelTitle
    private String date; //publishedAt
    private String title; //title
    private String description; //description
    private int viewCount;
    private String id; //channelId

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getViewCount() {
        return viewCount;
    }

    public String getId() {
        return id;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return  "channel = " + channel + "; " + "date = " + date + "; " + "title = " + title +  "; " + "description = " + description +  "; " + "viewCount = " + viewCount +  "; " + "id = " + id +"\n";
    }
}
