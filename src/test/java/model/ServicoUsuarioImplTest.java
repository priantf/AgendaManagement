/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 *
 * @author fabriciogmc
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ServicoUsuarioImplTest {

	ServicoUsuarioImpl servicoUsuario = new ServicoUsuarioImpl();
	
	@BeforeAll
	public void inserirTest() {
		Usuario u = new Usuario();
		String confirmSenha;
		u.setNomeUsuario("luiz0");//"oxlc3"
		u.setSenha("abcde"); 
		confirmSenha = "abcde";
		assertNotNull(servicoUsuario.inserir(u.getNomeUsuario(), u.getSenha(), confirmSenha, u.getContatos()));
		
		
		u.setNomeUsuario("luizfer");//"oxlcihu"
		u.setSenha("abc1234"); 
		confirmSenha = "abc1234";
		assertNotNull(servicoUsuario.inserir(u.getNomeUsuario(), u.getSenha(), confirmSenha, u.getContatos()));

		
		u.setNomeUsuario("luizfernandopri");//"oxlcihuqdqgrsul"
		u.setSenha("abcdefgh12345"); 
		confirmSenha = "abcdefgh12345";
		assertNotNull(servicoUsuario.inserir(u.getNomeUsuario(), u.getSenha(), confirmSenha, u.getContatos()));
	
		
		u.setNomeUsuario("luizfernandopri2");
		u.setSenha("abcdefgh12345678");
		confirmSenha = "abcdefgh12345678";
		assertNull(servicoUsuario.inserir(u.getNomeUsuario(), u.getSenha(), confirmSenha, u.getContatos()));
		
		
		u.setNomeUsuario("luiz$");
		u.setSenha("1234%");
		confirmSenha = "1234%";
		assertNull(servicoUsuario.inserir(u.getNomeUsuario(), u.getSenha(), confirmSenha, u.getContatos()));
		

		u.setNomeUsuario("luizw");
		u.setSenha("12345");
		confirmSenha = "1234";
		assertNull(servicoUsuario.inserir(u.getNomeUsuario(), u.getSenha(), confirmSenha, u.getContatos()));
		
	}

	@Test
	public void buscarPorNomeUsuarioTest() {
		String nomeUsuario = "luiz0"; //"oxlc3"
		String nomeUsuario2 = "luiz2";
		
		assertAll ("Testando tudo",
		() -> assertNull(servicoUsuario.buscarPorNomeUsuario(nomeUsuario2)),
		() -> assertNotNull(servicoUsuario.buscarPorNomeUsuario(nomeUsuario)));
	}

	@Test
	public void removerUsuarioTest() {
		Usuario u = servicoUsuario.criptografa("luizfernandopri", "abcdefgh12345678");
		assertTrue(servicoUsuario.removerUsuario(u));
		
		u = servicoUsuario.criptografa("luizfernando", "abcdefgh1238"); //Usuario nao existe
		assertFalse(servicoUsuario.removerUsuario(u));
	}
	

	@Test
	public void listarTodosUsuariosTest() {
		for (Usuario usuario : servicoUsuario.listarTodosUsuarios()) {
			assertTrue(usuario instanceof Usuario);
		}
	}
	
	@Test
	public void atualizarUsuario() {
		Usuario uAnt = servicoUsuario.criptografa("luizfer", "abc1234");
		
		Usuario uAtual = servicoUsuario.criptografa("atualizado", "abcde01");
		assertSame(uAtual.getNomeUsuario(), servicoUsuario.atualizarUsuario(uAnt, uAtual).getNomeUsuario());
		
		uAnt = servicoUsuario.criptografa("luizNaoExiste", "abc1234");
		assertNotSame(uAtual, servicoUsuario.atualizarUsuario(uAnt, uAtual) );
	}
	
	@Test
	public void criptografa() {
		String nomeUsuario = "luiz0";//oxlc3
		String senha = "luiz0";
		Usuario u = new Usuario();
		u.setNomeUsuario("oxlc3");
		u.setSenha("oxlc3");
		assertEquals(u, servicoUsuario.criptografa(nomeUsuario, senha));
	}
	
	@Test
	public void descriptografa() {
		String nomeUsuario = "oxlc3";//oxlc3
		String senha = "oxlc3";
		Usuario u = new Usuario();
		u.setNomeUsuario("luiz0");
		u.setSenha("luiz0");
		assertEquals(u, servicoUsuario.descriptografa(nomeUsuario, senha));
	}
	
}
