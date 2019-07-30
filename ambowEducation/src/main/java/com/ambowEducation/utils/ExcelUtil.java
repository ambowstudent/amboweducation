package com.ambowEducation.utils;


import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.dao.StudentMapper;
import com.ambowEducation.dto.StudentBaseInfoDto;
import com.ambowEducation.dto.StudentClassDto;
import com.ambowEducation.dto.StudentDormitoryDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExcelUtil {


    public static List<StudentDormitoryDto> getStudentDormitory(File excel, StudentMapper mapper)throws  Exception{
        String fileName = excel.getName();
        String[] split = fileName.split("\\.");
        Workbook wb;
        if ("xls".equals(split[1])) {
            FileInputStream fis = new FileInputStream(excel);
            wb = new HSSFWorkbook(fis);
        } else if ("xlsx".equals(split[1])) {
            wb = new XSSFWorkbook(excel);
        } else {
            throw new TutorException(-1, "不支持的文件类型");
        }
        List<StudentDormitoryDto> list = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        int numMergedRegions = sheet.getNumMergedRegions();
        for (int i = 0; i < numMergedRegions; i++) {
            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
            int firstRow = mergedRegion.getFirstRow();
            int lastRow = mergedRegion.getLastRow();
            for (int j = firstRow; j <= lastRow; j++) {
                Row row = sheet.getRow(j);
                String roomNum = sheet.getRow(firstRow).getCell(0).getStringCellValue();
                Cell roomNumCell = row.getCell(0);
                Cell nameCell = row.getCell(1);
                Cell schoolCell = row.getCell(3);
                Cell phoneCell = row.getCell(4);
                if (roomNumCell != null && nameCell != null && schoolCell != null && phoneCell != null) {
                    String name = nameCell.getStringCellValue();
                    String school = schoolCell.getStringCellValue();
                    phoneCell.setCellType(CellType.STRING);
                    String phone = phoneCell.getStringCellValue();
                    if("".equals(name)||"".equals(school)||"".equals(phone)){
                        continue;
                    }
                    StudentDormitoryDto dto = new StudentDormitoryDto();
                    dto.setSName(name);
                    dto.setSchool(school);
                    dto.setDNumber(roomNum);
                    dto.setSNo(mapper.querySNoByPhoneAndName(name,phone));
                    list.add(dto);
                }
            }
        }
        return list;
    }


    public static List<StudentClassDto> getStudentList(File excel,Integer cId) throws Exception{
        List<StudentClassDto> list=new ArrayList<>();
        if(excel.isFile() && excel.exists()) {
            String fileName = excel.getName();
            String[] split = fileName.split("\\.");
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

            for(int i=2;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                Cell nameCell = row.getCell(1);
                String name = nameCell.getStringCellValue();
                Cell phoneCell = row.getCell(4);
                phoneCell.setCellType(CellType.STRING);
                String phone = phoneCell.getStringCellValue();
                StudentClassDto s=new StudentClassDto();
                s.setCId(cId);
                s.setName(name);
                s.setPhone(phone);
                list.add(s);
            }
        }
        return list;
    }


    public static List<StudentBaseInfoDto> getStudentsFromExcel(File excel) throws Exception {
        List<StudentBaseInfoDto> list=new ArrayList<>();
        if(excel.isFile() && excel.exists()) {
            String[] split = excel.getName().split("\\.");
            String school = excel.getName().substring(excel.getName().lastIndexOf("-") + 1, excel.getName().lastIndexOf("."));
            StringBuilder sNo = new StringBuilder();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            sNo.append(year);
            String schoolStr = ExcelUtil.getSchool(school);
            if("".equals(schoolStr) || "error".equals(schoolStr)){
                throw new TutorException(-1,"未知学校");
            }else {
                sNo.append(schoolStr);
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
                    student.setSNo(sNo.append(no).toString());
                    student.setSchool(school);
                    student.setBirthday(birthday);
                    student.setGrade(grade);
                    student.setIdNumber(idNumber);
                    student.setNativePlace(nativePlace);
                    student.setPhone(phone);
                    student.setSex(sex);
                    student.setName(name);
                    list.add(student);
                    System.out.println(sNo);
                    sNo.delete(6, sNo.length());
                }
            }
        }else{
            throw new TutorException(-1,"未知错误");
        }
        return list;
    }




    public static String getSchool(String school){
        String str;
        if("工大".equals(school)){
            str="01";
        }else if ("烟大".equals(school)) {
            str="02";
        }else if ("临大".equals(school)) {
            str="03";
        }else if ("泰山".equals(school)) {
            str="04";
        }else if ("工商".equals(school)) {
            str="05";
        }else if ("理工".equals(school)) {
            str="06";
        }else if ("日职".equals(school)) {
            str="07";
        }else if ("滨职".equals(school)) {
            str="08";
        }else if ("烟职".equals(school)) {
            str="09";
        }else if ("外贸".equals(school)) {
            str="10";
        }else if ("威职".equals(school)) {
            str="11";
        }else if ("潍职".equals(school)) {
            str="12";
        }else if ("科技".equals(school)) {
            str="13";
        }else if ("信息".equals(school)) {
            str="14";
        }else if ("莱职".equals(school)) {
            str="15";
        }else {
            str="error";
        }
        return str;
    }



    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
