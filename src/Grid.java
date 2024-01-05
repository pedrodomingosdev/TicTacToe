public class Grid {
    private char[][] tabuleiro;
    private int x, y;

    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
        tabuleiro = new char[x][y];
        limparTabuleiro();
    }

    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void limparTabuleiro() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public void exibirTabuleiro() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }
}


