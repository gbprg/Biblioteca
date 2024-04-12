package br.com.gbdev.projeto.service;

import br.com.gbdev.projeto.dao.LivroDAO;
import br.com.gbdev.projeto.model.Livro;

import java.util.List;

public class LivroService {
    private LivroDAO livroDAO;

    public LivroService(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    public void emprestarLivro(int id) {
        Livro livro = livroDAO.buscarLivro(id);
        if (livro != null) {
            System.out.println("Livro emprestado com sucesso.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public void devolverLivro(int id) {
        Livro livro = livroDAO.buscarLivro(id);
        if (livro != null) {
            System.out.println("Livro devolvido com sucesso.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

}