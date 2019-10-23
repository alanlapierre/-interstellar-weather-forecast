package com.alanlapierre.solarsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.alanlapierre.solarsystem.util.DirectionName;


@Entity
@Table(name = "directions")
public class Direction extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dir_id")
	private long id;
	
	@NotNull
	@Column(name = "dir_name", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private DirectionName name;
	
	
	public Direction() {
		super();
	}

	public Direction(@NotNull DirectionName name) {
		super();
		this.name = name;
	}

	public Direction(long id, @NotNull DirectionName name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DirectionName getName() {
		return name;
	}

	public void setName(DirectionName name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Direction [id=" + id + ", name=" + name + "]";
	}

	
}
