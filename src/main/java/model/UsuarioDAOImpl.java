package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	@Override
	public Usuario inserir(Usuario u) {
		try {
			String users = "users.txt";
			String paths = "../trabalho2/src/main/resources/";
			String path = new File(paths+users).getCanonicalPath();
			FileWriter gravacao = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(gravacao);

			String usuario = u.getNomeUsuario() + " ;" + u.getSenha();
			pw.println(usuario);

			FileWriter fw = new FileWriter(paths+"data_u" + u.getNomeUsuario() + ".txt");

			pw.flush();
			pw.close();
			fw.close();
			gravacao.close();

			return u;
		} catch (IOException e) {
			
		}

		return null;

	}

	@Override
	public List<Usuario> buscarPorNomeUsuario(String nomeUsuario) {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			String path = new File("../trabalho2/src/main/resources/users.txt")
					.getCanonicalPath();
			BufferedReader br = new BufferedReader(new FileReader(path));

			String linha;
			while ((linha = br.readLine()) != null) {
				String[] uString = linha.split(" ;");
				Usuario u = new Usuario();
				u.setNomeUsuario(uString[0]);
				u.setSenha(uString[1]);
				
				if(u.getNomeUsuario().toLowerCase().equals(nomeUsuario.toLowerCase())) usuarios.add(u);
			}
			br.close();
			return usuarios;
		} catch (Exception e) {

		}

		return null;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean removerUsuario(Usuario u) {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			String path = new File("../trabalho2/src/main/resources/users.txt")
					.getCanonicalPath();
			BufferedReader br = new BufferedReader(new FileReader(path));

			String linha;
			while ((linha = br.readLine()) != null) {
				Usuario u2 = new Usuario();
				String[] uString = linha.split(" ;");
				u2.setNomeUsuario(uString[0]);
				u2.setSenha(uString[1]);
				usuarios.add(u2);
			}
			
			usuarios.remove(u);
			
			try {
				FileWriter gravacao = new FileWriter(path);
				PrintWriter pw = new PrintWriter(gravacao);

				pw.print("");

				pw.flush();
				pw.close();
				gravacao.close();
				
				String pathData = new File("../trabalho2/src/main/resources/").getCanonicalPath();

				File dir = new File(pathData);

				File[] arq = dir.listFiles();
				for (File file : arq) {
					if(file.getName().contains("data_u"+u.getNomeUsuario())) {
						file.delete();
					}
				}
				
			} catch (IOException e) {
				return false;
			}
			
			for (Usuario usuario : usuarios) {
				inserir(usuario);
			}
			br.close();

			return true;
		} catch (Exception e) {
			
		}
		return false;
	}

	@Override
	public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
		if (removerUsuario(uAnt)) return inserir(uAtual);
		
		return null;
	}

	@Override
	public List<Usuario> listarTodosUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		try {
			String path = new File("../trabalho2/src/main/resources/users.txt")
					.getCanonicalPath();
			BufferedReader br = new BufferedReader(new FileReader(path));

			String linha;
			while ((linha = br.readLine()) != null) {
				String[] uString = linha.split(" ;");
				Usuario u = new Usuario();
				u.setNomeUsuario(uString[0]);
				u.setSenha(uString[1]);
				usuarios.add(u);
			}
			br.close();
			return usuarios;
			
		} catch (Exception e) {
			return null;
		}

		
	}

	@SuppressWarnings("resource")
	@Override
	public Usuario validarUsuario(String nomeUsuario, String senha) {

		String linha;
		try {
			String path = new File("../trabalho2/src/main/resources/users.txt").getCanonicalPath();
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((linha = br.readLine()) != null) {
				String[] usuarioString = linha.split(" ;");

				if (nomeUsuario.equals(usuarioString[0]) && senha.equals(usuarioString[1])) {
					Usuario u = new Usuario();
					u.setNomeUsuario(nomeUsuario);
					u.setSenha(senha);
					return u;
				}
			}
			br.close();
		} catch (Exception e) {
			return null;
		}
		return null;

	}

}
