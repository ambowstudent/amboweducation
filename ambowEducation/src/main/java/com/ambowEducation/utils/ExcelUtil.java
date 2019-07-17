package com.ambowEducation.utils;


import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.dto.StudentBaseInfoDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExcelUtil {
    public static List<StudentBaseInfoDto> getStudentsFromExcel(File excel) throws Exception {
        List<StudentBaseInfoDto> list=new ArrayList<>();
        if(excel.isFile() && excel.exists()) {
            String[] split = excel.getName().split("\\.");
            String school = excel.getName().substring(excel.getName().lastIndexOf("-") + 1, excel.getName().lastIndexOf("."));
            StringBuilder schoolStr = new StringBuilder();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            schoolStr.append(year);
            if ("工大".equals(school)) {
                schoolStr.append("01");
            } else if ("烟大".equals(school)) {
                schoolStr.append("02");
            }
            Workbook wb;
            if ("xls".equals(split[1])) {
                FileInputStream fis = new FileInputStream(excel);
                wb = new HSSFWorkbook(fis);
            } else if ("xlsx".equals(split[1])) {
                wb = new XSSFWorkbook(excel);
            } else {
                throw new TutorException(-1,"不支持的文件类型");
            }
            Sheet sheet = wb.getSheetAt(0);
            int firstRowIndex=sheet.getFirstRowNum()+1;
            int lastRowIndex=sheet.getLastRowNum();
            System.out.println(firstRowIndex+" "+lastRowIndex);

            for(int rowIndex=firstRowIndex;rowIndex<=lastRowIndex;rowIndex++){ //遍历行


                Row row=sheet.getRow(rowIndex);
                int firstCellIndex=row.getFirstCellNum();
                int lastCellIndex=row.getLastCellNum();

                for(int cellIndex=firstCellIndex;cellIndex<lastCellIndex;cellIndex++){
                    CellType type = row.getCell(cellIndex).getCellTypeEnum();
                    if(type!=CellType.NUMERIC){
                        row.getCell(cellIndex).setCellType(CellType.STRING);
                    }
                }

                if(row!=null){
                    String name = row.getCell(1).toString();
                    int sex = "男".equals(row.getCell(2).toString())?0:1;
                    String grade = row.getCell(3).toString();
                    String nativePlace = row.getCell(4).toString();
                    Date birthday = row.getCell(5).getDateCellValue();
                    row.getCell(6).setCellType(CellType.STRING);
                    String phone = row.getCell(6).toString();
                    String idNumber = row.getCell(7).toString();
                    String no = String.format("%03d", rowIndex);
                    StudentBaseInfoDto student=new StudentBaseInfoDto();
                    student.setSNo(schoolStr.append(no).toString());
                    student.setSchool(school);
                    student.setBirthday(birthday);
                    student.setGrade(grade);
                    student.setIdNumber(idNumber);
                    student.setNativePlace(nativePlace);
                    student.setPhone(phone);
                    student.setSex(sex);
                    student.setName(name);
                    list.add(student);
                    schoolStr.delete(6, schoolStr.length());
                }
            }
        }else{
            System.out.println("找不到指定文件");
        }
        return list;
    }
}
