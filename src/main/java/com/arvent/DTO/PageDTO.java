package com.arvent.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    @JsonProperty("content")
    private List<T> content;
    @JsonProperty("is_end")
    private Boolean isEnd;
}