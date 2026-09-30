package Google;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class FileBase64 {

	public static String Base64(File arquivo) throws IOException {
		byte[] bytes = Files.readAllBytes(arquivo.toPath());

        return Base64.getEncoder().encodeToString(bytes);
	}
}
