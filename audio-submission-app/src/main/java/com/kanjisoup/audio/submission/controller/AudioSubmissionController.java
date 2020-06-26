package com.kanjisoup.audio.submission.controller;

import static org.springframework.web.client.HttpServerErrorException.*;

import com.kanjisoup.audio.event.sdk.client.AudioQueueClient;
import com.kanjisoup.audio.event.sdk.domain.PlayEvent;
import com.kanjisoup.audio.event.sdk.exception.SubmissionFailedException;
import com.kanjisoup.audio.submission.domain.AudioSubmissionRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.UUID;
import javax.validation.Valid;

@RestController
@RequestMapping("/event-audio")
@Slf4j
public class AudioSubmissionController {

    private AudioQueueClient audioQueueClient;

    @Autowired
    public AudioSubmissionController(AudioQueueClient audioQueueClient) {
        this.audioQueueClient = audioQueueClient;
    }

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Allows a user to submit an audio-play request.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Success")
    })
    public String submitAudio(@Valid @RequestBody AudioSubmissionRequest request) throws InternalServerError {
        try {
            audioQueueClient.submit(PlayEvent.builder()
                .uuid(UUID.randomUUID().toString())
                .filePath(request.getFilename())
                .build());
        } catch (SubmissionFailedException e) {
            throw HttpServerErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, "error",
                null, "Error submitting audio".getBytes(), null);
        }

        return "{\"status\": \"success\"}";
    }
}
