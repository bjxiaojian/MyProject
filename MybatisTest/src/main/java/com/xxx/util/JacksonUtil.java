package com.xxx.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;




public class JacksonUtil {


    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        SimpleModule simpleModule = new SimpleModule("SimpleJodaModule", new Version(1, 0, 0, null, null, null));
        objectMapper.registerModule(simpleModule);
        //这个特性，决定了解析器是否将自动关闭那些不属于parser自己的输入源。
        // 如果禁止，则调用应用不得不分别去关闭那些被用来创建parser的基础输入流InputStream和reader；
        //默认是true
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        //是否允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        //设置为true时，属性名称可以不带双引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //是否允许单引号来包住属性名称和字符串值
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        //是否允许JSON整数以多个0开始
        objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        //null的属性不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //按字母顺序排序属性,默认false
        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true);
        //是否以类名作为根元素，可以通过@JsonRootName来自定义根元素名称,默认false
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,true);
        //是否缩放排列输出,默认false
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT,false);
        //序列化Date日期时以timestamps输出，默认true
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,true);
        //序列化枚举是否以toString()来输出，默认false，即默认以name()来输出
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        //序列化枚举是否以ordinal()来输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,false);
        //序列化单元素数组时不以数组来输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,true);
        //序列化Map时对key进行排序操作，默认false
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);
        //序列化char[]时以json数组输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS,true);
        //序列化BigDecimal时是输出原始数字还是科学计数，默认false，即以toPlainString()科学计数方式来输出
        objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN,true);

        //当遇到未知属性（没有映射到属性，没有任何setter或者任何可以处理它的handler，是否应该抛出JsonMappingException异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //该特性决定对于json浮点数，是否使用BigDecimal来序列化。如果不允许，则使用Double序列化。 默认为false
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, false);
        //该特性决定对于json整形（非浮点），是否使用BigInteger来序列化。如果不允许，则根据数值大小来确定 是使用Integer或者Long
        objectMapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, false);
        //该特性决定JSON ARRAY是映射为Object[]还是List<Object>。如果开启，都为Object[]，false时，则使用List  默认为false
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, false);
        //是否使用Enum.toString()的值对json string进行反序列化。这个的设置和WRITE_ENUMS_USING_TO_STRING需要一致。
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);



//        objectMapper
//            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY) // auto-detect all member fields
//            .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY) // but only public getters
//            .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.ANY); // and none of "is-setters"
    }

    public static String encode(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param typeReference
     * @return
     */
    public static <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
        }
        return null;
    }
}
