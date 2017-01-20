package com.inteliment.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.inteliment.service.api.TextService;

@Service
public class TextServiceImp implements TextService {

    @Autowired
    ResourceLoader resourceLoader;

    public Map<String, List<HashMap<String, Integer>>> getSearchCount(List<String> searchTexts)
    {
        Map<String,Integer> countMap = getCounts();
        List<HashMap<String, Integer>> countsList = new ArrayList<>();
        searchTexts.forEach(searchText ->{
             countsList.add(new HashMap<String, Integer>()
             {{put(searchText,countMap.get(searchText.toUpperCase()) == null ? 0 : countMap.get(searchText.toUpperCase()));}});
        });
        Map<String, List<HashMap<String, Integer>>> response = new HashMap<>();
        response.put("counts", countsList);
        return response;
    }


    public String getTopCount(int topNo)
    {
        String response = "";
        List<Map.Entry<String, Integer>> sortedCountList = getSortedListByMapValue(getCounts());
        int sortedCountListSize = sortedCountList.size();
        for (int i = sortedCountListSize-1; i > sortedCountListSize-topNo-1; i--)
        {
            Map.Entry<String, Integer> entry = sortedCountList.get(i);
            response = response + entry.getKey().toLowerCase()+"|"+entry.getValue()+"\n";
        }
        return response;
    }

    private List<Map.Entry<String, Integer>> getSortedListByMapValue(Map<String, Integer> unsortMap)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
        Collections.sort(list, (Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2) -> (o1.getValue()).compareTo(o2.getValue()));
        return list;
    }
    
    private Map<String,Integer> getCounts()
    {
        Map<String,Integer> countMap = new HashMap<String,Integer>();
        Resource resource  = resourceLoader.getResource("classpath:static/Sample.txt");
        try(Scanner sc = new Scanner(resource.getInputStream()))
        {
            while(sc.hasNext())
            {
                String word = sc.next();
                word = word.replace(",", "").replace(".", "").toUpperCase();
               Integer wordRec =  countMap.get(word);
               if (wordRec == null)
               {
                   wordRec = 1;
                   countMap.put(word, wordRec);
               }
               else
               {
                   countMap.put(word, wordRec+1);
               }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return countMap;
    }

}
