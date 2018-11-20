/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Contato;
import model.ServicoContatoImpl;

/**
 *
 * @author fabriciogmc
 */
@SuppressWarnings("serial")
public class TelaListagem extends javax.swing.JFrame {

	/**
	 * Creates new form TelaListagem
	 */
	private ServicoContatoImpl servicoContato;
	private String nome_arq_dados_c;

	public TelaListagem(String nome_arq_dados_c) {
		this.nome_arq_dados_c = nome_arq_dados_c;
		servicoContato = new ServicoContatoImpl(nome_arq_dados_c);
		initComponents();
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// this.list1.add("Fabrício; 22222222; prof.fabriciogmc@gmail.com, Rua do
		// Fabrício");
		// this.list1.add("Pedro; 111111; pedro@pedro.com, Rua do Pedro");

	}
	
	public void setContatoParaBusca(String parteNome) {
		List<Contato> contatos = new ArrayList<>();
		contatos = servicoContato.buscarPorParteNome(parteNome);
		if (contatos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum Contato Encontrado");
			dispose();
		} else {
			for (Contato contato : contatos) {
				this.list1.add(contato.getNome() + ";" + contato.getTelefone() + ";" + contato.getEmail() + ";"
						+ contato.getEndereco());
			}
		}
	}

	public void setBusca() {
		List<Contato> contatos = new ArrayList<>();
		contatos = servicoContato.listarTodosContatos();
		if (contatos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum Contato Encontrado");
			dispose();
		} else {
			for (Contato contato : contatos) {
				this.list1.add(contato.getNome() + ";" + contato.getTelefone() + ";" + contato.getEmail() + ";"
						+ contato.getEndereco());
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		list1 = new java.awt.List();
		jButton1 = new javax.swing.JButton();
		jButton1.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String contato = list1.getSelectedItem();
				String[] infoContato = contato.split(";");
				TelaCadastro tc = new TelaCadastro(nome_arq_dados_c);
				Contato cAnt = new Contato();
				cAnt.setNome(infoContato[0]);
				cAnt.setTelefone(infoContato[1]);
				cAnt.setEmail(infoContato[2]);
				cAnt.setEndereco(infoContato[3]);
				list1.delItem(list1.getSelectedIndex());
				tc.editarContato(cAnt);

				dispose();
			}
		});
		jButton2 = new javax.swing.JButton();
		jButton2.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int posicao = list1.getSelectedIndex();

				String contato = list1.getSelectedItem();
				String[] contatoInfo = contato.split(";");

				Contato c = new Contato();

				c.setNome(contatoInfo[0]);
				c.setTelefone(contatoInfo[1]);
				c.setEmail(contatoInfo[2]);
				c.setEndereco(contatoInfo[3]);
				servicoContato.removerContato(c);

				list1.delItem(posicao);
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Contatos existentes na agenda:");

		list1.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				list1ItemStateChanged(evt);
			}
		});

		jButton1.setText("Editar");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Excluir");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(33, 33, 33)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(jButton1)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton2))
								.addComponent(jLabel1).addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 306,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(41, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(22, 22, 22)
						.addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 218,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(23, 23, 23)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1).addComponent(jButton2))
						.addContainerGap(32, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void list1ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_list1ItemStateChanged

	}// GEN-LAST:event_list1ItemStateChanged

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed

	}// GEN-LAST:event_jButton1ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private java.awt.List list1;
	// End of variables declaration//GEN-END:variables
}