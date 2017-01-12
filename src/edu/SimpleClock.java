/*
 * Copyright (c) 2017. Aleksey Eremin
 * 12.01.17 13:17
 *
 */

package edu;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ae on 12.01.2017.
 * Простая многопоточность на примере часов
 * by novel  http://java-course.ru/begin/multithread_01/
 */

public class SimpleClock extends JFrame
{
  private JLabel clockLabel = new JLabel();
  
  // страт программы
  public static void main(String[] args)
  {
    SimpleClock cl = new SimpleClock();
    cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    cl.setVisible(true);
  }
  
  // конструктор объекта
  public SimpleClock()
  {
    // установить заголовок
    setTitle("Clock Thread");
    
    // выровнять метку по горизонтали
    clockLabel.setHorizontalAlignment((SwingConstants.CENTER));
    
    // установить размер шрифта для метки
    // Установить размер шрифта для метки  - есть такой метод у Label
    // Для эт ого создаем шрифт и сразу его отдаем методу setFont
    Font f = new Font("Default", Font.BOLD + Font.ITALIC, 24);
    clockLabel.setFont(f);
    
    // добавить строку на основную панель
    getContentPane().add(clockLabel);
    
    // установить размер окна
    setBounds(400, 300, 300, 100);
    
    // ВНИМАНИЕ!!!
    // создаем отдельный поток, который будет обновлять значение строки
    MyThread tr = new MyThread(this);
    //tr.setClock(this);
    
    tr.start();
  }
  
  public void setTime()
  {
    // создадим объект для форматирования даты
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    // установим новое значение для строки
    clockLabel.setText(df.format(Calendar.getInstance().getTime()));
  }
  
} // end class  SimpleClock

class MyThread extends Thread
{
  private SimpleClock clock;
  
  public MyThread()
  {
    
  }
  
  public MyThread(SimpleClock clock)
  {
    this.clock = clock;
  }
  
  public void setClock(SimpleClock clock)
  {
    this.clock =  clock;
  }
  
  @Override
  public void run() {
    // бесконечный цикл
    while(true) {
      clock.setTime();
      try {
        Thread.sleep(500);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
}// end class  MyThread



