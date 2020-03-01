package it.poker.PokerOnline.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import it.poker.PokerOnline.model.enm.CodiceRuolo;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descrizione;
	@Enumerated(EnumType.STRING)
	private CodiceRuolo codice;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ruoli")
	private List<User> users = new ArrayList<>();

	public Role() {
	}

	public Role(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public CodiceRuolo getCodice() {
		return codice;
	}

	public void setCodice(CodiceRuolo codice) {
		this.codice = codice;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public static List<Role> buildRoleListFromIdRoleArray(String[] idRoleArray) {
		List<Role> result = new ArrayList<>();
		if (idRoleArray != null) {
			for (String id : idRoleArray) {
				try {
					result.add(new Role(Long.parseLong(id)));
				} catch (Exception e) {
				}
			}
		}

		return result;

	}

}
