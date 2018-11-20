package model;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MockServicoContatoImplTest {
	@Mock
	private ServicoContato servicoContato = (ServicoContato) Mockito.mock(ServicoContatoImpl.class);
	
	@Test
	public void inserirTest() {
		Contato c = new Contato();
		c.setNome("Luiz Mock");
		c.setTelefone("1234567856");
		c.setEmail("Mock@gmail.com");
		c.setEndereco("avenida do mock");
		
		Mockito.when(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco())).thenReturn(c);
	}
	
}
