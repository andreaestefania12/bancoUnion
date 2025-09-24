package com.gaei.clientManager.infrastructure.exception.dto;

public record ErrorResponseDTO (
        String idTx,
        String error
) {
}
