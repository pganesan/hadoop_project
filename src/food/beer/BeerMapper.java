package food.beer;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import food.Parser;

import static food.FoodConstants.TAB_DELIM;
import static food.FoodConstants.UNKNOWN;

public class BeerMapper extends Mapper<LongWritable, Text, Text, Text> {

	private static final LongWritable HEADER = new LongWritable(0);

	private Parser parser;

	public void map(LongWritable key, Text value, Context context) throws IOException,
			InterruptedException {
		String line = value.toString();
		if (HEADER.equals(key)) {
			parser = new Parser(line);
		} else {
			parser.parse(line);
			String region = "".equals(parser.get("from_region")) ? UNKNOWN : parser
					.get("from_region");
			String beerName = "".equals(parser.get("name")) ? UNKNOWN : parser.get("name");
			float alcoholContent = Float.MIN_VALUE;
			try {
				alcoholContent = Float.parseFloat(parser.get("alcohol_content"));
			} catch (NumberFormatException nfe) {
				alcoholContent = Float.MIN_VALUE;
			}
			context.write(new Text(region), new Text(beerName + TAB_DELIM + alcoholContent));
		}
	}
}