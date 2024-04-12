package br.com.gbdev.projeto.dao;

import br.com.gbdev.projeto.model.Livro;
import java.util.List;


public interface LivroDAO {
    void adicionarLivro(Livro livro);
    Livro buscarLivro(int id);
    List<Livro> listarLivros();
    void atualizarLivro(Livro livro);
    void deletarLivro(int id);
}
