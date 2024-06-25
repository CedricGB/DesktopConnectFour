package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class ConnectFour extends JFrame {

    private boolean playerTurn = false;
    private ArrayList<ArrayList<JButton>> listOfJButton = new ArrayList<>();
    private int i = 0;

    private boolean gameFinish = false;
    private JPanel gridButtonPanel;
    public ConnectFour() {
        super("connect");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        JPanel borderPanel = new JPanel(new BorderLayout());
        gridButtonPanel = createBoard();


        setLocationRelativeTo(null);
        setTitle("Connect Four");

        // Add reset button

        JPanel gridButtonReset = new JPanel(new GridLayout(1,1));
        JButton resetButton = new JButton("RESET");
        resetButton.setName("ButtonReset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        gridButtonReset.add(resetButton);


        borderPanel.add(gridButtonPanel, BorderLayout.CENTER);
        borderPanel.add(gridButtonReset, BorderLayout.SOUTH);
        add(borderPanel);
        setVisible(true);

    }

    private boolean checkCollumn(ArrayList<ArrayList<JButton>> listOfJButton, int i, int j){

        if (j + 3 < 6) {
            if(isNextCaseSame(listOfJButton, i,j+1,  listOfJButton.get(i).get(j) )
            && isNextCaseSame(listOfJButton, i,j+2,  listOfJButton.get(i).get(j))
            && isNextCaseSame(listOfJButton, i,j+3,  listOfJButton.get(i).get(j))){
                listOfJButton.get(i).get(j).setBackground(Color.decode("#01E777"));
                listOfJButton.get(i).get(j + 1).setBackground(Color.decode("#01E777"));
                listOfJButton.get(i).get(j + 2).setBackground(Color.decode("#01E777"));
                listOfJButton.get(i).get(j + 3).setBackground(Color.decode("#01E777"));
                return true;
            }
        }
        return false;

    }

    private boolean checkLine(ArrayList<ArrayList<JButton>> listOfJButton, int i, int j){
        int counter = 1;
        ArrayList<JButton> winnerButton = new ArrayList<>();


        for(int h = i - 1; h >=0 ; h--){

            if(isNextCaseSame(listOfJButton, h, j, listOfJButton.get(i).get(j))){
                counter++;
                winnerButton.add(listOfJButton.get(h).get(j));
            } else break;
        }

        for(int h = i + 1; h < 7 ; h++){

            if(isNextCaseSame(listOfJButton, h, j, listOfJButton.get(i).get(j))){
                counter++;
                winnerButton.add(listOfJButton.get(h).get(j));
            } else break;
        }


        if(counter >= 4){
            winnerButton.add(listOfJButton.get(i).get(j));
            for(JButton button : winnerButton){
                button.setBackground(Color.decode("#01E777"));
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean majorDiagonal(ArrayList<ArrayList<JButton>> listOfJButton, int i, int j){

        int counter = 1;
        ArrayList<JButton> winnerButton = new ArrayList<>();

        int h = i + 1;
        int k = j + 1;

        for(; h <= 6 && k <= 5; ){

            if(isNextCaseSame(listOfJButton, h,k,  listOfJButton.get(i).get(j))) {

                counter++;
                System.out.println(counter);
                winnerButton.add(listOfJButton.get(h ).get(k));
            }
            k++;
            h++;
        }

        h = i - 1;
        k = j - 1;
        for(; h > 0 && k > 0; ){
            if(isNextCaseSame(listOfJButton, h,k,  listOfJButton.get(i).get(j))) {
                counter++;
                winnerButton.add(listOfJButton.get(h ).get(k));
            }
            k--;
            h--;
        }
        if(counter >= 4){

            winnerButton.add(listOfJButton.get(i).get(j));
            for(JButton button : winnerButton){
                button.setBackground(Color.decode("#01E777"));
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean minorDiagonal(ArrayList<ArrayList<JButton>> listOfJButton, int i, int j){

        int counter = 1;
        ArrayList<JButton> winnerButton = new ArrayList<>();
        int h = i + 1;
        int k = j - 1;
        for(; h < 5 && k > 0; ){
            System.out.println();
            if(isNextCaseSame(listOfJButton, h,k,  listOfJButton.get(i).get(j))) {
                counter++;
                winnerButton.add(listOfJButton.get(h ).get(k));
            }
            h++;
            k--;
        }
        h = i - 1;
        k = j + 1;
        for(; h >= 0 && k < 6; ){
            if(isNextCaseSame(listOfJButton, h,k,  listOfJButton.get(i).get(j))) {

                counter++;
                winnerButton.add(listOfJButton.get(h).get(k));
            }
            h--;
            k++;

        }

        if(counter >= 4){

            winnerButton.add(listOfJButton.get(i).get(j));
            for(JButton button : winnerButton){
                button.setBackground(Color.decode("#01E777"));
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isNextCaseSame(ArrayList<ArrayList<JButton>> listOfListJButton, int i, int j, JButton jButton){
        if(j > 7 || i > 6){
            return false;
        }
        return listOfListJButton.get(i).get(j).getText().equals(jButton.getText());
    }

    private JPanel createBoard(){

        String[] rowLetter = { "A", "B", "C", "D","E","F", "G"};

      /*  for(int i = 6; i > 0; i--){
            for(int j = 0; j < rowLetter.length;j++){
                add(new JButton(  rowLetter[j] + i) ).setName("Button" + rowLetter[j] + i);
            }
        } */

        for(; i < rowLetter.length; i++) {
            listOfJButton.add(new ArrayList<>());
        }
        // An array list per letter, each array is a column
        for( i = 0; i < listOfJButton.size(); i++){
            for(int j = 6; j > 0;j--){

                JButton button = new JButton();
                button.setBackground(new Color(175,213,130));

                button.setName("Button" + rowLetter[(i)] + (j ));
                button.setText(" ");

                listOfJButton.get(i).add(button);
                if(!gameFinish) {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(!gameFinish){
                            for (i = 0; i < listOfJButton.size(); i++) {
                                if (listOfJButton.get(i).contains(button)) {
                                    for (int j = listOfJButton.get(i).size() - 1; j >= 0; j--) {
                                        if (listOfJButton.get(i).get(j).getText().equals(" ")) {
                                            if (playerTurn) {
                                                listOfJButton.get(i).get(j).setText("O");
                                                playerTurn = false;
                                            } else {
                                                listOfJButton.get(i).get(j).setText("X");
                                                playerTurn = true;
                                            }
                                            if(checkCollumn(listOfJButton,i , j)
                                                    || checkLine(listOfJButton,i , j)
                                                    || majorDiagonal(listOfJButton,i,j)
                                                    || minorDiagonal(listOfJButton, i , j)){
                                                gameFinish = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        }
                    });
                }
            }
        }
        // Add the button to the grid
        JPanel gridButtonPanel = new JPanel(new GridLayout(6,7,1,1));
        for(i = 0; i<6; i++){
            for(int j = 0; j < 7; j++){
                gridButtonPanel.add(listOfJButton.get(j).get(i));
            }
        }
        return gridButtonPanel;

    }

    private void resetBoard(){
        for(int i = 0; i < listOfJButton.size();i++){
            for(int j = 0; j < listOfJButton.get(i).size();j++){

                listOfJButton.get(i).get(j).setBackground(new Color(175,213,130));
              //  listOfJButton.get(i).get(j).setText(i + " ;" + j);
                listOfJButton.get(i).get(j).setText(" ");
                gameFinish = false;

            }
        }
        playerTurn = false;
    }

}