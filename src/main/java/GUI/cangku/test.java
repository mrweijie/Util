package GUI.cangku;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class test extends JFrame {


    private Appwindow appwindow = new Appwindow();
    private JPanel pane = null; // 主要的JPanel，该JPanel的布局管理将被设置成CardLayout
    private JPanel pane2 = null;
    private JPanel p = null; // 放按钮的JPanel
    private CardLayout card = null; // CardLayout布局管理器
    private CardLayout card2 = null;
    private JButton b_1 = null, b_2 = null, b_3 = null; // 三个可直接翻转到JPanel组件的按钮
    private JPanel p_1 = null, p_2 = null, p_3 = null; // 要切换的三个JPanel
    private JPanel p2_1 = null, p2_2 = null, p2_3 = null;


    Object[][] webAppsStr1  = new String[][]{{"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3","2","3","2","3","2","3"},
                                            {"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3","2","3","2","3","2","3"},
                                            {"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3","2","3","2","3","2","3"},
                                            {"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3","2","3","2","3","2","3"},
                                            {"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3","2","3","2","3","2","3"},
                                            {"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3","2","3","2","3","2","3"},
                                            {"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3","2","3","2","3","2","3"},
                                            {"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3","2","3","2","3","2","3"}
    };
    Object[][] webAppsStr2  = new String[][]{{"1","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3"},
                                             {"1","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3"},
                                             {"1","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3"},
                                             {"1","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3"},
                                             {"1","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3"},
                                             {"1","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3"},
                                             {"1","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3"},
                                             {"1","2","3","2","3","2","3","2","3"},{"1","2","3","2","3","2","3","2","3"},{"7","8","9","2","3","2","3","2","3"}
    };
    Object[][] webAppsStr3  = new String[][]{{"1","2","3","2","3","2","3","2","3","2"},{"1","2","3","2","3","2","3","2","3","2"},{"7","8","9","2","3","2","3","2","3","2"},
                                             {"1","2","3","2","3","2","3","2","3","2"},{"1","2","3","2","3","2","3","2","3","2"},{"7","8","9","2","3","2","3","2","3","2"},
                                             {"1","2","3","2","3","2","3","2","3","2"},{"1","2","3","2","3","2","3","2","3","2"},{"7","8","9","2","3","2","3","2","3","2"},
                                             {"1","2","3","2","3","2","3","2","3","2"},{"1","2","3","2","3","2","3","2","3","2"},{"7","8","9","2","3","2","3","2","3","2"},
                                             {"1","2","3","2","3","2","3","2","3","2"},{"1","2","3","2","3","2","3","2","3","2"},{"7","8","9","2","3","2","3","2","3","2"},
                                             {"1","2","3","2","3","2","3","2","3","2"},{"1","2","3","2","3","2","3","2","3","2"},{"7","8","9","2","3","2","3","2","3","2"},
                                             {"1","2","3","2","3","2","3","2","3","2"},{"1","2","3","2","3","2","3","2","3","2"},{"7","8","9","2","3","2","3","2","3","2"},
                                             {"1","2","3","2","3","2","3","2","3","2"},{"1","2","3","2","3","2","3","2","3","2"},{"7","8","9","2","3","2","3","2","3","2"}
    };
    String[] HomeNames={"名称","单价","库存","库存总价","进货次数","进货数量","总进货金额","销售次数","售出数量","总售出金额","总利润","备注","操作"};
    String[] EnterNames={"录入时间","名称","进货数量","成本","备注","总进货金额","操作"};
    String[] SellNames={"录入时间","名称","成本","出售数量","出售单价","总金额","利润","备注"};


    JScrollPane scp1;
    JScrollPane scp2;
    JScrollPane scp3;
    JTable tab1 = null;
    JTable tab2 = null;
    JTable tab3 = null;

    public test() {
        super("CardLayout Test");
        setTitle("仓库");
        setResizable(false);

        /*
            切换按钮
         */
        p = new JPanel(); // 构造放按钮的JPanel
        p.setLayout(new GridLayout(1,3));
        b_1 = new JButton("主页");
        b_2 = new JButton("入库记录");
        b_3 = new JButton("出售记录");
        b_1.setMargin(new Insets(1,20,1,20));
        b_2.setMargin(new Insets(1,20,1,20));
        b_3.setMargin(new Insets(1,20,1,20));
        p.add(b_1);
        p.add(b_2);
        p.add(b_3);

        /*
            table 列表
         */
        card = new CardLayout(3, 1);
        pane = new JPanel(card); // JPanel的布局管理将被设置成CardLayout
        p_2 = new JPanel();
        p_3 = new JPanel();
        tab1 = new JTable(webAppsStr1, HomeNames);
        tab1.setRowHeight(30);
        tab1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                int row = tab1.getSelectedRow();
                int column = tab1.getSelectedColumn();
                if(column == 12)
                {
//                    int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION);
//                    if (n == JOptionPane.YES_OPTION) {
//                        System.out.println("yes");
//                    } else if (n == JOptionPane.NO_OPTION) {
//                        System.out.println("no");
//                    }
                }
            }
        });

        scp1 = new JScrollPane(tab1);
        tab2 = new JTable(webAppsStr2, EnterNames);
        tab2.setRowHeight(30);
        scp2 = new JScrollPane(tab2);
        tab3 = new JTable(webAppsStr3, SellNames);
        tab3.setRowHeight(30);
        scp3 = new JScrollPane(tab3);
        p_2.setBackground(Color.BLUE);
        p_3.setBackground(Color.GREEN);
        p_2.add(new JLabel("JPanel_2"));
        p_3.add(new JLabel("JPanel_3"));
        pane.add(scp1, "p1");
        pane.add(scp2, "p2");
        pane.add(scp3, "p3");

        /*
            搜索框
         */
        card2 = new CardLayout(3, 1);
        pane2 = new JPanel(card2);
        p2_1 = new JPanel();
        p2_1.setLayout(null);
        p2_1.setBackground(Color.WHITE);
        JLabel jLabel = new JLabel("主页",JLabel.CENTER);
        jLabel.setBounds(0,0,280,50);
        p2_1.add(jLabel);

        JButton jButton = new JButton("新增");
        jButton.setBounds(0,70,280,50);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appwindow.setVisible(true);
            }
        });
        p2_1.add(jButton);


        JLabel jl1 = new JLabel("年份");
        jl1.setBounds(0,160,280,20);
        p2_1.add(jl1);
        String [] ct= {"","2019","2020","2021","2022","2023"};
        JComboBox jcb = new JComboBox(ct);
        jcb.setBounds(0,180,280,50);
        p2_1.add(jcb);

        JLabel jl2 = new JLabel("月份");
        jl2.setBounds(0,230,280,20);
        p2_1.add(jl2);
        String [] ct2 = {"","1","2","3","4","5","6","7","8","9","10","11","12"};
        JComboBox jcb2 = new JComboBox(ct2);
        jcb2.setBounds(0,250,280,50);
        p2_1.add(jcb2);

        JLabel jl3 = new JLabel("名称");
        jl3.setBounds(0,300,280,20);
        p2_1.add(jl3);
        JTextField jtf = new JTextField();
        jtf.setBounds(0,320,280,50);
        p2_1.add(jtf);
        JButton jb1 = new JButton("筛选");
        jb1.setBounds(0,400,280,50);
        p2_1.add(jb1);
        JButton jb2 = new JButton("取消筛选");
        jb2.setBounds(0,460,280,50);
        p2_1.add(jb2);

        p2_2 = new JPanel();
        p2_2.setBackground(Color.BLUE);
        p2_2.add(new JLabel("JPanel_2"));

        p2_3 = new JPanel();
        p2_3.setBackground(Color.GREEN);
        p2_3.add(new JLabel("JPanel_3"));

        pane2.add(p2_1, "p2_1");
        pane2.add(p2_2, "p2_2");
        pane2.add(p2_3, "p2_3");

        /*
            切换按钮监听
         */
        b_1.addActionListener(new ActionListener() { // 直接翻转到p_1
            public void actionPerformed(ActionEvent e) {
                card.show(pane, "p1");
                card2.show(pane2, "p2_1");
            }
        });
        b_2.addActionListener(new ActionListener() { // 直接翻转到p_2
            public void actionPerformed(ActionEvent e) {
                card.show(pane, "p2");
                card2.show(pane2, "p2_2");
            }
        });
        b_3.addActionListener(new ActionListener() { // 直接翻转到p_3
            public void actionPerformed(ActionEvent e) {
                card.show(pane, "p3");
                card2.show(pane2, "p2_3");
            }
        });


        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        this.getContentPane().add(p,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        this.getContentPane().add(pane2,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.gridheight = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.getContentPane().add(pane,gbc);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1800, 1000);
        this.setVisible(true);
    }

    public static JTable buildTable(Vector CellsVector,Vector TitleVector) {
        JTable table = new JTable(CellsVector,TitleVector);
        table.setShowGrid(true);
        table.setGridColor(Color.BLUE);
        table.setRowHeight(80);
        table.setFont(new Font("", Font.PLAIN, 50));
        return table;
    }
}