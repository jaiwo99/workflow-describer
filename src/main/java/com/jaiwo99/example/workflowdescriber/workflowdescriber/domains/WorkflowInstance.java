package com.jaiwo99.example.workflowdescriber.workflowdescriber.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowInstance {

    private String id;
    private String workflowId;
    private String assignee;
    private String step;
    private String status;

    public static @Nullable
    WorkflowInstance create(Map<String, String> map) {
        if (map.size() < 5) {
            return null;
        }

        final WorkflowInstance instance = new WorkflowInstance();
        instance.setId(map.get("id"));
        instance.setWorkflowId(map.get("workflowId"));
        instance.setAssignee(map.get("assignee"));
        instance.setStep(map.get("step"));
        instance.setStatus(map.get("status"));

        return instance;
    }
}
