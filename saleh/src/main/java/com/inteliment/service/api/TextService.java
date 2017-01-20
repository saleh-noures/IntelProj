package com.inteliment.service.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TextService {

	 Map<String, List<HashMap<String, Integer>>> getSearchCount(List<String> searchTexts);
	 String getTopCount(int topNo);
	
}
