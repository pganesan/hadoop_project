package food.diet;

import static food.FoodConstants.COMMA_DELIM;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DietReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,
			InterruptedException {
		StringBuilder builder = new StringBuilder();
		for (Text value : values) {
			if (builder.length() > 0) {
				builder.append(COMMA_DELIM);
			}
			builder.append(value.toString());		
		}

		context.write(key, new Text(builder.toString()));
	}

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		super.setup(context);
		context.write(new Text("diet_type"), new Text("compatible_dishes"));
	}
}
