import java.io.*;
import java.util.*;
import java.util.List;

public class MathGame {
    // I saw 20 minutes before submitting that you are not meant to have this all in the main, rookie mistake won't happen again.
    public static void main(String[] args) {
        //welcoming message
        System.out.println("              **Math Quiz Game**                     ");
        System.out.println("Division questions rounded up to 1 decimal place!");
        System.out.println("=======================================================");

        //Variable Declaration
        Random random = new Random();
        Scanner keyboard = new Scanner(System.in);
        int digit1;
        int digit2;
        int f_digit1 = 0;
        int f_digit2 = 0;
        int score = 0;
        int maxloop = 10;
        int trigger = 0;
        double correctAnswer = 0;
        double userAnswer;
        double repeatAnswer;
        String sub_question;
        String add_question;
        String mul_question;
        String div_question;
        String joke_question;
        String response = "";
        String failed_question = "";
        String n = "";


        for (int counter = 1; counter <= maxloop; counter++) { // loops through 10 times not including any repeat questions. Can increase the maxloop for longer rounds, would change the highscores
            digit1 = (random.nextInt(101) + 1);
            digit2 = (random.nextInt(51) + 1);
            sub_question = counter + ". How much is: " + digit1 + " - " + digit2 + " = ";
            add_question = counter + ". How much is: " + digit1 + " + " + digit2 + " = ";
            mul_question = counter + ". How much is: " + digit1 + " X " + digit2 + " = ";
            div_question = counter + ". How much is: " + digit1 + " / " + digit2 + " = ";
            joke_question = counter + ". What year is it ?";

            List<String> givenList = Arrays.asList(joke_question, sub_question, add_question, mul_question, div_question, sub_question, add_question, mul_question, div_question, sub_question, add_question, mul_question, div_question);
            double d = (double) digit1 / digit2;
            //Repeat questions and responses. One extra chance for full marks, does not skip the next question.
            if (n.equals("a")) {
                if (trigger == 1) {
                    correctAnswer = f_digit1 - f_digit2;
                }

                if (trigger == 2) {
                    correctAnswer = f_digit1 + f_digit2;
                }

                if (trigger == 3) {
                    correctAnswer = f_digit1 * f_digit2;
                }

                if (trigger == 4) {
                    d = (double) f_digit1 / f_digit2;
                    correctAnswer = (double) (Math.round(d * 10.0) / 10.0);
                }
                if (trigger == 5) {
                    correctAnswer = 2022;
                }
                System.out.println(failed_question);
                repeatAnswer = keyboard.nextDouble();

                if (repeatAnswer == correctAnswer) {
                    switch (random.nextInt(2) + 1) {
                        case 1 -> response = "     >>Nice comeback";
                        case 2 -> response = "     >>2nd time is the charm";


                    }
                    score++;
                    System.out.println(response);
                    failed_question = ""; // clears failed_question variable, not really needed
                    n = ""; // if repeat answer is correct, exits if statement and continues with the next question.

                } else {
                    response = switch (random.nextInt(2) + 1) {
                        case 1 -> "     >>Better luck next time";
                        case 2 -> "     >>Wrong again";
                        default -> response;
                    };
                    System.out.println(response);
                    n = "";
                }
            }

            Random rand = new Random();
            String randomElement = givenList.get(rand.nextInt(givenList.size()));
            System.out.println(randomElement);
            userAnswer = keyboard.nextDouble();

            //New question answers and response
            if (Objects.equals(randomElement, sub_question)) {
                correctAnswer = digit1 - digit2; // x - y
            }

            if (Objects.equals(randomElement, add_question)) {
                correctAnswer = digit1 + digit2; // x + y
            }

            if (Objects.equals(randomElement, mul_question)) {
                correctAnswer = digit1 * digit2; // x * y
            }

            if (Objects.equals(randomElement, div_question)) {
                d = (double) digit1 / digit2; // x / y rounded up to 1 decimal place E.G., 1.78 = 1.8
                correctAnswer = (double) (Math.round(d * 10.0) / 10.0);
            }
            if (Objects.equals(randomElement, joke_question)) {
                correctAnswer = 2022; // joke answer
            }


            if (userAnswer == correctAnswer) {
                response = switch (random.nextInt(4) + 1) {
                    case 1 -> "     >>Perfect";
                    case 2 -> "     >>Correct";
                    case 3 -> "     >>Nicely Done";
                    case 4 -> "     >>Good Job";
                    default -> response;
                };

                score++;

            } else {
                response = switch (random.nextInt(2) + 1) {
                    case 1 -> "     >>Incorrect";
                    case 2 -> "     >>Wrong";
                    default -> response;
                };

                failed_question = randomElement;
                n = "a"; // to trigger if statement for repeat question.
                f_digit1 = digit1; // alternate variables for the previous questions randomized digits.
                f_digit2 = digit2;
                if (Objects.equals(randomElement, sub_question))
                    trigger = 1; // Triggers for repeat question.
                if (Objects.equals(randomElement, add_question))
                    trigger = 2;
                if (Objects.equals(randomElement, mul_question))
                    trigger = 3;
                if (Objects.equals(randomElement, div_question))
                    trigger = 4;
                if (Objects.equals(randomElement, joke_question))
                    trigger = 5;
            }


            System.out.println(response);


        }//end of for loop
        System.out.println("=========================");
        System.out.print("Your Score is: " + score + "/" + maxloop + ".  ");

        //highscores
        int highscore = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("highscoredata.txt"));
            String line = reader.readLine();
            while (line != null)              // read the score file line by line
            {
                try {
                    int h_score = Integer.parseInt(line.trim()); //parse each line as an int
                    if (h_score > highscore)  // and keep track of the largest
                    {
                        highscore = h_score;
                    }
                } catch (NumberFormatException e1) {
                    // ignore invalid scores
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            System.err.println("ERROR reading score from file");
        }
        if (score > highscore) {
            System.out.println("You now have the new high score!  The Previous high score was" + highscore);
        } else if (score == highscore) {
            System.out.println("You tied the high score!");
        } else {
            System.out.println("The all time high score was " + highscore);
            System.out.println("Better luck next time ! ");
        }

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("highscoredata.txt", true));
            output.newLine();
            output.append("" + score);
            output.close();
        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n");
        }







}//end of main
}//end of class

