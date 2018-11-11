package com.jaiwo99.example.workflowdescriber.workflowdescriber.readers;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.WorkflowInstance;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.output.PrintableContractor;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.output.PrintableWorkflow;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.output.ScreenPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class DataAggregator {

    private final Database database;
    private final ScreenPrinter screenPrinter;

    public void showWorkflows(Function<PrintableWorkflow, PrintableWorkflow> mapper) {
        final List<PrintableWorkflow> collect = getPrintableWorkflows(mapper);
        screenPrinter.printCommandOutput(collect);
    }

    private List<PrintableWorkflow> getPrintableWorkflows(Function<PrintableWorkflow, PrintableWorkflow> mapper) {
        return database.getWorkflowInstances()
                .stream()
                .collect(
                        groupingBy(WorkflowInstance::getWorkflowId))
                .entrySet()
                .stream()
                .filter(entry -> database.findWorkflowById(entry.getKey()).isPresent())
                .map(entry -> PrintableWorkflow.create(database.findWorkflowById(entry.getKey()).get(), entry.getValue()))
                .map(mapper)
                .collect(Collectors.toList());
    }

    public void showAssignedContractorsByStatus(String status) {
        final List<PrintableContractor> printableContractors = getPrintableContractorsByStatus(status);
        screenPrinter.printCommandOutput(printableContractors);
    }

    private List<PrintableContractor> getPrintableContractorsByStatus(String status) {
        return database.getWorkflowInstances()
                .stream()
                .filter(instance -> instance.getStatus().equals(status))
                .collect(Collectors.groupingBy(WorkflowInstance::getAssignee))
                .entrySet()
                .stream()
                .filter(entry -> database.findContractorByEmail(entry.getKey()).isPresent())
                .map(entry -> PrintableContractor.create(database.findContractorByEmail(entry.getKey()).get(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
