package com.word.tools;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
 
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
 
public class Simicalcu {
 
    public static void main(final java.lang.String[] args) {
        java.awt.EventQueue.invokeLater(new java.lang.Runnable(){//awt是单线程模式的，所有awt的组件只能在推荐方式的事件处理线程中访问，从而保证组件状态的正确性
                public void run(){
                    final JFrame frame = new JFrame("字符串相似度计算");//声明JFrame
                    final JLabel tag = new JLabel("提示：请点击选择文件按钮选择待比较文件或者直接在文本框中输入文件。");//提示Label
                   //文件一
                    final JButton load = new JButton("选择文件一:");//选择文件Button
                    final JLabel filename = new JLabel("");//文件路径
                    final JTextArea textarea = new JTextArea(6, 20);//文本框
                    textarea.setLineWrap(true);//设置为自动换行
                    textarea.setWrapStyleWord(true);//超长行在边距处自动换行
                    final JScrollPane scroller = new JScrollPane(textarea);//滚动条效果
                    //加载文件一的事件监听
                    load.addActionListener(new ActionListener(){
                            private JFileChooser filechooser = null;
                            private DefaultEditorKit kit = new DefaultEditorKit();
                            public void actionPerformed(ActionEvent e){
                                if (filechooser == null) {
                                	//设置默认文件选择路径为桌面路径
                                    filechooser = new JFileChooser(System.getProperty("user.home"));
                                }
                                //过滤文件类型，允许 打开txt文件和doc文档
                                filechooser.setFileFilter(new FileNameExtensionFilter("text file","txt","text","doc","docs"));
                                if (filechooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                                	//显示文件路径
                                    filename.setText(filechooser.getSelectedFile().getAbsolutePath());
                                    FileReader reader = null;
                                    //将文件内容读取到textarea里面&异常处理
                                    try {
                                        reader = new FileReader(filechooser.getSelectedFile());
                                        textarea.setText("");
                                        kit.read(reader,textarea.getDocument(),0);
                                    } catch (Exception xe) {
                                        System.err.println(xe.getMessage());
                                    } finally {
                                        if (reader != null) {
                                            try {
                                                reader.close();
                                            } catch (IOException ioe) {
                                                System.err.println(ioe.getMessage());
                                            }
                                        }
                                    }
                                    textarea.setCaretPosition(0);//鼠标焦点
                                }
                                return;
                            }
                        });
                    
                    //文件二
                    final JButton load2 = new JButton("选择文件二:");
                    final JLabel filename2 = new JLabel("");
                    final JTextArea textarea2 = new JTextArea(6, 20);
                    textarea2.setLineWrap(true);
                    textarea2.setWrapStyleWord(true);
                    final JScrollPane scroller2 = new JScrollPane(textarea2);
                    //加载文件二的事件监听
                    load2.addActionListener(new ActionListener(){
                            private JFileChooser filechooser2 = null;
                            private DefaultEditorKit kit2 = new DefaultEditorKit();
                            public void actionPerformed(ActionEvent e){
                                if (filechooser2 == null) {
                                    filechooser2 = new JFileChooser(System.getProperty("user.home"));
                                }
                                filechooser2.setFileFilter(new FileNameExtensionFilter("text file","txt","text","doc","docs"));
                                if (filechooser2.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                                    filename2.setText(filechooser2.getSelectedFile().getAbsolutePath());
                                    FileReader reader2 = null;
                                    try {
                                        reader2 = new FileReader(filechooser2.getSelectedFile());
                                        textarea2.setText("");
                                        kit2.read(reader2,textarea2.getDocument(),0);
                                    } catch (Exception xe2) {
                                        System.err.println(xe2.getMessage());
                                    } finally {
                                        if (reader2 != null) {
                                            try {
                                                reader2.close();
                                            } catch (IOException ioe2) {
                                                System.err.println(ioe2.getMessage());
                                            }
                                        }
                                    }
                                    textarea2.setCaretPosition(0);
                                }
                                return;
                            }
                        });
                    
                    //显示相似内容的textarea
                    final JTextArea textarea_res = new JTextArea(6, 20);
                    textarea_res.setLineWrap(true);
                    textarea_res.setWrapStyleWord(true);
                    final JScrollPane scroller_res = new JScrollPane(textarea_res);
                   //设置textarea_res透明
                    textarea_res.setOpaque(false);
                    scroller_res.setOpaque(false);
                    scroller_res.getViewport().setOpaque(false);
                    
                    //textarea和textarea2内容改变事件,删除文件路径和相似内容
                    textarea.addKeyListener(new KeyAdapter(){
                    	public void keyPressed(KeyEvent e) {
                    		filename.setText("");
                    		textarea_res.setText("");
                    		 }
                    });
                    textarea2.addKeyListener(new KeyAdapter(){
                    	public void keyPressed(KeyEvent e) {
                    		filename2.setText("");
                    		textarea_res.setText("");
                    		 }
                    });
                    
                    //计算，退出按钮
                    final JButton start = new JButton("开始计算");
                    //开始计算相似度事件
                    start.addActionListener(new ActionListener(){
                    	public void actionPerformed(ActionEvent e) {
                    		 String temp_strA = textarea.getText();
                    	     String temp_strB = textarea2.getText();
                    	     String strA,strB;
                    	     //如果两个textarea都不为空且都不全为符号，则进行相似度计算，否则提示用户进行输入数据或选择文件
                    	     if(!(Computeclass.removeSign(temp_strA).length() == 0 && Computeclass.removeSign(temp_strB).length() == 0)){
                    	    	 if(temp_strA.length() >= temp_strB.length())
                    	    	 {
                    	    		 strA = temp_strA;
                    	    		 strB = temp_strB;
                    	    	 }else{
                    	    		 strA = temp_strB;
                    	    		 strB = temp_strA;
                    	    	 }
                    	    	 double result = Computeclass.SimilarDegree(strA, strB); 
                    	    	 //显示相似内容于textarea_res
                    	    	 textarea_res.setText("相似的内容为："+Computeclass.longestCommonSubstring(strA, strB));
                    	    	 //结果
                         		JOptionPane.showMessageDialog(null, "    相似度为：" + Computeclass.similarityResult(result), "计 算 结 果", JOptionPane.PLAIN_MESSAGE);
                    	     }else{
                    	    	 JOptionPane.showMessageDialog(null, "     您好，请输入正确内容！ ", "提    示", JOptionPane.ERROR_MESSAGE);
                    	     }
	                    }
                    });
                    final JButton cancle = new JButton("退        出");
                    //退出事件
                    cancle.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
	                    		frame.dispose();//释放窗体所占资源
		                    	System.exit(0);//退出程序
	                        }
	                    });
                    
                    
                    //总布局
                    //文件一north
                    final Box north = Box.createVerticalBox();//竖排列
                    north.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));//边距
                    north.add(tag);
                    north.add(Box.createVerticalStrut(10));
                    north.add(load);
                    north.add(Box.createVerticalStrut(5));
                    north.add(filename);
                    north.add(scroller);
                    frame.add(north,BorderLayout.NORTH);
                    //文件二center
                    final Box center = Box.createVerticalBox();
                    center.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    center.add(load2);
                    center.add(Box.createVerticalStrut(5));
                    center.add(filename2);
                    center.add(scroller2);
                    center.add(scroller_res);
                    frame.add(center,BorderLayout.CENTER);
                    //south
                    final Box south = Box.createHorizontalBox();
                    south.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                    south.add(Box.createHorizontalGlue());//按钮居中显示
                    south.add(start);
                    south.add(Box.createHorizontalStrut(20));//水平间距
                    south.add(cancle);
                    south.add(Box.createVerticalStrut(5));
                    frame.add(south,BorderLayout.SOUTH);
                    frame.pack();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗体默认退出形式
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    
                }
            });
    }
}

