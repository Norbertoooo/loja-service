package com.vitu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitu.producer.VendaProducer;
import com.vitu.service.VeiculoService;
import com.vitu.service.VendaService;
import com.vitu.web.dto.response.Veiculo;
import com.vitu.web.dto.request.VendaRequestDto;
import com.vitu.web.dto.response.Parcela;
import com.vitu.web.dto.response.Venda;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class VendaServiceImpl implements VendaService {

    private final VeiculoService veiculoService;
    private final VendaProducer vendaProducer;

    private final ObjectMapper objectMapper;

    public VendaServiceImpl(VeiculoService veiculoService, VendaProducer vendaProducer, ObjectMapper objectMapper) {
        this.veiculoService = veiculoService;
        this.vendaProducer = vendaProducer;
        this.objectMapper = objectMapper;
    }

    @Override
    public Venda realizarVenda(VendaRequestDto vendaRequestDto) throws JsonProcessingException {
        Veiculo veiculoHttpResponse = veiculoService.obterVeiculo(vendaRequestDto.veiculoId());

        System.out.println(veiculoHttpResponse);

        BigDecimal valorParcela = vendaRequestDto.valor()
                .divide(BigDecimal.valueOf(vendaRequestDto.quantidadeParcelas()));

        LocalDateTime dataVencimento = LocalDateTime.now().plusMonths(1);

        List<Parcela> parcelas = new ArrayList<>();

        for (int i = 0; i < vendaRequestDto.quantidadeParcelas(); i++) {
            parcelas.add(new Parcela(valorParcela, dataVencimento));
            dataVencimento = dataVencimento.plusMonths(1);
        }

        Venda venda = new Venda(vendaRequestDto.cliente(), veiculoHttpResponse, vendaRequestDto.valor(), parcelas);
        confirmarVenda(venda);
        System.out.println(venda);
        return venda;
    }

    private void confirmarVenda(Venda venda) throws JsonProcessingException {
        String vendaJson = objectMapper.writeValueAsString(venda);
        vendaProducer.produzirVenda(UUID.randomUUID().toString(), vendaJson);
    }
}
