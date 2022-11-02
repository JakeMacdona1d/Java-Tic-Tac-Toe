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

		for (int i = 0; n > i; i++) {
			int arr[] = new int[2];
			arr[0] = i; arr[1] = i;
			arrli.add(arr);
		}
		hm.put('X', arrli);

		int thot[] = new int[2];
		thot[0] = 69; thot[1] = 420;

		hm.get('X').add(thot);

		hm.size();

		char x = hm.keySet().toArray()[0].toString().charAt(0);
		System.out.println((hm.keySet().toArray()[0]));

		System.out.println(x);


		for (int j = 0; hm.size() > j; j++) {
			char key = hm.keySet().toArray()[j].toString().charAt(0);
			hm.get(key);
			for (int i = 0; hm.get(key).size() > i; i++) {
				int posx = hm.get(key).get(i)[0];
				int posy = hm.get(key).get(i)[1];

				System.out.println(Integer.toString(posx) + ","
				 + Integer.toString(posy));
			}
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
