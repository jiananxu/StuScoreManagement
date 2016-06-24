package com.java.gui;

import com.java.service.UserService;
import com.java.user.User;
import com.java.userdao.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Joe on 2016/6/22.
 */
public class MainWindow  extends JFrame{

        private static final long serialVersionUID = -3728301915417827744L;

        UserDAO userDAO;
        private JPanel pForm, pTable;
        private JLabel lid, lnamef, lscore1, lscore2;
        private JTextField tid, tname, tscore1, tscore2;
        private JButton bCreate, bDelete, bRead, bUpdate,bexcel;
        private JScrollPane spTable;
        private JTable tTable;

        public MainWindow() throws Exception {
            userDAO = new UserDAO();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            createForm();
            pack();
            refreshTable();
        }

        public void createForm() {
            pForm = new JPanel();
            pForm.setLayout(new GridLayout(5, 3, 10, 5));

            getContentPane().setLayout(
                    new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

            pForm.setLayout(new java.awt.GridLayout(5, 3, 10, 5));

            lid = new JLabel("学号: ");
            lid.setHorizontalAlignment(SwingConstants.RIGHT);
            tid = new JTextField();
            pForm.add(lid);
            pForm.add(tid);

            bCreate = new JButton("创建");
            bCreate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        create();
                    } catch (SQLException e) {
                        System.out.println("添加错误");
                    }
                }
            });
            pForm.add(bCreate);

            lnamef = new JLabel("姓名: ");
            lnamef.setHorizontalAlignment(SwingConstants.RIGHT);
            tname = new JTextField();
            pForm.add(lnamef);
            pForm.add(tname);

            bRead = new JButton("查询");
            bRead.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        read();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("查询错误");
                    }
                }
            });
            pForm.add(bRead);

            lscore1 = new JLabel("成绩1: ");
            lscore1.setHorizontalAlignment(SwingConstants.RIGHT);
            tscore1 = new JTextField();
            pForm.add(lscore1);
            pForm.add(tscore1);

            bUpdate = new JButton("更新");
            bUpdate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        update();
                    } catch (SQLException e) {
                        System.out.println("更新失败");
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            pForm.add(bUpdate);

            lscore2 = new JLabel("成绩2: ");
            lscore2.setHorizontalAlignment(SwingConstants.RIGHT);
            tscore2 = new JTextField();
            pForm.add(lscore2);
            pForm.add(tscore2);

            bDelete = new JButton("删除");
            bDelete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            pForm.add(bDelete);

            getContentPane().add(pForm);

            bexcel=new JButton("导出excel");
            bexcel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        bexcel();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            pForm.add(bexcel);
            getContentPane().add(pForm);

            pTable = new JPanel();
            tTable = new JTable();
            pTable.setLayout(new BorderLayout());
            tTable.setModel(new DefaultTableModel(new Object[4][4], new String[] {
                    "学号", "姓名", "成绩1", "成绩2" }));
            spTable = new JScrollPane();
            spTable.setViewportView(tTable);
            pTable.add(spTable, BorderLayout.CENTER);
            tTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        read();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

            getContentPane().add(pTable);
        }

        public void cleanFields() {
            tid.setText("");
            tname.setText("");
            tscore1.setText("");
            tscore2.setText("");
        }

        public boolean isValidData() {
            if (tid.getText()==null || tname.getText()==null
                    || tscore1.getText()==null || tscore2.getText()==null)
                return false;
            else
                return true;
        }

        public void refreshTable() throws Exception {
            DefaultTableModel tableModel = (DefaultTableModel) tTable.getModel();
            tableModel.setNumRows(0);

            java.util.List<User> usuarios = userDAO.findAll();

            for (int linha = 0; linha < usuarios.size(); linha++) {
                User user = usuarios.get(linha);

                tableModel.addRow(new Object[] { 1 });

                tTable.setValueAt(user.getId(), linha, 0);
                tTable.setValueAt(user.getName(), linha, 1);
                tTable.setValueAt(user.getScore1(), linha, 2);
                tTable.setValueAt(user.getScore2(), linha, 3);

            }
        }

        public int getSelectedId() {
            int linhaSelecionada = tTable.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int idSelecionado = (Integer) tTable.getValueAt(linhaSelecionada, 0);
                return idSelecionado;
            } else {
                System.err.println("错误!");
                return -1;
            }
        }

        public void create() throws SQLException {
            User user = new User(Integer.valueOf(tid.getText()), tname.getText(),
                    Integer.parseInt(tscore1.getText()),Integer.parseInt( tscore2.getText()));

            if (isValidData()) {
                userDAO.insert(user);
                try {
                    refreshTable();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("刷新表格错误");
                }
                JOptionPane.showMessageDialog(null, "" + tid.getText()
                        + " 创建成功");
                cleanFields();
            } else {
                JOptionPane
                        .showMessageDialog(null,"错误!");
            }

        }

        public void read() throws Exception {
            if (tid.getText()!=null) {
                User user = userDAO.findByID(Integer.parseInt(tid.getText()));
               // cleanFields();
                tid.setText(tid.getText());
                tname.setText(user.getName());
                tscore1.setText(String.valueOf(user.getScore1()));
                tscore2.setText(String.valueOf(user.getScore2()));
                refreshTable();
            }
            }


        public void update() throws Exception {

            User user=new User(Integer.parseInt(tid.getText()),tname.getText(),
           Integer.parseInt(tscore1.getText()),Integer.parseInt(tscore2.getText()) );
            userDAO.update(user);
            refreshTable();

        }

        public void delete() throws Exception {
            userDAO.delete(Integer.parseInt(tid.getText()));
            refreshTable();
        }
    public void bexcel() throws SQLException, ClassNotFoundException {
        UserService userService=new UserService();

        try {
            userService.getStudent();
        } catch (Exception e) {
            System.out.println("导出错误");
        }
    }

    }
