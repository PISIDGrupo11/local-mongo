package com.grupo11.readingsdownloader.database.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.Document;

import java.util.List;

@Data
@AllArgsConstructor
public class RawData {

    private List<Document> manufactureControlPassed;
    private List<Document> manufactureControlFail;
}
