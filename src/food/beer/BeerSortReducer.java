package food.beer;

import static food.FoodConstants.TAB_DELIM;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BeerSortReducer extends Reducer<IntWritable, Text, Text, Text> {

	public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException,
			InterruptedException {
		for (Text value : values) {
			context.write(new Text(key.toString()), value);
		}
	}

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		super.setup(context);
		context.write(new Text("beer_count"), new Text("from_region" + TAB_DELIM + "strongest_beer"
				+ TAB_DELIM + "max_alcohol_content"));
	}
}