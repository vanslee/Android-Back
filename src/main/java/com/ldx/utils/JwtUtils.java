package com.ldx.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

public class JwtUtils {
    /**
     * 密钥  防止JWT被篡改的关键，密钥是什么不重要，任意字符串即可，但是绝不可以泄露
     */
    private final static String KEY = "dle1sac";

    /**
     * JWT的有效时间。这个看需求而定
     */
    private final static Duration expiration = Duration.ofHours(2);

    /**
     * 生成jwt字符串
     *
     * @param username 根据用户名来生成
     * @return
     */
    public static String generator(String username) {
        // 使用定义的过期时间创建Date对象
        Date expDate = new Date(System.currentTimeMillis() + expiration.toMillis());
        return Jwts.builder()
                // 用户名
                .setSubject(username)
                // 过期时间
                .setExpiration(expDate)
                //创建时间
                .setIssuedAt(new Date())
                // 指定加密算法类型和密钥
                // 这里不知道为什么明明支持字符串却还是要转成bytes
                .signWith(SignatureAlgorithm.HS512, KEY.getBytes())
                // 生成JWT
                .compact();
    }

    // 对于JWT来说，所有存入JWT的信息内容都称为Claims， 所以我们在解析JWT的时候
    // 会得到Claims对象，该对象中保存的就是我们生成JWT时存入的信息，例如用户名，过期时间 ...

    /**
     * 根据token字符，解析出Claims对象
     *
     * @param token 传入的JWT字符
     * @return 解析过后的JWT结构对象(Claims)
     */
    public static Claims parse(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    // 解析时需要密钥
                    .setSigningKey(KEY.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException ex) {
            System.out.println("解析失败");
        }
        return claims;
    }


    public static void main(String[] args) {
        String ldx = JwtUtils.generator("ldx");
        Claims parse = JwtUtils.parse(ldx);
        System.out.println("JwtUtils.parse(ldx) = " + JwtUtils.parse(ldx).getSubject());

//        String sub = (String) jwt.setKey("ldx".getBytes()).getPayload("sub");
//        System.out.println("sub = " + sub);
//        System.out.println(generator("zhangsan"));
//        JWT jwt = JWTUtil.parseToken("eyJhbGciOiJIUzUxMiJ9.eyJzdIsImV4cCI6MTY1NjE0MTY5NSwiaWF0IjoxNjU2MTM0NDk2fQ.6RHkKjsz7YMxjsEzDEzTa3ic9LuXxT7m9wXK2aITFKR7ezoI1nE8p8N1nXiWvOPBKGkpU5EL57kX3QFNLZ3ioQ");
//        System.out.println(jwt.getSigner());
//        JWT jwt1 = JWTUtil.parseToken("");
//        if(jwt==null) {
//            System.out.println("jwt1为空");
//        }
//        System.out.println("jwt.getHeader() = " + jwt.getHeader());
//        System.out.println("jwt.getPayload() = " + jwt.getPayload().getClaimsJson());
//        System.out.println("jwt.getPayload().getClaimsJson().values() = " + jwt.getPayload().getClaimsJson().values());
//        System.out.println("jwt.getPayload().getClaim(\"sub\") = " + jwt.getPayload().getClaim("sub"));
//        System.out.println("jwt.getPayload(\"sub\") = " + jwt.getPayload("sub"));
//        System.out.println("jwt.setKey(\"ldx\".getBytes()).getPayload() = " + jwt.setKey("ldx".getBytes()).getPayload());
//        System.out.println(parse("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZHgiLCJleHAiOjE2NTYwODMwMTUsImlhdCI6MTY1NjA3NTgxNn0.CUagx4xtL2G2uKygWe4VUqxpMDV3-4WEtbjIIupajLB2PZGEFGNly8cRrNWU7hqc-Me8FFMX55DOQguyAskXqQ"));
    }

}
