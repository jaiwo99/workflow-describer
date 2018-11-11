package com.jaiwo99.example.workflowdescriber.workflowdescriber.commands;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.output.PrintableWorkflow;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.readers.DataAggregator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Order(Integer.MAX_VALUE - 1)
@RequiredArgsConstructor
public class ShowWorkflowWithRunningInstancesCommand implements Command {

    public static final String COMMAND = "show-workflows-with-running-instance";
    private final DataAggregator dataAggregator;

    @Override
    public boolean execute(String[] args) {
        if (!matches(args)) {
            return false;
        }

        dataAggregator.showWorkflows(pwf -> new PrintableWorkflow(
                pwf.getId(),
                pwf.getName(),
                pwf.getAuthor(),
                pwf.getVersion(),
                pwf.getWorkflowInstanceList()
                        .stream()
                        .filter(pwfi -> pwfi.getStatus().equals("RUNNING"))
                        .collect(Collectors.toList())
        ));
        return true;
    }

    private boolean matches(String[] args) {
        return args.length > 0 && COMMAND.equals(args[0]);
    }
}
