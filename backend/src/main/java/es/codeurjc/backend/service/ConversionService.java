package es.codeurjc.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversionService {

    @Autowired
    private ObjectMapper objectMapper;

    public <T, U> U convertToDTO(T entity, Class<U> dtoClass) {
        return objectMapper.convertValue(entity, dtoClass);
    }
    public <T, U> T convertToEntity(U dto, Class<T> entityClass) {
        return objectMapper.convertValue(dto, entityClass);
    }
}
