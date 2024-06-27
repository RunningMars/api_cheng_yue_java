package com.cheng.api.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.junit.platform.commons.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public class R<T> {

    @ApiModelProperty("状态")
    private Integer code; //状态编码

    @ApiModelProperty(value="信息",notes="信息")
    private String message; //返回信息

    @ApiModelProperty(value="数据",notes="数据")
    private T result; //数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

//    public Map getMap() {
//        return map;
//    }
//
//    public void setMap(Map map) {
//        this.map = map;
//    }

    // 在统一返回中添加 map 类型的数据,会导致 swagger 不能显示返回参数字段说明
//    @ApiModelProperty(value="动态数据",notes="")
//    private Map map = new HashMap(); //动态数据

    public R() {}

    public R(int i, String s, T data) {
        this.setCode(i);
        this.setMessage(s);
        this.setResult(data);
    }

    public static <T> R<T> success(T obj){
        R<T> r = new R<>();
        r.setCode(0);
        r.setMessage("操作成功.");
        r.setResult(obj);
        return r;
    }

    public R success1(T data){
        return new R(0,"操作成功.",data);
    }

    public static <T> R<T> error(){
        R<T> r = new R<>();
        r.setCode(500);
        r.setMessage("操作失败");
        return r;
    }

    public static <T> R<T> error(String msg){
        R<T> r = new R<>();
        r.setCode(500);
        if (StringUtils.isNotBlank(msg)){
            msg = "操作失败";
        }
        r.setMessage(msg);
        return r;
    }

    public static <T> R<T> error(Integer code,String msg){
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static <T> R<T> abort(String msg){
        R<T> r = new R<>();
        r.setCode(422);
        r.setMessage(msg);
        return r;
    }

    public static <T> R<T> notFound(String msg){
        R<T> r = new R<>();
        r.setCode(404);
        r.setMessage(msg);
        return r;
    }

    public static <T> R<T> unauthorized(){
        R<T> r = new R<>();
        r.setCode(401);
        r.setMessage("token已失效,请重新登录");
        return r;
    }

//    public R<T> add(String key,Object value){
//       this.map.put(key,value);
//       return this;
//    }
}
