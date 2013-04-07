package imdb.top100;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Top100 {
	public static void main(String[] args) throws Exception {

		Job job = new Job();
		job.setJarByClass(imdb.top100.Top100.class);

		FileInputFormat.addInputPath(job, new Path("input/imdb"));
		FileOutputFormat.setOutputPath(job, new Path("output"));

		job.setMapperClass(imdb.top100.Top100Mapper.class);
		job.setReducerClass(imdb.top100.Top100Reducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

