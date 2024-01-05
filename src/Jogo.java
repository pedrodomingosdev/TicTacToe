import java.util.Scanner;

public class Jogo {
    private Grid grid;


    public Jogo(Grid grid) {
        this.grid = grid;
    }


    public void colocarX(int linha, int coluna) {
        char[][] tabuleiro = grid.getTabuleiro();

        //Verifica a Grid para colocar o X
        if (linha >= 0 && linha < grid.getX() && coluna >= 0 && coluna < grid.getY() && tabuleiro[linha][coluna] == ' ') {
            tabuleiro[linha][coluna] = 'X';
        } else {
            System.err.println("Posição inválida!");
        }
    }

    public void colocarO(int linha, int coluna) {
        char[][] tabuleiro = grid.getTabuleiro();

        //Verifica a Grid para colocar o O
        if (linha >= 0 && linha < grid.getX() && coluna >= 0 && coluna < grid.getY() && tabuleiro[linha][coluna] == ' ') {
            tabuleiro[linha][coluna] = 'O';
        } else {
            System.err.println("Posição inválida!");
        }
    }

    static boolean jogoFinalizado(Grid grid) {
        // Verificar colunas
        for (int i = 0; i < grid.getX(); i++) {
            if (grid.getTabuleiro()[i][0] != ' ' && grid.getTabuleiro()[i][0] == grid.getTabuleiro()[i][1] && grid.getTabuleiro()[i][1] == grid.getTabuleiro()[i][2]) {
                return true;
            }
        }

        for (int j = 0; j < grid.getY(); j++) {
            if (grid.getTabuleiro()[0][j] != ' ' && grid.getTabuleiro()[0][j] == grid.getTabuleiro()[1][j] && grid.getTabuleiro()[1][j] == grid.getTabuleiro()[2][j]) {
                return true;
            }
        }

        // Verificar diagonais
        if (grid.getTabuleiro()[0][0] != ' ' && grid.getTabuleiro()[0][0] == grid.getTabuleiro()[1][1] && grid.getTabuleiro()[1][1] == grid.getTabuleiro()[2][2]) {
            return true;
        }

        if (grid.getTabuleiro()[0][2] != ' ' && grid.getTabuleiro()[0][2] == grid.getTabuleiro()[1][1] && grid.getTabuleiro()[1][1] == grid.getTabuleiro()[2][0]) {
            return true;

        }


        return false;
    }

    public void JogarAmigo(char jogadorAtual){
        Scanner scanner = new Scanner(System.in);
        while (!jogoFinalizado(grid)) {
            System.out.println("Para saber as Linhas e Colunas, siga esse grade");
            System.out.println("0/0 | 0/1 | 0/2 ");
            System.out.println("1/0 | 1/1 | 1/2  ");
            System.out.println("2/0 | 2/1 | 2/2 ");
            System.out.println("\n");
            System.out.println("É a vez do jogador " + jogadorAtual);
            System.out.println("\n");
            System.out.println("Escolha a linha: ");
            int linha = scanner.nextInt();
            System.out.println("Escolha a coluna: ");
            int coluna = scanner.nextInt();

            if (jogadorAtual == 'X') {
                colocarX(linha, coluna);
                if (jogoFinalizado(grid)) {
                    grid.exibirTabuleiro();
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    break; // Sai do loop
                }
                jogadorAtual = 'O';
            } else {
                colocarO(linha, coluna);
                if (jogoFinalizado(grid)) {
                    grid.exibirTabuleiro();
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    break; // Sai do loop
                }
                jogadorAtual = 'X';
            }

            grid.exibirTabuleiro();
            System.out.println("\n");


            //Verifica o empate
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

        }

    }

}
