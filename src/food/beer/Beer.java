package food.beer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Beer {

	public static void main(String[] args) throws Exception {
		
		Job job1 = new Job();
		job1.setJarByClass(food.beer.Beer.class);

		FileInputFormat.addInputPath(job1, new Path("input/food/beer.tsv"));
		FileOutputFormat.setOutputPath(job1, new Path("inter"));

		job1.setMapperClass(food.beer.BeerMapper.class);
		job1.setReducerClass(food.beer.BeerReducer.class);
		
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		
		if (job1.waitForCompletion(true)) {
			Job job2 = new Job();
			job2.setJarByClass(food.beer.Beer.class);

			FileInputFormat.addInputPath(job2, new Path("inter"));
			FileOutputFormat.setOutputPath(job2, new Path("output"));

			job2.setMapperClass(food.beer.BeerSortMapper.class);
			job2.setReducerClass(food.beer.BeerSortReducer.class);
			
			job2.setOutputKeyClass(IntWritable.class);
			job2.setOutputValueClass(Text.class);
			
			job2.setSortComparatorClass(BeerSortMapper.Comparator.class);
			
			job2.waitForCompletion(true);
		}
		
		FileSystem.get(new Configuration()).delete(new Path("inter"), true);
		
	}
}
