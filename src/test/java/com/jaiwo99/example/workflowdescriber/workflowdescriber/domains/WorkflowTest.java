package com.jaiwo99.example.workflowdescriber.workflowdescriber.domains;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkflowTest {
    @Test
    public void create_should_return_null_with_invalid_map() {
        final Map<String, String> map = new HashMap<String, String>() {{
            put("1", "1");
            put("2", "1");
        }};

        assertThat(Workflow.create(map)).isNull();
    }

    @Test
    public void create_should_create_valid_object() {
        final Map<String, String> map = new HashMap<String, String>() {{
            put("id", "id");
            put("name", "name");
            put("author", "author");
            put("version", "1");
        }};

        final Workflow workflow = Workflow.create(map);
        assertThat(workflow).isNotNull();
        assertThat(workflow.getId()).isEqualTo(map.get("id"));
        assertThat(workflow.getName()).isEqualTo(map.get("name"));
        assertThat(workflow.getAuthor()).isEqualTo(map.get("author"));
        assertThat(workflow.getVersion()).isEqualTo(Integer.valueOf(map.get("version")));
    }
}