package com.tickets.mapper;

import com.tickets.dto.PerformerDto;
import com.tickets.model.Performer;
import org.springframework.stereotype.Component;

@Component
public class PerformerDtoMapper {
    public Performer convertToEntity(PerformerDto performerDto) {
        if (performerDto.getId() != null) {
            return new Performer(
                    performerDto.getId(),
                    performerDto.getName(),
                    performerDto.getStageName(),
                    performerDto.getStageAge()
            );
        }
        return new Performer(
                performerDto.getName(),
                performerDto.getStageName(),
                performerDto.getStageAge()
        );
    }

    public PerformerDto convertToDto(Performer performer) {
        return new PerformerDto(
                performer.getId(),
                performer.getName(),
                performer.getStageName(),
                performer.getStageAge()
        );
    }
}
