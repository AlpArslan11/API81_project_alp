package test_data;

import java.util.HashMap;
import java.util.Map;

public class Jsonplaceholder_testData_K {

    public Map<String,Object> expectedDataMethod(Integer userId, String title, Boolean completed){

        Map<String,Object> expectedDataMap =new HashMap<>();
        expectedDataMap.put("userId",userId);
        expectedDataMap.put("title",title);
        expectedDataMap.put("completed",completed);

        return expectedDataMap;
      //          "id": 2,
      //          "title": "quis ut nam facilis et officia qui",
      //          "completed": false
    }
}
