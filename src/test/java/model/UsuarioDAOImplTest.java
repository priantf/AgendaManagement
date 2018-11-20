package model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class UsuarioDAOImplTest {

	UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
	
	@BeforeAll
	public void inserirTest() {

		Usuario u = new Usuario();

		u = new Usuario();
		u.setNomeUsuario("oxlc3");//"oxlc3"
		u.setSenha("abcde"); 
		assertNotNull(usuarioDAO.inserir(u));
		
		u = new Usuario();
		u.setNomeUsuario("oxlcihu");//"oxlcihu"
		u.setSenha("abc1234"); 
		assertNotNull(usuarioDAO.inserir(u));

		u = new Usuario();
		u.setNomeUsuario("oxlcihunandopri");//"oxlcihuqdqgrsul"
		u.setSenha("abcdefgh12345"); 
		assertNotNull(usuarioDAO.inserir(u));
	
	}

	@Test
	public void buscarPorNomeUsuarioTest() {
		String nomeUsuario = "oxlc3"; //"oxlc3"
		assertNotNull(usuarioDAO.buscarPorNomeUsuario(nomeUsuario));
	}

	@Test
	public void removerUsuarioTest() {
		Usuario u = new Usuario();
		u.setNomeUsuario("oxlcihunandopri");
		u.setSenha("abcdefgh12345678");
		assertTrue(usuarioDAO.removerUsuario(u));
	}
	

	@Test
	public void listarTodosUsuariosTest() {
		for (Usuario usuario : usuarioDAO.listarTodosUsuarios()) {
			assertTrue(usuario instanceof Usuario);
		}
	}
	
	@Test
	public void atualizarUsuario() {
		Usuario uAnt = new Usuario();
		uAnt.setNomeUsuario("oxlcihu");
		uAnt.setSenha("abc1234");
		Usuario uAtual = new Usuario();
		uAtual.setNomeUsuario("atualizado");
		uAtual.setSenha("abcde01");
		assertNotNull(usuarioDAO.atualizarUsuario(uAnt, uAtual));
	}

}
