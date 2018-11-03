package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileInputReader {
	List<String> lines;
	Path path;

	public FileInputReader(Path path) throws IOException {
		this.path = path;
		this.lines = Files.readAllLines(this.path, StandardCharsets.UTF_8);
	}
	public List<String> getInputsFromFile() throws IOException {
		return this.lines;
	}
	public static FileInputReader build (){
		try {
			return new FileInputReader(Paths.get(".","inputfile.txt"));
		} catch (IOException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		return null;
	}
}