# Agente Racional

Agente Racional que limpa um quarto com o mínimo possível de ações, e o objetivo é que todo o ambiente esteja limpo e o agente retorne ao lar (localização de início A).

### Conhecimento Prévio: 
- Todo o ambiente é dividido em quadrados de 4 por 4. 
- O agente (aspirador de pó) tem uma energia inicial de 100 pontos. 
- O agente pode se mover apenas para o Norte, Sul, Leste ou Oeste. Ele não pode se  mover diagonalmente. 
- Cada ação custa 1 ponto de energia. Por exemplo, cada movimento custa 1 ponto de energia, cada aspiração custa 1 ponto de energia. 
- O agente possui uma bolsa que coleta sujeira. A capacidade máxima é de 10. • Após cada aspiração, o agente precisa verificar sua bolsa; se estiver cheia, ele deve  voltar para Casa (localização A), esvaziar a bolsa e começar a aspirar novamente.

## PEAS

| Agente | Medida de Desempenho | Ambiente | Atuadores | Sensores |
|--------|----------------------|----------|-----------|----------|
| Aspirador de pó | Limpar todo o ambiente com o mínimo possível de ações e retornar à localização inicial | Área quadrada de 4 por 4, dividida em 16 localizações (A a P). Algumas localizações estão sujas e outras estão limpas. O agente pode se mover apenas para o Norte, Sul, Leste ou Oeste e não pode se mover diagonalmente | Capacidade de se mover em uma das quatro direções, aspirar a sujeira e voltar para casa | Capacidade de perceber a localização atual, se ela está suja ou limpa, e a capacidade do saco de sujeira |


#git pull original <Agente Racional>

