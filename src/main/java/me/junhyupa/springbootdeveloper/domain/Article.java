package me.junhyupa.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity //엔티티로 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id // id 필드로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 1씩 자동증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) // title이라는 null불가 칼럼과 매핑
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // 엔티티가 생성될 때 생성 시간을 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 마지막으로 저장될때 시간을 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    //빌터패턴은 객체를 직관적이고 유연적이게 생성가능
    /*
    non-builder-pattern
    new Article("ABC","def"); << 무엇이 title이고 content인지 구분 어려움

    builder-pattern
    Article.builder()
        .title("ABC")
        .content("def")
        .build();
    직관적이고 유연함
     */
    @Builder // 빌더패턴으로 객체 생성자 정의. 롬복에서 지원하는 애너테이션
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
    /*롬복의 @Getter 와 @NoArgsConstructor(access = AccessLevel.PROTECTED)
        애너테이션이 없으면 직접 정의해줘야하는 메소드들
    protected Article() {} //기본생성자 접근 제어

    public Long getId() {return id;}

    public String getTitle { return title; }

    public String getContent { return content; }
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
