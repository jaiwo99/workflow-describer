package com.jaiwo99.example.workflowdescriber.workflowdescriber.commands;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.readers.DataAggregator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MAX_VALUE - 1)
@RequiredArgsConstructor
public class ShowWorkflowWithInstancesCommand implements Command {

    public static final String COMMAND = "show-workflows-with-instance";
    private final DataAggregator dataAggregator;

    @Override
    public boolean execute(String[] args) {
        if (!matches(args)) {
            return false;
        }

        dataAggregator.showWorkflows(pwf -> pwf);
        return true;
    }

    private boolean matches(String[] args) {
        return args.length > 0 && COMMAND.equals(args[0]);
    }
}
