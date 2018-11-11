package com.jaiwo99.example.workflowdescriber.workflowdescriber.domains;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ContractorTest {

    @Test
    public void create_should_return_null_with_invalid_map() {
        final Map<String, String> map = new HashMap<String, String>() {{
            put("1", "1");
            put("2", "1");
        }};

        assertThat(Contractor.create(map)).isNull();
    }

    @Test
    public void create_should_create_valid_object() {
        final Map<String, String> map = new HashMap<String, String>() {{
            put("contractorName", "contractorName");
            put("fullName", "fullName");
            put("email", "email");
        }};

        final Contractor contractor = Contractor.create(map);
        assertThat(contractor).isNotNull();
        assertThat(contractor.getEmail()).isEqualTo(map.get("email"));
        assertThat(contractor.getFullName()).isEqualTo(map.get("fullName"));
        assertThat(contractor.getContractorName()).isEqualTo(map.get("contractorName"));
    }
}