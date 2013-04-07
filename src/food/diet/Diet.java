package food.diet;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Diet {
	public static void main(String[] args) throws Exception {

		Job job1 = new Job();
		job1.setJarByClass(food.diet.Diet.class);

		FileInputFormat.addInputPath(job1, new Path("input/food/ingredient.tsv"));
		FileOutputFormat.setOutputPath(job1, new Path("output"));

		job1.setMapperClass(food.diet.DietMapper.class);
		job1.setReducerClass(food.diet.DietReducer.class);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);

		System.exit(job1.waitForCompletion(true) ? 0 : 1);
	}
}
