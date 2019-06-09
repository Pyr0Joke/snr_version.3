package additionalFunction;


import SNRui.MainWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;



//Рендер для перекраски столбцов таблицы в красный - если SNR не удовлетворяет требованиям, и в зеленый - если удовлетворяет
public class SNRRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(Double.parseDouble(table.getValueAt(table.getRowCount()-1,column).toString())> MainWindow.SNRVar) {
            setBackground(Color.GREEN);
        }
        else
        {
            setBackground(Color.RED);
        }
        return l;
    }
}
