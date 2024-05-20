/*
Our local radio station is running a show where the songs are ordered in a very specific way.
The last word of the title of one song must match the first word of the title of the next song - for example, "Silent Running" could be followed by "Running to Stand Still".  No song may be played more than once.

Given a list of songs and a starting song, find the longest chain of songs that begins with that song, and the last word of each song title matches the first word of the next one.
Write a function that returns the longest such chain. If multiple equivalent chains exist, return any of them.

Example:
songs1 = [
    "Down By the River",
    "River of Dreams",
    "Take me to the River",
    "Dreams",
    "Blues Hand Me Down",
    "Forever Young",
    "American Dreams",
    "All My Love",
    "Cantaloop",
    "Take it All",
    "Love is Forever",
    "Young American",
    "Every Breath You Take",
]
song1_1 = "Every Breath You Take"
chaining(songs1, song1_1) => ['Every Breath You Take', 'Take it All', 'All My Love', 'Love is Forever', 'Forever Young', 'Young American', 'American Dreams', 'Dreams']

Additional Input:
song1_2 = "Dreams"
song1_3 = "Blues Hand Me Down"
song1_4 = "Cantaloop"

songs2 = [
    "Bye Bye Love",
    "Nothing at All",
    "Money for Nothing",
    "Love Me Do",
    "Do You Feel Like We Do",
    "Bye Bye Bye",
    "Do You Believe in Magic",
    "Bye Bye Baby",
    "Baby Ride Easy",
    "Easy Money",
    "All Right Now",
]
song2_1 = "Bye Bye Bye"
song2_2 = "Bye Bye Love"

songs3 = [
    "Love Me Do",
    "Do You Believe In Magic",
    "Magic You Do",
    "Magic Man",
    "Man In The Mirror"
]
song3_1 = "Love Me Do"

All Test Cases:
chaining(songs1, song1_1) => ['Every Breath You Take', 'Take it All', 'All My Love', 'Love is Forever', 'Forever Young', 'Young American', 'American Dreams', 'Dreams']
chaining(songs1, song1_2) => ['Dreams']
chaining(songs1, song1_3) => ['Blues Hand Me Down', 'Down By the River', 'River of Dreams', 'Dreams']
chaining(songs1, song1_4) => ['Cantaloop']
chaining(songs2, song2_1) => ['Bye Bye Bye', 'Bye Bye Baby', 'Baby Ride Easy', 'Easy Money', 'Money for Nothing', 'Nothing at All', 'All Right Now']
chaining(songs2, song2_2) => ['Bye Bye Love', 'Love Me Do', 'Do You Feel Like We Do', 'Do You Believe in Magic']
chaining(songs3, song3_1) => ['Love Me Do', 'Do You Believe in Magic', 'Magic Man', 'Man In The Mirror']

Complexity Variable:
n = number of songs in the input

idea:
tested in Twilio karat

typical graph, build the graph first
then kind of bfs
note to make a new copy of each chain, kind of dfs
https://python.plainenglish.io/python-riddle-getting-longest-possible-chains-from-list-of-words-869681bdff79
*/
import java.util.*;

class LongestSongs {
    public static void main(String[] args) {
        LongestSongs eg = new LongestSongs();
        List<String> songs = new ArrayList<>();
        songs.add("Down By the River");
        songs.add("River of Dreams");
        songs.add("Take me to the River");
        songs.add("Dreams");
        songs.add("Blues Hand Me Down");
        songs.add("Forever Young");
        songs.add("American Dreams");
        songs.add("All My Love");
        songs.add("Cantaloop");
        songs.add("Take it All");
        songs.add("Love is Forever");
        songs.add("Young American");
        songs.add("Every Breath You Take");
    
        List<String> result = eg.chaining(songs, "Every Breath You Take");
        System.out.println(result);

        result = eg.chaining(songs, "Dreams");
        System.out.println(result);

        result = eg.chaining(songs, "Blues Hand Me Down");
        System.out.println(result);

        result = eg.chaining(songs, "Cantaloop");
        System.out.println(result);
    }

    public String getLastWord(String s) {
        String[] matches = s.split("\\s+");
        return matches[matches.length - 1];
    }

    public String getFirstWord(String s) {
        String[] matches = s.split("\\s+");
        return matches[0];
    }

    public List<String> chaining(List<String> songs, String start) {
        // each song as key, value will be a list of songs starting with that last word of that song
        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0; i < songs.size(); i++)  {
            String song = songs.get(i);
            String lastWordOfTheSong = getLastWord(song);
            for (int j = 0; j < songs.size(); j++) {
                String otherSong = songs.get(j);
                String firstWordOfOtherSong = getFirstWord(otherSong);
                if (i != j) {
                    if (lastWordOfTheSong.equals(firstWordOfOtherSong)) {
                        graph.computeIfAbsent(song, x -> new ArrayList<>()).add(otherSong);
                    }
                }
            }
        }

        // System.out.println(graph);
        Queue<List<String>> queue = new LinkedList<>();
        List<List<String>> chains = new ArrayList<>();

        List<String> chain = new ArrayList<>();
        chain.add(start);
        queue.add(chain);
        
        while (!queue.isEmpty()) {
            List<String> currentChain = queue.poll();
            String lastSong = currentChain.get(currentChain.size() - 1);
            List<String> nextSongs = graph.get(lastSong);

            if (nextSongs == null) {
                chains.add(currentChain);
            } else {
                for (String nextSong : nextSongs) {
                    List<String> copyCurrentChain = new ArrayList<>();
                    copyCurrentChain.addAll(currentChain);
                    copyCurrentChain.add(nextSong);
                    queue.add(copyCurrentChain);
                }
            }
        }

        System.out.println(chains);

        int maxLen = 0;
        List<String> result = null;

        for (List<String> c : chains) {
            if (maxLen < c.size()) {
                maxLen = c.size();
                result = c;
            }
        }

        return result;
    }
}
