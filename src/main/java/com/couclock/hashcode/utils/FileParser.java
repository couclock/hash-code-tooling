package com.couclock.hashcode.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser {

	public static List<List<Integer>> parseInt(String fileName) throws URISyntaxException, IOException {

		Path path = Paths.get(FileParser.class.getClassLoader().getResource(fileName).toURI());

		List<List<Integer>> result = Files.lines(path) //
				.map(oneLine -> {
					return Arrays.asList(oneLine.split(" ")) //
							.stream() //
							.map(one -> Integer.parseInt(one)) //
							.collect(Collectors.toList());
				}) //
				.collect(Collectors.toList());

		return result;

	}

	public static List<List<String>> parseString(String fileName) throws URISyntaxException, IOException {

		Path path = Paths.get(FileParser.class.getClassLoader().getResource(fileName).toURI());

		List<List<String>> result = Files.lines(path) //
				.map(oneLine -> {
					return Arrays.asList(oneLine.split(" "));
				}) //
				.collect(Collectors.toList());

		return result;

	}

}
