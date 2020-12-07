package com.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {
    private final String[] bagRules;
    private final HashMap<String, List<BagRule>> ruleMap;

    public Day7(String[] bagRules) {
        this.bagRules = bagRules;
        this.ruleMap = populateMap();

    }

    private HashMap<String, List<BagRule>> populateMap() {
        var ruleMap = new HashMap<String, List<BagRule>>();

        for (var rule : bagRules) {
            var splitArr = trimDeLaTrim(rule).split("contain");
            var key = splitArr[0].trim();
            var bagRules = parseBagRules(splitArr[1]);

            if (ruleMap.containsKey(key)) // add a rule to the bag if we've already found it
                addIfAbsent(ruleMap.get(key), bagRules);
            else
                ruleMap.put(key, bagRules);
        }
        return ruleMap;
    }

    private List<BagRule> parseBagRules(String content) {
        return Arrays.stream(content.split(","))
                .filter(x -> !x.contains("no other")) // filters leaf-bags– bags not containing other bags since we don't need to map any rules to them
                .map(x -> new BagRule(Integer.parseInt(x.substring(1, 2)), x.substring(3, x.length() - 1)))
                .collect(Collectors.toList());
    }

    public int puzzle1(String[] targetBags) {
        var foundBags = ruleMap.entrySet().stream()
                .filter(x -> x.getValue().stream() // uniquely filters bags that resides within the any element in targetBags
                        .anyMatch(y -> Arrays.stream(targetBags)
                                .anyMatch(z -> y.bag().equals(z)))
                        && Arrays.stream(targetBags).noneMatch(k -> k.contains(x.getKey())))
                .map(Map.Entry::getKey)
                .toArray(String[]::new);

        var concArr = Stream.concat(Arrays.stream(foundBags), Arrays.stream(targetBags)).toArray(String[]::new); // concats targetBags with foundBags

        if (targetBags.length != concArr.length) // if we've found more bags than the targetBags, keep recursing
            return puzzle1(concArr);

        return concArr.length - 1; // return l-1 to exclude the original targetBag (shiny gold)
    }

    public int puzzle2(String bag) {
        return ruleMap.get(bag).stream()
                .map(x -> x.quantity() + x.quantity() * puzzle2(x.bag()))
                .reduce(0, Integer::sum);
    }

    private String trimDeLaTrim(String input) {
        return input.trim().replace("bags", "").replace("bag", "").replace(".", "");
    }

    private void addIfAbsent(List<BagRule> list, List<BagRule> elements) {
        for (var e : elements)
            if (list.stream().map(BagRule::bag).anyMatch(x -> x.contains(e.bag())))
                list.add(e);
    }
}

record BagRule(int quantity, String bag) {
}