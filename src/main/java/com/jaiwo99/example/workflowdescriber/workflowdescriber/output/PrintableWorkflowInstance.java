package com.jaiwo99.example.workflowdescriber.workflowdescriber.output;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.WorkflowInstance;
import lombok.Value;

import java.util.function.Function;

@Value
public class PrintableWorkflowInstance implements Printable {

    private final String id;
    private final String workflowId;
    private final String assignee;
    private final String step;
    private final String status;

    public static PrintableWorkflowInstance create(WorkflowInstance workflowInstance) {
      return new PrintableWorkflowInstance(
              workflowInstance.getId(),
              workflowInstance.getWorkflowId(),
              workflowInstance.getAssignee(),
              workflowInstance.getStep(),
              workflowInstance.getStatus());
    }

    @Override
    public void print(Function<String, Void> printFunc) {
        printFunc.apply("\t\tID: " + id);
        printFunc.apply("\t\tworkflowId: " + workflowId);
        printFunc.apply("\t\tassignee: " + assignee);
        printFunc.apply("\t\tstep: " + step);
        printFunc.apply("\t\tstatus: " + status);
        printFunc.apply("");
    }
}
