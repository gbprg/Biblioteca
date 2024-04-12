package br.com.gbdev.projeto.dao;

import br.com.gbdev.projeto.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOImpl implements LivroDAO {
    private final List<Livro> livros;
    private static int contador = 0;

    public LivroDAOImpl() {
        livros = new ArrayList<>();
    }

    @Override
    public void adicionarLivro(Livro livro) {
        livro.setId(++contador);
        livros.add(livro);
    }

    @Override
    public Livro buscarLivro(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;  // Retorna null se o livro não for encontrado
    }

    @Override
    public List<Livro> listarLivros() {
        return livros;
    }

    @Override
    public void atualizarLivro(Livro livro) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId() == livro.getId()) {
                livros.set(i, livro);
                break;
            }
        }
    }

    @Override
    public void deletarLivro(int id) {
        Livro livro = buscarLivro(id);
        if (livro != null) {
            livros.remove(livro);
        }
    }
}
