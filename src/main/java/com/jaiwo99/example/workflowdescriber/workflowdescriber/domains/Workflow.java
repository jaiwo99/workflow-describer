package com.jaiwo99.example.workflowdescriber.workflowdescriber.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workflow {

    private String id;
    private String name;
    private String author;
    private Integer version;

    public static @Nullable Workflow create(Map<String, String> map) {
        if (map.size() < 4) {
            return null;
        }

        final Workflow workflow = new Workflow();
        workflow.setId(map.get("id"));
        workflow.setName(map.get("name"));
        workflow.setAuthor(map.get("author"));
        workflow.setVersion(Integer.valueOf(map.get("version")));

        return workflow;
    }
}
