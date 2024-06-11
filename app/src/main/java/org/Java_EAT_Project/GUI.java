package Java_EAT_Project;

import javax.swing.*;
import java.awt.*;

import java.util.concurrent.CountDownLatch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
        String[] district = {"東區", "中西區", "北區"};
        for (String s : district) {
            JCheckBox checkBox = new JCheckBox(s);
            questionPanel_1.add(checkBox);
        }
        add(questionPanel_1);

        JPanel questionPanel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if(info.choose == 2 || info.choose == 3){
            questionPanel_2.add(new JLabel("餐廳類型:"));
            String[] cuisineType = {"中式", "美式", "韓式", "日式", "泰式"};
            for (String s : cuisineType) {
                JCheckBox checkBox = new JCheckBox(s);
                questionPanel_2.add(checkBox);
            }
            add(questionPanel_2);
        }

        JPanel questionPanel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionPanel_3.add(new JLabel("價格區間:"));
        String[] priceRange = {"100~200", "200~400", "400~600", "600以上"};
        for (String s : priceRange) {
            JCheckBox checkBox = new JCheckBox(s);
            questionPanel_3.add(checkBox);
        }
        add(questionPanel_3);

        JPanel questionPanel_4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionPanel_4.add(new JLabel("營業時間:"));
        String[] openingHours = {"即時", "不限"};
        for (String s : openingHours) {
            JCheckBox checkBox = new JCheckBox(s);
            questionPanel_4.add(checkBox);
        }
        add(questionPanel_4);

        // 创建确认按钮
        JButton confirmButton = new JButton("確認");

        // 添加确认按钮点击事件监听器
        confirmButton.addActionListener(e -> {
            Component[] comp_district = questionPanel_1.getComponents();
            for (Component component : comp_district) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    boolean isSelected = checkBox.isSelected();
                    info.district.add(isSelected);
                }
            }
            if(info.choose == 2 || info.choose == 3){
                Component[] comp_cuisineType = questionPanel_2.getComponents();
                for (Component component : comp_cuisineType) {
                    if (component instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) component;
                        boolean isSelected = checkBox.isSelected();
                        info.cuisineType.add(isSelected);
                    }
                }
            }
            Component[] comp_priceRange = questionPanel_3.getComponents();
            for (Component component : comp_priceRange) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    boolean isSelected = checkBox.isSelected();
                    info.priceRange.add(isSelected);
                }
            }
            Component[] comp_openingHours = questionPanel_4.getComponents();
            for (Component component : comp_openingHours) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    boolean isSelected = checkBox.isSelected();
                    info.openingHours.add(isSelected);
                }
            }

            function.setEnd(1);
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
    }
}

class infoStore{
    public static int choose = 0;//1 Coffee 2 Restaurant 3 Both
    public static ArrayList<Boolean> district = new ArrayList<>();
    public static ArrayList<Boolean> cuisineType = new ArrayList<>();
    public static ArrayList<Boolean> priceRange = new ArrayList<>();
    public static ArrayList<Boolean> openingHours = new ArrayList<>();
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
        return new infoStore();
    }
    public void setEnd(int end) {
        GUI_function.end = end;
        if (end == 1) {
            latch.countDown();
        }
    }
    public void second_part(ArrayList<Integer> order, Map<Integer, ArrayList<String>> restaurantMap){
        DataShowPage dataShowPage = new DataShowPage(order, restaurantMap);//,(CardLayout) cardPanel.getLayout(), cardPanel
        cardPanel.add(dataShowPage, "dataShowPage");
        cardLayout.show(cardPanel, "dataShowPage");    
    }
}

class DataShowPage extends JPanel {
    GUI_function function = new GUI_function();
    int frameWidth = function.frameWidth;
    int frameHeight = function.frameHeight;
    public DataShowPage(ArrayList<Integer> order, Map<Integer, ArrayList<String>> restaurantMap) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("以下是為您推薦的餐廳／咖啡廳！");
        label.setFont(new Font("SimSun", Font.PLAIN, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(label, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // 將資料加入到內容面板中
        for (Integer num : order) {
            ArrayList<String> details = restaurantMap.get(num);
            JPanel detailPanel = createDetailPanel(details);
            contentPanel.add(detailPanel);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 增加每個項目之間的間距
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel createDetailPanel(ArrayList<String> details) {
        JPanel panel = new JPanel(new GridLayout(1, 2)); // 左右排列
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 添加边框
        panel.setBackground(Color.LIGHT_GRAY); // 设置背景色
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 整体面板边距

        JPanel infoPanel = new JPanel(new GridLayout(3, 1)); // 左侧面板
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 90, 10, 10)); // 設置邊距

        // 添加左侧面板的内容
        infoPanel.add(new JLabel(details.get(0))).setFont(new Font("SimSun", Font.BOLD + Font.ITALIC, 16));;
        infoPanel.add(new JLabel("評分： " + details.get(1)));
        infoPanel.add(new JLabel("地址： " + details.get(2)));

        JPanel hoursPanel = new JPanel(new BorderLayout()); // 右侧面板
        hoursPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 150)); // 設置邊距

        // 添加右侧面板的内容
        String OpenHours = " ｜\t\n ｜\t\n營\t\n業\t\n時\t\n間\t\n ｜\t\n ｜\t";
        JLabel hoursTextArea1 = new JLabel("<html><b>" + OpenHours.replaceAll("\n", "<br>") + "</b></html>"); // 设置为粗体
        hoursPanel.add(hoursTextArea1, BorderLayout.WEST);
        

        JLabel hoursTextArea2 = new JLabel("<html>" + details.get(3).replaceAll("\n", "<br>") + "</html>"); // 將換行符號轉換為HTML的換行標籤
        hoursPanel.add(hoursTextArea2, BorderLayout.EAST);

        // 將左右两个面板添加到主要面板中
        panel.add(infoPanel);
        panel.add(hoursPanel);

        return panel;
    }
}