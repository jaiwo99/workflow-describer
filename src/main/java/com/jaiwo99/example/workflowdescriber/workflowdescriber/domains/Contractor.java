package com.jaiwo99.example.workflowdescriber.workflowdescriber.domains;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Map;

@Data
public class Contractor {
    private String contractorName;
    private String fullName;
    private String email;

    public static @Nullable
    Contractor create(Map<String, String> map) {
        if (map.size() < 3) {
            return null;
        }

        final Contractor contractor = new Contractor();
        contractor.setContractorName(map.get("contractorName"));
        contractor.setFullName(map.get("fullName"));
        contractor.setEmail(map.get("email"));

        return contractor;
    }
}

