package org.itstep.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "LESSONS" )
public class Lesson {

	private static final Long DURATION = 1000 * 60 * 90L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@Column( name = "LESSON_ID")
	@JsonProperty
	private Integer id;
	
	@ManyToOne( targetEntity = Subject.class )
	private Subject subject;
	
	@ManyToOne( targetEntity = Teacher.class )
	private Teacher teacher;
	
	@ManyToOne( targetEntity = Group.class )
	private Group group;
	
	@Column( name = "CABINET")
	@JsonProperty
	private String cabinet;
	
	@Column( name = "START_TIME")
	@JsonProperty
	private Long startTime;
	
	@Column( name = "END_TIME")
	@JsonProperty
	private Long endTime;
	
}
