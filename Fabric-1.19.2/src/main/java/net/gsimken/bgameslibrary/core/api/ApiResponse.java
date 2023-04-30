package net.gsimken.bgameslibrary.core.api;

import java.util.ArrayList;

public class ApiResponse {
    private Integer code;
    private String errorDescription;
    private ArrayList<Object> response;
    public ApiResponse() {
        this.code = null;
        this.errorDescription=null;
        this.response = new ArrayList<>();
    }
    public void setCodeError(int code,String errorDescription){
        this.code = code;
        this.errorDescription = errorDescription;
    }
    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<Object> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<Object> response) {
        this.response = response;
    }
}
