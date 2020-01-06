package com.manhpd;

import com.google.gson.JsonElement;
import com.manhpd.dto.ChartData;
import com.manhpd.dto.ChartResponse;
import com.manhpd.dto.DataPush;
import com.manhpd.utils.DataUtils;
import com.manhpd.utils.GsonUtils;
import com.manhpd.utils.JacksonUtils;
import com.manhpd.utils.JsonSimpleUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;


public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws ParseException, IOException {
//        String data = GsonUtils.createJsonSample();
//        String comments = JsonUtils.getDataBasedOnField(data, "comments");
//
//        System.out.println(comments);

        // get specific field path
//        JSONObject jsonObject = JsonUtils.toJsonObjectWithJsonSimple(data);
//        JSONArray comments = (JSONArray) JsonUtils.getDataBasedPathWithJsonSimple(jsonObject, "posts/comments");
//        System.out.println(comments.toString());

        // convert Json string array to Java array of string
//        String jsonStringArray = "[\"JSON\",\"To\",\"Java\"]";
//        List<String> lst = JsonUtils.toStringArray(jsonStringArray);
//        System.out.println(lst.toString());
//
//        System.out.println(JsonUtils.toJsonArray(lst));

        // convert Json integer array to Java array of integer
//        String jsonIntegerArray = "[101,201,301,401,501]";
//        List<Integer> lst = GsonUtils.toIntegerArray(jsonIntegerArray);
//        System.out.println(lst);

        // Use gson to get spedific path's field
//        JsonElement comments = GsonUtils.getDataBasedOnFields(data, "posts/comments");
//        System.out.println(comments.toString());

        // check json is valid
//        String tmp = "[\"hello\", \"hi\", \"bongzuo\"]";
//        System.out.println("Is valid json: " + JsonSimpleUtils.isJsonValid(tmp));

        // write file
//        JsonSimpleUtils.writeFile(data, "./data.json");

//        System.out.println(GsonUtils.createJsonObjectBasedField(Integer.toString(3044021)));

//        String nameOfClass = "Hello, this is main class.";
//        logger.debug("{}", nameOfClass);
        
//        try {
//            String data = "{\"type\":2,\"title\":\"Thứ tư vui vẻ! Khuyến mại thẻ đã về!\",\"body\":\"Viettel khuyến mại 20% giá trị tất cả thẻ nạp cho thuê bao Di động và Homephone trả trước trong ngày 20/11/2019. Tiền khuyến mại dùng để liên lạc nội mạng, ngoại mạng và có thời hạn sử dụng đến hết ngày 05/12/2019.\",\"message\":\"Viettel khuyến mại 20% giá trị tất cả thẻ nạp cho thuê bao Di động và Homephone trả trước trong ngày 20/11/2019. Tiền khuyến mại dùng để liên lạc nội mạng, ngoại mạng và có thời hạn sử dụng đến hết ngày 05/12/2019.\",\"description\":\"Viettel khuyến mại 20% giá trị tất cả thẻ nạp cho thuê bao Di động và Homephone trả trước trong ngày 20/11/2019. Tiền khuyến mại dùng để liên lạc nội mạng, ngoại mạng và có thời hạn sử dụng đến hết ngày 05/12/2019.\",\"action_button\":\"Nạp thẻ ngay\",\"object_id\":\"\",\"promotion_id\":\"\",\"id\":\"\",\"campaignCode\":\"13365\",\"campaignPush\":\"13298\"}";
//            DataPush dataPush = DataPush.of(data);
//            System.out.println("Result of data push is: " + dataPush.toString());
//        } catch(Exception ex) {
//            System.out.println("Error: " + ex.toString());
//        }

//        System.out.println(DataUtils.createData());

        String data = DataUtils.getChartData("./chart.json");
//        System.out.println(data);

        ChartData chartResponse = GsonUtils.toChartResponseWithGson(data);
        System.out.println(chartResponse);
    }

}
