package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ContatoTest {
	
	@Test
	public void nomeUsuarioTest(){
		Contato c1 = new Contato();
		Contato c2 = new Contato();
		c1.setNome("Luiz");
		c2.setNome("Luiz");
		assertEquals(c1, c2);
	}
}
