package com.cdt.service.impl;

import com.cdt.entities.Usuario;
import com.cdt.repository.IUsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 2-SEGUNDA CLASE
@Service
public class UsuarioServiceImpl implements UserDetailsService {

  @Autowired
  private IUsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Usuario usuario = usuarioRepository.findOneByUsername(username);

    if (usuario == null) {
      throw new UsernameNotFoundException(String.format("Usuario no existe", username));
    }

    List<GrantedAuthority> roles = new ArrayList<>();

    usuario.getRoles().forEach(rol -> {
      roles.add(new SimpleGrantedAuthority(rol.getNombre()));
    });

    UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), roles);

    return ud;
  }

}
