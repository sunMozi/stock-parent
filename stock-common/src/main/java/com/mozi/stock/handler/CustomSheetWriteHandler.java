package com.mozi.stock.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public class CustomSheetWriteHandler implements SheetWriteHandler {

  private static final short DEFAULT_ROW_HEIGHT = 500;  // 行高，twips单位 (1/20 point)
  private static final int[] COLUMN_WIDTHS = {3000, 4000, 6000, 4000, 4000, 4000, 4000, 4000, 4000,
      4000};

  @Override
  public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder,
                                WriteSheetHolder writeSheetHolder) {
    // 此处一般不处理样式，留空即可
  }

  @Override
  public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder,
                               WriteSheetHolder writeSheetHolder) {
    Sheet sheet = writeSheetHolder.getSheet();
    Workbook workbook = writeWorkbookHolder.getWorkbook();

    // 1. 设置默认行高（所有行）
    sheet.setDefaultRowHeight(DEFAULT_ROW_HEIGHT);

    // 2. 设置列宽
    for (int i = 0; i < COLUMN_WIDTHS.length; i++) {
      sheet.setColumnWidth(i, COLUMN_WIDTHS[i]);
    }

    // 3. 创建字体样式，适用于表头
    Font headerFont = workbook.createFont();
    headerFont.setFontName("微软雅黑");
    headerFont.setFontHeightInPoints((short) 12);
    headerFont.setBold(true);

    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFont(headerFont);
    headerStyle.setAlignment(HorizontalAlignment.CENTER);
    headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

    // 4. 给表头第一行的单元格设置样式
    Row headerRow = sheet.getRow(0);
    if (headerRow != null) {
      for (Cell cell : headerRow) {
        cell.setCellStyle(headerStyle);
      }
    }
  }
}
