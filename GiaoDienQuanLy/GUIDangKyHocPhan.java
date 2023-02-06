package GiaoDienQuanLy;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import service.DangKyHocPhan_Service;

public class GUIDangKyHocPhan{
    public static String maSV;

    public String getMaSV() {
        return maSV;
    }  
    public static void setMaSV(String maSV) {
        GUIDangKyHocPhan.maSV = maSV;
    }
    public static void main(String[] args) {
    JFrame frame = new JFrame("Đăng kí");
    JLabel lbl1 = new JLabel("Đăng kí học phần");
    JLabel lbl2 = new JLabel("Thuận tiện, nhanh chóng và dễ dàng");
    JLabel lbl3 = new JLabel("Chọn mã môn :");
    JLabel lbl4 = new JLabel("Chọn tên môn :");
    JLabel lbl7 = new JLabel("");

    //lua chon
    JComboBox comboBox = new JComboBox();
    JComboBox comboBox1 = new JComboBox();
    DangKyHocPhan_Service.luaChon(comboBox, comboBox1);
    frame.add(comboBox);
    frame.add(comboBox1);

    lbl1.setBounds(20,20,700,60);
    lbl1.setForeground(Color.BLUE);
    lbl1.setFont(new Font("Calibri",Font.BOLD,46));
    lbl2.setBounds(20, 60, 700, 60);
    lbl2.setFont(new Font("serif",Font.PLAIN,28));
    lbl3.setBounds(80, 120, 700, 60);
    lbl3.setFont(new Font("serif",Font.PLAIN,28));
    lbl4.setBounds(80, 220, 700, 60);
    lbl4.setFont(new Font("serif",Font.PLAIN,28));
    lbl7.setBounds(80, 360, 700, 60);
    lbl7.setFont(new Font("serif",Font.PLAIN,22));

    JButton btn = new JButton ("Đăng kí");
    btn.setBackground(Color.CYAN);
    btn.setBounds(120, 300, 250, 60);
    btn.setFont(new Font("Comic Sans MS", Font.BOLD,34));
    btn.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            DangKyHocPhan_Service.themThongTin(maSV, comboBox, lbl7);
        }
    });
        frame.setSize(590,500);
        frame.setLocationRelativeTo(frame);
        frame.setLayout(null);
        frame.add(lbl1);frame.add(lbl2);frame.add(lbl3);frame.add(lbl4);frame.add(lbl7);
        frame.add(btn);
        frame.setVisible(true);
    }
}


