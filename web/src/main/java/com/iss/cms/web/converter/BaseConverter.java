package com.iss.cms.web.converter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model, DTO> implements Converter<Model, DTO> {
    public Set<DTO> convertModelsToDTOs(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDTO)
                .collect(Collectors.toSet());
    }
}
