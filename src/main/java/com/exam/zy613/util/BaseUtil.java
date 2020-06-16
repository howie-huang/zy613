package com.exam.zy613.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一些数据类型转换
 * @author 31515
 */
public class BaseUtil {
    /**
     * 判空
     * @param str
     * @return Boolean
     */
    public static boolean checkIsNotNull(Object str){
        if(str!=null&&str!=""&&!str.equals("")){
            return true;
        }
        return false;
    }

    /**
     * 转换字符串
     * @param obj
     * @return string
     */
    public static String transToString(Object obj){
        if(obj!=null){
            return obj.toString();
        }
        return "";
    }

    /**
     * 转换成Integer类型
     * @param obj
     * @return integer
     */
    public static Integer transToInt(Object obj){
        if(obj!=null&&obj!=""){
            return Integer.parseInt(obj.toString());
        }
        return null;
    }

    /**
     * 密码加密
     * @param password
     * @param salt
     * @return string
     */
    public static String tranWord(String password, String salt){
        return new Md5Hash(password,salt,2).toString();
    }

    /**
     * 判断是否操作成功
     * @param i
     * @return resultData
     */
    public static ResultData toData(int i){
        if (i>0){
            return new ResultData(true,"操作成功",null);
        }
        return new ResultData(false,"操作失败",null);
    }
    public static void checkBoolean(boolean bl){
        if (bl=true){
            System.out.println("操作成功");
        }else {
            System.out.println("操作失败");
        }
    }

    /**
     * double转换成bigDecimal类型
     * @param db
     * @return BigDecimal
     */
    public static BigDecimal doubleToBigDecimal(double db){
        BigDecimal bigDecimal = new BigDecimal(db);
        return bigDecimal;
    }
   public static Date stringToDate(String str){
        if (str!=null&&str!=""&&!str.equals("")){
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(str);
                return date;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
   }
}
