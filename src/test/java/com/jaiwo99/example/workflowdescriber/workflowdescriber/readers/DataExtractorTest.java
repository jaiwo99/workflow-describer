package com.jaiwo99.example.workflowdescriber.workflowdescriber.readers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DataExtractorTest {

    private DataExtractor instance = new DataExtractor();

    @Test
    public void extract_should_work_on_normal_case() {
        final List<String> input = new ArrayList<>();
        input.add("something");
        input.add("otherthing");
        input.add("START");
        input.add("test:test");
        input.add("END");

        final List<Map<String, String>> output = instance.extract(input);

        assertThat(output).hasSize(1);
        assertThat(output.get(0).containsKey("test")).isTrue();
        assertThat(output.get(0).get("test")).isEqualTo("test");
    }

    @Test
    public void extract_should_ignore_malformed_start() {
        final List<String> input = new ArrayList<>();
        input.add("something");
        input.add("START");
        input.add("otherthing");
        input.add("START");
        input.add("test:test");
        input.add("END");

        final List<Map<String, String>> output = instance.extract(input);

        assertThat(output).hasSize(1);
        assertThat(output.get(0).containsKey("test")).isTrue();
        assertThat(output.get(0).get("test")).isEqualTo("test");
    }

    @Test
    public void extract_should_ignore_malformed_end() {
        final List<String> input = new ArrayList<>();
        input.add("something");
        input.add("otherthing");
        input.add("START");
        input.add("test:test");
        input.add("END");
        input.add("END");

        final List<Map<String, String>> output = instance.extract(input);

        assertThat(output).hasSize(1);
        assertThat(output.get(0).containsKey("test")).isTrue();
        assertThat(output.get(0).get("test")).isEqualTo("test");
    }

    @Test
    public void extract_should_ignore_malformed_value() {
        final List<String> input = new ArrayList<>();
        input.add("something");
        input.add("otherthing");
        input.add("START");
        input.add("test");
        input.add("test:test");
        input.add("test");
        input.add("END");

        final List<Map<String, String>> output = instance.extract(input);

        assertThat(output).hasSize(1);
        assertThat(output.get(0).containsKey("test")).isTrue();
        assertThat(output.get(0).get("test")).isEqualTo("test");
    }

    @Test
    public void extract_should_work_with_more_values() {
        final List<String> input = new ArrayList<>();
        input.add("something");
        input.add("otherthing");
        input.add("START");
        input.add("test1:test1");
        input.add("test2:test2");
        input.add("END");

        final List<Map<String, String>> output = instance.extract(input);

        assertThat(output).hasSize(1);
        assertThat(output.get(0).containsKey("test1")).isTrue();
        assertThat(output.get(0).containsKey("test2")).isTrue();
        assertThat(output.get(0).get("test1")).isEqualTo("test1");
        assertThat(output.get(0).get("test2")).isEqualTo("test2");
    }

    @Test
    public void extract_should_work_with_more_dataset() {
        final List<String> input = new ArrayList<>();
        input.add("something");
        input.add("otherthing");
        input.add("START");
        input.add("test1:test1");
        input.add("END");
        input.add("START");
        input.add("test2:test2");
        input.add("END");

        final List<Map<String, String>> output = instance.extract(input);

        assertThat(output).hasSize(2);
        assertThat(output.get(0).containsKey("test1")).isTrue();
        assertThat(output.get(1).containsKey("test2")).isTrue();
        assertThat(output.get(0).get("test1")).isEqualTo("test1");
        assertThat(output.get(1).get("test2")).isEqualTo("test2");
    }

}