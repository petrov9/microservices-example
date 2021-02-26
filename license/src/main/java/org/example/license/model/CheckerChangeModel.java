package org.example.license.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckerChangeModel {

    private String type;
    private String action;
    private String license;

    public CheckerChangeModel(String type, String action, String license) {
        this.type = type;
        this.action = action;
        this.license = license;
    }
}
