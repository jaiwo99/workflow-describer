package com.jaiwo99.example.workflowdescriber.workflowdescriber.domains;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkflowInstanceTest {

    @Test
    public void create_should_return_null_with_invalid_map() {
        final Map<String, String> map = new HashMap<String, String>() {{
            put("1", "1");
            put("2", "1");
        }};

        assertThat(WorkflowInstance.create(map)).isNull();
    }

    @Test
    public void create_should_create_valid_object() {
        final Map<String, String> map = new HashMap<String, String>() {{
            put("id", "id");
            put("workflowId", "workflowId");
            put("assignee", "assignee");
            put("step", "step");
            put("status", "status");
        }};

        final WorkflowInstance instance = WorkflowInstance.create(map);
        assertThat(instance).isNotNull();
        assertThat(instance.getId()).isEqualTo(map.get("id"));
        assertThat(instance.getWorkflowId()).isEqualTo(map.get("workflowId"));
        assertThat(instance.getAssignee()).isEqualTo(map.get("assignee"));
        assertThat(instance.getStep()).isEqualTo(map.get("step"));
        assertThat(instance.getStatus()).isEqualTo(map.get("status"));
    }
}