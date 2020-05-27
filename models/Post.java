package models;

import java.util.*;
import java.text.*;

import helper.DateEngine;

public class Post {
	
	Date date;
	String tournament, type, fullName;
	int all;
	double earn;
	
	
	public Post( String tournamentL, String typeL, String fullNameL, String allL  )
	{	
		
		int allInteger = getIntDefultValue( allL);
		
		
		tournament = getDefaultValueOfSting( tournamentL );
		type = capitalize( getDefaultValueOfSting( typeL ) );
		fullName = getDefaultValueOfSting( fullNameL );
		all = allInteger;
		earn = (0.6) * allInteger;
	}
	
	public Post( String[] textData )
	{
		this(textData[0], textData[1], textData[2], textData[3]);
	}
	
	
	protected String getDefaultValueOfSting( String str )
	{
		if( str.isEmpty() )
		{
			return str = "Test";
		}
		
		return str;
	}
	
	protected int getIntDefultValue( String str )
	{
		if( str.isEmpty() )
		{
			str = "50";
		}
		
		return Integer.parseInt( str);
	}
	
	public void setDate(int year, int month, int day) 
	{
		date = DateEngine.create(year, month, day);
	}
	
	public void setDate( int[]intData )
	{
		setDate(intData[0], intData[1], intData[2]);
	}
	
	public void setDate( String dateStr )
	{
		date = DateEngine.create(dateStr);
	}
	
	
	public String toString()
	{
		String result = "";
		String nl = System.lineSeparator();
		
		result += "Название турнира: " + tournament + nl;
		result += "Дата проведения: " + date + nl;
		result += "Вид спорта: " + type + nl;
		result += "ФИО: " + fullName + nl;
		result += "Размер призовых: " + all + nl;
		result += "Заработал: " + earn + nl;
		return result;
	}
	
	public String getPublicDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");  
		String strDate = dateFormat.format(date);  
		return strDate;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public String getTournamentName()
	{
		return tournament;
	}
	
	public String getSportType()
	{
		return type;
	}
	
	public String getFullName()
	{
		return fullName;
	}
	
	public int getIntAllCost() 
	{
		return all;
	}
	
	public double getDoubleEarn() {
		return earn;
	}
	
	public String getStringEarn()
	{
		return String.valueOf(earn); 
	}
	
	public String getStingAllCost() {
		return String.valueOf(all);
	}
	
	public String[] getArrayStringPublicData()
	{
		String[] result = new String[] { getTournamentName(), getPublicDate(), 
										getSportType(), getFullName(), getStingAllCost(), 
										getStringEarn() };
		return result;
		
	}
	
	public static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public boolean isThisEvent( String event )
	{
		return tournament.equals(event);
	}
	
	public boolean isThisSport( String sportName )
	{
		return type.equals(sportName);
	}
	
	public boolean isThisName( String name )
	{
		return fullName.contains(name);
	}
	
	public boolean isAllCost( int allCost )
	{
		return this.all == allCost;
	}
	
	public boolean isThisDate( Date date )
	{
		return DateEngine.compare(date, this.date);
	}
	
	public boolean equals( Post two ) {
		
		return this.isThisEvent(two.getTournamentName()) && this.isThisDate( two.getDate() ) 
				&& this.isThisName( two.getFullName() ) && this.isThisSport( two.getSportType() )
				&& this.isAllCost( two.getIntAllCost() );
	}
}
