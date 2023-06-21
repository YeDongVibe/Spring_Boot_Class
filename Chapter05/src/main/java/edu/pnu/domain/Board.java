package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
@Entity //class를 entity로 처리하기 위한 어노테이션
public class Board {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //id(seq)를 자동으로 증가하는 값으로 설정해주는 어노테이션 (=auto increment:sql에서)
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
}
