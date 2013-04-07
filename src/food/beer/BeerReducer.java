package food.beer;

import static food.FoodConstants.NA;
import static food.FoodConstants.TAB_DELIM;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BeerReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException,
			InterruptedException {
		int beerCount = 0;
		float maxAC = Float.NEGATIVE_INFINITY;
		String maxBeer = "";
		for (Text value : values) {
			Scanner scanner = new Scanner(value.toString());
			scanner.useDelimiter(TAB_DELIM);

			beerCount++;
			String beerName = scanner.next();
			float ac = scanner.nextFloat();

			if (maxAC < ac) {
				maxAC = ac;
				maxBeer = beerName;
			}
		}

		context.write(key, new Text(beerCount + TAB_DELIM + maxBeer + TAB_DELIM
				+ (maxAC == Float.MIN_VALUE ? NA : maxAC)));
	}

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		super.setup(context);
		context.write(new Text("from_region"), new Text("beer_count" + TAB_DELIM + "strongest_beer"
				+ TAB_DELIM + "max_alcohol_content"));
	}
}
