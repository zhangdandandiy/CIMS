package com.ruoyi.common.utils.file;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件合并具类
 *
 * @author Dandan
 * @date 2023/8/9 13:34
 **/
@Slf4j
public class FileMergeUtils {

    /**
     * 根据列名获取列索引
     * 列名由大写字母组成，如"A", "B", "AA", "AB"等
     *
     * @param name 列名
     * @return 列索引，从1开始
     */
    public static int getColumnIndexFromName(String name) {
        int index = 0;
        // AA；[A,A]
        for (char c : name.toUpperCase().toCharArray()) {
            // 每进入下一个循环，索引乘以26
            index *= 26;
            // 将字母转换为对应的数字（A=1, B=2, ..., Z=26）
            index += (c - 'A' + 1);
        }
        return index;
    }

    /**
     * 根据列索引获取列名
     * 列索引从1开始，将其转换为对应的大写字母列名，如1->A，2->B，27->AA
     *
     * @param index index 列索引，从1开始
     * @return 列名
     */
    public static String getColumnNameFromIndex(int index) {
        StringBuilder name = new StringBuilder();
        while (index > 0) {
            int remainder = index % 26;
            index = (index - 1) / 26;
            char columnLetter = (char) ('A' + remainder);
            name.insert(0, columnLetter);
        }
        return name.toString();
    }

    /**
     * 移动公式中单元格引用
     * 根据给定的行和列的偏移量，将公式中的单元格引用按相应的偏移进行移动
     *
     * @param formula      要移动引用的公式
     * @param rowsMoved    行的偏移量，正值表示向下移动，负值表示向上移动
     * @param columnsMoved 列的偏移量，正值表示向右移动，负值表示向左移动
     * @return 移动引用后的公式
     */
    public static String moveFormulaReferences(String formula, int rowsMoved, int columnsMoved) {
        // 用于匹配单元格引用的正则表达式
        String regex = "[A-Za-z]+\\d+";

        for (int i = 0; i < formula.length(); i++) {
            if (Character.isLetter(formula.charAt(i))) {
                StringBuilder cellReference = new StringBuilder();
                while (i < formula.length() && Character.isLetterOrDigit(formula.charAt(i))) {
                    cellReference.append(formula.charAt(i));
                    i++;
                }
                if (cellReference.toString().matches(regex)) {
                    String cellRef = cellReference.toString();
                    // 提取引用中的行号
                    int row = Integer.parseInt(cellRef.replaceAll("\\D+", ""));
                    // 提取引用中的列号
                    int column = getColumnIndexFromName(cellRef.replaceAll("\\d+", ""));

                    // 移动单元格的行和列
                    row += rowsMoved;
                    column += columnsMoved;

                    // 替换公式中的引用
                    // 根据新的行号和列号构建新的引用
                    String newCellRef = getColumnNameFromIndex(column) + row;
                    // 替换公式中的原始引用为新的引用
                    formula = formula.replace(cellRef, newCellRef);
                }
            }
        }
        return formula;
    }

    /**
     * 复制单元格样式
     * 根据给定的源工作簿、目标工作簿和源样式，复制样式并创建一个新的单元格样式
     *
     * @param sourceWorkbook 源工作簿，样式来源
     * @param targetWorkbook 目标工作簿，样式被复制到其中
     * @param sourceStyle    源样式，要复制的样式
     * @return 复制后的新样式
     */
    public static CellStyle copyCellStyle(Workbook sourceWorkbook, Workbook targetWorkbook, CellStyle sourceStyle) {
        // 创建新的单元格样式
        CellStyle newStyle = targetWorkbook.createCellStyle();
        // 克隆源样式到新样式
        newStyle.cloneStyleFrom(sourceStyle);

        // 复制数据格式
        newStyle.setDataFormat(sourceStyle.getDataFormat());

        // 复制字体
        Font sourceFont = sourceWorkbook.getFontAt(sourceStyle.getFontIndex());
        Font newFont = targetWorkbook.createFont();
        newFont.setBold(sourceFont.getBold());
        newFont.setItalic(sourceFont.getItalic());
        newFont.setColor(sourceFont.getColor());
        newFont.setFontHeight(sourceFont.getFontHeight());
        newFont.setFontName(sourceFont.getFontName());
        newFont.setUnderline(sourceFont.getUnderline());
        newStyle.setFont(newFont);

        // 复制字体的粗体设置
        return newStyle;
    }

    /**
     * 将文件2 sheet 中的条件样式设置到文件1 sheet 中
     *
     * @param sheet1
     * @param sheet2
     */
    public static void setConditionStyle(XSSFSheet sheet1, XSSFSheet sheet2) {
        // 获取第一个文件的行数
        int sheet1EndRow = sheet1.getLastRowNum() + 2;

        // 获取sheet中的条件样式对象
        XSSFSheetConditionalFormatting sheet1Formatting = sheet1.getSheetConditionalFormatting();
        XSSFSheetConditionalFormatting sheet2Formatting = sheet2.getSheetConditionalFormatting();
        // 获取文件2的sheet中条件样式的条数
        int numConditionalFormattings = 0;
        if (sheet2Formatting != null) {
            numConditionalFormattings = sheet2Formatting.getNumConditionalFormattings();
        }

        for (int i = 0; i < numConditionalFormattings; i++) {
//        for (int i = numConditionalFormattings; i > 0; i--) {
            // 获取文件2的sheet中的每一个条件样式
            XSSFConditionalFormatting conditionalFormattingAt = sheet2Formatting.getConditionalFormattingAt(i);
            // 获取文件2的sheet中的"条件样式"对应的单元格

            CellRangeAddress[] formattingRanges = conditionalFormattingAt.getFormattingRanges();

            // 获取起始行和结束行
            for (CellRangeAddress range : formattingRanges) {
                // 获取起始行和结束行
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();

                // 重新设置位置
                range.setFirstRow(firstRow + sheet1EndRow);
                range.setLastRow(lastRow + sheet1EndRow);
            }

            // 获取文件2特定条件格式位置的规则数量
            int numberOfRules = conditionalFormattingAt.getNumberOfRules();

            // 创建文件1 sheet中的"条件样式"中的"样式"
            XSSFConditionalFormattingRule conditionalFormattingColorScaleRule = sheet1Formatting.createConditionalFormattingColorScaleRule();
            // 获取"样式"对象
            XSSFPatternFormatting patternFormatting = conditionalFormattingColorScaleRule.createPatternFormatting();

            // 遍历并添加所有条件格式规则到文件1的条件格式
            for (int j = 0; j < numberOfRules; j++) {
//            for (int j = numberOfRules; j > 0; j--) {
                XSSFConditionalFormattingRule rule = conditionalFormattingAt.getRule(j);
                sheet1Formatting.addConditionalFormatting(formattingRanges, rule);
                // 设置文件1 sheet中"条件样式"的背景
                XSSFColor color = (XSSFColor) rule.getPatternFormatting().getFillBackgroundColorColor();
                String hexColor = color.getARGBHex();
                patternFormatting.setFillBackgroundColor(rule.getPatternFormatting().getFillBackgroundColorColor());
//                patternFormatting.setFillBackgroundColor(IndexedColors.RED.getIndex());
            }
        }
    }

    /**
     * 合并文件的条件格式
     * 根据给定的文件路径，打开并读取两个表格，然后将第二个表格的条件格式复制到第一个表格指定位置
     *
     * @param filePath1
     * @param filePath2
     */
    public static void combineExcelConditionalFormatting(String filePath1, String filePath2, String newFilePath) {
        try {
            // 打开第一个表格
            FileInputStream file1 = new FileInputStream(filePath1);
            // 延迟解析比率
            ZipSecureFile.setMinInflateRatio(-1.0d);
            Workbook workbook1 = new XSSFWorkbook(file1);
            XSSFSheet sheet1 = (XSSFSheet) workbook1.getSheetAt(0);

            // 打开第二个表格
            FileInputStream file2 = new FileInputStream(filePath2);
            // 延迟解析比率
            ZipSecureFile.setMinInflateRatio(-1.0d);
            Workbook workbook2 = new XSSFWorkbook(file2);
            XSSFSheet sheet2 = (XSSFSheet) workbook2.getSheetAt(0);

            setConditionStyle(sheet1, sheet2);
            // 重新计算所有公式和条件格式
            // workbook1.getCreationHelper().createFormulaEvaluator().evaluateAll();

            // 保存并关闭文件
            FileOutputStream outputStream = new FileOutputStream(newFilePath);
            // 将合并的表格保存到文件
            workbook1.write(outputStream);
            workbook1.close();
            workbook2.close();
            file1.close();
            file2.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制和合并源表格中的合并单元格到目标表格中的相同位置
     *
     * @param sourceSheet  源表格的Sheet对象
     * @param targetSheet  目标表格的Sheet对象
     * @param mergedRegion 要复制和合并的合并单元格的区域
     */
    public static void copyMergedCells(Sheet sourceSheet, Sheet targetSheet, CellRangeAddress mergedRegion) {

        // 获取合并区域的起始行、结束行、起始列、结束列
        int firstRow = mergedRegion.getFirstRow();
        int lastRow = mergedRegion.getLastRow();

        // 源文件结束行(要复制的文件)
        int sourceEndRow = sourceSheet.getLastRowNum();
        // 目标文件结束行(要保存的文件)
        int targetEndRow = targetSheet.getLastRowNum();
        int targetLocation = targetEndRow - sourceEndRow + 3;

        // 遍历合并区域内的每一行
        for (int row = firstRow; row <= lastRow; row++) {
            // 获取源表格中的当前行和目标表格中的当前行
            Row sourceRow = sourceSheet.getRow(row);
            Row targetRow = targetSheet.getRow(row - firstRow);

            // 复制行高和行隐藏状态
            if (sourceRow != null && targetRow != null) {
                targetRow.setHeight(sourceRow.getHeight());
                targetRow.setZeroHeight(sourceRow.getZeroHeight());
            }
        }

        // 合并复制后的区域
        CellRangeAddress newMergedRegion = new CellRangeAddress(
                (mergedRegion.getFirstRow() + targetLocation - 3),
                (mergedRegion.getLastRow() + targetLocation - 3),
                mergedRegion.getFirstColumn(),
                mergedRegion.getLastColumn()
        );
        targetSheet.addMergedRegion(newMergedRegion);
    }

    /**
     * 拼接 Excel
     * 根据给定的文件路径，打开并读取两个表格，然后将第二个表格的内容复制到第一个表格指定位置
     * 最后将合并后的结果保存到新的文件中
     *
     * @param filePath1   文件1地址
     * @param filePath2   文件2地址
     * @param newFilePath 新文件地址
     */
    public static void combineExcel(String filePath1, String filePath2, String newFilePath) throws IOException {
        combineExcelConditionalFormatting(filePath1, filePath2, newFilePath);

        try {
            // 打开第一个表格
            FileInputStream file1 = new FileInputStream(newFilePath);
            // 延迟解析比率
            ZipSecureFile.setMinInflateRatio(-1.0d);
            Workbook workbook1 = new XSSFWorkbook(file1);
            XSSFSheet sheet1 = (XSSFSheet) workbook1.getSheetAt(0);

            // 打开第二个表格
            FileInputStream file2 = new FileInputStream(filePath2);
            // 延迟解析比率
            ZipSecureFile.setMinInflateRatio(-1.0d);
            Workbook workbook2 = new XSSFWorkbook(file2);
            XSSFSheet sheet2 = (XSSFSheet) workbook2.getSheetAt(0);

            // 将第二个表格的内容复制到第一个表格
            // 计算起始行索引
            int startRow = sheet1.getLastRowNum() + 2;
            // 计算起始列索引
            int startColumn = 0;
            // 计算结束行索引
            int endRow = startRow + sheet2.getLastRowNum();
            // 计算结束列索引
            int endColumn = 0; // 默认最后一个单元格的索引为0
            for (int i = 0; i < sheet1.getLastRowNum(); i++) {
                Row zeroRow = sheet2.getRow(i);
                if (zeroRow != null) {
                    if (zeroRow.getLastCellNum() > endColumn) {
                        endColumn = zeroRow.getLastCellNum();
                    }
                }
            }

            FormulaEvaluator evaluator1 = workbook1.getCreationHelper().createFormulaEvaluator();

            // 要复制的区域
            for (int i = 0, j = startRow; j <= endRow; i++, j++) {
                // 获取第二个表格的行
                Row sourceRow = sheet2.getRow(i);
                // 创建第一个表格的行
                Row targetRow = sheet1.createRow(j);
                if (sourceRow != null) {
                    for (int k = startColumn; k <= endColumn; k++) {
                        // 获取第二个表格的单元格
                        Cell sourceCell = sourceRow.getCell(k);
                        FormulaEvaluator evaluator = workbook2.getCreationHelper().createFormulaEvaluator();

                        if (sourceCell != null) {
                            // 创建第一个表格的单元格
                            Cell targetCell = targetRow.createCell(k);

                            // 复制源单元格的样式到目标单元格
                            CellStyle targetCellStyle = copyCellStyle(workbook2, workbook1, sourceCell.getCellStyle());
                            targetCell.setCellStyle(targetCellStyle);

                            // 根据源单元格的类型进行复制
                            switch (sourceCell.getCellType()) {
                                case STRING:
                                    targetCell.setCellValue(sourceCell.getStringCellValue());
                                    break;
                                case NUMERIC:
                                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                                    // 设置目标单元格的数据格式为保留两位小数
                                    targetCell.getCellStyle().setDataFormat(workbook1.getCreationHelper().createDataFormat().getFormat("#.00"));
                                    break;
                                case BOOLEAN:
                                    targetCell.setCellValue(sourceCell.getBooleanCellValue());
                                    break;
                                case FORMULA:
                                    // 计算公式，并复制公式和计算后的值
                                    evaluator.evaluateFormulaCell(sourceCell);
                                    CellValue cellValue = evaluator.evaluate(sourceCell);

                                    // 检查公式结果的类型
                                    if (cellValue.getCellType() == CellType.STRING) {
                                        targetCell.setCellValue(cellValue.getStringValue());
                                    } else if (cellValue.getCellType() == CellType.NUMERIC) {
                                        targetCell.setCellValue(cellValue.getNumberValue());
                                        // 设置目标单元格的数据格式为保留两位小数
                                        targetCell.getCellStyle().setDataFormat(workbook1.getCreationHelper().createDataFormat().getFormat("#.00"));
                                    }

                                    // 复制公式，并调整引用的单元格索引
                                    targetCell.setCellFormula(moveFormulaReferences(sourceCell.getCellFormula(), startRow, startColumn - 1));

                                    break;
                                case ERROR:
                                    targetCell.setCellValue(sourceCell.getErrorCellValue());
                                    break;
                                default:
                                    // 处理其他未知类型
                                    break;
                            }
                        }
                    }
                }
            }
            // 重新计算整个工作表的公式
            evaluator1.clearAllCachedResultValues();
            evaluator1.evaluateAll();

            // 保存并关闭文件
            FileOutputStream outputStream = new FileOutputStream(newFilePath);
            // 将合并的表格保存到文件
            workbook1.write(outputStream);
            workbook1.close();
            workbook2.close();
            file1.close();
            file2.close();
            outputStream.close();

            FileInputStream sourceStream = new FileInputStream(filePath2);
            // 延迟解析比率
            ZipSecureFile.setMinInflateRatio(-1.0d);
            Workbook sourceWorkbook = new XSSFWorkbook(sourceStream);
            Sheet sourceSheet = sourceWorkbook.getSheetAt(0); // 获取第一个 Sheet

            FileInputStream targetStream = new FileInputStream(newFilePath);
            // 延迟解析比率
            ZipSecureFile.setMinInflateRatio(-1.0d);
            Workbook targetWorkbook = new XSSFWorkbook(targetStream);
            Sheet targetSheet = targetWorkbook.getSheetAt(0); // 获取第一个 Sheet

            int numMergedRegions = sourceSheet.getNumMergedRegions();
            for (int i = 0; i < numMergedRegions; i++) {
                CellRangeAddress mergedRegion = sourceSheet.getMergedRegion(i);
                // 合并单元格
                copyMergedCells(sourceSheet, targetSheet, mergedRegion);
            }
            // 保存并关闭文件
            FileOutputStream copyOutputStream = new FileOutputStream(newFilePath);
            // 将合并的表格保存到文件
            targetWorkbook.write(copyOutputStream);
            targetWorkbook.close();
            sourceWorkbook.close();
            sourceStream.close();
            targetStream.close();
            copyOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拼接多个 Excel 文件
     *
     * @param filePathList 文件地址列表
     * @param newFilePath  新文件地址
     */
    public static void combineMultipleExcel(List<String> filePathList, String newFilePath) throws IOException {
        if (filePathList.isEmpty()) {
            return;
        } else if (filePathList.size() == 1) {
            FileInputStream file = new FileInputStream(filePathList.get(0));
            // 延迟解析比率
            ZipSecureFile.setMinInflateRatio(-1.0d);
            Workbook workbook = new XSSFWorkbook(file);
            // 保存并关闭文件
            FileOutputStream outputStream = new FileOutputStream(newFilePath);
            // 将合并的表格保存到文件
            workbook.write(outputStream);
            workbook.close();
            file.close();
            outputStream.close();
        } else if (filePathList.size() == 2) {
            combineExcel(filePathList.get(0), filePathList.get(1), newFilePath);
        } else {
            combineExcel(filePathList.get(0), filePathList.get(1), newFilePath);
            for (int i = 2; i <= filePathList.size() - 1; i++) {
                // System.out.println("文件路径：" + filePathList.get(i));
                combineExcel(newFilePath, filePathList.get(i), newFilePath);
            }
        }
    }

    /**
     * 获取指定单元格的条件格式规则列表
     *
     * @param sheet
     * @param cell
     * @return
     */
    public static List<ConditionalFormattingRule> getConditionalRule(Sheet sheet, Cell cell) {
        List<ConditionalFormattingRule> ruleList = new ArrayList<ConditionalFormattingRule>();
        if (cell != null) {
            // 获取sheet中条件格式对象
            SheetConditionalFormatting scf = sheet.getSheetConditionalFormatting();
            // 条件格式的数量
            int countOfFormat = scf.getNumConditionalFormattings();
            for (int i = 0; i < countOfFormat; i++) {
                // 获取第i个条件格式
                ConditionalFormatting format = scf.getConditionalFormattingAt(i);
                // 获取条件格式的区域
                CellRangeAddress[] ranges = format.getFormattingRanges();
                for (int r = 0; r < ranges.length; r++) {
                    if (ranges[r].isInRange(cell)) {
                        // 检查cell是否在此区域内
                        int numOfRule = format.getNumberOfRules();
                        for (int j = 0; j < numOfRule; j++) {
                            // 获取具体的规则
                            ConditionalFormattingRule rule = format.getRule(j);
                            ruleList.add(rule);
                        }

                    }
                }

            }
        }
        return ruleList;
    }

    /**
     * 将行号和列号转换为 Excel 的单元格坐标（例如，1 --> A1）
     *
     * @param row
     * @param column
     * @return
     */
    public static String convertToCellCoordinates(int row, int column) {
        StringBuilder cellCoordinates = new StringBuilder();

        int dividend = column + 1;
        while (dividend > 0) {
            int modulo = (dividend - 1) % 26;
            char columnLetter = (char) (65 + modulo);
            cellCoordinates.insert(0, columnLetter);
            dividend = (int) ((dividend - modulo) / 26);
        }

        cellCoordinates.append(row + 1);

        return cellCoordinates.toString();
    }

    /**
     * 将源工作表的内容复制到目标工作表中
     *
     * @param sourceSheet    原工作表
     * @param targetSheet    目标工作表
     * @param targetWorkbook 目标工作簿
     */
    public static void copySheet(Sheet sourceSheet, Sheet targetSheet, Workbook targetWorkbook) {
        // 获取源工作表的最后一行行号
        int rowCount = sourceSheet.getLastRowNum();
        // 获取目标工作表的最后一行行号
        int targetRowCount = targetSheet.getLastRowNum();

        // 遍历源工作表的每一行
        for (int i = 0; i <= rowCount; i++) {
            // 获取当前行对象
            Row sourceRow = sourceSheet.getRow(i);
            if (sourceRow != null) {
                // 在目标工作表中创建新的行，行号为目标工作表的最后一行行号加上当前行号加1
                Row targetRow = targetSheet.createRow(targetRowCount + i + 1);
                // 获取当前行的单元格数量
                int cellCount = sourceRow.getLastCellNum();
                // 遍历当前行的每一个单元格
                for (int j = 0; j < cellCount; j++) {
                    // 获取当前单元格对象
                    Cell sourceCell = sourceRow.getCell(j);
                    if (sourceCell != null) {
                        // 在目标行中创建新的单元格，列号与源单元格相同
                        Cell targetCell = targetRow.createCell(j);
                        // 复制源单元格的内容到目标单元格
                        copyCell(sourceCell, targetCell, targetWorkbook);
                    }
                }
            }
        }
    }

    /**
     * 将源单元格的内容复制到目标单元格中
     *
     * @param sourceCell     源单元格
     * @param targetCell     目标单元格
     * @param targetWorkbook 目标工作簿
     */
    public static void copyCell(Cell sourceCell, Cell targetCell, Workbook targetWorkbook) {
        // 获取源单元格的样式
        CellStyle sourceCellStyle = sourceCell.getCellStyle();

        // 创建目标单元格的样式并克隆源单元格的样式
        CellStyle targetCellStyle = targetWorkbook.createCellStyle();
        targetCellStyle.cloneStyleFrom(sourceCellStyle);

        // 设置目标单元格的样式为克隆的样式
        targetCell.setCellStyle(targetCellStyle);

        // 如果源单元格的类型是空白，直接返回，不做任何操作
        if (sourceCell.getCellType() == CellType.BLANK) {
            return;
        }

        // 根据源单元格的类型设置目标单元格的值
        if (sourceCell.getCellType() == CellType.STRING) {
            // 如果是字符串类型，设置目标单元格的值为源单元格的字符串值
            targetCell.setCellValue(sourceCell.getStringCellValue());
        } else if (sourceCell.getCellType() == CellType.NUMERIC) {
            // 如果是数字类型，设置目标单元格的值为源单元格的数值
            targetCell.setCellValue(sourceCell.getNumericCellValue());
        } else if (sourceCell.getCellType() == CellType.BOOLEAN) {
            // 如果是数字类型，设置目标单元格的值为源单元格的数值
            targetCell.setCellValue(sourceCell.getBooleanCellValue());
        } else if (sourceCell.getCellType() == CellType.FORMULA) {
            // 如果是公式类型，设置目标单元格的公式为源单元格的公式
            targetCell.setCellFormula(sourceCell.getCellFormula());
        } else if (sourceCell.getCellType() == CellType.ERROR) {
            // 如果是错误类型，设置目标单元格的错误值为源单元格的错误值
            targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
        } else if (sourceCell.getCellType() == CellType._NONE) {
            // 如果是_NONE类型，进一步判断源单元格的类型
            if (sourceCell instanceof XSSFCell) {
                // 如果是XSSFCell（xlsx格式）
                // 创建目标工作簿的XSSFDrawing对象和源工作簿的XSSFDrawing对象
                XSSFDrawing targetDrawing = ((XSSFSheet) targetCell.getSheet()).createDrawingPatriarch();
                XSSFDrawing sourceDrawing = ((XSSFSheet) sourceCell.getSheet()).createDrawingPatriarch();
                // 获取源单元格的地址
                CellAddress address = sourceCell.getAddress();
                // 获取源绘图区域中的所有图形
                List<XSSFShape> sourceShapes = sourceDrawing.getShapes();
                // 遍历所有图形
                for (XSSFShape shape : sourceShapes) {
                    if (shape instanceof XSSFPicture) {
                        // 如果是图片类型
                        XSSFPicture sourcePicture = (XSSFPicture) shape;
                        // 检查图片位置与源单元格位置是否匹配
                        if (sourcePicture.getPreferredSize() != null && sourcePicture.getAnchor() instanceof XSSFClientAnchor &&
                                address.getColumn() == ((XSSFClientAnchor) sourcePicture.getAnchor()).getCol1() &&
                                address.getRow() == ((XSSFClientAnchor) sourcePicture.getAnchor()).getRow1()) {
                            // 获取源图片数据
                            XSSFPictureData sourcePictureData = sourcePicture.getPictureData();
                            // 添加图片数据到目标工作簿，并返回图片索引
                            int pictureIndex = targetWorkbook.addPicture(sourcePictureData.getData(), Workbook.PICTURE_TYPE_PNG);
                            // 获取源图片的位置信息
                            XSSFClientAnchor sourceAnchor = (XSSFClientAnchor) sourcePicture.getAnchor();
                            // 创建目标图片的位置（与目标单元格相同）
                            XSSFClientAnchor targetAnchor = new XSSFClientAnchor(sourceAnchor.getDx1(), sourceAnchor.getDy1(), sourceAnchor.getDx2(), sourceAnchor.getDy2(),
                                    address.getColumn(), address.getRow(), address.getColumn() + 1, address.getRow() + 1);
                            // 在目标绘图区域中创建新的图片对象，并设置位置和索引
                            XSSFPicture targetPicture = targetDrawing.createPicture(targetAnchor, pictureIndex);
                            targetPicture.resize();
                        }
                    }
                }
            } else if (sourceCell instanceof HSSFCell) {
                // 如果是HSSFCell（xls格式）
                // 创建目标工作簿的HSSFPatriarch对象和源工作簿的HSSFPatriarch对象
                HSSFPatriarch targetDrawing = ((HSSFSheet) targetCell.getSheet()).createDrawingPatriarch();
                HSSFPatriarch sourceDrawing = ((HSSFSheet) sourceCell.getSheet()).createDrawingPatriarch();
                // 获取源单元格的地址
                CellAddress address = sourceCell.getAddress();
                // 获取源绘图区域中的所有图形
                List<HSSFShape> sourceShapes = sourceDrawing.getChildren();
                // 遍历所有图形
                for (HSSFShape shape : sourceShapes) {
                    if (shape instanceof HSSFPicture) {
                        // 如果是图片类型
                        HSSFPicture sourcePicture = (HSSFPicture) shape;
                        // 获取源图片的位置信息
                        HSSFClientAnchor sourceAnchor = sourcePicture.getClientAnchor();
                        // 检查图片位置与源单元格位置是否匹配
                        if (sourceAnchor.getCol1() == address.getColumn() && sourceAnchor.getRow1() == address.getRow()) {
                            // 获取源图片数据
                            HSSFPictureData sourcePictureData = sourcePicture.getPictureData();
                            // 添加图片数据到目标工作簿，并返回图片索引
                            int pictureIndex = targetWorkbook.addPicture(sourcePictureData.getData(), Workbook.PICTURE_TYPE_PNG);
                            // 创建目标图片的位置（与目标单元格相同）
                            HSSFClientAnchor targetAnchor = new HSSFClientAnchor(sourceAnchor.getDx1(), sourceAnchor.getDy1(), sourceAnchor.getDx2(), sourceAnchor.getDy2(),
                                    (short) address.getColumn(), address.getRow(), (short) (address.getColumn() + 1), address.getRow() + 1);
                            // 在目标绘图区域中创建新的图片对象，并设置位置和索引
                            HSSFPicture targetPicture = targetDrawing.createPicture(targetAnchor, pictureIndex);
                            targetPicture.resize();
                        }
                    }
                }
            }
        }
    }

    public static void copyRow(Row sourceRow, Row targetRow, Workbook sourceWorkbook, Workbook targetWorkbook) {
        targetRow.setHeight(sourceRow.getHeight());

        for (Cell sourceCell : sourceRow) {
            Cell targetCell = targetRow.createCell(sourceCell.getColumnIndex());

            CellStyle sourceCellStyle = sourceCell.getCellStyle();
            CellStyle targetCellStyle = targetWorkbook.createCellStyle();
            targetCellStyle.cloneStyleFrom(sourceCellStyle);
            targetCell.setCellStyle(targetCellStyle);

            switch (sourceCell.getCellType()) {
                case STRING:
                    targetCell.setCellValue(sourceCell.getStringCellValue());
                    break;
                case NUMERIC:
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                    break;
                case BOOLEAN:
                    targetCell.setCellValue(sourceCell.getBooleanCellValue());
                    break;
                case BLANK:
                    targetCell.setCellValue("");
                    break;
                default:
                    // 其他类型处理逻辑
                    break;
            }
        }
    }

    public static void downloadFile(String groupFilePath, HttpServletResponse response) {
        try {
            if (!FileUtils.checkAllowDownload(groupFilePath)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", groupFilePath));
            }

            // 本地资源路径
            String localPath = RuoYiConfig.getDownloadPath();

            // 数据库资源地址
            String filePath = localPath + groupFilePath;
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(filePath, "/");

            // 设置一个总长度，否则无法估算进度
            // response.setHeader("Content-Length", String.valueOf(groupFilePath.length()));
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 将多个 excel 文件合并下载
     *
     * @param filePaths excel 文件地址
     * @return
     * @throws IOException
     */
    public ResponseEntity<ByteArrayResource> mergeFileDownload(List<String> filePaths) throws IOException {

        // 创建一个新的Excel工作簿
        Workbook mergedWorkbook = new XSSFWorkbook();

        // 遍历文件路径列表，读取每个Excel文件并合并到工作簿中
        try {
            int sheetCount = 1;
            for (String filePath : filePaths) {
                // 加上路径前缀
                String fullFilePath = RuoYiConfig.getDownloadPath() + filePath;
                // 逐个打开原始Excel文件
                Workbook originalWorkbook = WorkbookFactory.create(new File(fullFilePath));

                // 逐个复制原始文件的工作表到新工作簿中
                for (int i = 0; i < originalWorkbook.getNumberOfSheets(); i++) {
                    Sheet originalSheet = originalWorkbook.getSheetAt(i);
                    // 检查工作表名称是否存在于新工作簿中
                    String sheetName = originalSheet.getSheetName();
                    if (mergedWorkbook.getSheet(sheetName) != null) {
                        sheetName = sheetName + "-" + sheetCount;
                        sheetCount++;
                    }
                    Sheet mergedSheet = mergedWorkbook.createSheet(sheetName);

                    // 复制原始工作表的每一行到新工作表中
                    for (Row originalRow : originalSheet) {
                        Row mergedRow = mergedSheet.createRow(originalRow.getRowNum());
                        FileMergeUtils.copyRow(originalRow, mergedRow, originalWorkbook, mergedWorkbook);
                    }
                }
                originalWorkbook.close();
            }

            // 创建新的临时文件，将拼接后的工作簿保存为Excel文件
            File mergedFile = File.createTempFile("merged", ".xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(mergedFile);
            mergedWorkbook.write(fileOutputStream);

            // 关闭工作簿和输出流
            mergedWorkbook.close();
            fileOutputStream.close();

            // 构建响应实体对象
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(mergedFile.toPath()));

            // 设置响应头部信息
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mergedFile.xlsx");

            // 删除临时文件
            mergedFile.delete();

            // 返回Excel文件下载响应
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * 创建一个空白工作簿
     *
     * @param filePath
     * @throws IOException
     */
    public static void createEmptyExcelFile(String filePath) throws IOException {
        // 创建工作簿
        Workbook workbook = new XSSFWorkbook();

        // 创建工作表
        Sheet sheet = workbook.createSheet("Sheet1");

        // 写入文件
        FileOutputStream fileOut;
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // 创建文件路径
        }
        fileOut = new FileOutputStream(file);
        workbook.write(fileOut);
        fileOut.close();

        // 关闭工作簿
        workbook.close();
    }

    /**
     * 将sheet2中的条件格式复制到sheet1中
     *
     * @param sheet1
     * @param sheet2
     */
    public static void copyConditionStyle(XSSFSheet sheet1, XSSFSheet sheet2) {
        // 获取文件1的条件格式
        XSSFSheetConditionalFormatting scf1 = sheet1.getSheetConditionalFormatting();

        // 获取文件1最后一行的行数
        int lastRow = sheet1.getLastRowNum() + 2;

        // 计算文件2的行数和列数
        int rowNum = sheet2.getLastRowNum();
        int ColumnNum = 0;
        if (sheet2.getRow(0) != null) {
            ColumnNum = sheet2.getRow(0).getLastCellNum() - 1;
        }

        for (int rowIdx = 0; rowIdx <= rowNum; rowIdx++) {
            XSSFRow row2 = sheet2.getRow(rowIdx);

            if (row2 != null) {
                for (int cellIdx = 0; cellIdx <= ColumnNum; cellIdx++) {
                    XSSFCell cell2 = row2.getCell(cellIdx);

                    if (cell2 != null) {
                        List<ConditionalFormattingRule> ruleList = getConditionalRule(sheet2, cell2);

                        if (ruleList != null && ruleList.size() > 0) {
                            CellRangeAddress[] regions = {new CellRangeAddress(lastRow + rowIdx, lastRow + rowIdx, cellIdx, cellIdx)};
                            for (ConditionalFormattingRule rule : ruleList) {
                                scf1.addConditionalFormatting(regions, rule);
                            }
                        }
                    }
                }
            }
        }

    }

}
