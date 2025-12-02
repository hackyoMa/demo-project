package com.github.demoproject.constant;

import org.springframework.data.domain.Sort;

/**
 * SorterOrder
 *
 * @author hackyo
 * @since 1.0.0
 */
public enum SorterOrder {

    /**
     * asc
     */
    ascend,

    /**
     * desc
     */
    descend;

    public Sort.Direction order() {
        return switch (this) {
            case ascend -> Sort.Direction.ASC;
            case descend -> Sort.Direction.DESC;
        };
    }

}
