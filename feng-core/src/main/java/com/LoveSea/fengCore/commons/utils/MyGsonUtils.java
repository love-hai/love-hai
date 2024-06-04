package com.LoveSea.fengCore.commons.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * ClassName：MyGsonUtils
 * PackageName：com.lalang.core.utils
 * Description：Gson解析JSON
 *
 * @author zhiyong.li
 * Date：Create in 2018/4/18 9:44
 */
public class MyGsonUtils {


    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    // 不进行转义
    private static final Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();


    /**
     * MethodName: gsonString <br>
     * Description: 将object对象转成json字符串<br>
     * @param object {@link Object}  :
     * @return {@link String}
     * @author xiahaifeng
     */
    public static String gsonString(Object object) {
        return gson.toJson(object);
    }

    public static String gsonString2(Object object) {
        return gson2.toJson(object);
    }

    /**
     * MethodName: gsonToBean <br>
     * Description: 将gsonString转成泛型bean<br>
     * @param gsonString {@link String}    :
     * @param cls        {@link Class<T>}  :
     * @return {@link T}
     * @author xiahaifeng
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        return gson.fromJson(gsonString, cls);
    }

    public static <T> T jsonObjectToBean(JsonObject jsonObject, Class<T> cls) {
        return gson.fromJson(jsonObject, cls);
    }

    /**
     * MethodName: jsonToList <br>
     * Description: 把json字符串转成list<br>
     * @param json {@link String}    :
     * @param cls  {@link Class<T>}  :
     * @return {@link List<T>}
     * @author xiahaifeng
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        return jsonArrayToList(array,cls);
    }

    /**
     * MethodName: jsonArrayToList <br>
     * Description: 把jsonArray转成List
     * @param array {@link JsonArray}  :
     * @param cls   {@link Class<T>}         :
     * @return {@link List<T>}
     * @author xiahaifeng
     */
    public static <T> List<T> jsonArrayToList(JsonArray array, Class<T> cls) {
        List<T> list = new ArrayList<>();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }


    /**
     * gsonToListMaps: 把json字符串转成list中有map <br/>
     *
     * @param gsonString
     * @return
     * @author zhiyong.li
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return list;
    }


    /**
     * sonToMaps: 把json字符串转成map <br/>
     *
     * @param gsonString
     * @return
     * @author zhiyong.li
     */
    public static <T> Map<String, T> sonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }


    /**
     * 文本数据gzip压缩
     */
    public static String gzipCompress(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
            gzipOutputStream.flush();
            gzipOutputStream.finish();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    /**
     * 文本数据gzip解压
     */
    public static String gzipDecompress(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(text));
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)){
            byte[] buffer = new byte[256];
            int len;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        return byteArrayOutputStream.toString();
    }

}