package com.cdt.service;

import java.util.List;

public interface ICRUD<T, V> {

    T registrar(T t) throws Exception;

    T modificar(T t) throws Exception;

    List<T> listar() throws Exception;

    T buscarPorId(V id) throws Exception;

    void eliminar(V id) throws Exception;

}
