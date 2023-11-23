package com.example.cinra.data.responses;

import com.example.cinra.data.enums.ResponseType;
import lombok.Data;

import java.util.List;

@Data
public class GlobalGetListResponse<T> {
    ResponseType responseType;
    int amount;
    List<T> objects;

    public GlobalGetListResponse(List<T> objects, ResponseType responseType) {
        amount = objects.size();
        this.objects = objects;
        this.responseType = responseType;
    }
}
