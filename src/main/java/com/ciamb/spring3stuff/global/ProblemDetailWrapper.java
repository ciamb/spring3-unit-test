package com.ciamb.spring3stuff.global;

import org.springframework.http.ProblemDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemDetailWrapper extends ProblemDetail {

    private List<Map<String, String>> errors;

    public ProblemDetailWrapper() {
        this.errors = new ArrayList<>();
    }

    public void setErrors(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        this.errors.add(map);
    }

    public List<Map<String, String>> getErrors() {
        return this.errors;
    }

}
