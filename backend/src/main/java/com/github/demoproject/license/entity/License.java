package com.github.demoproject.license.entity;

import com.github.demoproject.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * License
 *
 * @author hackyo
 * @since 1.0.0
 */
@Data
@Entity(name = "license")
public class License extends BaseEntity {

    /**
     * authorized to
     */
    private String authorizedTo;

    /**
     * edition
     */
    private String edition;

    /**
     * start date
     */
    private LocalDateTime startDate;

    /**
     * end date
     */
    private LocalDateTime endDate;

}
