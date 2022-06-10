package com.vitu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vitu.web.dto.response.Veiculo;

public interface VeiculoService {

    Veiculo obterVeiculo(Long id) throws JsonProcessingException;
}
