package com.plate.hy.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public static Article create(String title, String content) {
        return new Article(title, content);
    }

    private Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
