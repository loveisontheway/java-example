package com.muxi.java.example.jlabel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * test
 * @author jjl
 * @date 2023/8/30
 */
public class TestFrame extends javax.swing.JFrame{
    public TestFrame() {
        initComponents();

        setVisible(true);// 显示
        setLocationRelativeTo(null);// JFrame 窗口居中显示
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TestFrame();
            System.out.println("启动成功！");
        });
    }

    private void SubmitActionPerformed(ActionEvent e) {
        // TODO add your code here
        System.out.println("---------------------------");
        System.out.println("姓名："+userNameTestField.getText());
        String sex = "";
        if (xyRadioButton.isSelected()) {
            sex = "男";
        } else if (xxRadioButton.isSelected()) {
            sex = "女";
        }else if (yyRadioButton.isSelected()) {
            sex = "不确定";
        }
        System.out.println("性别："+sex);
        String hobby = "";
        if (singCheckBox.isSelected()) {
            hobby += "唱、";
        }
        if (skipCheckBox.isSelected()) {
            hobby += "跳、";
        }
        if (rapCheckBox.isSelected()) {
            hobby += "rap、";
        }
        System.out.println("爱好："+hobby);
        System.out.println("自我评价："+selfTextArea.getText());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        userNameTestField = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        submit = new JButton();
        reset = new JButton();
        xxRadioButton = new JRadioButton();
        xyRadioButton = new JRadioButton();
        yyRadioButton = new JRadioButton();
        scrollPane1 = new JScrollPane();
        selfTextArea = new JTextArea();
        label4 = new JLabel();
        label5 = new JLabel();
        singCheckBox = new JCheckBox();
        skipCheckBox = new JCheckBox();
        rapCheckBox = new JCheckBox();

        //======== this ========
        setBackground(Color.gray);
        setTitle("Test GUI");
        setForeground(SystemColor.windowText);
        setMinimumSize(new Dimension(300, 200));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u59d3\u540d\uff1a");
        contentPane.add(label1);
        label1.setBounds(34, 55, 65, 30);
        contentPane.add(userNameTestField);
        userNameTestField.setBounds(119, 55, 200, userNameTestField.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u6027\u522b\uff1a");
        contentPane.add(label2);
        label2.setBounds(34, 95, 65, 30);

        //---- label3 ----
        label3.setText("\u81ea\u6211\u8bc4\u4ef7\uff1a");
        contentPane.add(label3);
        label3.setBounds(34, 165, 65, 30);

        //---- submit ----
        submit.setText("\u63d0\u4ea4");
        submit.addActionListener(e -> SubmitActionPerformed(e));
        contentPane.add(submit);
        submit.setBounds(new Rectangle(new Point(64, 271), submit.getPreferredSize()));

        //---- reset ----
        reset.setText("\u91cd\u7f6e");
        contentPane.add(reset);
        reset.setBounds(new Rectangle(new Point(219, 271), reset.getPreferredSize()));

        //---- xxRadioButton ----
        xxRadioButton.setText("\u5973");
        contentPane.add(xxRadioButton);
        xxRadioButton.setBounds(new Rectangle(new Point(184, 100), xxRadioButton.getPreferredSize()));

        //---- xyRadioButton ----
        xyRadioButton.setText("\u7537");
        contentPane.add(xyRadioButton);
        xyRadioButton.setBounds(new Rectangle(new Point(129, 100), xyRadioButton.getPreferredSize()));

        //---- yyRadioButton ----
        yyRadioButton.setText("\u4e0d\u786e\u5b9a");
        contentPane.add(yyRadioButton);
        yyRadioButton.setBounds(new Rectangle(new Point(239, 100), yyRadioButton.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(selfTextArea);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(117, 165, 202, 71);

        //---- label4 ----
        label4.setText("\u6d4b\u8bd5\u8868\u5355");
        label4.setFont(label4.getFont().deriveFont(22f));
        contentPane.add(label4);
        label4.setBounds(124, 0, 100, 45);

        //---- label5 ----
        label5.setText("\u7231\u597d\uff1a");
        contentPane.add(label5);
        label5.setBounds(34, 130, 65, 30);

        //---- singCheckBox ----
        singCheckBox.setText("\u5531");
        contentPane.add(singCheckBox);
        singCheckBox.setBounds(new Rectangle(new Point(129, 135), singCheckBox.getPreferredSize()));

        //---- skipCheckBox ----
        skipCheckBox.setText("\u8df3");
        contentPane.add(skipCheckBox);
        skipCheckBox.setBounds(new Rectangle(new Point(184, 135), skipCheckBox.getPreferredSize()));

        //---- rapCheckBox ----
        rapCheckBox.setText("rap");
        contentPane.add(rapCheckBox);
        rapCheckBox.setBounds(239, 135, 50, rapCheckBox.getPreferredSize().height);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(400, 365);
        setLocationRelativeTo(null);

        //---- buttonGroup2 ----
        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(xxRadioButton);
        buttonGroup2.add(xyRadioButton);
        buttonGroup2.add(yyRadioButton);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField userNameTestField;
    private JLabel label2;
    private JLabel label3;
    private JButton submit;
    private JButton reset;
    private JRadioButton xxRadioButton;
    private JRadioButton xyRadioButton;
    private JRadioButton yyRadioButton;
    private JScrollPane scrollPane1;
    private JTextArea selfTextArea;
    private JLabel label4;
    private JLabel label5;
    private JCheckBox singCheckBox;
    private JCheckBox skipCheckBox;
    private JCheckBox rapCheckBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
