package food;


import static food.FoodConstants.TAB_DELIM;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Parser {

	private LinkedHashMap<String, String> recordMap;

	private List<String> fieldList;

	public Parser(String header) {
		recordMap = new LinkedHashMap<String, String>();
		fieldList = new ArrayList<String>();
		if (header != null) {
			StringTokenizer tokenizer = new StringTokenizer(header, TAB_DELIM);
			while (tokenizer.hasMoreTokens()) {
				String field = tokenizer.nextToken();
				fieldList.add(field);
				recordMap.put(field, "");
			}
		}

	}

	public void parse(String line) {
		String[] tokens = line.split(TAB_DELIM, -1);

		for (int i = 0; i < fieldList.size(); i++) {
			recordMap.put(fieldList.get(i), tokens[i]);
		}
	}
	
	public String get(String fieldName) {
		return recordMap.get(fieldName);
	}

	public static void main(String[] args) {
		Parser parser = new Parser(
				"name	id	beer_style	first_brewed	alcohol_content	original_gravity	final_gravity	ibu_scale	country	brewery_brand	color_srm	from_region	containers");
		for (String key : parser.fieldList) {
			System.out.println(key);
		}

		System.out.println();
		parser.parse("33 Export	/m/0bgcfph										Cameroon	");
		for (String key : parser.recordMap.keySet()) {
			System.out.println(key + "=" + parser.recordMap.get(key));
		}
		
		
	}
}
