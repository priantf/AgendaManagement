/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author fabriciogmc
 */
public interface UsuarioDAO {
	public Usuario validarUsuario(String nomeUsuario, String senha);
    public Usuario inserir(Usuario u);
    public List<Usuario> buscarPorNomeUsuario(String nomeUsuario);
    public boolean removerUsuario(Usuario u);
    public Usuario atualizarUsuario(Usuario uAnt, 
                                    Usuario uAtual);
    public List<Usuario> listarTodosUsuarios();    
}
