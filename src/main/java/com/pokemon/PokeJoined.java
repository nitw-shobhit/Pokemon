package com.pokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pokemon.exception.PokeJoinedException;

public class PokeJoined {

	static Map<Character, List<String>> inputMap = null; // To group the alphabets by first letter
	static String[] words = null; // TO traverse and analyze each word
	
	public static void main(String[] args) throws PokeJoinedException {
		
		String input = getInput();
		
		formatInput(input);

		List<String> finalList = join();
		
		display(finalList);
	}
	
	/**
	 * Method to get the user input from the console.
	 * @return Input String
	 * @throws PokeJoinedException when user enters undefined input or there is an IO exception
	 */
	private static String getInput() throws PokeJoinedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter space separated names :");
		String input = null;
		try {
			input = br.readLine();
		} catch (IOException e) {
			throw new PokeJoinedException(e.getMessage(), e);
		}
		return input;
	}

	/**
	 * Method to format the user input and populate globally defined data structures.
	 * @param input : Input string
	 * @throws PokeJoinedException 
	 */
	public static void formatInput(String input) throws PokeJoinedException {
		if(input == null || input.trim().length() == 0) {
			throw new PokeJoinedException("Bad input!");
		}
		words = input.split("\\ ");
		inputMap = new HashMap<Character, List<String>>();
		for (String name : words) {
			char c = name.charAt(0);
			List<String> tempList;
			if (inputMap.containsKey(c)) {
				tempList = inputMap.get(c);
			} else {
				tempList = new ArrayList<String>();
			}

			tempList.add(name);
			inputMap.put(c, tempList);
		}
	}

	/**
	 * Method to format the user input and populate globally defined data structures.
	 * @return finalList. Output
	 */
	public static List<String> join() {
		List<String> finalList = new ArrayList<String>();
		for (String word : words) {
			List<String> temp = new ArrayList<String>();
			temp.add(word);
			List<String> temp2 = findPossibleConnections(temp);
			if (finalList.size() < temp2.size()) {
				finalList = temp2;
			}
		}
		
		return finalList;
	}

	//recursive method
	private static List<String> findPossibleConnections(List<String> visited) {
		String temp = visited.get(visited.size() - 1); // Get the last word.
		char lastChar = temp.charAt(temp.length() - 1); // Get the last character of the last word to join further.
		List<String> possibleConnections = null;
		// Check if the character key exists in the map.
		if (inputMap.get(lastChar) != null) {
			possibleConnections = new ArrayList<String>();
			for (String t : inputMap.get(lastChar)) {
				// Don't consider the ones which have been already added.
				if (!visited.contains(t)) {
					possibleConnections.add(t);
				}
			}

		}
		//If no possible links available, return the ones obtained.
		if (possibleConnections == null || possibleConnections.isEmpty())
			return visited;
		else {
			List<String> finalList = new ArrayList<String>();
			//For each possible link.
			for (String word : possibleConnections) {
				List<String> temp2 = new ArrayList<String>(visited);
				temp2.add(word);
				//Recursively find connections of connections...
				List<String> temp3 = findPossibleConnections(temp2);
				if (temp3.size() > finalList.size()) {
					finalList = temp3;
				}
			}
			return finalList;
		}
	}
	
	/**
	 * Method to display the output.
	 * @param finalList
	 */
	private static void display(List<String> finalList) {
		if(finalList.size() == 1) {
			// No valid pair of strings found where last character
			// of one  == first character of other.
			System.out.println("Could not find names to join");
		} else {
			// Print the output
			System.out.println("Output" + finalList);
		}
	}
}
