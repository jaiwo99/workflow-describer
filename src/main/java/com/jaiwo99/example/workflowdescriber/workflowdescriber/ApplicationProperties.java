package com.jaiwo99.example.workflowdescriber.workflowdescriber;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class ApplicationProperties {

    /**
     * Path of workflows.data, default is current path
     */
    private String workflowPath = "/tmp/workflows.data";
    private String workflowInstancePath = "/tmp/workflowInstances.data";
    private String contractorPath = "/tmp/contractors.data";
}
