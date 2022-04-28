package com.grupo11.readingsprocessor.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class UnprocessableEntity {

    @Id
    private final ObjectId objectId;
    private final String data;
}
