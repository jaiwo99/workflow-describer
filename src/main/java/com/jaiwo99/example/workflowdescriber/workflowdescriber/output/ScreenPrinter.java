package com.jaiwo99.example.workflowdescriber.workflowdescriber.output;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.commands.HelpCommand;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.commands.ShowContractorsCommand;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.commands.ShowWorkflowWithInstancesCommand;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.commands.ShowWorkflowWithRunningInstancesCommand;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * All print related functions are collected here, so it can be replaced later easily
 */
@Component
public class ScreenPrinter {

    public void printCommandOutput(List<? extends Printable> printableList) {
        printableList.forEach(printable -> printable.print(s -> {
            System.out.println(s);
            return null;
        }));
    }

    public void printUsage() {
        System.out.println("#######################################################################################");
        System.out.println("# ");
        System.out.println("# Usage:");
        System.out.println("# ");
        System.out.println("# java -jar <app.jar> <OPTION>");
        System.out.println("# ");
        System.out.println("# Current supported OPTIONs are:");
        System.out.println("# ");
        Arrays.asList(
                HelpCommand.COMMAND,
                ShowContractorsCommand.COMMAND,
                ShowWorkflowWithInstancesCommand.COMMAND,
                ShowWorkflowWithRunningInstancesCommand.COMMAND
        )
                .forEach(s -> System.out.println("# \t- " + s));
        System.out.println("# ");
        System.out.println("# Default data locations:");
        System.out.println("# ");
        System.out.println("# \t- contractor data:\t\t\t/tmp/contractors.data");
        System.out.println("# \t- workflow data:\t\t\t/tmp/workflows.data");
        System.out.println("# \t- workflow instance data:\t/tmp/workflowInstances.data");
        System.out.println("# ");
        System.out.println("# You can override data location by using parameter:");
        System.out.println("# ");
        System.out.println("# \t- --app.workflow-path=<PATH>");
        System.out.println("# \t- --app.workflow-instance-path=<PATH>");
        System.out.println("# \t- --app.contractor-path=<PATH>");
        System.out.println("# ");
        System.out.println("# Example:");
        System.out.println("# ");
        System.out.println("# \tjava -jar <app.jar> <OPTION> --app.workflow-path=<PATH>");
        System.out.println("# ");
        System.out.println("#######################################################################################");
    }

    public void printSimpleMessage(String s) {
        System.out.println(s);
    }
}
