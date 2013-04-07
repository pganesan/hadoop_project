package food.diet;

import static food.FoodConstants.COMMA_DELIM;
import static food.FoodConstants.UNKNOWN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import food.Parser;

public class DietMapper extends Mapper<LongWritable, Text, Text, Text> {

	private static final LongWritable HEADER = new LongWritable(0);

	private Parser parser;

	public void map(LongWritable key, Text value, Context context) throws IOException,
			InterruptedException {
		String line = value.toString();
		if (HEADER.equals(key)) {
			parser = new Parser(line);
		} else {
			parser.parse(line);
			String dishes = parser.get("dishes");

			String diets = "".equals(parser.get("compatible_with_dietary_restrictions")) ? UNKNOWN
									: parser.get("compatible_with_dietary_restrictions");
			if (!("".equals(dishes))) {
				List<String> dietList = list(diets);
				for (String diet : dietList) {
					context.write(new Text(diet.toUpperCase()), new Text(dishes));
				}
					
			}
		}
	}
	
	private List<String> list(String fieldValue) {
		StringTokenizer tokenizer = new StringTokenizer(fieldValue, COMMA_DELIM);
		List<String> values = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			values.add(tokenizer.nextToken());
		}
		
		return values;
	}
}