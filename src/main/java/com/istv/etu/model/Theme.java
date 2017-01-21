package com.istv.etu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="THEME")
public class Theme {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDTHEME")
    private Integer id;
    private String libelle;
    
	public Integer getId() {
		return id;
	}
	
	public void setId(final Integer pId) {
		this.id = pId;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(final String pLibelle) {
		this.libelle = pLibelle;
	}

	
}
