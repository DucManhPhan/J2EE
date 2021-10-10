package com.manhpd.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorContent {

    private String errorCode;

    private String content;

    @Override
    public String toString() {
        return this.errorCode + " - " + this.content;
    }

}
