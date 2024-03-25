package snoopy.controller;

import java.util.Timer;
import java.util.TimerTask;

import snoopy.model.Board;

public class JeuDeBaseController {
    private Board board;
    private Timer timer;

    public JeuDeBaseController() {
        this.board = new Board();
    }

    public void startGame() {
        startTimer();
    }

    private void startTimer() {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            int timeLeft = 60;

            @Override
            public void run() {
                if (timeLeft % 2 == 0) {
                    board.moveBall();
                }
                // Update other game components
                timeLeft--;
                if (timeLeft <= 0) {
                    timer.cancel();
                }
            }
        }, 0, 1000); // Run every second
    }

    // Other methods

    public static void main(String[] args) {
        JeuDeBaseController controller = new JeuDeBaseController();
        controller.startGame();
    }
}
