package food.beer;

import static food.FoodConstants.TAB_DELIM;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import food.Parser;

public class BeerSortMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	private static final LongWritable HEADER = new LongWritable(0);

	private Parser parser;

	public void map(LongWritable key, Text value, Context context) throws IOException,
			InterruptedException {
		String line = value.toString();
		if (HEADER.equals(key)) {
			parser = new Parser(line);
		} else {
			parser.parse(line);
			context.write(new IntWritable(Integer.parseInt(parser.get("beer_count"))), new Text(
					parser.get("from_region") + TAB_DELIM + parser.get("strongest_beer") + TAB_DELIM
							+ parser.get("max_alcohol_content")));
		}
	}

	static class Comparator extends IntWritable.Comparator {

		@Override
		public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
			return super.compare(b2, s2, l2, b1, s1, l1);
		}
		
	}
}