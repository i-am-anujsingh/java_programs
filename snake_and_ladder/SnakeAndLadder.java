import java.util.*;
import java.io.*;

//---start of player class---
class Player{
String name, status;	//status to save win or lose
char sym;	// sym to save the symbol of the player to be displayed on board
int position;
int sixCnt;
boolean plyStatus;	//plyStatus for whether the player it allowed to move or not

//---player class constructor---
Player(String n, char s){
this.name = n;
this.sym = s;
this.status = "LOSS";
this.position = -1;
this.plyStatus = false;
this.sixCnt = 0;
}

} //end player class


//---start of snake class---
class Snake{
int bite, tail;

//---snake class cunstructor---
Snake(int b, int t){
this.bite = b;
this.tail = t;
}

int getBite(){return this.bite;}

int getTail(){return this.tail;}

} //end of snake class


//---start of ladder class---
class Ladder{
int bottom, top;

//---ladder class cunstructor---
Ladder(int b, int t){
this.bottom = b;
this.top = t;
}

int getLadder(){return this.bottom;}

int getTop(){return this.top;}

} //end of ladder class



//---MAIN SnakeAndLadder CLASS---
class SnakeAndLadder{
    
public static void main(String args[]){

System.out.println("\n------------| Snake & Ladder |------------\n");

Scanner sc = new Scanner(System.in);

//---Taking input for number of players---
int plyCnt = 6;
while((plyCnt>5 || plyCnt<2) && plyCnt!=0){
System.out.print("Enter number of players (1-5 or 0 for exit): ");
plyCnt = sc.nextInt();
sc.nextLine(); //---removing the leftover newline char---
}

//---exiting if 0 is entered---
if(plyCnt==0){
System.out.println("Game exited!");
return;
}

//---creating players---
Player ar[] = new Player[plyCnt]; //---player array to track all players---

for(int i=0; i<plyCnt; i++){
System.out.print("Enter name of the player: ");
String name = sc.nextLine();

System.out.print("Enter a unique symbol for the player: ");
char symbol = sc.next().charAt(0);

sc.nextLine(); //---removing the leftover newline char---

ar[i] = new Player(name, symbol);
System.out.println("Player "+(i+1)+" is created successfully!\n");
}

//---displaying players one time details---
System.out.println("\n-------|PLAYER DETAILS ARE|-------");
for(int i=0; i<plyCnt; i++){
System.out.println("Player "+(i+1)+" deatails:-");
System.out.println(ar[i].name);
System.out.println(ar[i].sym);
}



//---creating snakes---
Snake[] snakes = new Snake[]{
new Snake(32,10),
new Snake(36,6),
new Snake(48,26),
new Snake(62,18),
new Snake(88,24)};

//---creating ladders---
Ladder[] ladders = new Ladder[]{
new Ladder(3,22),
new Ladder(17,93),
new Ladder(32,51),
new Ladder(39,80),
new Ladder(75,96)};

//---creating board to display player movement---
int[][] board = {
    {100, 99, 98, 97, 96, 95, 94, 93, 92, 91},
    {81, 82, 83, 84, 85, 86, 87, 88, 89, 90},
    {80, 79, 78, 77, 76, 75, 74, 73, 72, 71},
    {61, 62, 63, 64, 65, 66, 67, 68, 69, 70},
    {60, 59, 58, 57, 56, 55, 54, 53, 52, 51},
    {41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
    {40, 39, 38, 37, 36, 35, 34, 33, 32, 31},
    {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
    {20, 19, 18, 17, 16, 15, 14, 13, 12, 11},
    {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}};


//---from here i will start a loop that will run untill one of the players wins the game---
int p = 0;
Random rand  = new Random();
while(true){


System.out.print(ar[p].name+" press any alphabet or number key + enter to roll the dice!");
sc.next();

//---generating random dice numbers---
int dice = (rand.nextInt(6) + 1);
System.out.println("You Got "+dice); 

//---player opening logic---
if(dice==6 && (!ar[p].plyStatus)){
ar[p].plyStatus = true;
ar[p].position = 1;
System.out.println("Congrats "+ar[p].name+" you are open to play!!");
}

//---if player is open to play then only update the position---
if(ar[p].plyStatus){

if((100-ar[p].position)>=dice){
ar[p].position += dice;

//---this will only work if the player can move---
if(dice==6){
ar[p].sixCnt++;
}
else{
ar[p].sixCnt=0;
}

} //---end of position assigning---

//---moving player back to 18 positions back if it gets a triple 6---
if(ar[p].sixCnt==3){
System.out.println("You got triple 6, You are being moved 18 positions back!");
ar[p].sixCnt=0;
ar[p].position-=18;
dice=0;
}

//---checking for snake bites and ladders---
for(int i=0; i<5; i++){
if(ar[p].position==snakes[i].getBite()){
System.out.println("You got bitten by a snake! at "+ar[p].position);
System.out.println("You have been send to position "+snakes[i].getTail());
ar[p].position = snakes[i].getTail();
}
if(ar[p].position==ladders[i].getLadder()){
System.out.println("You got a ladder! at "+ar[p].position);
System.out.println("You have been send to position "+ladders[i].getTop());
ar[p].position = ladders[i].getTop();
}
}//---end of loop---


}//---end of if player is open to play---

//---printing of the board---
System.out.println();
for(int i=0; i<10; i++){
for(int j=0; j<10; j++){
if(ar[p].position==board[i][j]){
System.out.print(ar[p].sym+" ");
}
else{
System.out.print(board[i][j]+" ");
}
if(board[i][j]<100){
System.out.print(" ");
}
if(board[i][j]<10){
System.out.print(" ");
}
}
System.out.println();
}//---end of the board printing loop---

//---checking whether the current player won the game or not---
if(ar[p].position==100){
ar[p].status = "WON!";
System.out.println("\n\n"+ar[p].name+" WON THE GAME!!\n");
break;
}

//---updating player's turn logic---

if(dice==6){
System.out.println("You got 6, you will get another turn to roll the dice!");
p--;
}

if(p == (plyCnt-1)){
p=0;
}
else{
p++;
}

}//---end of the while loop that is checking player's status---

}
}