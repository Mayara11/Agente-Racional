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

        Quarto(char[][] ambiente, char[] pontosSujos) {
            this.ambiente = ambiente;
            this.estadoQuarto = new char[ambiente.length][ambiente[0].length];
            this.inicializarEstadoQuarto(pontosSujos);
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
