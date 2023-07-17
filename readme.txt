This is a game of Tic-Tac-Toe written in Java, using JFrame for the GUI and MVP design pattern for architecture. Project just for practice and as preparation for chess game. 

#Board
This is the Model. It holds the state of the game in an 1D array of Cells (enum: CROSS,NOUGHT,EMPTY). It keeps track of status of each player. Has methods for making a move, for checking if game is over and holds 2 more enums (GameStatus:RUNNING,CROSS_WINS,NOUGHT_WINS,TIE and Player:CROSS,NOUGHT).

#SimpleTicTacToeGame
This is the controller. It holds a model and a view, as well as an Opponent. It can make a move for each player, it checks if the game is over and if so handles that. 

#GUI
Holds a controller. Extends JFrame, sets up the buttons(representing the cells of the board) and updates itself when needed. It listens for actions, and lets the controller handle those.  


