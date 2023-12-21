package com.example.demo.vo;
//JDBC API - > MyBatis(ORM매핑오픈소스-if문지원,동적쿼리를지원 - sql문사용) 
//-> Hibernate(ORM프레임워크) - sql문없음 -근데 조회가된다
//JPA - 마지막목표 - 좋다 나쁘다 문제아님 -장단점이 분명하다
//튜닝이안됨,복잡한계산식은 SQL문 사용이 유리
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//왜 롬복을 사용하지?
// getter/setter메소드를 추가하지않아도 괜찮아 
//전변을 private로 선언하였다. - 캡슐레이션 - 고유한 정보들을 외부에서 직접수정하는걸 막아준다. 

@Data // @Setter,@Getter 합쳐놓은것
@NoArgsConstructor
public class BoardVO {

	private int b_no = 0;
	private String b_title = null;
	private String b_writer = null;
	private String b_content = null;
	private int b_hit = 0;
	private String b_date = null;
	private String b_file = null;

	//Lombok에서 제공하는 @Builder를 사용하면 파라미터 개수나 타입을 일일이 맞추지않고 자유롭게 사용가능함
	// 사용이랑 생성자의 파라미터값을통한 전역변수의 초기화작업 
	@Builder
	public BoardVO(int b_no,String b_title,String b_writer,String b_content,int b_hit,String b_date,String b_file) {
		super();
		//디폴트생성자호출 -왜냐면 파라미터를 갖는생성자가 하나라도있으면 디폴트생정자를 제공하지않음
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_writer = b_writer;
		this.b_content = b_content;
		this.b_hit = b_hit;
		this.b_date = b_date;
		this.b_file = b_file;
		
	}
	
}
