import java.util.*;
import java.util.regex.Pattern;

public class YouTubeVideoIndexer {
    private List<YouTubeVideo> items = new ArrayList<>();
    private Map<String, YouTubeWordItem> words = new HashMap<>();
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("\\p{Punct}");
    private int countVideo = 0;
//    private String mostUsed = null;
    public void index(List<YouTubeVideo> lists) {
        for(YouTubeVideo video : lists) {
            String[] videoString = extractWords(video.getTitle() + " " + video.getDescription());
//            for (int j = 0; j < videoString.length; j++) {
//                videoString[j] = removePunctuation(videoString[j]);
//            }
            for (String string : videoString){
                if (words.containsKey(string)){
                    words.get(string).incrementCount();
                    words.get(string).add(video);
                }  else {
                    YouTubeWordItem wordItem = new YouTubeWordItem();
                    wordItem.setWord(string);
                    wordItem.incrementCount();
                    wordItem.add(video);
                    words.put(string, wordItem);
                }
            }
        }
    }

    public void wordAndCount(String word){
        YouTubeWordItem wordItem = findWord(word);
        if(wordItem!=null) {
            System.out.println("Word: " + wordItem.getWord());
            System.out.println("Count: " + wordItem.getCount());
        }
//        return word;
    }


    // method to print any video which have the requested word
    public void printAssociatedVideos(String word) {
        // Find the YouTubeWordItem for the specified word
        YouTubeWordItem wordItem = findWord(word);

        if (wordItem != null) {
//            System.out.println("Word: " + wordItem.getWord());
//            System.out.println("Count: " + wordItem.getCount());
            System.out.println("Associated Videos:");

            for (YouTubeVideo video : wordItem.getVideos()) {
                countVideo++;
            }
            System.out.println("Number of associated videos: "+countVideo);

            for (YouTubeVideo video : wordItem.getVideos()) {
                System.out.print(/*"Video number " + countVideo + ": " +*/ video.getId() + ", ");
            }

            if (wordItem.getVideos().isEmpty()) {
                System.out.println("No associated videos found for the word: " + word);
            }
        } else {
            System.out.println("Word not found: " + word);
        }
    }

    // method to find the word
    public YouTubeWordItem findWord(String word) {
        // Remove punctuation from the word
//        String cleanedWord = removePunctuation(word);
        return words.get(word);
    }

    // method to extract each word
    public String[] extractWords(String text) {
        // Split text into words without any cleaning
        return text.split("\\s+");
    }

    // method to remove any punctuation
    public String removePunctuation(String word) {
        // Define a regular expression to match punctuation symbols
        String punctuationPattern = "[\\p{Punct}]";

        // Remove punctuation from the word
        return word.replaceAll(punctuationPattern, "");
    }

    // method to find the word that is used the most
    // there might be some words have the same number of existence and it can happen to be most used word. So, I used List instead of just string

    public List<String> findMostUsedWords() {
        List<String> mostUsedWords = new ArrayList<>();
        int mostUsedWordCount = 0;

        for (String i : words.keySet()) {
            YouTubeWordItem wordItem = words.get(i);
            int count = wordItem.getCount();

            // there are some blank spaces which the program recognises as words s
            // Skip blank space words
            if (i != null && !i.trim().isEmpty()) {
                if (count > mostUsedWordCount) {
                    mostUsedWords.clear();
                    mostUsedWords.add(i);
                    mostUsedWordCount = count;
                } else if (count == mostUsedWordCount) {
                    mostUsedWords.add(i);
                }
            }

        }
        System.out.println("The number of appearance: " + mostUsedWordCount);
        return mostUsedWords;
    }

    //create a list of words sorted by their counts
    public List<YouTubeWordItem> getSortedYouTubeWordItems() {
        List<YouTubeWordItem> sortCount = new ArrayList<>(words.values());
        sortCount.removeIf(wordItem -> wordItem.getWord() == null || wordItem.getWord().trim().isEmpty());
        sortCount.sort((a1, a2) -> Integer.compare(a2.getCount(), a1.getCount()));
        return sortCount;
    }
}
