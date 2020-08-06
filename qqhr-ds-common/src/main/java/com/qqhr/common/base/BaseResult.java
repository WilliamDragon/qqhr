package com.qqhr.common.base;

/**
 * 结果类
 *
 */
public class BaseResult<T> {

    //执行成功错误码：0000 代表成功
    public final static String CODE_SUCCESS = "0000";
    public final static String MSG_SUCCESS = "操作成功";
    //动作是否执行成功
    private boolean isSuccess = false;
    //动作执行成功是返回的结果
    private T result = null;
    //错误码
    private String errorCode = null;
    //错误信息
    private String errorMessgage = null;
    //动作执行失败
    public BaseResult<T> fail(String errorCode, String errorMessgage){
        this.isSuccess = false;
        this.result = null;
        this.setErrorCode(errorCode);
        this.setErrorMessgage(errorMessgage);
        return this;
    }
    //动作执行成功
    public BaseResult<T> success(T result){
        this.isSuccess = true;
        this.result = result;
        this.setErrorCode(CODE_SUCCESS);
        this.setErrorMessgage(MSG_SUCCESS);
        return this;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessgage() {
        return errorMessgage;
    }

    public void setErrorMessgage(String errorMessgage) {
        this.errorMessgage = errorMessgage;
    }
}
