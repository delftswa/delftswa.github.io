library(jsonlite)
library(ggplot2)

data <- read.csv("../R script/jobdata.csv", sep=";", header=T)
data<-data[order(data$openings),]
library(Cairo)
CairoPNG("job.png", width=750, height=400)
barplot(data$openings, names.arg = data$Name, las=1, horiz = T , cex.names=0.65, main="Job openings on indeed.com")
dev.off()

CairoPNG("extraSkill.png", width=750, height=400)
numbers<-c(76, 91, 10, 21)
desc<-c("Scala, and Java (76)", "just Java (91)", "just Scala (10)", "none (21)")
pie(numbers,desc, main="Play Framework positions from indeed.com (198)\nwith required extra skills from Java and Scala")
dev.off()
