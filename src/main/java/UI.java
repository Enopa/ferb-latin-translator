
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.CharArrayReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


class UI extends Frame implements ActionListener
{
    Panel controls, picture;
    JTextArea input, output;


    public UI()
    {
        this.setTitle("Ferb-Latin-inator");
        this.setSize(850, 600);
        this.setLayout(new GridBagLayout());



        //Defining the panel layout
        GridBagConstraints c = new GridBagConstraints();


        c.weightx = 1;
        c.weighty = 1;


        input = new JTextArea();
        input.setBackground(new Color(255, 151, 145));
        input.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        input.setText("Enter text to be translated here");
        c.fill = GridBagConstraints.BOTH;
        input.setLineWrap(true);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 240;
        JScrollPane inputPane = new JScrollPane(input);
        this.add(inputPane, c);

        output = new JTextArea();
        output.setBackground(new Color(99, 255, 107));
        output.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        output.setText("Translated text appears here! ave-Herb un-ferb!");
        output.setLineWrap(true);
        output.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        JScrollPane outputPane = new JScrollPane(output);
        this.add(outputPane, c);

        controls = new Panel();
        controls.setLayout(new GridLayout(1, 3));

        Button clear = new Button("Clear");
        clear.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        clear.setBackground(new Color(255, 153, 204));
        clear.addActionListener(this);

        Button translate = new Button("Translate");
        translate.addActionListener(this);
        translate.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        translate.setBackground(new Color(138, 144, 255));


        Button source = new Button("Source");
        source.addActionListener(this);
        source.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        source.setBackground(new Color(135, 135, 135));

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 50;
        c.gridwidth = 2;
        controls.add(clear);
        controls.add(translate);
        controls.add(source);
        this.add(controls, c);

        this.setVisible(true);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }
        });
    }


    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {
            case ("Clear"):
                input.setText("");
                output.setText("");
                break;
            case ("Translate"):
                output.setText(textParser(input.getText()));
                break;
            case("Source"):
                openWebpage(URI.create("https://www.youtube.com/watch?v=CkUe13zOPsQ"));
                break;
            default:
                break;
        }
    }

    private String textParser(String text)
    {
        String word = "";
        String paragraph = "";
        text += " ";
        for (int i = 0; i < text.length(); i++)
        {
            if ((int)text.charAt(i) == 32)
            {
                if (word.length() <= 2)
                {
                    paragraph += word + " ";
                    word = "";

                } else
                {
                    paragraph += ferbInator(word) + " ";
                    word = "";
                }

            } else
            {
                word += text.charAt(i);
            }


        }
        return paragraph;
    }

    private String ferbInator(String word)
    {
        Character first = word.charAt(0);
        Character last = word.charAt(word.length() - 1);
        String toAdd = "-" + first + "erb";
        Character[] punctuation = {'.', ',', '!', ':', ';', ')'};

        for (int i = 0; i < punctuation.length; i++)
        {

            if (last.equals(punctuation[i]))
            {
                toAdd = "-" + first + "erb" + last;
                word = word.substring(0, word.length() - 1);
            }
        }

        if ((((int)last) >= 48) && (((int)last) <= 57))
        {
            toAdd = "";
            word = word;
        } else
        {
            word = word.substring(1, word.length());
        }

        return (word + toAdd);

    }

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}