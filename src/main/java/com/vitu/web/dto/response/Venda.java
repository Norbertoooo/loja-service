package com.vitu.web.dto.response;

import com.vitu.web.dto.VeiculoDto;

import java.math.BigDecimal;
import java.util.List;

public record Venda(String cliente, VeiculoDto veiculoDto, BigDecimal valor, List<Parcela> parcelas) {
}
