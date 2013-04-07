#hadoop_project - My experiments with analyzing public data sets with Hadoop framework in Java
Developed by Poornima Ganesan

#TECHNOLOGIES
Hadoop, Java, Design Patterns, Regex

#ABOUT THE PROJECT
Mapper and Reducers for analyzing public data sets - Beer and IMDB movies

##BEER ANALYSIS
* The beer data set provides information on various beers. 
* The ASCII text files consist of beer names, brewing company name, beer alcohol content, IBU scale, 
gravity, country/region where the beer originated from and other related information pertaining to beers. 
* Each line in the file provides information about a single beer. 
* The various fields in the file were delimited by tab spaces. 
* The Beer map-reduce job analyzes this data to find out which country produces the largest number of beer 
and which the strongest beer from each country found in the data corpus. 
* Download URL: http://download.freebase.com/datadumps/latest/browse/food/beer.tsv

##IMDB ANALYSIS
* The IMDB public data set provides ratings of all movies available in IMDB database as of March 3rd 2013. 
* The ASCII text data was split across 15 files, each file provides information on several movies, documentaries, 
TV series etc available on IMDB. 
* Each line in a file represents a single movie record containing following 
fields - movie title, year, imdb rank, rating distribution, total user votes
* The fields are separated by multiple spaces.
* The IMDB job analyzes this data corpus to find the top 100 movies as ranked by users in IMDB.
* Download URL: ftp://ftp.fu-berlin.de/pub/misc/movies/database/movies.list.gz
