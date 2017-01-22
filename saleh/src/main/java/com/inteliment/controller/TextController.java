package com.inteliment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inteliment.exception.NotInRangeException;
import com.inteliment.service.api.TextService;




@RestController
@CrossOrigin
@RequestMapping(path = "/counter-api")
public class TextController {

    @Autowired
    TextService textServiceImp;

    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = "application/json")
        public Map<String, List<HashMap<String, Integer>>> search( @RequestBody Map<String, Object> body)
        {
         List<String> searchTexts = (ArrayList<String>) body.get("searchText");
         Map<String, List<HashMap<String, Integer>>> response = textServiceImp.getSearchCount(searchTexts);
         return response;
        }

     @RequestMapping(path = "/top/{topNo}", method = RequestMethod.GET)
        public String getTop(@PathVariable int topNo)  throws NotInRangeException
        {
             /* This is a very basic validation. for beans we can use JSR-303/JSR-349 Bean Validation API */
             if (topNo != 5 && topNo != 10 && topNo != 20 && topNo != 30 )
             {
                 /*Enable the line below to return BAD REQUEST (400) Http response status and an error message
                  * when using a browser to send the request*/
                 //throw new NotInRangeException(topNo);

                 return  "Invalid TopNo: "+ topNo + ", Please select a number form this range (5, 10, 20, 30)!";
             }
             String response = textServiceImp.getTopCount(topNo);
             return response;
        }
}
