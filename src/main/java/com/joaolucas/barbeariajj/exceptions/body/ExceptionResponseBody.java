package com.joaolucas.barbeariajj.exceptions.body;

import java.time.LocalDateTime;

public record ExceptionResponseBody(String error, int errorCode, String message, LocalDateTime timestamp) {
}
