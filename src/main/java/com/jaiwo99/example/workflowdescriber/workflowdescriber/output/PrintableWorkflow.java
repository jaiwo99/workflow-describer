package com.jaiwo99.example.workflowdescriber.workflowdescriber.output;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.Workflow;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.WorkflowInstance;
import lombok.Value;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Value
public class PrintableWorkflow implements Printable {

    private final String id;
    private final String name;
    private final String author;
    private final Integer version;
    private final List<PrintableWorkflowInstance> workflowInstanceList;

    public static PrintableWorkflow create(Workflow workflow, List<WorkflowInstance> list) {
        final List<PrintableWorkflowInstance> printableWorkflowInstances = list.stream().map(PrintableWorkflowInstance::create).collect(toList());

        return new PrintableWorkflow(
                workflow.getId(),
                workflow.getName(),
                workflow.getAuthor(),
                workflow.getVersion(),
                printableWorkflowInstances);
    }

    @Override
    public void print(Function<String, Void> printFunc) {
        printFunc.apply("============================================================");
        printFunc.apply("Workflow:");
        printFunc.apply("\tID: " + id);
        printFunc.apply("\tName: " + name);
        printFunc.apply("\tAuthor: " + author);
        printFunc.apply("\tVersion: " + version);
        printFunc.apply("\tInstances: ");
        workflowInstanceList.forEach(instance -> instance.print(printFunc));
        printFunc.apply("============================================================");
    }
}
