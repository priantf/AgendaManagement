package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(Lifecycle.PER_CLASS)
public class ServicoContatoImplTest {

	ServicoContatoImpl servicoContato = new ServicoContatoImpl("data_uoxlc3.txt");
	Contato c;

	@BeforeEach
	public void servicoContato() {
		c = new Contato();
	}

	@BeforeAll
	public void inserir() {
		Contato c = new Contato();

		// Cadastro Inválido
		c.setNome("Luiz@02"); // Nome com caractere invalido
		c.setTelefone("123456789");
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("lugar nenhum");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));// false

		// Cadastro Inválido
		c.setNome(""); // Nome vazio
		c.setTelefone("123456789");
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("rua 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro válido
		c.setNome("Luiz Fernando");
		c.setTelefone("123456789");
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("rua 2");
		assertEquals(c, servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz Fernando"); // Nome Existente
		c.setTelefone("123456789");
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("rua 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 02");
		c.setTelefone("123456"); // Telefone com menos de 8 caracteres
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 02");
		c.setTelefone("123456789123456789123"); // Telefone com mais de 20 caracteres
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 02");
		c.setTelefone("cadastrado"); // Telefone com letras
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");
		// assertEquals(c, servicoContato.inserir(c.getNome(), c.getTelefone(),
		// c.getEmail(), c.getEndereco()));
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Válido
		c.setNome("Luiz 02");
		c.setTelefone("12345678"); // Telefone com 8 caracteres
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");
		assertNotNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Válido
		c.setNome("Luiz 03");
		c.setTelefone("12345678912345678912"); // Telefone com 20 caracteres
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");
		assertNotNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 04");
		c.setTelefone("123456789");
		c.setEmail("@gmail.com"); // String vazia antes do arroba
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 04");
		c.setTelefone("123456789");
		c.setEmail("02@gmail.com"); // Apenas números antes do arroba
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 04");
		c.setTelefone("123456789");
		c.setEmail("luiz02@ gmail.com"); // Espaço em branco logo após o @
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 04");
		c.setTelefone("123456789");
		c.setEmail("luiz02@gmailcom"); // Sem "." depois do @
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 04");
		c.setTelefone("123456789");
		c.setEmail("luiz02@1gmail.com"); // Um numero logo após o @
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 04");
		c.setTelefone("123456789");
		c.setEmail("luiz02@@gmail.com"); // Mais de um @
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 04");
		c.setTelefone("123456789");
		c.setEmail("luiz02gmail.com"); // Sem @
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 04");
		c.setTelefone("123456789");
		c.setEmail("luiz02@gm.ail.com"); // Mais de um "." depois do @
		c.setEndereco("avenida dois 2");
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Válido
		c.setNome("Luiz 04");
		c.setTelefone("123456789");
		c.setEmail("luiz04@gmail.com"); // Email correto
		c.setEndereco("avenida dois 2");
		assertNotNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 05");
		c.setTelefone("123456789");
		c.setEmail("luiz05@gmail.com");
		c.setEndereco("av"); // Menos de 3 caracteres no endereço
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 05");
		c.setTelefone("123456789");
		c.setEmail("luiz05@gmail.com");
		c.setEndereco(
				"01234567891011121314151617181920212223242526272829303132333435363738394041424344454647484950515253545556575859606162636465666768697071727374757677787980818283848586878889909192939495969798991001011021031041051061071081091101111121131141151161171181191201219"); // Menos
		// Endereço com mais de 255 caracteres (com 256)
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Válido
		c.setNome("Luiz 05");
		c.setTelefone("123456789");
		c.setEmail("luiz05@gmail.com");
		c.setEndereco("ave"); // Endereço com 3 caracteres
		assertNotNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Válido
		c.setNome("Luiz 06");
		c.setTelefone("123456789");
		c.setEmail("luiz05@gmail.com");
		c.setEndereco(
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		// Endereço com 255 caracteres
		assertNotNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 7");
		c.setTelefone("123456789");
		c.setEmail("luiz05@gmail.com");
		c.setEndereco("Na avenida lugar nenhum"); // palavra proibida
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 7");
		c.setTelefone("123456789");
		c.setEmail("luiz05@gmail.com");
		c.setEndereco("Onde o judas perdeu a bota"); // palavra proibida
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 7");
		c.setTelefone("123456789");
		c.setEmail("luiz05@gmail.com");
		c.setEndereco("Rapaz, pior que num sei"); // palavra proibida
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

		// Cadastro Inválido
		c.setNome("Luiz 7");
		c.setTelefone("123456789");
		c.setEmail("luiz05@gmail.com");
		c.setEndereco("Na casa da senhora sua mae joana"); // palavra proibida
		assertNull(servicoContato.inserir(c.getNome(), c.getTelefone(), c.getEmail(), c.getEndereco()));

	}

	@ParameterizedTest
	@ValueSource(strings = { "Luiz", "L" })
	/*
	 * ParameterizedTest irá te fornecer a possibilidade de passar valores para
	 * teste como parâmtros para a funçao e, assim, evitar que fique criando os
	 * valores dentro do método
	 * 
	 */
	public void buscarPorParteNomeTest(String parteNome) {
		assertNotNull(servicoContato.buscarPorParteNome(parteNome));
	}

	@Test
	public void removerContatoTest() {
		c.setNome("Luiz 02");
		c.setTelefone("12345678");
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");

		assumeTrue(servicoContato.removerContato(c));

		c.setNome("Luiz fsdfsd");
		c.setTelefone("12345678");
		c.setEmail("aaaa@gmail.com");
		c.setEndereco("avenida dois 2");

		assumeFalse(servicoContato.removerContato(c));
	}

	@Test
	public void atualizarContatoTest() {
		// Atualizando um contato
		Contato cAnt = new Contato();
		cAnt.setNome("Luiz 03");
		cAnt.setTelefone("12345678912345678912");
		cAnt.setEmail("aaaa@gmail.com");
		cAnt.setEndereco("avenida dois 2");

		Contato cAtual = new Contato();

		cAtual.setNome("Luiz 777");
		cAtual.setTelefone("1234567891");
		cAtual.setEmail("luiz@gmail.com");
		cAtual.setEndereco("avenida dois mil");
		assertEquals(cAtual, servicoContato.atualizarContato(cAnt, cAtual));

		cAnt = cAtual;

		cAtual.setNome("Luiz@777"); // Falha proposital para realizar a verificaçao na atualizaçao de contato
		cAtual.setTelefone("1234567891");
		cAtual.setEmail("luiz@gmail.com");
		cAtual.setEndereco("avenida dois mil");
		assertNull(servicoContato.atualizarContato(cAnt, cAtual));

		cAnt.setNome("Luiz nao existe"); // Editando um contato inexistente
		cAnt.setTelefone("12345678912345678912");
		cAnt.setEmail("aaaa@gmail.com");
		cAnt.setEndereco("avenida dois 2");
		assertNotEquals(cAtual, servicoContato.atualizarContato(cAnt, cAtual));
	}

	@Test
	@DisplayName("Lista todos os contatos cadastrados")
	/*
	 * DisplayName é usado para que você dê um nome para o seu método de teste no
	 * relatório
	 */
	public void listarTodosContatosTest() {
		assertNotNull(servicoContato.listarTodosContatos());
	}

	@RepeatedTest(3)
	/*
	 * RepeatedTes irá executar o mesmo teste determinado numero de vezes e irá
	 * colocar cada uma delas no relatório de teste
	 */
	public void buscarContatoTest() {
		assertTrue(servicoContato.buscarContato("Luiz 06") instanceof Contato);

		assertFalse(servicoContato.buscarContato("Luiz Inexistente") instanceof Contato);
	}

	@SuppressWarnings("resource")
	@AfterAll
	public void limparArquivo() {
		String path;
		try {
			path = new File("../trabalho2/src/main/resources/users.txt").getCanonicalPath();
			FileWriter gravacao = new FileWriter(path);
			PrintWriter pw = new PrintWriter(gravacao);

			pw.println("");

		} catch (IOException e) {

		}

		try {
			String pathData = new File("../trabalho2/src/main/resources/").getCanonicalPath();

			File dir = new File(pathData);

			File[] arq = dir.listFiles();
			for (File file : arq) {
				if (file.getName().contains("data_uoxlc3.txt")) {
					file.delete();
				}
			}

		} catch (IOException e) {

		}

	}

}
