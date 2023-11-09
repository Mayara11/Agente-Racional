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

        public static void main(String[] args) {
            char[][] ambiente = new char[][] {
                    { 'A', 'B', 'C', 'D' },
                    { 'E', 'F', 'G', 'H' },
                    { 'I', 'J', 'K', 'L' },
                    { 'M', 'N', 'O', 'P' }
            };

            char[] pontosSujos = { 'C', 'F', 'H', 'I', 'K', 'M', 'O' };

            Quarto quarto = new Quarto(ambiente, pontosSujos);
        }

        private static char determinarAcao(Quarto quarto) {
            if (bolsa = 10) {
                return 'V';
            }
            if (temSujeira()) {
                return 'A';
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
    }
}
