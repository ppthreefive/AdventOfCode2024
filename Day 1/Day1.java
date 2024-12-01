/** Author: Phillip Pham
 *  Link to question: https://adventofcode.com/2024/day/1
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Day1 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Must provide valid filepath as the only argument.");
        }

        String filePath = args[0];
        File file = new File(filePath);

        if (!file.isFile()) {
            System.err.println("Invalid filepath");
            System.exit(1);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            List<String> columnFirst = new ArrayList<>();
            List<String> columnSecond = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split("\\s+");
                columnFirst.add(splitLine[0]);
                columnSecond.add(splitLine[1]);
            }

            System.out.println("Total distance between lists: " + findTotalDistance(columnFirst, columnSecond));
            System.out.println("Similarity score between lists: " + findSimilarityScore(columnFirst, columnSecond));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static int findTotalDistance(List<String> first, List<String> second) {
        int totalDistance = 0;

        if (first.isEmpty() || second.isEmpty() || first.size() != second.size()) {
            return -1;
        }

        Collections.sort(first);
        Collections.sort(second);

        for (int i = 0; i < first.size(); i++) {
            totalDistance += Math.abs(Integer.parseInt(first.get(i)) - Integer.parseInt(second.get(i)));
        }

        return totalDistance;
    }

    public static int findSimilarityScore(List<String> first, List<String> second) {
        int similarityScore = 0;

        if (first.isEmpty() || second.isEmpty()) {
            return -1;
        }

        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String s: second) {
            frequencyMap.merge(s, 1, Integer::sum);
        }

        for (String s: first) {
            if(frequencyMap.containsKey(s)) {
                similarityScore += Integer.parseInt(s) * frequencyMap.get(s);
            }
        }

        return similarityScore;
    }
}