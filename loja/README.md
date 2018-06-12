# Loja Amarilho #

O sistema faz a contabilidade de uma loja, através de arquivos de entrada.

### Como funciona: ###

- A cada 25 segundos ele varre um diretório que pode ser configurável nas properties
- Busca arquivos de determinada extensão que é configurado nas properties
- Executa sumarização em cima desses arquivos, de acordo coma as regras de negócio
- Altera o nome do arquivo lido, para não reprocessar o mesmo arquivo
- Gera aquivo de saida com os resultados, usando mesmo nome do arquivo com uma extensão cofigurável

