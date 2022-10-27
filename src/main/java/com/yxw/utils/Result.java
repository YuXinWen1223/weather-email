package com.yxw.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yuxinwen
 * @mingcheng : CRM
 * @模块 : com.yxw.utils
 * @date :2022/9/11 15:19
 */
@Data
public class Result<T> {
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private Map<String, Object> data = new HashMap<>();

    /**
     * 私有化构造方法，禁止在其它类创建对象
     */
    private Result() {
    }

    /**
     * 成功执行，不返回数据
     * <p>
     * /
     * <p>
     * /**
     * 成功执行，并返回数据
     *
     * @return result
     */
    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        return result;
    }

    /**
     * 失败
     *
     * @return result
     */
    public static <T> Result<T> error() {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("执行失败");
        return result;
    }


    /**
     * 设置是否成功
     *
     * @param success;
     * @return result
     */
    public Result<T> success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 设置状态码
     *
     * @param code;
     * @return result
     */
    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 设置返回消息
     *
     * @param message;
     * @return message
     */
    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 是否存在
     *
     * @return result
     */
    /**
     * 是否存在
     *
     * @return
     */
    public static <T> Result<T> exist() {
        Result<T> result = new Result<T>();
        result.setSuccess(false);//存在该数据
//由于vue-element-admin模板在响应时验证状态码是否是200，如果不是200，则报错
        result.setCode(ResultCode.SUCCESS);//执行成功，但存在该数据
        result.setMessage("该数据存在");
        return result;
    }

    public Result data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {
        setData(map);
        return this;
    }
}
