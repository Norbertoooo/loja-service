package com.vitu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vitu.web.dto.request.VendaRequestDto;
import com.vitu.web.dto.response.Venda;

public interface VendaService {

    Venda realizarVenda(VendaRequestDto vendaRequestDto) throws JsonProcessingException;
}
