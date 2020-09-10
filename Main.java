import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

enum Operation{
    Add,Sub,Mul,Div,None
}
public class Main extends Application {
    private final int BTN_WIDTH=120;
    private final int BTN_HEIGHT=60;
    private Label numLabel;
    private Operation op;
    private double num;

    public Main(){
        super();
        numLabel=new Label("0");
        numLabel.setPrefSize(BTN_WIDTH*4,50);
        numLabel.setAlignment(Pos.CENTER_RIGHT);
        numLabel.setPadding(new Insets(10));
        numLabel.setFont(new Font("Arial",15));
        num=0.0;
        op=Operation.None;
    }

    @Override
    public void start(Stage primaryStage) {
        Button cBtn = new Button("C");
        cBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        cBtn.setOnAction(x->{
            numLabel.setText("0");
            op=Operation.None;
        });

        Button sqrBtn = new Button("x^2");
        sqrBtn.setOnAction(x->{
            numLabel.setText(String.valueOf(Math.pow(Double.parseDouble(numLabel.getText()),2)));
        });
        sqrBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);


        Button sqrtBtn = new Button("sqrt");
        sqrtBtn.setOnAction(x->{
            if(!numLabel.getText().contains("-")){
                numLabel.setText(String.valueOf(Math.sqrt(Double.parseDouble(numLabel.getText()))));
            }else{
                new Alert(Alert.AlertType.ERROR,"Math Error").showAndWait();
            }
        });
        sqrtBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);


        Button divBtn = new Button("/");
        divBtn.setOnAction(x->{
            this.num=Double.parseDouble(numLabel.getText());
            this.op=Operation.Div;
            numLabel.setText("0");
        });
        divBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);


        Button mulBtn = new Button("x");
        mulBtn.setOnAction(x->{
            this.num=Double.parseDouble(numLabel.getText());
            this.op=Operation.Mul;
            numLabel.setText("0");
        });
        mulBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);


        Button subBtn = new Button("-");
        subBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        subBtn.setOnAction(x->{
            this.num=Double.parseDouble(numLabel.getText());
            this.op=Operation.Sub;
            numLabel.setText("0");
        });


        Button addBtn = new Button("+");
        addBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        addBtn.setOnAction(x->{
            this.num=Double.parseDouble(numLabel.getText());
            this.op=Operation.Add;
            numLabel.setText("0");
        });


        Button eqBtn = new Button("=");
        eqBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        eqBtn.setOnAction(x->{
            if(this.op!=Operation.None){
                double d=Double.parseDouble(numLabel.getText());
                double ans=0.0;
                boolean err=false;
                switch(this.op){
                    case Add:
                        ans=this.num+d;break;
                    case Sub:
                        ans=this.num-d;break;
                    case Mul:
                        ans=this.num*d;break;
                    case Div:
                        if(d==0){
                            new Alert(Alert.AlertType.ERROR,"Math Error").showAndWait();
                            err=true;
                        }else{
                            ans=this.num/d;break;
                        }
                }
                if(!err)
                    numLabel.setText(String.valueOf(ans));
            }
            this.num=0;
            this.op=Operation.None;
        });
        Button dotBtn = new Button(".");
        dotBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        dotBtn.setOnAction(x->{
            String s=numLabel.getText();
            if(!s.contains(".")){
                numLabel.setText(s+".");
            }
        });


        Button plusMinBtn = new Button("+/-");
        plusMinBtn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        plusMinBtn.setOnAction(x->{
            String s=numLabel.getText();
            if(s.startsWith("-")){
                s=s.substring(1);
            }else{
                s="-"+s;
            }
            numLabel.setText(s);
        });


        Button[] nBtn=createNumberButtons();
        VBox vb=new VBox();

        HBox l1=new HBox();
        l1.getChildren().addAll(cBtn,sqrBtn,sqrtBtn,divBtn);
        HBox l2=new HBox();
        l2.getChildren().addAll(nBtn[7],nBtn[8],nBtn[9],mulBtn);
        HBox l3=new HBox();
        l3.getChildren().addAll(nBtn[4],nBtn[5],nBtn[6],subBtn);
        HBox l4=new HBox();
        l4.getChildren().addAll(nBtn[1],nBtn[2],nBtn[3],addBtn);
        HBox l5=new HBox();
        l5.getChildren().addAll(plusMinBtn,nBtn[0],dotBtn,eqBtn);

        vb.getChildren().addAll(numLabel,l1,l2,l3,l4,l5);


        StackPane root = new StackPane();
        root.getChildren().add(vb);

        Scene scene = new Scene(root, BTN_WIDTH*4, BTN_HEIGHT*5+50);

        primaryStage.setTitle("181380057-Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button[] createNumberButtons(){
        Button[] btn=new Button[10];
        for(int i=0;i<10;++i){
            btn[i]=new Button(String.valueOf(i));
            btn[i].setPrefSize(BTN_WIDTH, BTN_HEIGHT);
            btn[i].setOnAction(e->{
                String enteredNum=((Button)e.getSource()).getText();
                if(numLabel.getText().equals("0")){
                    numLabel.setText(enteredNum);
                }else{
                    numLabel.setText(numLabel.getText()+enteredNum);
                }

            });
        }
        return btn;
    }
    public static void main(String[] args) {
        launch(args);
    }

}