rm(list = ls())

dados <- read.csv2("dados.csv")

boxplot(dados$Caso1, dados$Caso2)
# Menor resultado do Caso 1 - melhor
min(dados$Caso1)

# Max resultado do Caso 1
max(dados$Caso1)

# Desvio padrao
sd(dados$Caso1)

# Media do Caso 1
mean(dados$Caso1)

# Menor resultado do Caso 2 - melhor
min(dados$Caso2)

# Max resultado do Caso 2
max(dados$Caso2)

# Desvio padrao
sd(dados$Caso2)


# Media do Caso 2
mean(dados$Caso2)

# Media1 != Media2
t.test(dados$Caso1, dados$Caso2)
# Media1 < Media2
t.test(dados$Caso1, dados$Caso2, alternative = 'l')
# Media1 > Media2
t.test(dados$Caso1, dados$Caso2, alternative = 'g')

#TSP

# Caso 1 -> pop - 100, ger - 1000
# Caso 2 -> pop - 1000, ger - 100
