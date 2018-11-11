package com.jaiwo99.example.workflowdescriber.workflowdescriber.output;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.Contractor;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.WorkflowInstance;
import lombok.Value;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Value
public class PrintableContractor implements Printable {

    private String contractorName;
    private String fullName;
    private String email;
    private final List<PrintableWorkflowInstance> workflowInstanceList;

    public static PrintableContractor create(Contractor contractor, List<WorkflowInstance> list) {
        final List<PrintableWorkflowInstance> printableWorkflowInstances = list.stream().map(PrintableWorkflowInstance::create).collect(toList());

        return new PrintableContractor(
                contractor.getContractorName(),
                contractor.getFullName(),
                contractor.getEmail(),
                printableWorkflowInstances);
    }

    @Override
    public void print(Function<String, Void> func) {
        func.apply("============================================================");
        func.apply("Workflow:");
        func.apply("\tContractor Name : " + contractorName);
        func.apply("\tFull Name: " + fullName);
        func.apply("\tEmail: " + email);
        func.apply("\tInstances: ");
        workflowInstanceList.forEach(instance -> instance.print(func));
        func.apply("============================================================");
    }
}
