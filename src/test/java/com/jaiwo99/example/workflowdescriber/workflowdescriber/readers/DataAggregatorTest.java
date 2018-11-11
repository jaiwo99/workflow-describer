package com.jaiwo99.example.workflowdescriber.workflowdescriber.readers;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.Workflow;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.domains.WorkflowInstance;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.output.PrintableWorkflow;
import com.jaiwo99.example.workflowdescriber.workflowdescriber.output.ScreenPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataAggregatorTest {

    @Mock
    private Database database;
    @Mock
    private ScreenPrinter screenPrinter;
    @InjectMocks
    private DataAggregator dataAggregator;
    @Captor
    private ArgumentCaptor<List<PrintableWorkflow>> workflowListCaptor;

    @Before
    public void setup() {
        when(database.getWorkflowInstances()).thenReturn(Arrays.asList(
                new WorkflowInstance("id", "workflowId", "assignee", "step", "status"),
                new WorkflowInstance("id2", "workflowId", "assignee", "step", "status")
        ));
        when(database.findWorkflowById(eq("workflowId"))).thenReturn(Optional.of(new Workflow("workflowId", "name", "author", 1)));
    }

    @Test
    public void showWorkflows_should_honer_mapper_function() {
        dataAggregator.showWorkflows(f -> f);

        verify(screenPrinter).printCommandOutput(workflowListCaptor.capture());

        assertThat(workflowListCaptor.getValue()).hasSize(1);
        final PrintableWorkflow printableWorkflow = workflowListCaptor.getValue().get(0);
        assertThat(printableWorkflow.getId()).isEqualTo("workflowId");
    }

    @Test
    public void showWorkflows_should_honer_another_mapper_function() {
        dataAggregator.showWorkflows(f -> new PrintableWorkflow(
                "anotherId",
                f.getName(),
                f.getAuthor(),
                f.getVersion(),
                f.getWorkflowInstanceList()
        ));

        verify(screenPrinter).printCommandOutput(workflowListCaptor.capture());

        assertThat(workflowListCaptor.getValue()).hasSize(1);
        final PrintableWorkflow printableWorkflow = workflowListCaptor.getValue().get(0);
        assertThat(printableWorkflow.getId()).isEqualTo("anotherId");
    }
}