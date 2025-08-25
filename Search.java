import java.util.List;
public class Search{
    private List<Report> reports;
    private long comparisons ;
    
    public Search(List<Report> reports) {
		this.reports = reports;
        this.comparisons=0;
	}
    public long getcomparisons(){
        return this.comparisons;
    }

    public Report pesquisaSequencial(int index, String key){
        this.comparisons=0;
        for(int i=0;i<this.reports.size();i++){
            this.comparisons++;
            if(this.reports.get(i).getValue(index).equals(key)){
                return this.reports.get(i);
            }
        }
        return null;
    }
}