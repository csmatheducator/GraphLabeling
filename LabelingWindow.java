
/**
 * LabelingWindow. The graphic user interface (GUI) of the project
 *
 * @author Hong Biao Zeng
 * @version Dec 24, 2021
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class LabelingWindow extends JFrame implements ActionListener
{
    Graph<Integer> g = null;
    GraphLabeling gl = null;

    String[] choices = { "Fibonacci", "Graceful", "L21", "Prime"};
    final JComboBox<String> cb = new JComboBox<String>(choices);
    JButton fileChoose, getLabeling;
    JTextField fileText;
    JTextArea resultTextArea;

    public LabelingWindow(){
        super("Graph Labeling Window");

        // choose a criteria
        JLabel labeling = new JLabel("Choose a criteria");
        JPanel labelPanel = new JPanel();
        labelPanel.add(labeling);
        labelPanel.add(cb);

        // choose a file for graph
        JLabel fileLabel = new JLabel("Chose an input file for graph");
        fileChoose = new JButton("Browse Computer");
        fileText = new JTextField(20);
        JPanel filePanel = new JPanel();
        filePanel.add(fileText);
        filePanel.add(fileChoose);
        fileChoose.addActionListener(this);

        JPanel upper = new JPanel();
        upper.setLayout(new GridLayout(3, 1));
        upper.add(labelPanel);
        upper.add(fileLabel);
        upper.add(filePanel);

        // display result
        resultTextArea = new JTextArea(13, 36);
        resultTextArea.setText("Result display here");
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultTextArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel textPanel = new JPanel();
        textPanel.add(scrollPane);

        // get labeling
        getLabeling = new JButton("Get Labeling");
        getLabeling.addActionListener(this);
        JPanel bottom = new JPanel();
        bottom.add(getLabeling);

        // add component
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(upper, BorderLayout.NORTH);
        c.add(textPanel, BorderLayout.CENTER);
        c.add(bottom, BorderLayout.SOUTH);
        // set up size and other property
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if(cmd.equals("Browse Computer")){
            chooseFile();
        }
        else if(cmd.equals("Get Labeling")){
            getLabeling();
        }
    }

    private void chooseFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        // if user clicedk Cancel button on dialog, return
        if(result == JFileChooser.CANCEL_OPTION)
            return;

        File file = fileChooser.getSelectedFile(); // get selected file

        String fileName = file.getAbsolutePath();
        fileText.setText(fileName);
    }

    private void getLabeling(){
        try{
            g = Graph.createGraphFromFile(fileText.getText());
            String s = "Check the graph labeling using " 
                + (String) cb.getSelectedItem()
                + " criteria. The graph is: \n" + g.toString();
            String lr = getLabelingResult(g, (String) cb.getSelectedItem());
            resultTextArea.setText(s + lr);   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid File Name", "Invalid File Name", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getLabelingResult(Graph<Integer> g, String criteriaName){
        int[] labels;
        GraphLabeling gl;
        String result = "";
        if(criteriaName.equals("Fibonacci")){
            labels = LabelingTools.fibonaciis(g.getNumOfVertices());
            gl = new GraphLabeling(g, labels, new FibonacciLabelingCriteria());
            result = gl.getOneLabeling();
        }
        else if(criteriaName.equals("Graceful")){
            String lamda = JOptionPane.showInputDialog(null, "Enter your lamda: ");
            labels = new int[Integer.parseInt(lamda) + 1];
            LabelingTools.initializeArrayStarts0(labels);
            gl = new GraphLabeling(g, labels, new GracefulLabelingCriteria());
            result = gl.getOneLabeling();
        }
        else if(criteriaName.equals("L21")){
            String lamda = JOptionPane.showInputDialog(null, "Enter your lamda: ");
            labels = new int[Integer.parseInt(lamda) + 1];
            LabelingTools.initializeArrayStarts0(labels);
            gl = new GraphLabeling(g, labels, new L21LabelingCriteria());
            result = gl.getOneLabeling();
        }
        else if(criteriaName.equals("Prime")){
            labels = new int[g.getNumOfVertices()];
            LabelingTools.initializeArray(labels); 
            gl = new GraphLabeling(g, labels, new PrimeLabelingCriteria());
            result = gl.getOneLabeling();
        }
        return result;
    }

    public static void main(String[] args){
        LabelingWindow window = new LabelingWindow();
    }
}
