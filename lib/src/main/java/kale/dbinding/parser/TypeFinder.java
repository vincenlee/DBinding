package kale.dbinding.parser;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

import kale.dbinding.annotation.Visibility;

/**
 * @author Kale
 * @date 2016/1/20
 *
 * 一个属性可能支持多个不同类型的参数，比如：src支持drawable，bitmap，drawableId。
 * 但这里只提供一种最常用，最合适的参数。对src来说就是bitmap。其余的属性也参考同样的思路。
 */
public class TypeFinder {

    /**
     * Example:
     * First String:    text
     * Second String:   CharSequence
     */
    private static Map<String,String> attrTypeMap = new HashMap<>();

    public static String findTypeByAttrName(String name) {
        // find value in custom config
        String type = attrTypeMap.get(name);
        if (type != null) {
            return type;
        }
        
        // find value in default config
        switch (name) {
            case "text":
                type = CharSequence.class.getCanonicalName();
                break;
            case "visibility":
                type = "@" + Visibility.class.getCanonicalName() + " " + int.class.getCanonicalName();
                break;
            case "src":
            case "drawableLeft":
                type = Bitmap.class.getCanonicalName();
                break;
            default:
                //System.err.println(name + " 未支持的类型");
                type = Object.class.getCanonicalName();
        }
        return type;
    }

    public static void setCustomAttrConfig(Map<String,String> config) {
        attrTypeMap = config;
    }
}
