library(jsonlite)
library(ggplot2)
# url with some information about project in Andalussia
url <- 'https://api.stackexchange.com/2.2/questions?pagesize=100&order=desc&sort=activity&tagged=playframework&site=stackoverflow'

# read url and convert to data.frame
document <- fromJSON(txt=url)
res <- document$items$creation_date
i<-2

while(document$has_more) {
  url2 <- paste(url, '&page=', i, sep="")
  document <- fromJSON(txt=url2)
  res<-c(res,document$items$creation_date)
  i<-i+1
}

url <- 'https://api.stackexchange.com/2.2/questions?pagesize=100&key=b6gI4yC5CtL7mJMPfK8LOw((&order=desc&sort=activity&tagged=spring-mvc&site=stackoverflow'

# read url and convert to data.frame
document <- fromJSON(txt=url)
spring <- document$items$creation_date
i<-2

while(document$has_more) {
  url2 <- paste(url, '&page=', i, sep="")
  document <- fromJSON(txt=url2)
  spring<-c(spring,document$items$creation_date)
  i<-i+1
}
CairoPNG("frequenogramStackoverflow.png", width = 750, height=500)
a<-hist(as.POSIXct(spring, origin="1970-01-01"), breaks="months", plot=TRUE, col=rgb(1,0,0,0.5), freq=T, main="Questions on Stackoverflow.com", xlab="date, column per month", cex.axis=1.5)
hist(as.POSIXct(res, origin="1970-01-01"), breaks="months", plot=TRUE, col=rgb(0,0,1,0.5), add=T, axes=F, freq=T)
legend("topleft", legend = c("Spring MVC", "Play Framework"),col=c(rgb(1,0,0,0.5),rgb(0,0,1,0.5)), pch=c(15,15))
dev.off()
