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
public class ServicoContatoImpl implements ServicoContato {

	private ContatoDAOImpl contatoDAOImpl;

	/*
	 * O construtor deve receber como parÃ¢metro o nome do arquivo de dados de
	 * contatos (referente ao usuÃ¡rio especÃ­fico carregado)
	 */
	public ServicoContatoImpl(String nome_arq_dados_c) {
		contatoDAOImpl = new ContatoDAOImpl(nome_arq_dados_c);
	}

	@Override
	public Contato inserir(Contato c) {

		return contatoDAOImpl.inserir(c);

	}

	@Override
	public Contato inserir(String nome, String tel, String email, String end) {
		if (buscarContato(nome) == null) {
			if (nome.matches("[a-zA-ZwÀ-ú\\s0-9\\\\.]+")) {
				if (tel.length() >= 8 && tel.length() <= 20 && tel.matches("[0-9]+")) {
					email = email.toLowerCase();
					String[] partesEmail = email.split("@");
					if (partesEmail.length == 2) {
						if (!partesEmail[0].equals("") && !partesEmail[1].equals("")) {
							String preArroba = partesEmail[0];
							String posArroba = partesEmail[1];
							boolean soNumeros = false;
							if (preArroba.matches("[0-9]+"))
								soNumeros = true;
							if (!soNumeros && posArroba.matches("[a-z0-9\\.]+")) {
								if (Character.isLetter(posArroba.charAt(0))) {
									String[] verificaPonto = posArroba.split("[.]");
									if (verificaPonto.length == 2) {
										String[] palavrasProibidas = { "lugar nenhum", "judas perdeu as botas",
												"casa da mãe joana", "num sei" };
										boolean permitido = true;
										for (String proibido : palavrasProibidas) {
											if (end.toLowerCase().contains(proibido)) {
												permitido = false;
											}
										}
										if (permitido) {
											if (end.length() >= 3 && end.length() <= 255 && end.matches("[a-z0-9 ]+")) {

												Contato c = new Contato();
												c.setNome(nome);
												c.setEmail(email);
												c.setTelefone(tel);
												c.setEndereco(end);

												return inserir(c);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public List<Contato> buscarPorParteNome(String parteNome) {

		return contatoDAOImpl.buscarPorParteNome(parteNome.toLowerCase());

	}

	@Override
	public boolean removerContato(Contato c) {
		if (listarTodosContatos().contains(c))
			return contatoDAOImpl.removerContato(c);

		return false;

	}

	@Override
	public Contato atualizarContato(Contato cAnt, Contato cAtual) {
		if (listarTodosContatos().contains(cAnt))
			if(inserir(cAtual) != null)
				return contatoDAOImpl.atualizarContato(cAnt, cAtual);
			else
				return null;
		else return null;

	}

	@Override
	public List<Contato> listarTodosContatos() {

		return contatoDAOImpl.listarTodosContatos();

	}

	@Override
	public Contato buscarContato(String nome) {

		return contatoDAOImpl.buscarContato(nome);
	}

}
