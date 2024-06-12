package org.Java_EAT_Project;

import javax.swing.*;
import java.awt.*;

import java.util.concurrent.CountDownLatch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


class HomePage extends JPanel {
    infoStore info = new infoStore();
    public HomePage(CardLayout cardLayout, JPanel cardPanel,int frameWidth, int frameHeight) {
        setLayout(null);

        JLabel label = new JLabel("今天想吃什～麼！");
        label.setFont(new Font("SimSun", Font.PLAIN, 24));
        FontMetrics metrics = label.getFontMetrics(label.getFont());
        int textWidth = metrics.stringWidth(label.getText());
        int textHeight = metrics.getHeight();
        label.setBounds((frameWidth-textWidth)/2, (frameHeight-textHeight)/10, textWidth, textHeight);
        add(label);

        int buttonWidth = (frameWidth-60)/3;
        int buttonHeight = frameHeight/7*4;
        JButton button1 = new JButton("咖啡廳");
        button1.setBounds(15,(frameHeight-buttonHeight)/3*2,buttonWidth,buttonHeight);
        JButton button2 = new JButton("餐廳");
        button2.setBounds(30 + buttonWidth,(frameHeight-buttonHeight)/3*2,buttonWidth,buttonHeight);
        JButton button3 = new JButton("都可以");
        button3.setBounds(45 + buttonWidth*2,(frameHeight-buttonHeight)/3*2,buttonWidth,buttonHeight);
        
        // 添加按钮点击事件监听器，切换到不同的页面
        button1.addActionListener(e -> {
            info.choose = 1;
            QuestionPanel questionPanel = new QuestionPanel((CardLayout) cardPanel.getLayout(), cardPanel);
            cardPanel.add(questionPanel, "questionPanel");
            cardLayout.show(cardPanel, "questionPanel");       
        });
        button2.addActionListener(e -> {
            info.choose = 2;
            QuestionPanel questionPanel = new QuestionPanel((CardLayout) cardPanel.getLayout(), cardPanel);
            cardPanel.add(questionPanel, "questionPanel");
            cardLayout.show(cardPanel, "questionPanel");
        });
        button3.addActionListener(e -> {
            info.choose = 3;
            QuestionPanel questionPanel = new QuestionPanel((CardLayout) cardPanel.getLayout(), cardPanel);
            cardPanel.add(questionPanel, "questionPanel");
            cardLayout.show(cardPanel, "questionPanel");
        });
        
        // 将按钮添加到主页面板
        add(button1);
        add(button2);
        add(button3);
    }
}

class QuestionPanel extends JPanel {
    infoStore info = new infoStore();
    GUI_function function = new GUI_function();
    public QuestionPanel(CardLayout cardLayout, JPanel cardPanel) {
        

        // 添加標籤
        if(info.choose == 1){
            setLayout(new GridLayout(5, 1, 10, 10)); // 10 像素的垂直间隙
            add(new JLabel("咖啡廳選擇器！", JLabel.CENTER));
        }else if(info.choose == 2){
            setLayout(new GridLayout(6, 1, 10, 10)); // 10 像素的垂直间隙
            add(new JLabel("餐廳選擇器！", JLabel.CENTER));
        }else{
            setLayout(new GridLayout(6, 1, 10, 10)); // 10 像素的垂直间隙
            add(new JLabel("咖啡廳&餐廳 選擇器！", JLabel.CENTER));
        }

        // 创建并添加四个问题
        JPanel questionPanel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionPanel_1.add(new JLabel("地區:"));
        String[] area = {"東區", "中西區", "北區"};
        for (String s : area) {
            JCheckBox checkBox = new JCheckBox(s);
            questionPanel_1.add(checkBox);
        }
        add(questionPanel_1);

        JPanel questionPanel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if(info.choose == 2 || info.choose == 3){
            questionPanel_2.add(new JLabel("餐廳類型:"));
            String[] type = {"中式", "美式", "韓式", "日式", "泰式"};
            for (String s : type) {
                JCheckBox checkBox = new JCheckBox(s);
                questionPanel_2.add(checkBox);
            }
            add(questionPanel_2);
        }

        JPanel questionPanel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionPanel_3.add(new JLabel("價格區間:"));
        String[] money = {"100~200", "200~400", "400~600", "600以上"};
        for (String s : money) {
            JCheckBox checkBox = new JCheckBox(s);
            questionPanel_3.add(checkBox);
        }
        add(questionPanel_3);

        JPanel questionPanel_4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionPanel_4.add(new JLabel("營業時間:"));
        String[] time = {"即時", "不限"};
        for (String s : time) {
            JCheckBox checkBox = new JCheckBox(s);
            questionPanel_4.add(checkBox);
        }
        add(questionPanel_4);

        // 创建确认按钮
        JButton confirmButton = new JButton("確認");

        // 添加确认按钮点击事件监听器
        confirmButton.addActionListener(e -> {
            Component[] comp_area = questionPanel_1.getComponents();
            for (Component component : comp_area) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    boolean isSelected = checkBox.isSelected();
                    info.area.add(isSelected);
                }
            }
            if(info.choose == 2 || info.choose == 3){
                Component[] comp_type = questionPanel_2.getComponents();
                for (Component component : comp_type) {
                    if (component instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) component;
                        boolean isSelected = checkBox.isSelected();
                        info.type.add(isSelected);
                    }
                }
            }
            Component[] comp_money = questionPanel_3.getComponents();
            for (Component component : comp_money) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    boolean isSelected = checkBox.isSelected();
                    info.money.add(isSelected);
                }
            }
            Component[] comp_time = questionPanel_4.getComponents();
            for (Component component : comp_time) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    boolean isSelected = checkBox.isSelected();
                    info.time.add(isSelected);
                }
            }

            function.setEnd(1);
            System.out.println(function.end);
            Loading loading = new Loading();
            cardPanel.add(loading, "loading");
            cardLayout.show(cardPanel, "loading");
        });        

        // 添加确认按钮到咖啡廳面板
        add(confirmButton);
    }
}

class Loading extends JPanel {
    private int angle = 0; // 旋转角度
    private Color[] colors; // 圆点颜色
    private String loadingText = "Loading"; // 加载文本

    public Loading() {
        // 设置圆点颜色
        colors = new Color[10];
        for (int i = 0; i < colors.length; i++) {
            int colorValue = 255 - (i * 20); // 逐渐变浅
            colors[i] = new Color(colorValue, colorValue, colorValue);
        }

        // 使用定时器每隔一段时间触发一次重绘动作，实现旋转效果和文本轮播
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle += 1; // 每次增加1度
                updateLoadingText(); // 更新加载文本
                repaint(); // 重绘
            }
        });
        timer.start(); // 启动定时器
    }

    private void updateLoadingText() {
        // 轮播加载文本
        switch (loadingText) {
            case "Loading":
                loadingText = "Loading .";
                break;
            case "Loading .":
                loadingText = "Loading . .";
                break;
            case "Loading . .":
                loadingText = "Loading . . .";
                break;
            default:
                loadingText = "Loading";
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        // 将坐标原点移动到面板中心
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        g2d.translate(centerX, centerY);

        // 绘制圆点
        int radius = 30; // 圆点半径
        int angleStep = 360 / colors.length; // 圆点之间的角度间隔

        for (int i = 0; i < colors.length; i++) {
            // 计算圆点的位置
            double radians = Math.toRadians(angle + i * angleStep);
            int x = (int) (Math.cos(radians) * radius);
            int y = (int) (Math.sin(radians) * radius);

            // 绘制圆点
            g2d.setColor(colors[i]);
            g2d.fillOval(x - 5, y - 5, 10, 10); // 圆点大小为10

            // 交换位置，达到旋转效果
            if (i == colors.length - 1) {
                colors[i] = colors[0];
            } else {
                colors[i] = colors[i + 1];
            }
        }

        // 绘制加载文本
        g2d.setColor(Color.BLACK);
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(loadingText);
        g2d.drawString(loadingText, -textWidth / 2, 50); // 文本下移50像素

        g2d.dispose();
    }
}

public class GUI {
    public static GUI_function main() {
        // 外觀
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 設置frame的大小、標籤、關閉方式
        JFrame frame = new JFrame("台南吃吃吃");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension.width/2, dimension.height/2);
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();

        //設定視窗顯示在螢幕畫面中間位置
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        
        // 创建卡片布局容器
        JPanel cardPanel = new JPanel(new CardLayout());

        GUI_function function = new GUI_function(frame,(CardLayout) cardPanel.getLayout(), cardPanel, frameWidth, frameHeight);
        return function;
        //function.firstPart();
    }
    public infoStore getInfo(){
        infoStore info = new infoStore();
        return info;
    }
    
}

class infoStore{
    public static int choose = 0;//1 Coffee 2 Restaurant 3 Both
    public static ArrayList<Boolean> area = new ArrayList<>();
    public static ArrayList<Boolean> type = new ArrayList<>();
    public static ArrayList<Boolean> money = new ArrayList<>();
    public static ArrayList<Boolean> time = new ArrayList<>();
}

class GUI_function{
    JFrame frame;
    CardLayout cardLayout;
    JPanel cardPanel;
    int frameWidth; 
    int frameHeight;
    public static int end = 0;
    private static CountDownLatch latch = new CountDownLatch(1);
    public GUI_function(){

    }
    public GUI_function(JFrame frame, CardLayout cardLayout, JPanel cardPanel,int frameWidth, int frameHeight){
        this.frame = frame;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }
    public infoStore firstPart(){
        // 创建主页面
        HomePage homePage = new HomePage((CardLayout) this.cardPanel.getLayout(), this.cardPanel, this.frameWidth, this.frameHeight);
        
        // 将页面添加到卡片布局容器中
        this.cardPanel.add(homePage, "home");
        
        // 默认显示主页面
        CardLayout cl = (CardLayout) (this.cardPanel.getLayout());
        cl.show(this.cardPanel, "home");
        
        // 添加卡片布局容器到框架
        this.frame.add(this.cardPanel);
        
        // 显示框架
        this.frame.setVisible(true);

        
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("out");
        return new infoStore();
    }
    public void setEnd(int end) {
        GUI_function.end = end;
        if (end == 1) {
            latch.countDown();
            System.out.println("countdown");
        }
    }
}