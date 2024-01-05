import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Computador {

    private Grid grid;
    private Jogo jogo;
    public Computador(Grid grid, Jogo jogo){
        this.grid = grid;
        this.jogo = jogo;
    }
    private void jogadaVitoriosa(char escolha) {
        char[][] tabuleiro = grid.getTabuleiro();

        // Verifica se há uma jogada que levará à vitória
        for (int i = 0; i < grid.getX(); i++) {
            for (int j = 0; j < grid.getY(); j++) {
                if (tabuleiro[i][j] == ' ') {
                    char simbolo = (escolha == 'X') ? 'O' : 'X';
                    tabuleiro[i][j] = simbolo;

                    if (jogo.jogoFinalizado(grid)) {
                        // Se a vitória é garantida, faz a jogada e retorna
                        return;
                    }

                    tabuleiro[i][j] = ' '; // Desfaz a jogada para tentar a próxima posição
                }
            }
        }

        jogadaAleatoria(escolha);
    }


    private void bloquearJogadaJogador(char escolha) {
        char[][] tabuleiro = grid.getTabuleiro();

        // Verifica se o jogador tem uma linha quase completa e a bloqueia
        for (int i = 0; i < grid.getX(); i++) {
            for (int j = 0; j < grid.getY(); j++) {
                if (tabuleiro[i][j] == escolha) {
                    // Tenta bloquear em linha horizontal
                    if (j - 1 >= 0 && j + 2 < grid.getY() && tabuleiro[i][j + 1] == escolha && tabuleiro[i][j - 1] == ' ' && tabuleiro[i][j + 2] == ' ') {
                        tabuleiro[i][j - 1] = (escolha == 'X') ? 'O' : 'X';
                        return;
                    }
                    // Tenta bloquear em linha vertical
                    if (i - 1 >= 0 && i + 2 < grid.getX() && tabuleiro[i + 1][j] == escolha && tabuleiro[i - 1][j] == ' ' && tabuleiro[i + 2][j] == ' ') {
                        tabuleiro[i - 1][j] = (escolha == 'X') ? 'O' : 'X';
                        return;
                    }
                    // Tenta bloquear na diagonal
                    if (i - 1 >= 0 && j - 1 >= 0 && i + 2 < grid.getX() && j + 2 < grid.getY() &&
                            tabuleiro[i - 1][j - 1] == escolha && tabuleiro[i + 1][j + 1] == escolha && tabuleiro[i + 2][j + 2] == ' ') {
                        tabuleiro[i + 2][j + 2] = (escolha == 'X') ? 'O' : 'X';
                        return;
                    }
                    // Tenta bloquear na diagonal oposta
                    if (i - 1 >= 0 && j + 1 < grid.getY() && i + 2 < grid.getX() && j - 2 >= 0 &&
                            tabuleiro[i - 1][j + 1] == escolha && tabuleiro[i + 1][j - 1] == escolha) {
                        if (tabuleiro[i + 2][j - 2] == ' ') {
                            tabuleiro[i + 2][j - 2] = (escolha == 'X') ? 'O' : 'X';
                            return;
                        }
                    }
                    // Tenta bloquear em coluna
                    if (i - 1 >= 0 && i + 2 < grid.getX() && tabuleiro[i + 1][j] == escolha && tabuleiro[i - 1][j] == escolha) {
                        if (tabuleiro[i + 2][j] == ' ') {
                            tabuleiro[i + 2][j] = (escolha == 'X') ? 'O' : 'X';
                            return;
                        } else if (tabuleiro[i - 1][j] == ' ') {
                            tabuleiro[i - 1][j] = (escolha == 'X') ? 'O' : 'X';
                            return;
                        }
                    }
                    // Tenta bloquear em linha
                    if (j - 1 >= 0 && j + 2 < grid.getY() && tabuleiro[i][j - 1] == escolha && tabuleiro[i][j + 1] == ' ' && tabuleiro[i][j + 2] == ' ') {
                        tabuleiro[i][j + 1] = (escolha == 'X') ? 'O' : 'X';
                        return;
                    }
                }
            }
        }

        // Se ainda não houver jogada de bloqueio, faça uma jogada aleatória
        jogadaAleatoria(escolha);
    }

    private void jogadaAleatoria(char escolha) {
        char[][] tabuleiro = grid.getTabuleiro();
        Random random = new Random();
        int linha;
        int coluna;

        // Coloca um símbolo aleatório no Grid
        do {
            linha = random.nextInt(grid.getX());
            coluna = random.nextInt(grid.getY());
        } while (tabuleiro[linha][coluna] != ' ');

        char simbolo = (escolha == 'X') ? 'O' : 'X';
        tabuleiro[linha][coluna] = simbolo;
    }

    public void BotEasy(char escolha){
        jogadaAleatoria(escolha);
    }

    public void BotMedium(char escolha) {
        jogadaVitoriosa(escolha);
    }


    private void BotHard(char escolha) {
        char[][] tabuleiro = grid.getTabuleiro();
        // Verifica se o computador pode vencer nas próximas jogadas e realiza a jogada de vitória
        for (int i = 0; i < grid.getX(); i++) {
            for (int j = 0; j < grid.getY(); j++) {
                if (tabuleiro[i][j] == ' ') {
                    tabuleiro[i][j] = (escolha == 'X') ? 'O' : 'X';

                    if (jogo.jogoFinalizado(grid)) {
                        // Se o computador puder vencer, realiza a jogada de vitória e retorna
                        return;
                    }

                    tabuleiro[i][j] = ' '; // Desfaz a jogada para tentar a próxima posição
                }
            }
        }


        // Verifica se o jogador pode vencer nas próximas jogadas e bloqueia essa possibilidade
        for (int i = 0; i < grid.getX(); i++) {
            for (int j = 0; j < grid.getY(); j++) {
                if (tabuleiro[i][j] == ' ') {
                    tabuleiro[i][j] = escolha;

                    if (jogo.jogoFinalizado(grid)) {
                        // Se o jogador puder vencer, bloqueia a jogada
                        tabuleiro[i][j] = (escolha == 'X') ? 'O' : 'X';
                        return;
                    }

                    tabuleiro[i][j] = ' '; // Desfaz a jogada para tentar a próxima posição
                }
            }
        }

        for (int i = 0; i < grid.getX(); i++) {
            for (int j = 0; j < grid.getY(); j++) {
                if (tabuleiro[i][j] == ' ') {
                    tabuleiro[i][j] = escolha;

                    if (jogo.jogoFinalizado(grid)) {
                        // Se o jogador puder vencer, bloqueia a jogada
                        tabuleiro[i][j] = (escolha == 'X') ? 'O' : 'X';
                        return;
                    }

                    tabuleiro[i][j] = ' '; // Desfaz a jogada para tentar a próxima posição
                }
            }
        }

        if (grid.getTabuleiro()[1][1] == ' ') {
            tabuleiro[1][1] = (escolha == 'X') ? 'O' : 'X';
        }else if (grid.getTabuleiro()[1][1] == escolha) {
            tabuleiro[2][2] = (escolha == 'X') ? 'O' : 'X';
        }else{
            bloquearJogadaJogador(escolha);
        }
    }


    public void JogarComputador(char jogadorAtual, int dificuldade){
        Scanner scanner = new Scanner(System.in);
        while (!jogo.jogoFinalizado(grid)) {
            System.out.println("\nPara saber as Linhas e Colunas, siga esse grade");
            System.out.println("0/0 | 0/1 | 0/2 ");
            System.out.println("1/0 | 1/1 | 1/2  ");
            System.out.println("2/0 | 2/1 | 2/2 ");
            System.out.println("\n");
            System.out.println("Sua Vez!" );
            System.out.println("\n");
            System.out.println("Escolha a linha: ");
            int linha = scanner.nextInt();
            System.out.println("Escolha a coluna: ");
            int coluna = scanner.nextInt();

            if (jogadorAtual == 'X') {
                jogo.colocarX(linha, coluna);
            } else {
                jogo.colocarO(linha, coluna);

            }

            grid.exibirTabuleiro();

            //verifica se você venceu
            if (jogo.jogoFinalizado(grid)) {
                System.out.println("\n");
                System.out.println("Você venceu!");
                break;
                // Sai do loop
            }

            //verifica se houve empate

            boolean empate = true;
            for (int i = 0; i < grid.getX(); i++) {
                for (int j = 0; j < grid.getY(); j++) {
                    if (grid.getTabuleiro()[i][j] == ' ') {
                        empate = false;
                        break;
                    }
                }
            }

            if (empate) {
                System.out.println("Jogo empatou!");
                break;
            }

            System.out.println("\nAgora é a vez do Computador...");

            try {
                // Dorme por 3 segundos (3000 milissegundos)
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // Lida com a exceção se a thread for interrompida enquanto dorme
                e.printStackTrace();
            }

            // Jogada do computador
            if(dificuldade == 1){
                BotEasy(jogadorAtual);
            } else if (dificuldade == 2) {
                BotMedium(jogadorAtual);
            }else {
                BotHard(jogadorAtual);
            }

            System.out.println("\n");
            grid.exibirTabuleiro();

            // Verifica se o você perdeu
            if (jogo.jogoFinalizado(grid)) {
                System.out.println("\n");
                System.out.println("Você perdeu!");
                break; // Sai do loop
            }

            //verifica se houve empate

            boolean empateComputador = true;
            for (int i = 0; i < grid.getX(); i++) {
                for (int j = 0; j < grid.getY(); j++) {
                    if (grid.getTabuleiro()[i][j] == ' ') {
                        empateComputador = false;
                        break;
                    }
                }
            }

            if (empateComputador) {
                System.out.println("Jogo empatou!");
                break;
            }


        }

    }


}

