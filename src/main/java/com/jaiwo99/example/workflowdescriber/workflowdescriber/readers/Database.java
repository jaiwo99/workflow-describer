package com.jaiwo99.example.workflowdescriber.workflowdescriber.readers;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.ApplicationProperties;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.ExecutionFailedException;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.Contractor;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.Workflow;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.WorkflowInstance;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class Database {

    private final ApplicationProperties applicationProperties;
    private final DataExtractor dataExtractor;
    @Getter
    private List<Workflow> workflows;
    @Getter
    private List<WorkflowInstance> workflowInstances;
    @Getter
    private List<Contractor> contractors;

    public void init() {
        try {
            workflows = readWorkflows(applicationProperties.getWorkflowPath());
            workflowInstances = readWorkflowInstances(applicationProperties.getWorkflowInstancePath());
            contractors = readContractors(applicationProperties.getContractorPath());
        } catch (IOException e) {
            final String message = "Failed to read data files";
            log.error(message, e);
            throw new ExecutionFailedException(message, e);
        }
    }

    public Optional<Workflow> findWorkflowById(String id) {
        return workflows.stream().filter(workflow -> workflow.getId().equals(id)).findFirst();
    }

    public Optional<Contractor> findContractorByEmail(String email) {
        return contractors.stream().filter(contractor -> contractor.getEmail().equals(email)).findFirst();
    }

    List<Contractor> readContractors(String path) throws IOException {
        final List<String> list = Files.readAllLines(Paths.get(path));
        return dataExtractor.extract(list)
                .stream()
                .map(Contractor::create)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    List<Workflow> readWorkflows(String path) throws IOException {
        final List<String> list = Files.readAllLines(Paths.get(path));
        return dataExtractor.extract(list)
                .stream()
                .map(Workflow::create)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    List<WorkflowInstance> readWorkflowInstances(String path) throws IOException {
        final List<String> list = Files.readAllLines(Paths.get(path));
        return dataExtractor.extract(list)
                .stream()
                .map(WorkflowInstance::create)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }
}
