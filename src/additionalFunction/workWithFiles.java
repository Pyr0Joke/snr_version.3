package additionalFunction;

import SNRui.MainWindow;
import com.sun.xml.internal.ws.wsdl.writer.document.Message;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class workWithFiles {

    //загрузить xls файл с матрицей с выбором файла
    public static int downloadFile(DefaultTableModel tableModel, ArrayList<Object[]> matrix) {
        int countColums = 0;
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Choose xls file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(0);
                HSSFRow row;
                HSSFCell cell;

                int rows; // No of rows
                rows = sheet.getPhysicalNumberOfRows();

                int cols = 0; // No of columns
                int tmp = 0;

                // This trick ensures that we get the data properly even if it doesn't start from first few rows
                for (int i = 0; i < 10 || i < rows; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                        if (tmp > cols) cols = tmp;
                    }
                }
                cols++;
                countColums = cols;
                for (int i = 0; i < cols; i++)
                    tableModel.addColumn(i);

                for (int r = 0; r < rows; r++) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add((r + 1) + " наблюдение");
                    row = sheet.getRow(r);
//                    System.out.println();
                    if (row != null) {
                        for (int c = 0; c < cols; c++) {
                            cell = row.getCell((short) c);
                            if (cell != null) {
                                arrayList.add("" + cell);
                            }
                        }
                    }
                    tableModel.addRow(arrayList.toArray());
                    matrix.add(arrayList.toArray());
                }
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }

        }
        return countColums;
    }


    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void saveSNRVectorAndResultsInFile(ArrayList<String> vector, ArrayList<String> results) {

        vector.add(" ");
        vector.addAll(results);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");


        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        for (int i = 0; i < vector.size(); i++) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(vector.get(i));
            cell.setCellStyle(style);
        }
        Date date = new Date();

        File file = new File("src/" + date.toString() + ".xls");
        file.getParentFile().mkdirs();
        try {
            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveSNRVectorAndResultsInFileInOneVector(ArrayList<String> vector) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");


        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        for (int i = 0; i < vector.size(); i++) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(vector.get(i));
            cell.setCellStyle(style);
        }
        Date date = new Date();

        File file = new File("src/" + date.toString() + ".xls");
        file.getParentFile().mkdirs();
        try {
            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //загрузка вектора
    public static void downloadVector(ArrayList<String> matrix) {
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Choose xls file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(0);
                HSSFRow row;
                HSSFCell cell;

                int rows; // No of rows
                rows = sheet.getPhysicalNumberOfRows();

                int cols = 1; // No of columns
                for (int r = 0; r < rows; r++) {
                    row = sheet.getRow(r);
                    if (row != null) {
                        for (int c = 0; c < cols; c++) {
                            cell = row.getCell((short) c);
                            if (cell != null) {
                                matrix.add("" + cell);
                            }
                        }
                    }
                }
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }
        }
    }

    //ToDO
    //Сохранение данных в файл
    public static void saveFile(JTable table) throws IOException {
        TableModel model = table.getModel();
        FileWriter out = new FileWriter("D:\\result.xls");
        String groupExport = "";
        for (int i = 0; i < (model.getColumnCount()); i++) {//* disable export from TableHeaders
            groupExport = String.valueOf(model.getColumnName(i));
            out.write(String.valueOf(groupExport) + "\t");
        }
        out.write("\n");
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < (model.getColumnCount()); j++) {
                if (model.getValueAt(i, j) == null) {
                    out.write("null" + "\t");
                } else {
                    groupExport = String.valueOf(model.getValueAt(i, j));
                    out.write(String.valueOf(groupExport) + "\t");
                }
            }
            out.write("\n");
        }
        out.close();

    }

    public static ArrayList<ArrayList<Object[]>> downloadBook(DefaultTableModel tableModel) {
        int countColums = 0;
        ArrayList<ArrayList<Object[]>> pageList = new ArrayList<>();
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Choose xls file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
                HSSFWorkbook wb = new HSSFWorkbook(fs);
//                HSSFSheet sheet = wb.getSheetAt(0);
                int sheetsCount = wb.getNumberOfSheets();
                for (int j = 0; j < sheetsCount; j++) {
                    HSSFSheet sheet = wb.getSheetAt(j);
                    HSSFRow row;
                    HSSFCell cell;

                    int rows; // No of rows
                    rows = sheet.getPhysicalNumberOfRows();

                    int cols = 0; // No of columns
                    int tmp = 0;


                    // This trick ensures that we get the data properly even if it doesn't start from first few rows
                    for (int i = 0; i < 10 || i < rows; i++) {
                        row = sheet.getRow(i);
                        if (row != null) {
                            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                            if (tmp > cols) cols = tmp;
                        }
                    }

                    cols++;

                    countColums = cols;
                    if (j == 0) {
                        for (int i = 0; i < cols; i++)
                            tableModel.addColumn(i);
                    }

                    ArrayList<Object[]> matrix = new ArrayList<>();
                    for (int r = 0; r < rows; r++) {
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add((r + 1) + " наблюдение");
                        row = sheet.getRow(r);
//                    System.out.println();
                        if (row != null) {
                            for (int c = 0; c < cols; c++) {
                                cell = row.getCell((short) c);
                                if (cell != null) {
                                    arrayList.add("" + cell);
                                }
                            }
                        }
                        if (j == 0) {
                            tableModel.addRow(arrayList.toArray());
                        }
                        matrix.add(arrayList.toArray());
                    }
                    pageList.add(matrix);
                }
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }

        }
        return pageList;
    }

    public static void saveInFileResultOfBookCalculated(ArrayList<ArrayList<String>> vectors) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");


        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);


        for (int j = 0; j < vectors.size(); j++) {
            row = sheet.createRow(rownum);
            for (int i = 1; i < vectors.get(j).size(); i++) {
                cell = row.createCell(i, CellType.STRING);
                if(!vectors.get(j).get(i).equals(" ")){
                    if(Double.parseDouble(vectors.get(j).get(i))>= MainWindow.SNRVar)
                        cell.setCellValue(vectors.get(j).get(i));
                    else {
                        cell.setCellValue("0");
                    }
                }
                else{
                    cell.setCellValue(" ");
                }
                cell.setCellStyle(style);
            }
            rownum++;
        }
        Date date = new Date();

        File file = new File("src/" + date.toString() + ".xls");
        file.getParentFile().mkdirs();
        try {
            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveInFileWithMultOnVectorCalculated(ArrayList<ArrayList<String>> vectors) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");


        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);


        for (int j = 0; j < vectors.size(); j++) {
            row = sheet.createRow(rownum);
            for (int i = 0; i < vectors.get(j).size(); i++) {
                cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(vectors.get(j).get(i));
                cell.setCellStyle(style);
            }
            rownum++;
        }
        Date date = new Date();

        File file = new File("src/" + date.toString() + ".xls");
        file.getParentFile().mkdirs();
        try {
            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
