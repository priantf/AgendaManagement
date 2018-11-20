package view;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import model.ServicoUsuarioImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class TelaCadastroUsuarioAgendaTest {
	
	@Test
	public void telaCadastroUsuarioAgendaTest() {
		TelaCadastroUsuarioAgenda tc = new TelaCadastroUsuarioAgenda("data_uoxlc3.txt");
		tc.setVisible(true);
		
		tc.jTextFieldNome.setText("luizcadastro");
		tc.jTextFieldSenha.setText("luizcadastro");
		tc.jTextFieldConfirmSenha.setText("luizcadastro");
		tc.jButton1.doClick();
		
		ServicoUsuarioImpl servicoUsuario = new ServicoUsuarioImpl();
		
		assertNotNull(servicoUsuario.inserir(tc.jTextFieldNome.getText(), tc.jTextFieldSenha.getText(), tc.jTextFieldConfirmSenha.getText(), null));
		
	}
	
	@SuppressWarnings("resource")
	@AfterEach
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
				if(file.getName().contains("data_")) {
					file.delete();
				}
			}

		} catch (IOException e) {

		}
	}
}
