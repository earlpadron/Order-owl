package dev.earl.order_owl.exception;

import java.time.LocalDateTime;

public record ErrorInfo(
        String message,
        Integer code,
        LocalDateTime timestamp
) {
}
