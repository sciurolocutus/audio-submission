package com.kanjisoup.audio.submission.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AudioSubmissionRequest {
    @NotBlank
    private String filename;
}
