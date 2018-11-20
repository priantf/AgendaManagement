package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ContatoDAOImplTest {
	
	ContatoDAOImpl contatoDAO = new ContatoDAOImpl("data_uwhvwh.txt");

	@BeforeAll
	public void inserir() {
		Contato c = new Contato();
		
		c.setNome("Luiz 021");
		c.setTelefone("12345678"); 
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");
		assertNotNull(contatoDAO.inserir(c));
		
		c.setNome("Luiz 031");
		c.setTelefone("12345678912345678912"); 
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");
		assertNotNull(contatoDAO.inserir(c));
		
		c.setNome("Luiz 041");
		c.setTelefone("123456789"); 
		c.setEmail("luiz04@gmail.com"); 
		c.setEndereco("avenida dois 2");
		assertNotNull(contatoDAO.inserir(c));
		
		c.setNome("Luiz 051");
		c.setTelefone("123456789"); 
		c.setEmail("luiz05@gmail.com"); 
		c.setEndereco("ave"); 
		assertNotNull(contatoDAO.inserir(c));
		
		c.setNome("Luiz 062");
		c.setTelefone("123456789"); 
		c.setEmail("luiz05@gmail.com"); 
		c.setEndereco("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); 
		assertNotNull(contatoDAO.inserir(c));

	}
	
	@Test
	public void buscarPorParteNomeTest() {
		String nome = "Luiz 062";
		
		assertEquals(nome, contatoDAO.buscarContato(nome).getNome());
		//assumingThat(contatoDAO.buscarContato(nome), is(instance of Contato));
	}
	
	@Test
	public void removerContatoTest() {
		Contato c = new Contato();
		c.setNome("Luiz 031");
		c.setTelefone("12345678"); 
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");
		
		assertTrue(contatoDAO.removerContato(c));
		
	}
	
	@Test
	public void atualizarContatoTest() {
		Contato cAnt = new Contato();
		cAnt.setNome("Luiz 021");
		cAnt.setTelefone("12345678"); 
		cAnt.setEmail("aaaa@gmail.com");
		cAnt.setEndereco("avenida dois 2");
		
		Contato cAtual = new Contato();
		
		cAtual.setNome("Luiz 777");
		cAtual.setTelefone("1234567891"); 
		cAtual.setEmail("luiz@gmail.com");
		cAtual.setEndereco("avenida dois mil");
		
		assertSame(cAtual, contatoDAO.atualizarContato(cAnt, cAtual));
	}
	
	@Test
	public void listarTodosContatosTest() {
		assertNotNull(contatoDAO.listarTodosContatos());
	}
	
	@Test
	public void buscarContato() {
		String nome = "Luiz 051";
		
		assertNotNull(contatoDAO.buscarContato(nome));
		
	}
	

}
