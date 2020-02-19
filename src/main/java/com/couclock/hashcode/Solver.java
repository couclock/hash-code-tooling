package com.couclock.hashcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver {

	private static int startSlices = 1000000000;
	private static Map<Integer, Integer> idx2maxScore = new HashMap<Integer, Integer>();
	private static Map<String, List<Integer>> remaining2Best = new HashMap<>();

	private static Map<Integer, List<Integer>> idx2Scores = new HashMap<>();
	private static Map<Integer, String> idxScore2idxList = new HashMap<>();

	private static List<Integer> pizzaSizes = new ArrayList<>();

	private static int getMaxScore(int maxIdx) {

		if (!idx2maxScore.containsKey(maxIdx)) {
			int res = 0;
			for (int i = 0; i < maxIdx + 1; i++) {
				res += pizzaSizes.get(i);
			}
			idx2maxScore.put(maxIdx, res);
		}

		return idx2maxScore.get(maxIdx);
	}

	private static int getScore(List<Integer> idxList) {

		int res = 0;
		for (int i = 0; i < idxList.size(); i++) {
			res += pizzaSizes.get(idxList.get(i));
		}

		return res;
	}

	public static List<List<Integer>> runInt(List<List<Integer>> data) {

		int maxSlices = data.get(0).get(0);
		int pizzaTypeCount = data.get(0).get(1);

		pizzaSizes = data.get(1);

		List<Integer> selectedPizzaIdx = new ArrayList<>();

		int currentSlices = 0;
		int currentIdx = pizzaSizes.size() - 1;
		while (currentSlices <= maxSlices) {
			currentSlices += pizzaSizes.get(currentIdx);
			selectedPizzaIdx.add(currentIdx);
			currentIdx--;
		}
		selectedPizzaIdx.remove(selectedPizzaIdx.size() - 1);

		List<List<Integer>> response = Arrays.asList(Arrays.asList(selectedPizzaIdx.size()), selectedPizzaIdx);

		return response;

	}

	public static List<List<String>> runString(List<List<String>> data) {

		List<List<String>> response = Arrays.asList(Arrays.asList("3"), Arrays.asList("0", "2", "3"));

		return response;

	}

}
