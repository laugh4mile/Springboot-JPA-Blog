package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리를 쓸거임 // <html>태그가 섞여서 디자인 됨 -> 겁나 길어짐
	
	@ColumnDefault("0")
	private int count; // 조회수
	
	@ManyToOne // Board가 Many User는 One // 한명의 유저는 여러개의 게시글을 쓸 수 있다는 거임.
	@JoinColumn(name = "userId") // 실제로 db에 만들어질 때는 userId로 만들어 질거임
	private User userId; // DataBase는 오브젝트를 저장할 수 없다. 그래서 FK를 사용하지. 하지만 자바는 오브젝트 저장이 가능함. 
	
	@CreationTimestamp // 데이터가 insert 혹은 update 될때 자동으로 값이 들어가는 꿀 어노테이션임!
	private Timestamp createDate;
}
