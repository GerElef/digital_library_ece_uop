package com.gerelef.gui.library;

import com.gerelef.books.Book;
import com.gerelef.model.IOLibManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Inflator implements Runnable {
    IOLibManager libManager = IOLibManager.getInstance();
    JPanel guipanel;

    Inflator(JPanel panel){
        this.guipanel = panel;
    }

    @Override
    public void run() {
        ArrayList<Book> books = libManager.getAllBooks();

        for (Book b : books) {
            JPanel container = new JPanel();
            JPanel container1 = new JPanel();
            JPanel container2 = new JPanel();
            JPanel container3 = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
            container1.setLayout(new BoxLayout(container1, BoxLayout.LINE_AXIS));
            container2.setLayout(new BoxLayout(container2, BoxLayout.LINE_AXIS));
            container3.setLayout(new BoxLayout(container3, BoxLayout.LINE_AXIS));

            container.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JTextPane type = new JTextPane();
            JTextPane title = new JTextPane();
            JTextPane writer = new JTextPane();
            JTextPane bookType = new JTextPane();
            JTextPane ISBN = new JTextPane();
            JTextPane date = new JTextPane();
            type.setText(b.getType());
            title.setText(b.getTitle());
            writer.setText(b.getWriter());
            bookType.setText(b.getBookType());
            ISBN.setText(b.getISBN() + "");
            date.setText(b.getDate() + "");

            container1.add(type);
            container1.add(title);
            container1.add(Box.createRigidArea(new Dimension(32, 32)));
            container2.add(writer);
            container2.add(bookType);
            container2.add(new ImagePanel(b, guipanel));
            container3.add(ISBN);
            container3.add(date);
            container3.add(Box.createRigidArea(new Dimension(32, 32)));

            container.add(container1);
            container.add(container2);
            container.add(container3);
            guipanel.add(container);
        }
        guipanel.revalidate();
    }

    class ImagePanel extends JPanel {

        IOLibManager libManager = IOLibManager.getInstance();
        BufferedImage image;
        Book b;

        ImagePanel(Book b, Component parent){
            try {
                image = ImageIO.read(new File(".\\icons\\delete_book.png"));
            }catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            this.b = b;

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println();
                    int res = JOptionPane.showConfirmDialog(parent, "Are you sure you want to delete this file?");
                    switch (res){
                        case 0:
                            libManager.removeBook(b);
                            break;
                        case 1:
                        case 2:
                        default:
                            break;
                    }


                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0,0, this);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(32,32);
        }

        Book getBook(){
            return b;
        }
    }
}
