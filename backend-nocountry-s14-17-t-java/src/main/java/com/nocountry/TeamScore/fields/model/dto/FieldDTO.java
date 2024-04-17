package com.nocountry.TeamScore.fields.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldDTO {

        @Schema(description = "Field id", example = "1")
        private Long id;

        @Schema(description = "Field name", example = "Iniciative")
        private String name;

        @Schema(description = "Field description", example = "This field measure the disposibility of an initiative")
        private String description;

        @Schema(description = "Field status", example = "true")
        private String status;

        @Schema(description = "Field min value", example = "1")
        private Integer min;

        @Schema(description = "Field max value", example = "5")
        private Integer max;

        @Schema(description = "Field allow comment", example = "true")
        private Integer allowComment;
}
