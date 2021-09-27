package com.cos.blog.model;

import java.sql.Timestamp; // 이거 조심!

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(혹은 다른 언어) Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 테이블이 생성된다.
// @DynamicInsert // insert시에 null 인 필드를 자동으로 제외한다!
public class User {
	
	@Id  // 기본 키가 됨
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 100) // 해쉬 (비밀번호 암호화) 때문에 길게해야함
	private String password;
	
	@Column(nullable = false, length = 50) 
	private String email;
	
//	@ColumnDefault("'user'") // 따옴표 꼭 써야함. // 이런 애들 같은 경우는 Enum을 사용해서 더 효율적으로 바꿀 수 있다.
	// DB는 RoleType이 없다. 따라서 어노테이션을 붙인다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. // admin, user, manager
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}
