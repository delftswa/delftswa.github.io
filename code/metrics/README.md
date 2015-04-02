# How to view these metrics

Copy [this json file](https://github.com/delftswa2014/team-playframework/releases/download/metric-modules-output/metricscache.json) 
to the metrics folder of your copy of this repository. Then run:

````bash
npm install http-server -g
http-server # run inside the [metrics] folder
````
Now surf to [http://localhost:8080/chart.html](http://localhost:8080/chart.html) to view the graph.

You should see something like this:
![Module metrics](https://cloud.githubusercontent.com/assets/791189/6657079/6ef44d4e-cb40-11e4-840c-7a76cd495619.png)

# How to run these metrics

````bash
brew install cloc # or apt-get install on Ubuntu
cd team-playframework/metrics #(where this README is too)
# Assuming you have sbt; The spaces around the word modules are intended:
sbt "run /absolute-path-to-play-source/ modules framework/src"
````

You computer will start indexing the LOC count. 
If you place 
[this json file](https://github.com/delftswa2014/team-playframework/releases/download/metric-modules-output/metricscache.bare-git.json) 
in the same directory as where you run the sbt command you will utilize that as a cache, 
which speeds things up tremendously.