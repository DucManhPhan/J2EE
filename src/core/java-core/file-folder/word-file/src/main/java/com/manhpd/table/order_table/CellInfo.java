package com.manhpd.table.order_table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CellInfo {

    private Color backgroundColor;

    private boolean isBold;

    private int alignment;

    private double rowHeight;

//    private List<Integer> alignmentCells;

}
