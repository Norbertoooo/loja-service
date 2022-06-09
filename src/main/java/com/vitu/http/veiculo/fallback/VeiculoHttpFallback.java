package com.vitu.http.veiculo.fallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitu.http.veiculo.VeiculoHttp;
import com.vitu.web.dto.VeiculoDto;
import io.micronaut.http.HttpResponse;
import io.micronaut.retry.annotation.Fallback;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Fallback
public class VeiculoHttpFallback implements VeiculoHttp {

    @Override
    public HttpResponse<VeiculoDto> buscarPorId(Long id) throws JsonProcessingException {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379, 2000, "lojaService");
        Jedis resource = jedisPool.getResource();
        String veiculoJson = resource.get(id.toString());
        VeiculoDto veiculoDto = new ObjectMapper().readValue(veiculoJson, VeiculoDto.class);
        return HttpResponse.ok(veiculoDto);
    }
}
