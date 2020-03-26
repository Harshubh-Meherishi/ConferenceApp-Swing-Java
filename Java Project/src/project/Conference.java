package project;

public class Conference {
	String name,location,type,date,proceedings,year,month;
	int number;
	Conference(String name,String location,String year,String month,String date,String type,String proceedings,int number)
	{
		this.name=name;
		this.location=location;
		this.year=year;
		this.month=month;
		this.date=date;
		this.type=type;
		this.proceedings=proceedings;
		this.number=number;
		
	}
	public String toString() {
		
		return "NAME: "+name+"\nLOCATION: "+location+"\nYEAR: "+year+"\nMONTH: "+month+"\nDATE: "+date+"\nTYPE: "+type+"\nPROCEEDINGS: "+"\nNUMBER OF PAPERS PUBLISHED: "+number;
	}

}