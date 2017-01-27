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
    private Integer idTheme;
    private String libelleTheme;
    private Integer fk_idUser;
    
	public Integer getIdTheme() {
		return idTheme;
	}
	
	public void setIdTheme(final Integer pId) {
		this.idTheme = pId;
	}
	
	public String getLibelleTheme() {
		return libelleTheme;
	}
	
	public void setLibelleTheme(final String pLibelle) {
		this.libelleTheme = pLibelle;
	}

	public Integer getFk_idUser() {
		return fk_idUser;
	}

	public void setFk_idUser(Integer fk_idUser) {
		this.fk_idUser = fk_idUser;
	}
}
