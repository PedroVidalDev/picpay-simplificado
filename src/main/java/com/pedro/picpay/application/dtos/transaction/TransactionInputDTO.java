package com.pedro.picpay.application.dtos.transaction;

import jakarta.validation.constraints.NotNull;

public record TransactionInputDTO(
        @NotNull Long payer,
        @NotNull Long payee,
        @NotNull Float value) {
}
