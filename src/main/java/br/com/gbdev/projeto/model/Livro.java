package br.com.gbdev.projeto.model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private static final int MAX_TITULO_LENGTH = 20;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo.length() > MAX_TITULO_LENGTH) {
            throw new IllegalArgumentException("O título do livro deve ter no máximo" + MAX_TITULO_LENGTH + "caracteres.");
        }

        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }



}
