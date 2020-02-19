package com.couclock.hashcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver2 {

	private static int startSlices = 1000000000;
	private static Map<Integer, Integer> idx2maxScore = new HashMap<Integer, Integer>();
	private static Map<String, List<Integer>> remaining2Best = new HashMap<>();

	private static List<Integer> pizzaSizes = new ArrayList<>();

	private static List<Integer> findBestCombi(int remaingSlices, int maxPizzaListIdx) {

		String hashKey = remaingSlices + "#" + maxPizzaListIdx;

		if (!remaining2Best.containsKey(hashKey)) {
			int bestScore = 0;
			List<Integer> bestCombi = new ArrayList<>();

			if (getMaxScore(maxPizzaListIdx) <= remaingSlices) {
				bestCombi = pizzaSizes.subList(0, maxPizzaListIdx + 1);
				System.out.println("Found total list matching: " + hashKey);

			} else {

				for (int i = maxPizzaListIdx; i >= 0; i--) {
					int selectedPizzaSize = pizzaSizes.get(i);

					// System.out.println("Handling idx : " + i);

					if (remaingSlices == startSlices) {
						System.out.println("Handling idx : " + i);
					}

					// Si la pizza pivot courante a plus de part que le max restant => on sort
					if (selectedPizzaSize > remaingSlices) {
						continue;
					}
					// Si la somme des parts restantes est inférieure au meilleur score => on sort
					if (getMaxScore(i) < bestScore) {
						break;
					}
					List<Integer> bestSubCombi = new ArrayList<>();
					if (i > 0) {
						bestSubCombi = findBestCombi(remaingSlices - selectedPizzaSize, i - 1);
					}
					int curScore = getScore(bestSubCombi);
					if (curScore + selectedPizzaSize > bestScore) {
						bestCombi = new ArrayList<>(bestSubCombi);
						bestCombi.add(i);
						bestScore = curScore + selectedPizzaSize;
						// System.out.println("Best score better : " + bestScore + "/" + remaingSlices);

					}

				}
			}
			remaining2Best.put(hashKey, bestCombi);
		} else {
			;// System.out.println("Found old best : " + hashKey);
		}

		return remaining2Best.get(hashKey);

	}

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

		List<Integer> selectedPizzaIdx = findBestCombi(maxSlices, pizzaSizes.size() - 1);

		List<List<Integer>> response = Arrays.asList(Arrays.asList(selectedPizzaIdx.size()), selectedPizzaIdx);

		return response;

	}

	public static List<List<String>> runString(List<List<String>> data) {

		List<List<String>> response = Arrays.asList(Arrays.asList("3"), Arrays.asList("0", "2", "3"));

		return response;

	}

}
