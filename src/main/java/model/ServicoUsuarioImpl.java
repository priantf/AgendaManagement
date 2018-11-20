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
public class ServicoUsuarioImpl implements ServicoUsuario {

	UsuarioDAOImpl usuarioDAO;

	public ServicoUsuarioImpl() {
		usuarioDAO = new UsuarioDAOImpl();
	}

	@Override
	public Usuario inserir(Usuario u) {

		return usuarioDAO.inserir(u);
	}

	@Override
	public Usuario inserir(String nomeUsuario, String senha, String confirmSenha, List<Contato> contatos) {
		if (usuarioDAO.buscarPorNomeUsuario(nomeUsuario).isEmpty()) {
			if (nomeUsuario.length() >= 5 && nomeUsuario.length() <= 15 && nomeUsuario.matches("[a-zA-Z0-9]+")) {
				if (senha.length() >= 5 && senha.length() <= 15 && senha.matches("[a-zA-Z0-9]+")
						&& confirmSenha.equals(senha)) {

					Usuario u = new Usuario();
					u.setNomeUsuario(nomeUsuario);
					u.setSenha(senha);
					Usuario u2 = criptografa(u.getNomeUsuario(), u.getSenha());

					return inserir(u2);
				}
			}
		}
		return null;
	}

	@Override
	public List<Usuario> buscarPorNomeUsuario(String nomeUsuario) {
		Usuario u = criptografa(nomeUsuario, "");
		if (listarTodosUsuarios().contains(u))
			return usuarioDAO.buscarPorNomeUsuario(u.getNomeUsuario());

		return null;
	}

	@Override
	public boolean removerUsuario(Usuario u) {
		if (listarTodosUsuarios().contains(u))
			return usuarioDAO.removerUsuario(u);

		return false;
	}

	@Override
	public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
		if (listarTodosUsuarios().contains(uAnt))
			return usuarioDAO.atualizarUsuario(uAnt, uAtual);

		return null;
	}

	@Override
	public List<Usuario> listarTodosUsuarios() {
		return usuarioDAO.listarTodosUsuarios();
	}

	@Override
	public Usuario validarUsuario(String nomeUsuario, String senha) {
		Usuario u = criptografa(nomeUsuario, senha);
		if (listarTodosUsuarios().contains(u))
			return usuarioDAO.validarUsuario(u.getNomeUsuario(), u.getSenha());

		return null;
	}

	@Override
	public Usuario criptografa(String nomeUsuario, String senha) {
		Usuario u = new Usuario();
		String nomeCript = "";
		for (int i = 0; i < nomeUsuario.length(); i++) {
			int letra = 0;
			if (nomeUsuario.charAt(i) == 'z')
				letra = 96;
			else if (nomeUsuario.charAt(i) == 'y')
				letra = 95;
			else if (nomeUsuario.charAt(i) == 'x')
				letra = 94;
			else if (nomeUsuario.charAt(i) == '9')
				letra = 47;
			else if (nomeUsuario.charAt(i) == '8')
				letra = 46;
			else if (nomeUsuario.charAt(i) == '7')
				letra = 45;
			else
				letra = (int) nomeUsuario.charAt(i);

			letra += 3;
			nomeCript += (char) letra;
		}
		u.setNomeUsuario(nomeCript);

		String passCript = "";
		for (int i = 0; i < senha.length(); i++) {
			int letra;
			if (senha.charAt(i) == 'z')
				letra = 96;
			else if (senha.charAt(i) == 'y')
				letra = 95;
			else if (senha.charAt(i) == 'x')
				letra = 94;
			else if (senha.charAt(i) == '9')
				letra = 47;
			else if (senha.charAt(i) == '8')
				letra = 46;
			else if (senha.charAt(i) == '7')
				letra = 45;
			else {
				letra = (int) senha.charAt(i);
				
			}

			letra += 3;

			passCript += (char) letra;
		}
		u.setSenha(passCript);
		return u;
	}

	@Override
	public Usuario descriptografa(String nomeUsuario, String senha) {
		Usuario u = new Usuario();
		String nomeCript = "";
		for (int i = 0; i < nomeUsuario.length(); i++) {
			int letra;
			if (nomeUsuario.charAt(i) == 'a')
				letra = 120;
			else if (nomeUsuario.charAt(i) == 'b')
				letra = 121;
			else if (nomeUsuario.charAt(i) == 'c')
				letra = 122;
			else if (nomeUsuario.charAt(i) == '0')
				letra = 55;
			else if (nomeUsuario.charAt(i) == '1')
				letra = 56;
			else if (nomeUsuario.charAt(i) == '2')
				letra = 57;
			else {
				letra = (int) nomeUsuario.charAt(i);
				letra -= 3;
			}
			
			nomeCript += (char) letra;
		}
		u.setNomeUsuario(nomeCript);

		String passCript = "";
		for (int i = 0; i < senha.length(); i++) {
			int letra;
			if (senha.charAt(i) == 'a')
				letra = 120;
			else if (senha.charAt(i) == 'b')
				letra = 121;
			else if (senha.charAt(i) == 'c')
				letra = 122;
			else if (senha.charAt(i) == '0')
				letra = 55;
			else if (senha.charAt(i) == '1')
				letra = 56;
			else if (senha.charAt(i) == '2')
				letra = 57;
			else {
				letra = (int) senha.charAt(i);
				letra -= 3;
			}
			
			passCript += (char) letra;
		}
		u.setSenha(passCript);

		return u;
	}

}
