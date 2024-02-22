package com.app.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
