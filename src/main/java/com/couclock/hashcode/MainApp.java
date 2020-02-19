package com.couclock.hashcode;

import java.util.Arrays;
import java.util.List;

import com.couclock.hashcode.utils.DataWriter;
import com.couclock.hashcode.utils.FileParser;

public class MainApp {

	public static void main(String[] args) {

		// List<String> filenames = Arrays.asList("a-example.in.txt", "b-small.in.txt",
		// "c-medium.in.txt");
		List<String> filenames = Arrays.asList("d-quite-big.in.txt");
		// List<String> filenames = Arrays.asList("c-medium.in.txt");

		filenames.forEach(oneFileName -> {

			try {

				List<List<Integer>> fileContent = FileParser.parseInt(oneFileName);

				long start = System.currentTimeMillis();
				List<List<Integer>> response = Solver.runInt(fileContent);
				System.out.println("Duration : " + (System.currentTimeMillis() - start));

				DataWriter.writeInt(response, oneFileName);

			} catch (Exception e) {
				System.err.println("Error handking : " + oneFileName);
				e.printStackTrace();
			}

		});

	}

}
