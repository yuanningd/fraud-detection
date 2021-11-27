package com.dong;

import com.dong.model.Record;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecordParser {
    private static final String SEPARATOR = ",";
    public List<Record> parse(List<String> lines) {
        return lines.stream().map(this::parseRecord).collect(Collectors.toList());
    }

    private Record parseRecord(String line) {
        if(Objects.isNull(line) || line.trim().length() == 0) {
            return null;
        }
        String[] recordAttrs = Arrays.stream(line.split(SEPARATOR)).map(String::trim).toArray(String[]::new);
        if(recordAttrs.length != 3) {
            throw new IllegalArgumentException("Item format is not correct: " + line);
        }
        return new Record(recordAttrs[0], LocalDateTime.parse(recordAttrs[1]), Double.parseDouble(recordAttrs[2]));
    }

}
