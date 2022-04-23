package com.cdt;

import com.cdt.entities.Usuario;
import com.cdt.repository.IUsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ApplicationTests {

  @Autowired
  private IUsuarioRepository usuarioRepository;

  @Autowired
  private BCryptPasswordEncoder bcrypt;

  @Test
  void crearUsuarioEnBdYverificarPassword() {

    Usuario usuario = new Usuario();
    usuario.setIdUsuario(1);
    usuario.setUsername("usertest");
    usuario.setPassword(bcrypt.encode("67890"));
    System.out.println(usuario.getPassword());
    Usuario usuarioCreado = usuarioRepository.save(usuario);

      Assertions.assertNotNull(usuarioCreado);
      Assertions.assertEquals(usuario.getPassword(), usuarioCreado.getPassword());
  }

}
