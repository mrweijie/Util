package GUI.moneyMatters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Demo extends JFrame {

    // 定义组件
    JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7;
    JLabel jlb1;
    JLabel jlb2;
    JLabel jlb3;
    JLabel jlb4;
    JLabel jlb5;
    JButton jb1, jb2, jb3;
    JRadioButton jrb1, jrb2;
    JTextField jtf1, jtf2, jtf3;
    JTextArea jta1;
    JRadioButton dk; //贷款
    JRadioButton lc; //理财

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Demo d1 = new Demo();
    }

    // 构造函数
    public Demo() {

        jp1 = new JPanel();

        jlb1 = new JLabel("本金: ");
        jlb2 = new JLabel("年化率: ");
        jlb3 = new JLabel("时间(天): ");
        jlb4 = new JLabel("是否复利： ");
        jlb5 = new JLabel("理财/贷款： ");

        jb1 = new JButton("计算");
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lc.isSelected()){
                    if(jrb1.isSelected()){
                        jta1.setText(overlying(Double.parseDouble(jtf1.getText()),Double.parseDouble(jtf2.getText()),Integer.parseInt(jtf3.getText())).toString());
                    }
                    if(jrb2.isSelected()){
                        jta1.setText(notoverlying(Double.parseDouble(jtf1.getText()),Double.parseDouble(jtf2.getText()),Integer.parseInt(jtf3.getText())).toString());
                    }
                }
                if(dk.isSelected()){
                    if(jrb1.isSelected()){
                        jta1.setText(calculateEqualPrincipal(Double.parseDouble(jtf1.getText()),Double.parseDouble(jtf2.getText()),Integer.parseInt(jtf3.getText())).toString());
                    }
                    if(jrb2.isSelected()){
                        jta1.setText(calculateEqualPrincipalAndInterest(Double.parseDouble(jtf1.getText()),Double.parseDouble(jtf2.getText()),Integer.parseInt(jtf3.getText())).toString());
                    }
                }
            }
        });
        jb2 = new JButton("取消");
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jb3 = new JButton("清空");
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtf1.setText("");
                jtf2.setText("");
                jtf3.setText("");
                jrb1.setSelected(true);
            }
        });

        jtf1 = new JTextField(20);
        jtf1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
                    System.exit(0);
                }
                super.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
        jtf2 = new JTextField(20);
        jtf3 = new JTextField(20);


        jrb1 = new JRadioButton("是");
        jrb2 = new JRadioButton("否");
        // 一定要把jrb1，jrb2放入到一个ButtonGroup里面
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        jrb1.setSelected(true);
        bg.add(jrb2);

        dk = new JRadioButton("贷款");
        lc = new JRadioButton("理财");
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(lc);
        lc.setSelected(true);
        bg2.add(dk);


        // 加入各个组件
        jp1.setLayout(new GridLayout(6, 2));

        jp7 = new JPanel();
        jp7.add(jlb5);
        jp7.add(lc);
        lc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlb1.setText("本金: ");
                jlb2.setText("年化率: ");
                jlb3.setText("时间(天): ");
                jlb4.setText("是否复利：");
                jrb1.setText("是");
                jrb2.setText("否");
            }
        });
        jp7.add(dk);
        dk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlb1.setText("贷款金额: ");
                jlb2.setText("年利率: ");
                jlb3.setText("期数: ");
                jlb4.setText("等金/等息：");
                jrb1.setText("等金");
                jrb2.setText("等息");
            }
        });
        jp1.add(jp7);

        jp2 = new JPanel();
        jp2.add(jlb1);
        jp2.add(jtf1);
        jp1.add(jp2);

        jp3 = new JPanel();
        jp3.add(jlb2);
        jp3.add(jtf2);
        jp1.add(jp3);

        jp6 = new JPanel();
        jp6.add(jlb3);
        jp6.add(jtf3);
        jp1.add(jp6);

        jp4 = new JPanel();
        jp4.add(jlb4);
        jp4.add(jrb1);
        jp4.add(jrb2);
        jp1.add(jp4);

        jp5 = new JPanel();
        jp5.add(jb1);
        jp5.add(jb3);
        jp5.add(jb2);
        jp1.add(jp5);

        jta1 = new JTextArea("",1,1);

        // 加入到JFrame
        this.setLayout(new GridLayout(1, 2));
        this.add(jp1);
        JScrollPane scroll = new JScrollPane(jta1);
        this.add(scroll);

//        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(d.width, d.height);
        this.setSize(750, 350);
        this.setTitle("计算机");
        this.setLocation(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * 计算理财年化率有复利
     *
     * @param capital 本金
     * @param rate 年华率
     * @param day 天数
     *
     */
    public StringBuffer overlying(double capital, double rate, int day){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < day; i++) {
            double lixi = (capital*rate/365);
            sb.append("第"+(i+1)+"日收益："+lixi+"\r\n");
            capital = capital + lixi;
        }
        sb.append("到期收入-->"+capital);
        return sb;
    }

    /**
     * 计算理财年化率无复利
     *
     * @param capital 本金
     * @param rate 年华率
     * @param day 天数
     * @return
     */
    public StringBuffer notoverlying(double capital, double rate, int day){
        StringBuffer sb = new StringBuffer();
        double lixi = capital * rate;
        sb.append("日收益："+lixi/day+"\r\n");
        capital = capital + lixi;
        sb.append("到期收入-->"+capital);
        return sb;
    }

    /**
     * 计算等额本息还款
     *
     * @param principal 贷款总额
     * @param rate      贷款利率
     * @param months    贷款期限
     * @return
     */
    public static StringBuffer calculateEqualPrincipalAndInterest(double principal, double rate, int months) {
        StringBuffer sb = new StringBuffer();
        double monthRate = rate / (100 * 12);//月利率
        double preLoan = (principal * monthRate * Math.pow((1 + monthRate), months)) / (Math.pow((1 + monthRate), months) - 1);//每月还款金额
        double totalMoney = preLoan * months;//还款总额
        double interest = totalMoney - principal;//还款总利息
        sb.append("\r\n还款总额 :"+(totalMoney));//还款总额
        sb.append("\r\n贷款总额 :"+(principal));//贷款总额
        sb.append("\r\n还款总利息 :"+(interest));//还款总利息
        sb.append("\r\n每月还款金额 :"+(preLoan));//每月还款金额
        sb.append("\r\n还款期限 :"+String.valueOf(months));//还款期限
        return sb;
    }

    /**
     * 计算等额本金还款
     *
     * @param principal 贷款总额
     * @param rate      贷款利率
     * @param months    贷款期限
     * @return
     */
    public static StringBuffer calculateEqualPrincipal(double principal, double rate, int months) {
        StringBuffer sb = new StringBuffer();
        double monthRate = rate / (100 * 12);//月利率
        double prePrincipal = principal / months;//每月还款本金
        double firstMonth = prePrincipal + principal * monthRate;//第一个月还款金额
        double decreaseMonth = prePrincipal * monthRate;//每月利息递减
        double interest = (months + 1) * principal * monthRate / 2;//还款总利息
        double totalMoney = principal + interest;//还款总额

        for (int i = 0; i < months; i++) {
            sb.append("\r\n第"+(i+1)+"个月还 "+ (firstMonth - i*decreaseMonth));
        }

        sb.append("\r\n还款总额 :"+(totalMoney));//还款总额
        sb.append("\r\n贷款总额 :"+(principal));//贷款总额
        sb.append("\r\n还款总利息 :"+(interest));//还款总利息
        sb.append("\r\n首月还款金额 :"+(firstMonth));//首月还款金额
        sb.append("\r\n每月递减利息 :"+(decreaseMonth));//每月递减利息
        sb.append("\r\n还款期限 :"+String.valueOf(months));//还款期限

        return sb;
    }
}
