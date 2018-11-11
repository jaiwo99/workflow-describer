package com.jaiwo99.example.workflowdescriber.workflowdescriber;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.commands.Command;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.output.ScreenPrinter;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.readers.Database;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
public class WorkflowDescriberApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowDescriberApplication.class, args);
    }

    /**
     * Go through all commands, if no one matches, it will print the help message
     */
    @Bean
    CommandLineRunner commandLineRunner(List<Command> commandList, Database database, ScreenPrinter printer) {
        return args -> {
            try {
                database.init();
                for (Command command : commandList) {
                    if (command.execute(args)) {
                        return;
                    }
                }
            } catch (Exception e) {
                log.error("Failed to run command: {}", e.getMessage());
                log.debug("Failed to run command", e);
                printer.printSimpleMessage("Failed to run command because of " + e.getMessage());
                printer.printUsage();
                System.exit(1);
            }
        };
    }
}
