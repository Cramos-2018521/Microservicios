package org.cramos.springcloud.msvc.usuarios.services;

import org.cramos.springcloud.msvc.usuarios.clients.CursoClienteRest;
import org.cramos.springcloud.msvc.usuarios.models.entity.Usuario;
import org.cramos.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private CursoClienteRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> porId(long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
        client.eliminarCursoUsuarioPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarPorIds(Iterable<Long> ids) {
        return (List<Usuario>) repository.findAllById(ids);
    }

    @Override
    public Optional<Usuario> porEmail(String email) {
        return repository.porEmail(email);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }


}
