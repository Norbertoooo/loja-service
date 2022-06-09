package com.vitu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vitu.web.dto.request.VendaRequestDto;

public interface VendaService {

    void realizarVenda(VendaRequestDto vendaRequestDto) throws JsonProcessingException;
}
