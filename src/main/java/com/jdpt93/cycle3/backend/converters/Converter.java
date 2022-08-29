package com.jdpt93.cycle3.backend.converters;

import java.util.List;

public abstract class Converter<E, D> {

    public abstract E toEntity(D object);

    public abstract D toData(E object);

    public List<E> toEntity(List<D> list) {
        return list.stream()
                .map(object -> toEntity(object))
                .toList();
    }

    public List<D> toData(List<E> list) {
        return list.stream()
                .map(object -> toData(object))
                .toList();
    }

}
