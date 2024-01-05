import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grid grid = new Grid(3, 3);
        Jogo jogo = new Jogo(grid);
        Computador computador = new Computador(grid, jogo);


        System.out.println("1 - Jogar Sozinho");
        System.out.println("2 - Jogar com Amigo");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("1 - Fácil");
                System.out.println("2 - Médio");
                System.out.println("3 - Díficil");
                int dificuldade = scanner.nextInt();
                switch (dificuldade) {
                    case 1:
                        System.out.println("Escolha entre X ou O: ");
                        char jogadorFacil;
                        do {
                            jogadorFacil = scanner.next().charAt(0);
                        } while (jogadorFacil != 'X' && jogadorFacil!= 'O');
                        computador.JogarComputador(jogadorFacil, dificuldade);
                        break;
                    case 2:
                        System.out.println("Escolha entre X ou O: ");
                        char jogadorMedio;
                        do {
                            jogadorMedio = scanner.next().charAt(0);
                        } while (jogadorMedio != 'X' && jogadorMedio != 'O');
                        computador.JogarComputador(jogadorMedio, dificuldade);
                        break;
                    case 3:
                        System.out.println("Escolha entre X ou O: ");
                        char jogadorDificil;
                        do {
                            jogadorDificil = scanner.next().charAt(0);
                        } while (jogadorDificil != 'X' && jogadorDificil != 'O');
                        computador.JogarComputador(jogadorDificil, dificuldade);
                        break;

                }
                break;
            case 2:
                System.out.println("Escolha entre X ou O: ");
                char jogadorAtual;
                do {
                    jogadorAtual = scanner.next().charAt(0);
                } while (jogadorAtual != 'X' && jogadorAtual != 'O');
                jogo.JogarAmigo(jogadorAtual);
                break;


        }
    }
}





