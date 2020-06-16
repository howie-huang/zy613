package com.exam.zy613.util;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 密码比较器
 * @author 31515
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token,
                                      AuthenticationInfo info) {
        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
        //注意token.getPassword()拿到的是一个char[]，不能直接用toString()，它底层实现不是我们想的直接字符串，只能强转
        Object tokenCredentials = BaseUtil.tranWord(String.valueOf(usertoken.getPassword()),usertoken.getUsername());
        Object accountCredentials = getCredentials(info);
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
        return equals(tokenCredentials, accountCredentials);
    }
}

/**
 * 密码加密
 */
//class Encrypt {
    /*
     * 散列算法一般用于生成数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的数据，
     * 常见的散列算法如MD5、SHA等。一般进行散列时最好提供一个salt（盐），比如加密密码“admin”，
     * 产生的散列值是“21232f297a57a5a743894a0e4a801fc3”，
     * 可以到一些md5解密网站很容易的通过散列值得到密码“admin”，
     * 即如果直接对密码进行散列相对来说破解更容易，此时我们可以加一些只有系统知道的干扰数据，
     * 如用户名和ID（即盐）；这样散列的对象是“密码+用户名+ID”，这样生成的散列值相对来说更难破解。
     */

    //高强度加密算法,不可逆
//    public static String md5(String password, String salt) {
//        //密码123  用户名admin是盐值  2是打散次数
//        return new Md5Hash(password,salt,2).toString();
//    }

//    public static void main(String[] args) {
//        System.out.println(BaseUtil.tranWord("521314","admin"));
//    }
//}