package com.vitu.http.veiculo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vitu.web.dto.VeiculoDto;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.CircuitBreaker;

@CircuitBreaker
@Client(id = "http://localhost:8080/veiculos")
public interface VeiculoHttp {

    @Get("/{id}")
    HttpResponse<VeiculoDto> buscarPorId(@PathVariable Long id) throws JsonProcessingException;
}
