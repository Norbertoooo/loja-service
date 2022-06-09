package com.vitu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vitu.web.dto.VeiculoDto;

public interface VeiculoService {

    VeiculoDto obterVeiculo(Long id) throws JsonProcessingException;
}
