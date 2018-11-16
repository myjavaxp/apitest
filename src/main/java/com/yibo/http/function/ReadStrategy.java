package com.yibo.http.function;

import java.util.List;

@FunctionalInterface
public interface ReadStrategy<T> {
    List<T> convert(List<T> list);
}