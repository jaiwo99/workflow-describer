package com.jaiwo99.example.workflowdescriber.workflowdescriber.commands;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.output.ScreenPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order // this makes sure this is the last command
@RequiredArgsConstructor
public class HelpCommand implements Command {

    private final ScreenPrinter screenPrinter;
    public static final String COMMAND = "help";

    /**
     * This is the last command in the command chain, if no match command found, usage will be printed.
     */
    @Override
    public boolean execute(String[] args) {
        screenPrinter.printUsage();
        return true;
    }
}
