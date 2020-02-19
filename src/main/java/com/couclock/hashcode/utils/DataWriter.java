package com.couclock.hashcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DataWriter {

	private static void write(String data, String outputFileName) throws IOException {
		Path path = Paths.get(outputFileName);
		byte[] strToBytes = data.getBytes();

		Files.write(path, strToBytes);
	}

	public static void writeInt(List<List<Integer>> response, String fileName) throws IOException {

		String outputFileName = "src/main/resources/" + fileName.replaceAll("in.txt", "out.txt");

		String data = response.stream() //
				.map(a -> a.stream().map(String::valueOf).collect(Collectors.joining(" "))) //
				.collect(Collectors.joining("\n"));

		write(data, outputFileName);

	}

	public static void writeString(List<List<String>> response, String fileName) throws IOException {

		String outputFileName = "src/main/resources/" + fileName.replaceAll("in.txt", "out.txt");

		String data = response.stream() //
				.map(a -> a.stream().collect(Collectors.joining(" "))) //
				.collect(Collectors.joining("\n"));

		write(data, outputFileName);

	}

}
