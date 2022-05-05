package com.tickets.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class PerformerDto {
    private UUID id;
    private String name;
    private String stageName;
    private int stageAge;

    public PerformerDto(UUID id, String name, String stageName, int stageAge) {
        this.id = id;
        this.name = name;
        this.stageName = stageName;
        this.stageAge = stageAge;
    }
}
