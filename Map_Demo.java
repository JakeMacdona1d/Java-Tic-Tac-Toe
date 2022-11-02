// Java code to illustrate the get() method

import java.util.*;

public class Map_Demo {
	public static void main(String[] args)
	{
		// Creating an empty Map
		Map<String, Integer> map = new HashMap<String, Integer>();

		Map<Character, ArrayList<int[]>> hm = new HashMap<Character, ArrayList<int[]>>();

		int n = 5;

		ArrayList<int[]> arrli = new ArrayList<int[]>(n);

		for (int i = 1; i <= n; i++) {
			int temp[] = new int[2];
			temp[0] = i;
			temp[1] = i;

            arrli.add(temp);
		}
		
		for (int i = 0; i < arrli.size(); i++)
            System.out.print(Integer.toString(arrli.get(i)[0]) + " ");





		// Mapping int values to string keys
		map.put("Geeks", 10);
		map.put("4", 15);
		map.put("Geeks", 20);
		map.put("Welcomes", 25);
		map.put("You", 30);

		// Displaying the Map
		System.out.println("Initial Mappings are: " + map);

		// Getting the value of "Geeks"
		System.out.println("The Value is: " + map.get("Geeks"));

		// Getting the value of "You"
		System.out.println("The Value is: " + map.get("You"));
	}
}
