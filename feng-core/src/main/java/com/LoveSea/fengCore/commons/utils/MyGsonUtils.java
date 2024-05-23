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


    private static Gson gson = null;
    private static Gson gson2 = null;

    static {
        if (gson == null) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
        if (gson2 == null) {
            gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
        }
    }


    private MyGsonUtils() {
    }


    /**
     * gsonString: 将object对象转成json字符串 <br/>
     *
     * @param object 对象
     * @return
     * @author zhiyong.li
     */
    public static String gsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }


    /**
     * MethodName：gsonString2
     * Description：将对象转换成json字符串，包括特殊字符
     * @param
     * @return
     * @author xi.jie
     * Date: Create in 2020/5/26 14:29
     */
    public static String gsonString2(Object object) {
        String gsonString = null;
        if (gson2 != null) {
            gsonString = gson2.toJson(object);
        }
        return gsonString;
    }

    /**
     * gsonToBean: 将gsonString转成泛型bean <br/>
     *
     * @param gsonString json字符串
     * @param cls        对象
     * @return
     * @author zhiyong.li
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    public static <T> T jsonObjectToBean(JsonObject jsonObject, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(jsonObject, cls);
        }
        return t;
    }

    /**
     * jsonToList: 把json字符串转成list 解决泛型问题 <br/>
     *
     * @param json
     * @param cls
     * @return
     * @author zhiyong.li
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * MethodName：jsonArrayToList
     * Description：把jsonArray转成List
     * @param
     * @return
     * @author zhiyong.li
     * Date: Create in 2018/4/21 17:02
     */
    public static <T> List<T> jsonArrayToList(JsonArray array, Class<T> cls) {
        List<T> list = new ArrayList<T>();
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
