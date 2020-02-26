package com.doodle.doodle.poll;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "doodle", name = "polls")
public class Poll {
	
	@Id
	@GeneratedValue
	private Long id;

	
	@Column(columnDefinition = "text")
    private String poll;

	public Poll() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPoll() {
		return poll;
	}

	public void setPoll(String poll) {
		this.poll = poll;
	}

}
