package org.example.checker.events.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
