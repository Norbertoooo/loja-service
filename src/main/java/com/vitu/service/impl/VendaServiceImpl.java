package com.vitu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vitu.service.VeiculoService;
import com.vitu.service.VendaService;
import com.vitu.web.dto.VeiculoDto;
import com.vitu.web.dto.request.VendaRequestDto;
import com.vitu.web.dto.response.Parcela;
import com.vitu.web.dto.response.Venda;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class VendaServiceImpl implements VendaService {

    private final VeiculoService veiculoService;

    public VendaServiceImpl(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Override
    public void realizarVenda(VendaRequestDto vendaRequestDto) throws JsonProcessingException {
        VeiculoDto veiculoDtoHttpResponse = veiculoService.obterVeiculo(vendaRequestDto.veiculoId());

        System.out.println(veiculoDtoHttpResponse);

        BigDecimal valorParcela = vendaRequestDto.valor()
                .divide(BigDecimal.valueOf(vendaRequestDto.quantidadeParcelas()));

        LocalDateTime dataVencimento = LocalDateTime.now().plusMonths(1);

        List<Parcela> parcelas = new ArrayList<>();

        for (int i = 0; i < vendaRequestDto.quantidadeParcelas(); i++) {
            parcelas.add(new Parcela(valorParcela, dataVencimento));
            dataVencimento = dataVencimento.plusMonths(1);
        }

        Venda venda = new Venda(vendaRequestDto.cliente(), veiculoDtoHttpResponse, vendaRequestDto.valor(), parcelas);
        System.out.println(venda);
    }
}
