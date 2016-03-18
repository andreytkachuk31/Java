package model.matrixdistance;

import org.json.JSONException;
import org.json.JSONObject;
import utils.JsonReader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Date: 11.05.13
 *
 * @author andrey.tkachuk
 */
public class MatrixDistance {

    private static final String BASE_URL = "http://maps.googleapis.com/maps/api/directions/json";// путь к Directions API по HTTP
    private static final String SENSOR = "sensor";
    private static final String LANGUAGE = "language";
    private static final String MODE = "mode";
    private static final String ORIGIN = "origin";
    private static final String DESTINATION = "destination";

    public static int[][] buildMatrixDistance(List<Point> points) throws IOException, JSONException, InterruptedException {
        long start = System.currentTimeMillis();
        int length = points.size();
        int[][] matrix = new int[length][length];

        final Map<String, String> params = new LinkedHashMap<String, String>();
        params.put(SENSOR, "false");// указывает, исходит ли запрос на геокодирование от устройства с датчиком
        params.put(LANGUAGE, "ru");// язык данных
        params.put(MODE, "driving");// едем на машине, может быть driving, walking, bicycling

        for (int i = 0; i < length; i++) {
            Point originPoint = points.get(i);
            for (int j = 0; j < length; j++) {
                Point destinationPoint = points.get(j);
                params.put(ORIGIN, originPoint.toString());// адрес или координаты отправных пунктов
                params.put(DESTINATION, destinationPoint.toString());// адрес или координаты пунктов назначения
                final String url = BASE_URL + '?' + encodeParams(params);// генерируем путь с параметрами
                final JSONObject response = JsonReader.read(url);// делаем запрос к вебсервису и получаем от него ответ
                System.out.println(response.get("status"));
                if ("OK".equals(response.get("status"))) {
                    JSONObject location = response.getJSONArray("routes").getJSONObject(0);
                    location = location.getJSONArray("legs").getJSONObject(0);
                    matrix[i][j++] = location.getJSONObject("distance").getInt("value");
                } else {
                    j--;
                    Thread.sleep(1000);
                }
            }
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("Time build matrix distance: " + time + " ms");
        printMatrix(matrix);
        return matrix;
    }

    // получаем значение вида key1=value1&key2=value2...
    private static String encodeParams(final Map<String, String> params) {
        StringBuilder paramsUrl = new StringBuilder("");
        for (Map.Entry<String, String> param : params.entrySet()) {
            final StringBuilder buffer = new StringBuilder();
            try {
                buffer.append(param.getKey());// получаем значение вида key=value
                buffer.append('=');
                buffer.append(URLEncoder.encode(param.getValue(), "utf-8"));// кодируем строку в соответствии со стандартом HTML 4.01
            } catch (UnsupportedEncodingException e) {
                System.err.println("Error charset");
            }
            if (paramsUrl.length() > 0) {
                paramsUrl.append("&");
            }
            paramsUrl.append(buffer);
        }
        return paramsUrl.toString();
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
