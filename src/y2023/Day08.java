package y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Day08 {
    public static void main(String[] args) throws IOException {
        List<String> testInput = readInput("Day08_test_part1");
        System.out.println(part1(testInput));
//        System.out.println(part2(testInput));

        List<String> input = readInput("Day08");
        System.out.println(part1(input));
//        System.out.println(part2(input));
    }

    private static List<String> readInput(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    private static String pickDirection(Pair<String, String> node, char dir) {
        return 'L' == dir ? node.left : node.right;
    }

    private static int part1(List<String> input) {
        String pattern = input.get(0);
        Iterator<Character> ruleSeq = new Iterator<>() {

            private int index = 0;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Character next() {
                char res = pattern.charAt(index);
                index = (index + 1) % pattern.length();
                return res;
            }
        };
        Map<String, Pair<String, String>> map = new HashMap<>();
        input.stream().skip(2).filter(s -> !s.isBlank()).forEach(s ->{
            String key = s.substring(0, s.indexOf(" ="));
            String[] parts = s.substring(s.indexOf("= (") + 3, s.indexOf(")")).split(", ");
            map.put(key, new Pair<>(parts[0], parts[1]));
        });

        String node = "AAA";
        int count = 0;
        while (!"ZZZ".equals(node)) {
            node = pickDirection(map.get(node), ruleSeq.next());
            count++;
        }
        return count;
    }

    record Pair<L, R>(L left, R right) { }
}

