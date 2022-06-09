package com.vitu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitu.http.veiculo.VeiculoHttp;
import com.vitu.service.VeiculoService;
import com.vitu.web.dto.VeiculoDto;
import jakarta.inject.Singleton;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Singleton
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoHttp veiculoHttp;

    private final ObjectMapper objectMapper;

    public VeiculoServiceImpl(VeiculoHttp veiculoHttp, ObjectMapper objectMapper) {
        this.veiculoHttp = veiculoHttp;
        this.objectMapper = objectMapper;
    }

    @Override
    public VeiculoDto obterVeiculo(Long id) throws JsonProcessingException {
        VeiculoDto veiculoDto = veiculoHttp.buscarPorId(id).body();
        enviarParaCache(veiculoDto);
        return veiculoDto;
    }

    public void enviarParaCache(VeiculoDto veiculoDto) throws JsonProcessingException {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379, 2000, "lojaService");
        Jedis resource = jedisPool.getResource();
        resource.set(veiculoDto.id().toString(), objectMapper.writeValueAsString(veiculoDto));
    }
}
