package be.klak.junit.jasmine;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;

import be.klak.rhino.RhinoContext;

class JasmineJSSuiteConverter {

	private final RhinoContext context;

	public JasmineJSSuiteConverter(RhinoContext context) {
		this.context = context;
	}

	public JasmineDescriptions convertToJunitDescriptions(Class<?> testClass, NativeArray baseSuites) {
		Description rootDescription = Description.createSuiteDescription(testClass);
		List<JasmineSpec> specs = convertSuiteArrayToDescriptions(baseSuites, rootDescription, new ArrayList<String>());
		return new JasmineDescriptions(rootDescription, specs);
	}

	private List<JasmineSpec> convertSuiteArrayToDescriptions(NativeArray suiteArray, Description rootDescription,
			List<String> processed) {
		List<JasmineSpec> specs = new ArrayList<JasmineSpec>();
		for (Object idObj : suiteArray.getIds()) {
		
			NativeObject suite = (NativeObject) suiteArray.get((Integer) idObj, suiteArray);
			String description = (String) suite.get("description", suite);
			if (!processed.contains(description)) {
				Description suiteDescription = addSuiteToDescription(rootDescription, processed, description);
				specs.addAll(convertToJunitDescription(suite, suiteDescription));
				specs.addAll(convertSuiteArrayToDescriptions(getSubSuites(suite), suiteDescription, processed));
			}
		}

		return specs;
	}

	private NativeArray getSubSuites(NativeObject suite) {
		// Really Hacky code till I figure this all out
		NativeArray specsArray = (NativeArray) suite.get("children");
		ArrayList<NativeObject> subSuites = new ArrayList<NativeObject>();
		for (Object idObj2 : specsArray.getIds()) {
			NativeObject spec = (NativeObject) specsArray.get((Integer) idObj2, specsArray);
			if (spec.get("id", suite).toString().startsWith("suite")) {
				subSuites.add(spec);
			}
		}
		return new NativeArray(subSuites.toArray(new NativeObject[subSuites.size()]));
		
	}
	
	private Description addSuiteToDescription(Description description, List<String> processed, String suiteName) {
		processed.add(suiteName);
		Description suiteDescription = Description.createSuiteDescription(suiteName, (Annotation[]) null);
		description.addChild(suiteDescription);
		return suiteDescription;
	}

	private List<JasmineSpec> convertToJunitDescription(NativeObject suite, Description description) {
		List<JasmineSpec> specsMap = new ArrayList<JasmineSpec>();
		NativeArray specsArray = (NativeArray) suite.get("children");
		for (Object idObj : specsArray.getIds()) {
			NativeObject spec = (NativeObject) specsArray.get((Integer) idObj, specsArray);
			String id = (String) spec.get("id", suite).toString();
			if (id.startsWith("spec")) {
				JasmineSpec jasmineSpec = new JasmineSpec(spec);
				specsMap.add(jasmineSpec);
				description.addChild(jasmineSpec.getDescription());
			}
		}

		return specsMap;
	}

}
