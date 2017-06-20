package com.rodrifmed.lib.dataintegration;

import java.util.HashMap;
import java.util.Map;

import com.rodrifmed.lib.dataintegration.transformation.TransformationManager;

public class TransformationExecutionTest {
	public TransformationExecutionTest() {
	}

	public static void main(String[] args) {
		TransformationManager manager = new TransformationManager();

		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put("HELLO_WORLD", "Hello World");

		manager.executeTransformation("transformations/hello_world.ktr");
	}
}
