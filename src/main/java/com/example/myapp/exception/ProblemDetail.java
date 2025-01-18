package com.example.myapp.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Problem detail object for error responses")
public class ProblemDetail {
    @Schema(description = "HTTP status code", example = "404")
    private int status;

    @Schema(description = "Error type", example = "https://example.com/probs/out-of-credit")
    private String type;

    @Schema(description = "Short, human-readable summary of the problem", example = "You do not have enough credit.")
    private String title;

    @Schema(description = "Detailed explanation of the problem", example = "Your current balance is 30, but that costs 50.")
    private String detail;

    @Schema(description = "URI of the request that caused the error", example = "/account/12345/credits")
    private String instance;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
