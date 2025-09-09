package com.thesis.saas.project;

import java.time.LocalDate;

public record ProjectDTO(
    String name,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    boolean active,
    Long companyId
) {}
