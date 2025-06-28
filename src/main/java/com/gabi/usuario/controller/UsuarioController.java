package com.gabi.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabi.usuario.messaging.UsuarioProducer;
import com.gabi.usuario.model.Usuario;
import com.gabi.usuario.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioProducer producer;

    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = repository.save(usuario);

        String mensagem = "Novo usuário criado: " + usuarioCriado.getNome();
        producer.enviarMensagem(mensagem);

        return usuarioCriado;
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existente = repository.findById(id).orElseThrow();

        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());
        existente.setSenha(usuario.getSenha());

        Usuario usuarioAtualizado = repository.save(existente);

        String mensagem = "Usuário atualizado: " + usuarioAtualizado.getNome();
        producer.enviarMensagem(mensagem);

        return usuarioAtualizado;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);

        String mensagem = "Usuário deletado com ID: " + id;
        producer.enviarMensagem(mensagem);
    }
}
