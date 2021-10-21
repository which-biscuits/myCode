<%--
  Created by IntelliJ IDEA.
  User: 11412
  Date: 2021/4/2
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="image/jpeg" language="java" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.awt.geom.*" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="javafx.scene.shape.Arc" %>
<%
    int width = 320,height = 300;
    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    g.setColor(Color.lightGray);
    g.fillRect(0,0,width,height);
    Graphics2D g_2d = (Graphics2D) g;
    Ellipse2D ellipse = new Ellipse2D.Double(70,90,120,60);
    g_2d.setColor(Color.BLUE);
    AffineTransform trans = new AffineTransform();
    for (int i = 0; i <= 24; i++) {
        trans.rotate(15.0*Math.PI / 180,1160,130);
        g_2d.setTransform(trans);
        g_2d.draw(ellipse);
    }
    g_2d.setColor(Color.red);
    Arc2D arc = new Arc2D.Double(200,220,100,40,0,270,Arc2D.PIE);
    g_2d.fill(arc);
    arc.setArc(5,5,100,40,0,-270,Arc2D.PIE);
    g_2d.fill(arc);
    g_2d.setColor(Color.black);
    g_2d.setFont(new Font("",Font.BOLD,18));
    g_2d.drawString("王子潇,Graphic Draer",10,280);
    g_2d.dispose();
    OutputStream outClient = response.getOutputStream();
    boolean boo = ImageIO.write(image,"jpeg",outClient);
%>