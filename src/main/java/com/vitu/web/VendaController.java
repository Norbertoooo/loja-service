package com.vitu.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vitu.service.VendaService;
import com.vitu.web.dto.request.VendaRequestDto;
import com.vitu.web.dto.response.Venda;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Post
    public HttpResponse<Venda> efetuarVenda(@Body VendaRequestDto vendaRequestDto) throws JsonProcessingException {
        Venda venda = vendaService.realizarVenda(vendaRequestDto);
        return HttpResponse.ok(venda);
    }

}
