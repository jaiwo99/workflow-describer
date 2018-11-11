package com.jaiwo99.example.workflowdescriber.workflowdescriber.readers;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNoneBlank;
import static org.apache.commons.lang3.StringUtils.strip;

@Component
public class DataExtractor {

    public List<Map<String, String>> extract(List<String> list) {
        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> instance = null;
        for (String line : list) {
            if (line.equalsIgnoreCase("start") && instance == null) {
                instance = new HashMap<>();
            } else if (line.equalsIgnoreCase("end") && instance != null) {
                result.add(instance);
                instance = null;
            } else if (instance != null) {
                final String[] split = line.split(":");

                if (split.length == 2) {
                    final String key = strip(split[0]);
                    final String value = strip(split[1]);

                    if (isNoneBlank(key, value)) {
                        instance.put(key, value);
                    }
                }
            }
        }

        return result;
    }
}
