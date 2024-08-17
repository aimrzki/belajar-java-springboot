package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

@Document(collection = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String taskId;
    @NotBlank(message = "Description is mandatory")
    @Size(min = 1, max = 3000, message = "Description must be between 1 and 3000 characters")
    private String description;

    @Min(value = 0, message = "Severity must be at least 0")
    private int severity;

    @NotBlank(message = "Assignee is mandatory")
    @Size(min = 1, max = 100, message = "Assignee must be between 1 and 100 characters")
    private String assignee;

    @Min(value = 0, message = "Story point must be at least 0")
    @Max(value = 100, message = "Story point must be at most 100")
    private int storyPoint;
}
