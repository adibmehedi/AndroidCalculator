package adib.mehedi.simplecalculator;


public class CalcHistory {
    private int id;
    private String history;

    public CalcHistory(){

    }

    public CalcHistory(String history){
        this.history = history;
    }

    public void set_id(int id){
        this.id = id;
    }

    public void set_history(String history){
        this.history = history;
    }

    public int get_id(){
        return id;
    }

    public String get_history(){
        return history;
    }
}