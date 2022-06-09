package com.vitu.web.dto.request;

import java.math.BigDecimal;

public record VendaRequestDto(String cliente, Long veiculoId, BigDecimal valor, Integer quantidadeParcelas) {

}
