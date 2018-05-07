/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

/**
 *
 * @author 502094807
 */
public class TicTacToe extends JFrame
{
    static private int playerOneScore = 0;
    static private int playerTwoScore = 0;
    private JPanel scoreBoard;
    private JPanel playField;
        
    private JButton [][] button;
    
    private JTextField playerOne;
    private JTextField playerTwo;
    
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    
    private String PlayerOneIcon = "X";
    private String PlayerTwoIcon = "O";
    
    private int cpuSelect = -1;
    
    private int turn = 0;
    private boolean whoWon;
    
    private String [][]board = {{"*","*","*"},{"*","*","*"},{"*","*","*"}};
        
    /**
     * @param args the command line arguments
     */
    public TicTacToe()
    {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        
        setPreferredSize(new Dimension(300, 425));
        
        buildPlayField();
        buildScoreBoard();
        
        add(scoreBoard, BorderLayout.SOUTH);
        add(playField, BorderLayout.CENTER);
        pack();
        setVisible(true);
        
    }
    
    private void buildPlayField()
    {
    playField = new JPanel();
    button = new JButton[3][3];
    
    playField.setLayout(new GridLayout(3,3));
    
    playField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     
    for(int x = 0; x <3; x++)
    {
        for (int y = 0; y < 3;y++)
        {
        button[x][y] = new JButton("*");
        button[x][y].setFont(new Font("Arial",Font.BOLD, 40));
        button[x][y].setForeground(Color.BLACK);
        button[x][y].setBackground(Color.LIGHT_GRAY);
        button[x][y].addActionListener(new playFieldListener());  
        playField.add(button[x][y]);
        }
    }    
     
    
    pack();
    }
    
    private void buildScoreBoard(){
        scoreBoard = new JPanel();
        
        playerOneLabel = new JLabel("PlayerOne Wins: ");
        playerTwoLabel = new JLabel("PlayerTwo Wins: ");
        
        playerOne = new JTextField();
        playerOne.setEditable(false);
        playerTwo = new JTextField();
        playerTwo.setEditable(false);
        
        scoreBoard.setLayout(new GridLayout(2,2));
        scoreBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        scoreBoard.add(playerOneLabel);
        scoreBoard.add(playerOne);
        scoreBoard.add(playerTwoLabel);
        scoreBoard.add(playerTwo);
       playerOne.setText("0");
       playerTwo.setText("0");
    }
    
    private class playFieldListener implements ActionListener
    {
            public void actionPerformed(ActionEvent e)
            {
                for(int x = 0; x < 3;x++)
                {
                    for(int y = 0; y < 3;y++)
                    {
                    if(button[x][y] == e.getSource()){
                        
                        if(turn % 2 == 0){ 
                            
                            button[x][y].setText(PlayerOneIcon);
                            button[x][y].setFont(new Font("Arial",Font.BOLD, 40));
                            button[x][y].setEnabled(false);
                            button[x][y].setBackground(Color.RED);
                            board[x][y] = PlayerOneIcon;
                            turn++;
                        }
                        
                        
                        else{
                            button[x][y].setText(PlayerTwoIcon);
                            button[x][y].setBackground(Color.BLUE);
                            button[x][y].setFont(new Font("Arial",Font.BOLD, 40));
                            button[x][y].setEnabled(false);
                            board[x][y] = PlayerTwoIcon;
                            turn++;
                        }
                     
                    
                        
                        if (checkWin(board,PlayerOneIcon) == true){
                            
                            int reply = JOptionPane.showConfirmDialog(null,"You Won!\nPlay again?", "Game Result",JOptionPane.YES_NO_OPTION);
                            playerOneScore++;
                            playerOne.setText(String.valueOf(playerOneScore));
                            if(reply == JOptionPane.YES_OPTION){
                                resetBoard();
                            }
                        
                        else
                        {
                            System.exit(0);
                        }
                                    
                    }
                        
                    else if(checkWin(board,PlayerTwoIcon) == true)
                    {
                            
                        int reply = JOptionPane.showConfirmDialog(null,"You Loss\nPlay again?!", "Game Result",JOptionPane.YES_NO_OPTION);
                        playerTwoScore++;
                        playerTwo.setText(String.valueOf(playerTwoScore));
                        if(reply == JOptionPane.YES_OPTION)
                        {
                            resetBoard();
                        }
                            
                        else
                        {
                            System.exit(0);
                        }
                            
                    }

                }
            }

            }
            
                
            if(turn == 9)
            {
                
                int reply = JOptionPane.showConfirmDialog(null,"It's A Draw!\nPlay again?", "Game Result",JOptionPane.YES_NO_OPTION);
                        
                if(reply == JOptionPane.YES_OPTION)
                {
                    resetBoard();
                }
                            
                            
                else
                {       
                    System.exit(0);
                }
                        
            }

        }
    }
    
    public void resetBoard()
    {
            
            for(int x = 0; x< 3; x++)
            {
                for (int y = 0; y < 3; y++)
                {
                board[x][y] = "*";
                button[x][y].setText("*");
                button[x][y].setForeground(Color.BLACK);
                button[x][y].setBackground(Color.LIGHT_GRAY);
                button[x][y].setEnabled(true);
                }

            }
        turn = 0;
    }
    public int cpuMove()
    {
        Random rand = new Random();
        int move = rand.nextInt(9);
        return move;
    }
    
    public boolean checkWin(String[][] board, String icon)
    {
	boolean endGame = false;

        
	if(board[0][0] == icon && board[0][1] == icon && board[0][2] == icon)
	endGame = true;
	if(board[1][0] == icon && board[1][1]== icon && board[1][2] == icon)
	endGame = true;
	if(board[2][0] == icon && board[2][1]== icon && board[2][2] == icon)
	endGame = true;


	//checks for column wins
	if(board[0][0] == icon && board[1][0] == icon && board[2][0] == icon)
	endGame = true;
	if(board[0][1] == icon && board[1][1] == icon && board[2][1] == icon)
	endGame = true;
	if(board[0][2] == icon && board[1][2] == icon && board[2][2] == icon)
	endGame = true;

	//checks for diagnoal wins
	if(board[0][0] == icon && board[1][1] == icon && board[2][2] == icon)
	endGame = true;
	if(board[0][2] == icon && board[1][1] == icon && board[2][0] == icon)
	endGame = true;

        
        

	//Checks for  row wins
	/*
        if(board[0] == icon && board[1] == icon && board[2] == icon)
	endGame = true;
	if(board[3] == icon && board[4]== icon && board[5] == icon)
	endGame = true;
	if(board[6] == icon && board[7]== icon && board[8] == icon)
	endGame = true;


	//checks for column wins
	if(board[0] == icon && board[3] == icon && board[6] == icon)
	endGame = true;
	if(board[1] == icon && board[4] == icon && board[7] == icon)
	endGame = true;
	if(board[2] == icon && board[5] == icon && board[8] == icon)
	endGame = true;

	//checks for diagnoal wins
	if(board[0] == icon && board[4] == icon && board[8] == icon)
	endGame = true;
	if(board[2] == icon && board[4] == icon && board[6] == icon)
	endGame = true;
        */
	return endGame;
    }
    
    
    public static void main(String[] args) 
    {
      new TicTacToe();
    }
    
}
