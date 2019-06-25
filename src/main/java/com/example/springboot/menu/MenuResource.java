package com.example.springboot.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class MenuResource {

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("router")
	private String router;

	@JsonProperty("name")
	private String name;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("id")
	private String id;

	@JsonProperty("child")
	private List<ChildItem> child;

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setRouter(String router){
		this.router = router;
	}

	public String getRouter(){
		return router;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setChild(List<ChildItem> child){
		this.child = child;
	}

	public List<ChildItem> getChild(){
		return child;
	}

	@Override
 	public String toString(){
		return 
			"MenuResponse{" + 
			"symbol = '" + symbol + '\'' + 
			",router = '" + router + '\'' + 
			",name = '" + name + '\'' + 
			",icon = '" + icon + '\'' + 
			",id = '" + id + '\'' + 
			",child = '" + child + '\'' + 
			"}";
		}
}
