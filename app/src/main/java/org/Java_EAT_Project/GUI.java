package Java_EAT_Project;

import javax.swing.*;
import java.awt.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 主页面类
class HomePage extends JPanel {
    infoStore info = new infoStore();
    public HomePage(CardLayout cardLayout, JPanel cardPanel) {
        JButton button1 = new JButton("咖啡廳");
        JButton button2 = new JButton("餐廳");
        JButton button3 = new JButton("都可以");
        
        // 添加按钮点击事件监听器，切换到不同的页面
        button1.addActionListener(e -> {
            cardLayout.show(cardPanel, "coffee");
            info.choose = 1;
        });
        button2.addActionListener(e -> {
            cardLayout.show(cardPanel, "restaurant");
            info.choose = 2;
        });
        button3.addActionListener(e -> {
            cardLayout.show(cardPanel, "both");
            info.choose = 3;
        });
        
        // 将按钮添加到主页面板
        add(button1);
        add(button2);
        add(button3);
    }
}

// 页面1类
class Coffee extends JPanel {
    public Coffee() {
        add(new JLabel("這是咖啡廳"));
    }
}

// 页面2类
class Restaurant extends JPanel {
    public Restaurant() {
        add(new JLabel("這是餐廳"));
    }
}

// 页面3类
class Both extends JPanel {
    public Both() {
        add(new JLabel("這是都可以"));
    }
}

public class GUI {
    public static void main(String[] args) {
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
        
        // 创建主页面
        HomePage homePage = new HomePage((CardLayout) cardPanel.getLayout(), cardPanel);
        
        // 创建页面1、2、3
        Coffee coffee = new Coffee();
        Restaurant restaurant = new Restaurant();
        Both both = new Both();
        
        // 将页面添加到卡片布局容器中
        cardPanel.add(homePage, "home");
        cardPanel.add(coffee, "coffee");
        cardPanel.add(restaurant, "restaurant");
        cardPanel.add(both, "both");
        
        // 默认显示主页面
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, "home");
        
        // 添加卡片布局容器到框架
        frame.add(cardPanel);
        
        // 显示框架
        frame.setVisible(true);
    }
}

class infoStore{
    public static int choose = 0;//1 Coffee 2 Restaurant 3 Both


}