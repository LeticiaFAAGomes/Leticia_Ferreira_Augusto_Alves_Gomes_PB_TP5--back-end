package br.edu.infnet.leticia.JSports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.infnet.leticia.JSports.enums.TipoUsuario;
import br.edu.infnet.leticia.JSports.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public boolean realizarCadastro(String nome, String email, String senha, String telefone, TipoUsuario tipoUsuario) {
        return usuarioService.executarCadastro(nome, email, senha, telefone, tipoUsuario);
    }

    public boolean realizarLogin(String email, String senha) {
        return usuarioService.executarLogin(email, senha);
    }
}
