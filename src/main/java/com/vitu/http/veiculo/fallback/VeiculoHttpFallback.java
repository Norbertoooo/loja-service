package com.vitu.http.veiculo.fallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitu.config.RedisConfig;
import com.vitu.http.veiculo.VeiculoHttp;
import com.vitu.web.dto.response.Veiculo;
import io.micronaut.http.HttpResponse;
import io.micronaut.retry.annotation.Fallback;

@Fallback
public class VeiculoHttpFallback implements VeiculoHttp {

    private final RedisConfig redisConfig;

    public VeiculoHttpFallback(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    @Override
    public HttpResponse<Veiculo> buscarPorId(Long id) throws JsonProcessingException {
        String veiculoJson = redisConfig.get(id.toString());
        Veiculo veiculo = new ObjectMapper().readValue(veiculoJson, Veiculo.class);
        return HttpResponse.ok(veiculo);
    }
}
