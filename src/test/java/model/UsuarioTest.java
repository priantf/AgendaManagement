/* Interface que modela usuários da Agenda. */
package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UsuarioTest {
	@Test
	public void nomeUsuarioTest(){
		Usuario u1 = new Usuario();
		Usuario u2 = new Usuario();
		u1.setNomeUsuario("Luiz");
		u2.setNomeUsuario("Luiz");
		assertEquals(u1, u2);
	}
}