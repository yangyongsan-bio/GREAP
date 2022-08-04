package com.lyy.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public Integer totalNumber = 0;


    /**
     * @param fileName 文件全路径
     * @param startRow 开始索引
     * @param pageSize 每页的条数
     * @return List<String>
     */
    public List<String> getLines(String fileName, int startRow, int pageSize) {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> lines = new ArrayList<>();
        try {
            // 计算结束行数
            int endRow = startRow + pageSize;
            reader = new BufferedReader(new FileReader(file));
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
            lineNumberReader.skip(Long.MAX_VALUE);
            // 获取文件里面的总行数
            int lineNumber = lineNumberReader.getLineNumber();
            totalNumber = lineNumber;
            if (endRow >= lineNumber) {
                endRow = lineNumber;
            }
            String tempString = "";
            int line = 0;
            // 一次读入一行，直到读入 null 为文件结束
            while (tempString != null) {
                line++;
                tempString = reader.readLine();
                if (line >= startRow && line <= endRow) {
                    lines.add(tempString);
                }
            }
            reader.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
