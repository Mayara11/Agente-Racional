import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgenteRacional {

    private static class Coordenadas {
        int x;
        int y;

        Coordenadas(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Quarto {
        char[][] ambiente;
        char[][] estadoQuarto;
        Coordenadas posicaoAgente;
        int energia = 100;
        int bolsa = 0;

        Quarto(char[][] ambiente, char[] pontosSujos) {
            this.ambiente = ambiente;
            this.estadoQuarto = new char[ambiente.length][ambiente[0].length];
            this.inicializarEstadoQuarto(pontosSujos);
            this.posicaoAgente = new Coordenadas(0, 0);
        }

        private void inicializarEstadoQuarto(char[] pontosSujos) {
            for (int i = 0; i < estadoQuarto.length; i++) {
                for (int j = 0; j < estadoQuarto[i].length; j++) {
                    if (this.contemPontoSujos(pontosSujos, ambiente[i][j])) {
                        estadoQuarto[i][j] = 'S';
                    } else {
                        estadoQuarto[i][j] = 'L';
                    }
                }
            }
        }

        private boolean contemPontoSujos(char[] pontosSujos, char ponto) {
            for (char sujeira : pontosSujos) {
                if (sujeira == ponto) {
                    return true;
                }
            }
            return false;
        }

        private boolean todasAsCelulasEstaoLimpa() {
            for (char[] linha : estadoQuarto) {
                for (char celula : linha) {
                    if (celula == 'S') {
                        return false;
                    }
                }
            }
            System.out.println("O quarto está limpo!");
            return true;
        }

        public void moverAgente(char direcao) {
            int novoPosX = posicaoAgente.x;
            int novoPosY = posicaoAgente.y;

            switch (direcao) {
                case 'N':
                    if (novoPosY > 0) {
                        novoPosY--;
                    }
                    break;
                case 'S':
                    if (novoPosY < 3) {
                        novoPosY++;
                    }
                    break;
                case 'L':
                    if (novoPosX < 3) {
                        novoPosX++;
                    }
                    break;
                case 'O':
                    if (novoPosX > 0) {
                        novoPosX--;
                    }
                    break;
            }

            posicaoAgente = new Coordenadas(novoPosX, novoPosY);
        }

        private boolean temSujeira() {
            int x = posicaoAgente.x;
            int y = posicaoAgente.y;
            return x >= 0 && x < estadoQuarto[0].length && y >= 0 && y < estadoQuarto.length
                    && estadoQuarto[y][x] == 'S';
        }

        private void limparSujeira() {
            int x = posicaoAgente.x;
            int y = posicaoAgente.y;
            estadoQuarto[y][x] = 'L';
        }
    }

    public static void main(String[] args) {
        char[][] ambiente = new char[][] {
                { 'A', 'B', 'C', 'D' },
                { 'E', 'F', 'G', 'H' },
                { 'I', 'J', 'K', 'L' },
                { 'M', 'N', 'O', 'P' }
        };

        char[] pontosSujos = { 'C', 'F', 'H', 'I', 'K', 'M', 'O' };

        Quarto quarto = new Quarto(ambiente, pontosSujos);

        while (quarto.energia > 0) {
            char posicaoAtual = quarto.ambiente[quarto.posicaoAgente.y][quarto.posicaoAgente.x];
            System.out.println("Posição do agente: " + posicaoAtual);

            char acao = determinarAcao(quarto);

            switch (acao) {
                case 'M':
                    char direcao = determinarDirecao(quarto.posicaoAgente, quarto.estadoQuarto);
                    quarto.moverAgente(direcao);
                    quarto.energia--;
                    break;
                case 'L':
                    System.out.println("Sujeira encontrada e limpa em " + posicaoAtual);
                    quarto.limparSujeira();
                    quarto.bolsa++;
                    quarto.energia--;
                default:
                    break;
            }
        }
    }

    private static char determinarAcao(Quarto quarto) {
        if (quarto.bolsa == 10) {
            return 'V';
        }
        if (quarto.temSujeira()) {
            return 'L';
        }
        return 'M';
    }

    private static char determinarDirecao(Coordenadas posicaoAgente, char[][] estadoQuarto) {
        Random random = new Random();
        char[] direcoesPossiveis = { 'N', 'S', 'L', 'O' };
        List<Character> direcoesDisponiveis = new ArrayList<>();

        for (char direcao : direcoesPossiveis) {
            if (direcaoLevaASujeira(direcao, posicaoAgente, estadoQuarto)) {
                direcoesDisponiveis.add(direcao);
            }
        }

        if (direcoesDisponiveis.isEmpty()) {
            return direcoesPossiveis[random.nextInt(4)];
        }

        return direcoesDisponiveis.get(random.nextInt(direcoesDisponiveis.size()));
    }

    private static boolean direcaoLevaASujeira(char direcao, Coordenadas posicaoAgente, char[][] estadoQuarto) {
        int novoPosX = posicaoAgente.x;
        int novoPosY = posicaoAgente.y;

        switch (direcao) {
            case 'N':
                novoPosY--;
                break;
            case 'S':
                novoPosY++;
                break;
            case 'L':
                novoPosX++;
                break;
            case 'O':
                novoPosX--;
                break;
        }

        return novoPosX >= 0 && novoPosX < estadoQuarto[0].length && novoPosY >= 0 && novoPosY < estadoQuarto.length
                && estadoQuarto[novoPosY][novoPosX] == 'S';
    }

    private static String identificarRotaDeVolta(Coordenadas posicaoAgente) {
        StringBuilder rota = new StringBuilder();
        while (posicaoAgente.x > 0) {
            rota.append('O');
            posicaoAgente.x--;
        }
        while (posicaoAgente.y > 0) {
            rota.append('N');
            posicaoAgente.y--;
        }
        return rota.toString();
    }

    private static void testarObjetivo(Quarto quarto, char posicaoAgente) {
        if (quarto.todasAsCelulasEstaoLimpa() && posicaoAgente == 'A') {
            System.out.println("O objetivo foi alcançado!");
        } else {
            System.out.println("O objetivo não foi alcançado!");
        }
    }
}
