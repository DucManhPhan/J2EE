package com.manhpd.word.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorVds {

    private String errorCode;

    private String content;

    @Override
    public String toString() {
        return this.errorCode + " - " + this.content;
    }

}
