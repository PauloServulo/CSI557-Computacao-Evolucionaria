# 
# Análise entre AG Binário e o AG Real
#

# Leitura obrigatória: 
# Prof. Felipe Campelo, Ph.D. UFMG
# https://github.com/fcampelo/Design-and-Analysis-of-Experiments

# Limpando o ambiente de trabalho
rm(list = ls())

# Definir a pasta de trabalho
# setwd("")

# Ler o arquivo com os dados
dados <- read.csv2("data-ag-bin-real.txt")
# Transformação da coluna com os números com E - isso depende da base de dados
dados$FO <- as.numeric(as.character.numeric_version(dados$FO))

### Teste preliminares 

# Carregando biblioteca
library(plyr)
# Contanto o número de observações de acordo com o teste
count(dados, c("Teste"))

# Menor valor
min(dados$FO)
# Maior valor
max(dados$FO)
# Desvio padrão
sd(dados$FO)
# Média
mean(dados$FO)

# Plot do valor em função do teste
boxplot(FO~Teste, data=dados)

### Análise estatística - dependendo do caso, pode ser aplicado o Teste T ou a AOV.
# As técnicas são aplicáveis quando as premissas de normalidade são válidas.
# Aqui, as premissão são assumidas.

# Separação dos valores conforme o método
bin1 <- dados$FO[dados$Teste == "BIN1"]
bin2 <- dados$FO[dados$Teste == "BIN2"]
real1 <- dados$FO[dados$Teste == "REAL1"]
real2 <- dados$FO[dados$Teste == "REAL2"]

## Teste T para os métodos separados
# http://www.portalaction.com.br/inferencia/52-teste-para-media-teste-t
# Podem haver implicações estatísticas da maneira que isso é verificado.

# Media1 != Media2
t.test(bin1, bin2)
t.test(real1, real2)
t.test(bin2, real1)

# Media1 < Media2
t.test(bin1, bin2, alternative = 'l')
t.test(real1, real2, alternative = 'l')
t.test(bin2, real1, alternative = 'l')

# Media1 > Media2
t.test(bin1, bin2, alternative = 'g')
t.test(real1, real2, alternative = 'g')
t.test(bin2, real1, alternative = 'g')

## Análise de variância
# http://www.portalaction.com.br/anova
modelo <- aov(FO~Teste, data=dados)
# Exibição do resultado do teste
summary(modelo)

## Comparações múltiplas
# Teste de Tukey (TSD - Tukey Significant Difference)
# http://www.portalaction.com.br/anova/31-teste-de-tukey
require(multcomp)
modelo.Tukey <- glht(modelo, linfct=mcp(Teste="Tukey"))
par(mar = c(2,8,2,2), mfrow = c(1,1))
plot(modelo.Tukey)

