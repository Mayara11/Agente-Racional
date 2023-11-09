public class AgenteRacional {
    
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

        private static boolean contemPontoSujos(char[] pontosSujos, char ponto) {
            for (char sujeira : pontosSujos) {
                if (sujeira == ponto) {
                    return true;
                }
            }
            return false;
        }
    }
}