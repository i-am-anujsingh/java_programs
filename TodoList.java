import java.util.*;
import java.awt.*;
import java.awt.event.*;

class Todo{
private String title;
private String detail;
Todo(String t, String d){
this.title = t;
this.detail = d;
}
public String getTitle(){
return this.title;
}
public String getDetail(){
return this.detail;
}
public void setTitle(String t){
this.title = t;
}
public void setDetail(String d){
this.detail = d;
}
}


public class TodoList{
static ArrayList<Todo> todos = new ArrayList<Todo>();
static int x=0, y=0;
static Frame f;
static TextField title;
static TextArea detail;
static Button create, delete, update, reset;
static Label t, d;

static void init(){
f = new Frame();
f.setTitle("Todo List");
f.setLayout(null);
f.setSize(700,700);
f.setVisible(true);

display();

f.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent we){
System.exit(0);
}
});
}

static void display(){

t = new Label("Enter Title");
d = new Label("Enter Detail");
f.add(t);
f.add(d);
t.setBounds(20, 60, 100, 30);
d.setBounds(20, 110, 100, 30);

title = new TextField();
detail = new TextArea(5,20);
f.add(title);
f.add(detail);
title.setBounds(140, 60, 150, 30);
detail.setBounds(140, 110, 300, 200);

create = new Button("Create Todo");
f.add(create);
create.setBounds(140, 350, 80, 30);
create.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
createTodo();
}
});

reset = new Button("Reset");
f.add(reset);
reset.setBounds(250, 350, 80, 30);
reset.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
f.removeAll();
f.repaint();
display();
createTodoBtns(todos);
}
});

}


static void createTodo(){

String ttl = title.getText();
String dtl = detail.getText();
todos.add(new Todo(ttl, dtl));
createTodoBtns(todos);
title.setText("");
detail.setText("");
}


static void createTodoBtns(ArrayList<Todo> td){
f.removeAll();
f.repaint();
display();
y=0;
for(Todo todo : td){

final Todo currentTodo = todo;
final int i = td.indexOf(currentTodo);

Button b = new Button(currentTodo.getTitle());
f.add(b);
b.setBounds(500,60+y, 100,30);

b.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
//This will remove all the components and repaint the frame to show only the selected todo's details and update/delete buttons
f.removeAll();
f.repaint();
display();
createTodoBtns(todos);

title.setText(currentTodo.getTitle());
detail.setText(currentTodo.getDetail());

update = new Button("Update Todo");
f.add(update);
update.setBounds(140, 400, 80, 30);
update.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
updateTodo(i);
}
});

delete = new Button("Delete Todo");
f.add(delete);
delete.setBounds(250, 400, 80, 30);
delete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
deleteTodo(i);
}
});

}
});

y+=50;

}
}


static void updateTodo(int i){
todos.set(i, new Todo(title.getText(), detail.getText()));
f.removeAll();
f.repaint();
display();
createTodoBtns(todos);
}

static void deleteTodo(int i){
todos.remove(i);
f.removeAll();
f.repaint();
display();
createTodoBtns(todos);
}


public static void main(String[] args){
init();
}
}
