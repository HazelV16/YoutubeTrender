import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class YouTubeApp {
    YouTubeDataParser instance = new YouTubeDataParser();

    List<YouTubeVideo> list = new ArrayList<>();
    List<YouTubeVideo> sortList = new ArrayList<>();

    public void parsingYouTubeVideo() throws YouTubeDataParserException {
        list.clear();
        Scanner in = new Scanner(System.in);
        boolean input = true;
        boolean retry = true;
        do {
            System.out.println("Enter the path of a Youtube file you want to parse: ");
            String fileName = in.nextLine();
            if (fileName.equalsIgnoreCase("n")) {
                input = false;
                System.out.println("Terminate program");
                System.exit(0);
            } else {
                try {
                    list = instance.parse(fileName);
//                    System.out.println(list);
                    input = false;
                } catch (YouTubeDataParserException e) {
                    System.err.println("Error: " + e.getMessage());
                    do {
                        System.out.println("Do you want to try again? (Y/N) ");
                        String retryInput = in.nextLine();
                        if (!retryInput.equalsIgnoreCase("Y")) {
                            System.out.println("Invalid input. Please enter Y or N");
                            retry = false;
                        }
                        if(retryInput.equalsIgnoreCase("y")) {
                            retry=true;
                        }
                    } while (!retry);
                }
            }
        } while(input);
    }

    public void sorting() {
        boolean continueSorting = true;
//        List<YouTubeVideo> sortList;
        while (continueSorting) {
            do {
                System.out.println("\n---SORT MENU---\n" +
                        "1. Sort by Published Date\n" +
                        "2. Sort by Channel Title\n" +
                        "3. Sort by Number of Views\n" +
                        "4. Sort by Description Word Count\n" +
                        "5. Exiting the sorting program");

                YouTubeSort sort = new YouTubeSort();
                Scanner in = new Scanner(System.in);
                switch (in.nextInt()) {
                    case 1 -> {
                        sortList=sort.sortByDate(list);
//                        System.out.println("Do you want to print the result of video sort by date? (Y/any key to exit) ");
//                        String choice = in.next();
//                        if(choice.equalsIgnoreCase("y")) {
//                            System.out.println("sort by date id " + sortList.get(0).getId() +"\n" +
//                                    "sort by date channel " + sortList.get(0).getChannel() + "\n" +
//                                    "sort by date title " + sortList.get(0).getTitle() + "\n" +
//                                    "sort by date date " + sortList.get(0).getDate() +"\n"+
//                                    "sort by date views " + sortList.get(0).getViewCount() + "\n");
//                        }
                            continueSorting = askToContinueSorting(in);

                    }
                    case 2 -> {
                        sortList = sort.sortByChannel(list);
//                        System.out.println("Do you want to print the result of video sort by channel name? (Y/any key to exit) ");
//                        String choice = in.next();
//                        if(choice.equalsIgnoreCase("y")) {
//                            System.out.println("sort by channel name id " + sortList.get(0).getId() +"\n" +
//                                    "sort by channel name channel " + sortList.get(0).getChannel() + "\n" +
//                                    "sort by channel name title " + sortList.get(0).getTitle() + "\n" +
//                                    "sort by channel name date " + sortList.get(0).getDate() +"\n"+
//                                    "sort by channel name views " + sortList.get(0).getViewCount() +"\n");
//                        }
                        continueSorting = askToContinueSorting(in);
                    }
                    case 3 -> {
                        sortList = sort.sortByViewCount(list);
//                        System.out.println("Do you want to print the result of video sort by views? (Y/any key to exit) ");
//                        String choice = in.next();
//                        if(choice.equalsIgnoreCase("y")) {
//                            System.out.println("sort by views id " + sortList.get(0).getId() +"\n" +
//                                    "sort by views channel " + sortList.get(0).getChannel() + "\n" +
//                                    "sort by views title " + sortList.get(0).getTitle() + "\n" +
//                                    "sort by views date " + sortList.get(0).getDate() +"\n"+
//                                    "sort by views views " + sortList.get(0).getViewCount() +"\n");
//                        }
                        continueSorting = askToContinueSorting(in);
                    }
                    case 4 -> {
                        sortList = sort.sortByDescriptionLength(list);
//                        System.out.println("Do you want to print the result of video sort by description length? (Y/any key to exit) ");
//                        String choice = in.next();
//                        if(choice.equalsIgnoreCase("y")) {
//                            System.out.println("sort by description length id " + sortList.get(0).getId() +"\n" +
//                                    "sort by description length channel " + sortList.get(0).getChannel() + "\n" +
//                                    "sort by description length title " + sortList.get(0).getTitle() + "\n" +
//                                    "sort by description length date " + sortList.get(0).getDate() +"\n"+
//                                    "sort by description length views " + sortList.get(0).getViewCount() +"\n");
//                        }
                        continueSorting = askToContinueSorting(in);
                    }
                    case 5 -> {
                        continueSorting = false;
                    }

                    default -> {
                        System.out.println("Can't understand command");
                        continueSorting = askToContinueSorting(in);
                    }
                }
//                System.out.println("Do ypu want to print the sort list? (Y/N) ");
//                String response = in.nextLine();
//                if(response.equalsIgnoreCase("y")) {
//                    System.out.println(list);
//                }
            } while (continueSorting);
        }
    }

    private boolean askToContinueSorting(Scanner in) {
        System.out.println("Do you want to continue sorting (Y/N) or print the sort list (P)? ");
        String response = in.next().trim().toLowerCase();
//        in.nextLine();
//        return response.equalsIgnoreCase("y");
        if (response.equalsIgnoreCase("y")) {
            return true;
        } else if (response.equalsIgnoreCase("n")) {
            System.out.println("Exiting the sorting program.");
            return false;
        }
        else if (response.equalsIgnoreCase("p")) {
            System.out.println(sortList);

            return askToContinueSorting(in);
        }
        else {
            System.out.println("Invalid input. Please enter Y or N");
            return askToContinueSorting(in); // Ask again for valid input
        }
    }

    public void trending() {
        YouTubeVideoIndexer indexer = new YouTubeVideoIndexer();
        indexer.index(list);
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        boolean continueAnalysis = true;
        String word = null;
        int option;

        while (continueAnalysis) {
            do {
                System.out.println("\n---TRENDING TOPICS ANALYSIS MENU---\n" +
                        "0. Enter a word/another word that you want to find \n" +
                        "1. Find the count associated with a word\n" +
                        "2. Find all the videos that use a word\n" +
                        "3. Find the word that is used the most\n" +
                        "4. Create a list of words sorted by their counts\n" +
                        "5. Exit the trending topics analysis menu\n" +
                        "Choose 1 - 5 if you already chose option 0");
                    option = scanner.nextInt();
                    if (option == 1 || option == 2) {
                        while (word == null) {
                            System.out.println("Please enter a word first before selecting options 1 or 2.");
                            System.out.println("Enter the word you want to find: ");
                            word = scanner.next();
                            if (indexer.findWord(word) == null) {
                                System.out.println("Word not found. Please enter a valid word.");
                                word = null;
                            }
                            continue;
                        }
                    }

                    switch (option) {
                        case 0:
                            while (word == null) {
                                System.out.println("Enter the word you want to find: ");
                                word = scanner.next();
                                if (indexer.findWord(word) == null) {
                                    System.out.println("Word not found. Please enter a valid word.");
                                    word = null;
                                }
                            }
                            break;
                        case 1:
                            indexer.wordAndCount(word);
                            continueAnalysis = askToContinueAnalysis(in);
                            break;
                        case 2:
                            indexer.printAssociatedVideos(word);
                            System.out.println();
                            continueAnalysis = askToContinueAnalysis(in);
                            break;
                        case 3:
                            List<String> mostUsedWords = indexer.findMostUsedWords();
                            if (!mostUsedWords.isEmpty()) {
                                System.out.print("Most Used Words: ");
                                for (String i : mostUsedWords) {
                                    System.out.println(i);
                                }
                            } else {
                                System.out.println("No words found.");
                            }
                            continueAnalysis = askToContinueAnalysis(in);
                            break;
                        case 4:
                            List<YouTubeWordItem> sortedWords = indexer.getSortedYouTubeWordItems();
                            System.out.println("Words Sorted by Counts:");
                            for (YouTubeWordItem i : sortedWords) {
                                System.out.println(i);
                            }
                            continueAnalysis = askToContinueAnalysis(in);
                            break;
                        case 5:
                            continueAnalysis = false;
                            break;
                        default:
                            System.out.println("Invalid option. Please select a valid option from the menu.");
                            continueAnalysis = askToContinueAnalysis(in);
                            break;
                    }
            } while (continueAnalysis);
        }
    }

    private static boolean askToContinueAnalysis(Scanner in) {
        System.out.print("Do you want to continue analyzing trending topics? (Y/N): ");
        String response = in.next().trim().toLowerCase();

            if (response.equalsIgnoreCase("Y")) {
                return true;
            } else if (response.equalsIgnoreCase("N")) {
                System.out.println("Exiting trending analysis menu");
                return false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
                return askToContinueAnalysis(in);
            }
    }
}
