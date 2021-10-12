import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Main extends Frame
{

    static JLabel pic;
    static BufferedImage picSource;
    static JPanel picture;

    public static void main(String[] args)
    {
        Frame f = new UI();
    }
}
