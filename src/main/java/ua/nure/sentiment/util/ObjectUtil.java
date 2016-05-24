package ua.nure.sentiment.util;

import org.apache.spark.ml.Model;

import java.io.*;

public class ObjectUtil {

	public static void saveModel(Model<?> model, String path) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(model);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object loadModel(String path) {
		try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(path))) {
			return oos.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
