package br.com.gbdev.projeto.app;

import br.com.gbdev.projeto.dao.LivroDAO;
import br.com.gbdev.projeto.dao.LivroDAOImpl;
import br.com.gbdev.projeto.model.Livro;
import br.com.gbdev.projeto.service.LivroService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Criação do DAO e do Service
        LivroDAO livroDAO = new LivroDAOImpl();
        LivroService livroService = new LivroService(livroDAO);

        // Criação do Scanner para entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Lógica da aplicação
        int opcao;
        do {
            System.out.println("==== Menu ====");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Emprestar livro");
            System.out.println("4. Devolver livro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    adicionarLivro(scanner, livroDAO);
                    break;
                case 2:
                    listarLivros(livroDAO);
                    break;
                case 3:
                    try {
                        System.out.print("Informe o ID do livro a ser emprestado: ");
                        int idEmprestimo = scanner.nextInt();
                        livroService.emprestarLivro(idEmprestimo);
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número válido.");
                    }
                    scanner.nextLine(); // Limpa o buffer do scanner
                    break;
                case 4:
                    try {
                        System.out.print("Informe o ID do livro a ser devolvido: ");
                        int idDevolucao = validarIdLivro(scanner);
                        livroService.devolverLivro(idDevolucao);
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número válido.");
                        scanner.nextLine();
                        opcao = 0; // Definir opcao como 0 para evitar loops infinitos
                    }
                    scanner.nextLine(); // Limpa o buffer do scanner
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 5.");
            }

        } while (opcao != 5);

        scanner.close();
    }

    private static void adicionarLivro(Scanner scanner, LivroDAO livroDAO) {
        System.out.print("Informe o título do livro: ");
        scanner.nextLine();
        String titulo = scanner.nextLine();

        // Verifica se o título do livro tem no máximo 20 caracteres
        if (titulo.length() > 20) {
            System.out.println("O título do livro não pode ter mais de 20 caracteres.");
            return; // Sai do método se o título for muito longo
        }

        System.out.print("Informe o autor do livro: ");
        String autor = scanner.nextLine();

        int anoPublicacao = 0;
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("Informe o ano de publicação do livro: ");
            String entradaAno = scanner.nextLine(); // Lê toda a linha como uma string

            try {
                anoPublicacao = Integer.parseInt(entradaAno); // Tenta converter a string em um inteiro
                inputValido = true; // Se a conversão for bem-sucedida, o input é válido
            } catch (NumberFormatException e) {
                System.out.println("Ano de publicação inválido. Por favor, insira um número inteiro.");
            }
        }

        Livro novoLivro = new Livro();
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoPublicacao(anoPublicacao);

        livroDAO.adicionarLivro(novoLivro);
        System.out.println("Livro adicionado com sucesso.");
    }




    private static void listarLivros(LivroDAO livroDAO) {
        System.out.println("==== Listagem de Livros ====");
        for (Livro livro : livroDAO.listarLivros()) {
            System.out.println("ID: " + livro.getId());
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
            System.out.println();
        }
    }

    private static int validarIdLivro(Scanner scanner) throws InputMismatchException {
        int id;
        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw e;
        }
        return id;
    }

}