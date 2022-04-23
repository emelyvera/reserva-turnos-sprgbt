package com.cdt.repository;

import com.cdt.entities.Usuario;

public interface IUsuarioRepository extends IGenericRepository<Usuario, Integer>{

  Usuario findOneByUsername(String username);

}
