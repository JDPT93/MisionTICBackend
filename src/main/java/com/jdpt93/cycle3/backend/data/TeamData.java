package com.jdpt93.cycle3.backend.data;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamData {

    private int id;

    @NotBlank(message = "Name is required")
    private String name;

}
