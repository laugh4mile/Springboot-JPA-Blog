package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 나중에 하면서 저절로 알게될테니 설명은 생략한다.
@Entity // 어노테이션 중 Entity는 제일 밑에 있는것이 좋다.
public class Reply {
	
	@Id  // 기본 키가 됨
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne // 하나의 게시글에 여러개의 답변이 있을 수 있다는 의미.
	@JoinColumn(name = "boardId")
	private Board board;
	
	@ManyToOne // 하나의 유저가 여러개의 답변을 쓸 수 있으므로..
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp // 현재 시간을 자동으로 저장해주는 어노테이션
	private Timestamp createDate;
}
