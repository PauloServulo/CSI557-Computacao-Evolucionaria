# 
# Análise entre AG Binário e o AG Real
#

# Limpando o ambiente de trabalho
rm(list = ls())

# setwd("~/Dropbox/Dados/Desenvolvimento/Netbeans/CSI557-Computacao-Evolucionaria/Codes/Analyses")

dados <- read.csv2("data-ag-bin-real.txt")
dados$FO <- as.numeric(as.character.numeric_version(dados$FO))

library(plyr)
count(dados, c("Teste"))

min(dados$FO)
max(dados$FO)
sd(dados$FO)
mean(dados$FO)

boxplot(FO~Teste, data=dados)

modelo <- aov(FO~Teste, data=dados)
summary(modelo)

bin1 <- dados$FO[dados$Teste == "BIN1"]
bin2 <- dados$FO[dados$Teste == "BIN2"]
real1 <- dados$FO[dados$Teste == "REAL1"]
real2 <- dados$FO[dados$Teste == "REAL2"]

t.test(bin1, bin2)
t.test(real1, real2)
t.test(bin2, real1)

