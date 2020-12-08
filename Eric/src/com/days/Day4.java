package com.days;

import java.util.HashMap;

public class Day4 {
    private final String[] records;
    private final HashMap<String, String> validators;

    public Day4(String[] records) {
        this.records = records;
        validators = new HashMap<>() {{
            put("byr", "\\b(19[2-8][0-9]|199[0-9]|200[0-2])\\b");
            put("iyr", "\\b(201[0-9]|2020)\\b");
            put("eyr", "\\b(202[0-9]|2030)\\b");
            put("hgt", "\\b(1[5-8][0-9]|19[0-3])(cm)\\b|\\b(59|6[0-9]|7[0-6])(in)\\b");
            put("hcl", "^(#\\w{6})$");
            put("ecl", "^(amb|blu|brn|gry|grn|hzl|oth)$");
            put("pid", "^([0-9]{9})$");
        }};
    }

    public int countValidPassports1() {
        return countValidPassports(this::hasRequiredFields);
    }

    public int countValidPassports2() {
        return countValidPassports(this::isValidPassport);
    }

    private int countValidPassports(IValidator validator) {
        var count = 0;
        var record = new StringBuilder();
        for (var line : records) {
            if (line.isBlank()) {
                if (validator.validate(record.toString()))
                    count++;
                record = new StringBuilder();
            } else
                record.append(" ").append(line);
        }
        return count;
    }

    private boolean hasRequiredFields(String record) {
        return validators.keySet().stream().allMatch(record::contains);
    }

    private boolean isValidPassport(String record) {
        return hasRequiredFields(record) &&
                validators.entrySet().stream().allMatch(x -> isValidEntry(x.getKey(), x.getValue(), record));
    }

    private boolean isValidEntry(String key, String val, String record) {
        var entry = record.substring(record.indexOf(key) + key.length() + 1).split(" ")[0];
        return entry.matches(val);
    }

    private interface IValidator {
        boolean validate(String record);
    }
}


