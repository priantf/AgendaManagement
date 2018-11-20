package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContatoDAOImpl implements ContatoDAO {

	private String nome_arq_dados_c;

	public ContatoDAOImpl(String nome_arq_dados_c) {
		this.nome_arq_dados_c = nome_arq_dados_c;
	}

	@Override
	public Contato inserir(Contato c) {

		try {
			File arquivo = new File("../trabalho2/src/main/resources/" + nome_arq_dados_c);
			//if (arquivo.exists()) {
				String path = arquivo.getCanonicalPath();
				FileWriter gravacao = new FileWriter(path, true);
				PrintWriter pw = new PrintWriter(gravacao);

				pw.println(c.getNome() + ";" + c.getTelefone() + ";" + c.getEmail() + ";" + c.getEndereco());

				pw.flush();
				pw.close();
				gravacao.close();

				return c;
			//} else
			//	return null;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Contato> buscarPorParteNome(String parteNome) {
		ArrayList<Contato> contatos = new ArrayList<>();
		try {
			File arquivo = new File("../trabalho2/src/main/resources/" + nome_arq_dados_c);
				String path = arquivo.getCanonicalPath();
				BufferedReader br = new BufferedReader(new FileReader(path));

				String linha;
				while ((linha = br.readLine()) != null) {
					String[] contatoString = linha.split(";");
					if (contatoString[0].toLowerCase().startsWith(parteNome.toLowerCase())) {
						Contato contato = new Contato();
						contato.setNome(contatoString[0]);
						contato.setTelefone(contatoString[1]);
						contato.setEmail(contatoString[2]);
						contato.setEndereco(contatoString[3]);
						contatos.add(contato);
					}
				}
				br.close();
				return contatos;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public boolean removerContato(Contato c) {
		List<Contato> contatos = new ArrayList<>();
		try {
			File arquivo = new File("../trabalho2/src/main/resources/" + nome_arq_dados_c);
				String path = arquivo.getCanonicalPath();
				BufferedReader br = new BufferedReader(new FileReader(path));

				String linha;
				while ((linha = br.readLine()) != null) {
					Contato contato = new Contato();
					String[] infoContato = linha.split(";");
					contato.setNome(infoContato[0]);
					contato.setTelefone(infoContato[1]);
					contato.setEmail(infoContato[2]);
					contato.setEndereco(infoContato[3]);
					contatos.add(contato);

				}

				contatos.remove(c);

				try {
					FileWriter gravacao = new FileWriter(path);
					PrintWriter pw = new PrintWriter(gravacao);

					pw.print("");

					pw.flush();
					pw.close();
					gravacao.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				for (Contato contato : contatos) {
					inserir(contato);
				}
				br.close();

				return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Contato atualizarContato(Contato cAnt, Contato cAtual) {
		removerContato(cAnt);
		if (buscarPorParteNome(cAtual.getNome()) != null)
			return cAtual;

		return null;

	}

	@Override
	public List<Contato> listarTodosContatos() {
		List<Contato> contatos = new ArrayList<>();
		try {
			File arquivo = new File("../trabalho2/src/main/resources/" + nome_arq_dados_c);
				String path = arquivo.getCanonicalPath();
				BufferedReader br = new BufferedReader(new FileReader(path));

				String linha;
				while ((linha = br.readLine()) != null) {
					String[] contatoString = linha.split(";");
					Contato contato = new Contato();
					contato.setNome(contatoString[0]);
					contato.setTelefone(contatoString[1]);
					contato.setEmail(contatoString[2]);
					contato.setEndereco(contatoString[3]);
					contatos.add(contato);
				}
				br.close();
				if (contatos.isEmpty())
					return null;

				Collections.sort(contatos);
				return contatos;
		} catch (Exception e) {
			return null;
		}

		

	}

	@Override
	public Contato buscarContato(String nome) {
		List<Contato> contatos = listarTodosContatos();

		if (contatos == null)
			return null;
		for (Contato contato : contatos) {
			if (contato.getNome().toLowerCase().equals(nome.toLowerCase()))
				return contato;
		}

		return null;
	}

}
