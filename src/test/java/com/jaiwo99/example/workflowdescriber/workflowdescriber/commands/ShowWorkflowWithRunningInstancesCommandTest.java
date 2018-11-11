package com.jaiwo99.example.workflowdescriber.workflowdescriber.commands;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.readers.DataAggregator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShowWorkflowWithRunningInstancesCommandTest {

    @InjectMocks
    private ShowWorkflowWithRunningInstancesCommand instance;
    @Mock
    private DataAggregator dataAggregator;

    @Test
    public void execute_should_match_command() {
        final boolean result = instance.execute(new String[]{ShowWorkflowWithRunningInstancesCommand.COMMAND});

        assertThat(result).isEqualTo(true);
        verify(dataAggregator).showWorkflows(any());
    }

    @Test
    public void execute_should_ignore_unknown_command() {
        final boolean result = instance.execute(new String[]{"unknown"});

        assertThat(result).isEqualTo(false);
        verify(dataAggregator, never()).showWorkflows(any());
    }
}