package com.manhpd.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.manhpd.dto.Content;
import com.manhpd.dto.DataResponse;
import com.manhpd.dto.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DataUtils {

    private String path = "./chart.json";

    public static String getChartData(String path) throws FileNotFoundException {
        BufferedReader bf = new BufferedReader(new FileReader(path));
        Stream<String> stream = bf.lines();
        StringBuilder builder = new StringBuilder();
        stream.forEach(item -> builder.append(item));

        return builder.toString();
    }

    public static String createData() throws IOException {
        List<String> lstCharts = Arrays.asList("Hello, world!", "This is test project");

        DataResponse data = new DataResponse();
        Response response = new Response("text", new Content("This is content"));
        data.addResponse(response, 0);

        String chartsJson = GsonUtils.convertListStringToJson(lstCharts);
        ArrayNode array = JacksonUtils.toJsonArray(chartsJson);
        Response chartsResponse = new Response("chart", array);
        data.addResponse(chartsResponse, 1);

//        return new Gson().toJson(data);
        return JacksonUtils.toJsonString(data);
    }

}
