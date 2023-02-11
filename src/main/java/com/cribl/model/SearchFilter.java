package com.cribl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchFilter {
    private String fileName;
    private String pattern;
    private Integer matchCount;
}
