package com.mysite.sbb.category;

import java.util.List;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.question.Question;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private List<Question> questionList;
}