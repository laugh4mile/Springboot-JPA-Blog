package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 쓴다는 의미임.
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터를 쓸때 씀
	private String content; // 우리는 섬머노트 라이브러리를 쓸거임 // <html>태그가 섞여서 디자인 됨 -> 겁나 길어짐
	
	@ColumnDefault("0") // User 테이블을 만들때는 "''" 로 했지만 count는 int 값이기 때문에 ''는 제외한다
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // Board가 Many User는 One // 한명의 유저는 여러개의 게시글을 쓸 수 있다는 거임.
	// ManyToOne는 기본 fetch 전략이 EAGER 이다 : board 테이블을 select 하면 user 정보는 가져올게! (왜냐? user 정보는 board 당 한 건 밖에 없으니까!)
	@JoinColumn(name = "userId") // 실제로 db에 만들어질 때는 userId로 만들어 질거임
	private User userId; // DataBase는 오브젝트를 저장할 수 없다. 그래서 FK를 사용하지. 하지만 자바는 오브젝트 저장이 가능함. 
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 하나의 게시글은 여러개의 답글이 있을 수 있다
	// OneToMany는 기본 fetch 전략이 LAZY이다
	// board를 select할 때 join을 통해서 값을 얻기 위할 뿐이다. 
	// mappedBy : 연관관계의 주인이 아니다. (난 FK가 아니야!) 따라서 DB에 컬럼을 만들지 마라
	// 여기서 "board" 는 Reply클래스의 필드 이름. 
	// @JoinColumn(name = "replyId") // <- 이게 필요없다.  FK 가 필요없으므로.
	private List<Reply> reply;
	
	@CreationTimestamp // 데이터가 insert 혹은 update 될때 자동으로 값이 들어가는 꿀 어노테이션임!
	private Timestamp createDate;
}
