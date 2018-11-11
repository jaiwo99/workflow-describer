package com.jaiwo99.example.workflowdescriber.workflowdescriber.readers;

import com.jaiwo99.example.workflowdescriber.workflowdescriber.ApplicationProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseTest {

    @Mock
    private ApplicationProperties applicationProperties;
    @Spy
    private DataExtractor dataExtractor;
    @InjectMocks
    private Database database;

    @Before
    public void setup() {
        when(applicationProperties.getWorkflowPath()).thenReturn(extractPath("workflows.data"));
        when(applicationProperties.getWorkflowInstancePath()).thenReturn(extractPath("workflowInstances.data"));
        when(applicationProperties.getContractorPath()).thenReturn(extractPath("contractors.data"));
    }

    @Test
    public void init_should_call_extractor_3_times() {
        database.init();
        verify(dataExtractor, times(3)).extract(anyList());
    }

    @Test
    public void findWorkflowById_should_find_workflow_with_existing_id() {
        database.init();
        assertThat(database.findWorkflowById("1").isPresent()).isTrue();
    }

    @Test
    public void findWorkflowById_should_return_empty_when_id_not_exist() {
        database.init();
        assertThat(database.findWorkflowById("4").isPresent()).isFalse();
    }

    @Test
    public void findContractorByEmail_should_find_contractor_with_existing_email() {
        database.init();
        assertThat(database.findContractorByEmail("f.consultant@example.org").isPresent()).isTrue();
    }

    @Test
    public void findContractorByEmail_should_return_empty_when_email_not_exist() {
        database.init();
        assertThat(database.findContractorByEmail("unknown").isPresent()).isFalse();
    }

    @Test
    public void readContractors_should_filter_null_object() throws IOException {
        final String path = extractPath("contractors_with_invalid.data");

        final int actualSize = database.readContractors(path).size();
        final List<String> list = Files.readAllLines(Paths.get(path));
        final int expectSize = dataExtractor.extract(list).size() - 1;
        assertThat(actualSize).isEqualTo(expectSize);
    }

    @Test
    public void readWorkflows_should_filter_null_object() throws IOException {
        final String path = extractPath("workflows_with_invalid.data");

        final int actualSize = database.readWorkflows(path).size();
        final List<String> list = Files.readAllLines(Paths.get(path));
        final int expectSize = dataExtractor.extract(list).size() - 1;
        assertThat(actualSize).isEqualTo(expectSize);
    }

    @Test
    public void readWorkflowInstances_should_filter_null_object() throws IOException {
        final String path = extractPath("workflowInstances.data");

        final int actualSize = database.readWorkflowInstances(path).size();
        final List<String> list = Files.readAllLines(Paths.get(path));

        // notice: original data have 2 malformed data
        final int expectSize = dataExtractor.extract(list).size() - 2;
        assertThat(actualSize).isEqualTo(expectSize);
    }

    private String extractPath(String filename) {
        return this.getClass().getClassLoader().getResource(filename).getFile();
    }

}