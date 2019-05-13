/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).
|S| | |
| | |F|

snake.move("R"); -> Returns 0
| |S| |
| | |F|

snake.move("D"); -> Returns 0
| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
| |F| |
| |S|S|

snake.move("U"); -> Returns 1
| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)
| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

idea:
https://www.cnblogs.com/grandyang/p/5558033.html
use double end queue to represent snake
use 总的序数 x * width + y 来表示蛇的位置
use foodIdx 来表明吃了多少了
*/

import java.util.*;

class SnakeGame {
	public static void main(String[] args) {
		int width = 3;
		int height = 2;
		int[][] food = new int[][] {{1,2}, {0,1}};

		SnakeGame snake = new SnakeGame(width, height, food);

		int ret = snake.move("R");
		System.out.println(ret);

		ret = snake.move("D");
		System.out.println(ret);

		ret = snake.move("R");
		System.out.println(ret);

		ret = snake.move("U");
		System.out.println(ret);

		ret = snake.move("L");
		System.out.println(ret);

		ret = snake.move("U");
		System.out.println(ret);
	}
	// Sat May 11 21:34:50 2019
	int width;
    int height;
    int[][] food;
    
    int foodIdx;
    boolean over;
    Deque<Integer> snake;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        
        this.foodIdx = 0;
        this.over = false;
        this.snake = new LinkedList<>();
        
        // note, not forget add 0 posotion
        this.snake.add(0);
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if (over) {
            return -1;
        }

        int head = snake.getFirst();
        int headX = head / width;
        int headY = head % width;
        // clearly understand four directions
        switch (direction) {
            case "U":
                headX--;
                break;
            case "L":
                headY--;
                break;
            case "R":
                headY++;
                break;
            case "D":
                headX++;
                break;
        }
        // to simulate snake moving
        // move one step, first tail shrink
        int tail = snake.removeLast();
        
        // if snake can make a move
        int newHead = headX * width + headY;

        // if collide with the boundary, cannot move, game over
        // run into snake itself
        if (headX < 0 || headX >= height || headY < 0 || headY >= width || snake.contains(newHead)) {
            over = true;
            
            return -1;
        }

        // then head increases,
        snake.addFirst(newHead);
        
        // if eat the food, how to eat? it means newHead is equal to food position
        if (foodIdx < food.length) {
            int[] foodPos = food[foodIdx];
            // if eat, 又长回去了
            if (headX == foodPos[0] && headY == foodPos[1]) {
                snake.addLast(tail);
                foodIdx++;
            }
        }
        // first position is default, so have to minus -1
        return snake.size() - 1;
    }




	int width = 0;
	int height = 0;
	int[][] food;
	Deque<Integer> snake;

	int foodIdx = 0;
	boolean gameOver = false;

	public SnakeGame(int width, int height, int[][] food) {
	    this.width = width;
	    this.height = height;
	    this.food = food;
	    this.snake = new LinkedList<Integer>();
	    this.snake.addLast(0);
	}

	/**
	 * Moves the snake.
	 * @param  direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
	 * @return The game's score after the move. Return -1 if game over.
	 * Game over when snake crosses the screen boundary or bites its body.
	 */
	public int move(String direction) {
	    if (gameOver) {
	    	return -1;
	    }

	    int head = snake.getFirst();
	    int headX = head / width;
	    int headY = head % width;

	    int tail = snake.removeLast();

	    switch (direction) {
	        case "U":
	        	headX--;
	        	break;
	        case "L":
	        	headY--;
	        	break;
	        case "R":
	        	headY++;
	        	break;
	        case "D":
	        	headX++;
	        	break;
	    }

	    int snakePos = getPosition(headX, headY);
	    if (headX < 0 || headX >= height || headY < 0 || headY >= width || snake.contains(snakePos)) {
	    	gameOver = true;
	    	return -1;
	    }
	    // whatsoever, head will move to a new position
	    snake.addFirst(snakePos);
	    // if eat the food, should put tail back, so snake length get increased by 1
	    if (foodIdx < food.length && headX == food[foodIdx][0] && headY == food[foodIdx][1]) {
	        foodIdx++;
	        snake.addLast(tail);
	    }

	    return snake.size() - 1; // except snake head, others are all obtained by eating food which are score
	}

	public int getPosition(int x, int y) {
	    return x * width + y;
	}
}