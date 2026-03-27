package org.example.Demo.DTO.Class;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class DeleteClassesDTO {
    @Schema(description = "id")
    private List<Long> id;
}
